package org.poseibon.rac.infrastructure.common;

import com.google.common.collect.ImmutableList;
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
import org.poseibon.common.utils.Collections2;

import java.util.Collection;
import java.util.List;

/**
 * sql解析器
 *
 * @author qingchuan
 * @date 2020/12/26
 */
public class SqlParser {

    public static void main(String[] args) throws JSQLParserException {
        String sql = "select * from (select * from tb_biz_entity) a ";
        Statement statement = null;
        statement = CCJSqlParserUtil.parse(sql);
        // 非查询方法，直接报错
        Select select = (Select) statement;
        // 获取对应的selectBody
        SelectBody selectBody = select.getSelectBody();
        // 判断是否是join查询
        // 需要区分是否需要添加扩展表 ，判断需求，查询字段，where字段，order by 字段 group by 字段
        PlainSelect plainSelect = (PlainSelect) selectBody;
        // 分几种情况
        // 1、单表
        FromItem fromItem = plainSelect.getFromItem();
        if (fromItem instanceof Table && Collections2.isEmpty(plainSelect.getJoins())) {
            Table table = (Table) fromItem;
            Expression expression = plainSelect.getWhere();
            Parenthesis parenthesis = new Parenthesis(expression);
            ExpressionList itemsList = new ExpressionList();
            itemsList.setExpressions(ImmutableList.of(new LongValue(1)));
            InExpression inExpression = new InExpression(new Column(table, "biz_line_id"), itemsList);
            AndExpression andExpression = new AndExpression(parenthesis, inExpression);
            plainSelect.setWhere(andExpression);
            System.out.println(plainSelect.toString());
            return;
        }
        // 多表join
        if (fromItem instanceof SubSelect && !Collections2.isEmpty(plainSelect.getJoins())) {
            Table table = (Table) fromItem;
            Expression expression = plainSelect.getWhere();
            Parenthesis parenthesis = new Parenthesis(expression);
            ExpressionList itemsList = new ExpressionList();
            itemsList.setExpressions(ImmutableList.of(new LongValue(1)));
            InExpression inExpression = new InExpression(new Column(table, "biz_line_id"), itemsList);
            AndExpression andExpression = new AndExpression(parenthesis, inExpression);
            plainSelect.setWhere(andExpression);
            System.out.println(plainSelect.toString());
            return;
        }

        // 子查询
        if (fromItem instanceof SubSelect && Collections2.isEmpty(plainSelect.getJoins())) {
            Table table = (Table) fromItem;
            Expression expression = plainSelect.getWhere();
            Parenthesis parenthesis = new Parenthesis(expression);
            ExpressionList itemsList = new ExpressionList();
            itemsList.setExpressions(ImmutableList.of(new LongValue(1)));
            InExpression inExpression = new InExpression(new Column(table, "biz_line_id"), itemsList);
            AndExpression andExpression = new AndExpression(parenthesis, inExpression);
            plainSelect.setWhere(andExpression);
            System.out.println(plainSelect.toString());
            return;
        }
        if (fromItem instanceof SubJoin && Collections2.isEmpty(plainSelect.getJoins())) {
            Table table = (Table) fromItem;
            Expression expression = plainSelect.getWhere();
            Parenthesis parenthesis = new Parenthesis(expression);
            ExpressionList itemsList = new ExpressionList();
            itemsList.setExpressions(ImmutableList.of(new LongValue(1)));
            InExpression inExpression = new InExpression(new Column(table, "biz_line_id"), itemsList);
            AndExpression andExpression = new AndExpression(parenthesis, inExpression);
            plainSelect.setWhere(andExpression);
            System.out.println(plainSelect.toString());
            return;
        }
        if (fromItem instanceof && Collections2.isEmpty(plainSelect.getJoins())) {
            Table table = (Table) fromItem;
            Expression expression = plainSelect.getWhere();
            Parenthesis parenthesis = new Parenthesis(expression);
            ExpressionList itemsList = new ExpressionList();
            itemsList.setExpressions(ImmutableList.of(new LongValue(1)));
            InExpression inExpression = new InExpression(new Column(table, "biz_line_id"), itemsList);
            AndExpression andExpression = new AndExpression(parenthesis, inExpression);
            plainSelect.setWhere(andExpression);
            System.out.println(plainSelect.toString());
            return;
        }

        // 5、union
    }

    public void appendAuth(PlainSelect plainSelect, Table table, String fieldName, Collection<Object> authList) {
        Expression expression = plainSelect.getWhere();
        Expression inExpression = new InExpression(new Column(table, fieldName),
                new ExpressionList(getInExpressionList(authList)));
        if (expression != null) {
            Parenthesis parenthesis = new Parenthesis(expression);
            AndExpression andExpression = new AndExpression(parenthesis, inExpression);
            plainSelect.setWhere(andExpression);
        } else {
            plainSelect.setWhere(inExpression);
        }
    }

    public List<Expression> getInExpressionList(Collection<Object> authList) {
        List<Expression> expressions = Lists.newArrayList();
        for (Object obj : authList) {
            if(obj instanceof Integer || obj.getClass().isPrimitive()){

            }
        }
        return expressions;
    }
}
