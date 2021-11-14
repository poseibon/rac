package org.poseibon.rac.infrastructure.common;

import com.google.common.collect.Lists;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.poseibon.common.utils.Collections2;

import java.util.Collection;
import java.util.List;

/**
 * sql解析器
 *
 * @author qingchuan
 * @date 2020/12/26
 */
public class AuthSqlSupporter {

    /**
     * 实例
     */
    public static final AuthSqlSupporter instance = new AuthSqlSupporter();

    /**
     * 实例方法
     *
     * @return 实例
     */
    public static AuthSqlSupporter getInstance() {
        return instance;
    }

    /**
     * 生成带权限的sql
     *
     * @param originalSql 原始SQL
     * @param tblName     需要加权限过滤的表
     * @param fieldName   权限字段名
     * @param authList    权限集合
     * @param <T>         类型
     * @return 带权限的sql
     */
    public <T> String newSqlWithAuth(String originalSql, String tblName, String fieldName, Collection<T> authList) {
        Statement statement = null;
        try {
            statement = CCJSqlParserUtil.parse(originalSql);
        } catch (JSQLParserException e) {
            throw new RuntimeException(e);
        }
        // 非查询方法，直接报错
        Select select = (Select) statement;
        // 获取对应的selectBody
        SelectBody selectBody = select.getSelectBody();
        PlainSelect plainSelect = (PlainSelect) selectBody;
        // 分几种情况
        // 1、单表
        FromItem fromItem = plainSelect.getFromItem();
        // @TODO 此处只能处理单表，和多个简单表的join语句，尚不支持复杂子查询sql解析
        if (fromItem instanceof Table && Collections2.isEmpty(plainSelect.getJoins())) {
            plainSelect.setWhere(newExpressionWithAuth((Table) fromItem, plainSelect.getWhere(), fieldName, authList));
        } else if (fromItem instanceof Table && CollectionUtils.isNotEmpty(plainSelect.getJoins())) {
            Table table = getTable((Table) fromItem, plainSelect.getJoins(), tblName, originalSql);
            plainSelect.setWhere(newExpressionWithAuth(table, plainSelect.getWhere(), fieldName, authList));
        }
        return plainSelect.toString();
    }

    /**
     * 获取Join时的表名
     *
     * @param firstTbl   sql中第一个表
     * @param joinTables join的表
     * @return 匹配相关名称表
     * @TODO 此方法对于Join中是子查询等多种情况没有处理，只支持Join单表
     */
    private Table getTable(Table firstTbl, List<Join> joinTables, String tblName, String originSql) {
        if ((firstTbl != null && firstTbl.getName().equals(tblName)) || StringUtils.isEmpty(tblName)) {
            return firstTbl;
        }
        for (Join join : joinTables) {
            FromItem item = join.getRightItem();
            if (!(item instanceof Table)) {
                continue;
            }
            Table table = (Table) item;
            if (table.getName().equals(tblName)) {
                return table;
            }
        }
        throw new RuntimeException(String.format("can't match table \"%s\" from sql : %s", tblName, originSql));
    }

    /**
     * 带权限的表达式
     *
     * @param table     表
     * @param where     where条件
     * @param fieldName 字段名称
     * @param authList  授权列表
     * @param <T>       类型
     * @return 新的带权限的where条件
     */
    private <T> Expression newExpressionWithAuth(Table table, Expression where,
                                                 String fieldName, Collection<T> authList) {
        Expression inExpression = new InExpression(new Column(table, fieldName),
                new ExpressionList(getExpressionList(authList)));
        if (where == null) {
            return inExpression;
        }
        Parenthesis parenthesis = new Parenthesis(where);
        return new AndExpression(parenthesis, inExpression);
    }

    /**
     * 生成in表达式
     *
     * @param authList 授权列表
     * @param <T>      类型
     * @return 表达式值
     */
    private <T> List<Expression> getExpressionList(Collection<T> authList) {
        List<Expression> expressions = Lists.newArrayList();
        for (T obj : authList) {
            expressions.add(new LongValue(obj.toString()));
        }
        return expressions;
    }
}
