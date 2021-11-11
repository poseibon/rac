package org.poseibon.rac.infrastructure.common;


import java.util.List;
import java.util.stream.Collectors;

/**
 * sql查询实现
 * <p>
 * panbin@baidu.com
 * <p>
 * created on 2019/11/11.
 */
public class SelectBuilder {

//    private static final SelectBuilder INSTANCE = new SelectBuilder();
//
//    public static SelectBuilder getInstance() {
//        return INSTANCE;
//    }
//
//    public PrepareSql buildPrepare(QSelectBody select) {
//        ArgHolder.ARG_LIST.set(Lists.newArrayList());
//        ArgHolder.IS_PRE.set(true);
//        String sql = build(select);
//        List<Object> argList = Lists.newArrayList(ArgHolder.ARG_LIST.get());
//        ArgHolder.IS_PRE.remove();
//        ArgHolder.ARG_LIST.remove();
//        return PrepareSql.builder().sql(StringUtils.replace(sql, ArgHolder.PLACE_HOLDER_R, "?"))
//                .argList(argList).build();
//    }
//
//    public String build(QSelectBody select) {
//        if (select == null) {
//            return "";
//        }
//        Select s = new Select();
//
//        if (select instanceof QSelect) {
//            PlainSelect body = new PlainSelect();
//            processBody((QSelect) select, body);
//            s.setSelectBody(body);
//        } else {
//            QUnionSelect qUnionSelect = (QUnionSelect) select;
//            // 循环对应的子查询
//            SetOperationList setOperationList = new SetOperationList();
//            // 解析子表，同时解析union
//            List<SetOperation> ops = Lists.newArrayList();
//            List<SelectBody> selectList = Lists.newArrayList();
//            List<Boolean> brackets = Lists.newArrayList();
//            processSub(qUnionSelect, ops, selectList, brackets);
//            setOperationList.setBracketsOpsAndSelects(brackets, selectList, ops);
//            s.setSelectBody(setOperationList);
//        }
//        return s.toString();
//    }
//
//    private void processSub(QUnionSelect qUnionSelect, List<SetOperation> ops, List<SelectBody> selectList,
//                            List<Boolean> brackets) {
//        int i = 0;
//        for (QSelectBody qSelectBodyItem : qUnionSelect.getSelects()) {
//            PlainSelect body = new PlainSelect();
//            processBody((QSelect) qSelectBodyItem, body);
//            selectList.add(body);
//            brackets.add(false);
//            if (i > 0) {
//                UnionOp union = new UnionOp();
//                union.setAll(true);
//                ops.add(union);
//            }
//            i++;
//        }
//    }
//
//    private void processBody(QSelect select, PlainSelect body) {
//        processTable(select, body);
//        processJoin(select, body);
//        processDistinct(select, body);
//        processSelectColumn(select, body);
//        processWhereExpression(select, body);
//        processGroupBy(select, body);
//        processHavingExpression(select, body);
//        processOrderBy(select, body);
//        processLimit(select, body);
//    }
//
//    /**
//     * 处理关联表
//     */
//    private void processJoin(QSelect select, PlainSelect body) {
//        if (CollectionUtils.isNotEmpty(select.getJoins())) {
//            List<Join> joins = Lists.newArrayList();
//            for (QJoin join : select.getJoins()) {
//                Join j = new Join();
//                j.setLeft(join.isLeft());
//                j.setRight(join.isRight());
//                j.setFull(join.isFull());
//                j.setInner(join.isInner());
//                FromItem table = createTable(join.getJoinTable());
//                j.setRightItem(table);
//                j.setOnExpression(processExpression(join.getOnExpression()));
//                joins.add(j);
//            }
//            body.setJoins(joins);
//        }
//    }
//
//    /**
//     * 处理where表达式
//     */
//    private void processWhereExpression(QSelect select, PlainSelect body) {
//        if (select.getWhere() != null) {
//            body.setWhere(processExpression(select.getWhere()));
//        }
//    }
//
//    /**
//     * 处理having语句
//     */
//    private void processHavingExpression(QSelect select, PlainSelect body) {
//        if (select.getHaving() != null && select.getGroupByColumns() != null) {
//            body.setHaving(processExpression(select.getHaving()));
//        }
//    }
//
//    /**
//     * 處理表達式
//     */
//    private Expression processExpression(QExpression qExpression) {
//        if (qExpression == null) {
//            return null;
//        }
//        return ConverterButler.getInstance(qExpression.getClass().getName()).convert(qExpression);
//    }
//
//
//    /**
//     * 處理查詢表
//     */
//    private void processTable(QSelect select, PlainSelect body) {
//        FromItem fromTable = createTable(select.getFromTable());
//        body.setFromItem(fromTable);
//    }
//
//    private FromItem createTable(QFromItem fromItem) {
//        if (fromItem instanceof QTable) {
//            QTable table = (QTable) fromItem;
//            Table fromTable = new Table(table.getName());
//            if (StringUtils.isNotEmpty(table.getAlias())) {
//                fromTable.setAlias(new Alias(table.getAlias(), false));
//            }
//            if (StringUtils.isNotEmpty(table.getSchema())) {
//                fromTable.setSchemaName(table.getSchema());
//            }
//            return fromTable;
//        }
//        QSubSelect qSubSelect = (QSubSelect) fromItem;
//        SubSelect subSelect = new SubSelect();
//        if (qSubSelect.getSelect() != null) {
//            QSelectBody qSelectBody = qSubSelect.getSelect();
//            if (qSelectBody instanceof QSelect) {
//                PlainSelect body = new PlainSelect();
//                processBody((QSelect) qSelectBody, body);
//                subSelect.setSelectBody(body);
//            }
//            if (qSelectBody instanceof QUnionSelect) {
//                // 循环对应的子查询
//                SetOperationList setOperationList = new SetOperationList();
//                // 解析子表，同时解析union
//                List<SetOperation> ops = Lists.newArrayList();
//                List<SelectBody> selectList = Lists.newArrayList();
//                List<Boolean> brackets = Lists.newArrayList();
//                processSub(((QUnionSelect) qSelectBody), ops, selectList, brackets);
//                setOperationList.setBracketsOpsAndSelects(brackets, selectList, ops);
//                subSelect.setSelectBody(setOperationList);
//            }
//        }
//        if (qSubSelect.getAlias() != null) {
//            subSelect.setAlias(new Alias(qSubSelect.getAlias(), false));
//        }
//        return subSelect;
//    }
//
//
//    /**
//     * 處理查詢列
//     */
//    private void processSelectColumn(QSelect select, PlainSelect body) {
//        List<SelectItem> selectItems = Lists.newArrayList();
//        for (QExpression qColumn : select.getColumns()) {
//            SelectExpressionItem selectItem =
//                    new SelectExpressionItem(ConverterButler
//                            .getInstance(qColumn.getClass().getName()).convert(qColumn));
//            if (qColumn instanceof QAlias) {
//                QAlias alias = (QAlias) qColumn;
//                if (StringUtils.isNotEmpty(alias.getAlias())) {
//                    selectItem.setAlias(new Alias(alias.getAlias()));
//                }
//            }
//            selectItems.add(selectItem);
//        }
//        body.addSelectItems(selectItems.toArray(new SelectItem[selectItems.size()]));
//    }
//
//    /**
//     * 處理分頁
//     */
//    private void processLimit(QSelect select, PlainSelect body) {
//        if (select.getLimit() != null
//                && select.getLimit().getPageSize() != null) {
//            Limit limit = new Limit();
//            if (select.getLimit().getOffset() != null) {
//                limit.setOffset(ConverterButler.getInstance(QObjectValue.class.getName()).convert(select.getLimit().getOffset()));
//            }
//            limit.setRowCount(ConverterButler.getInstance(QObjectValue.class.getName()).convert(select.getLimit().getPageSize()));
//            body.setLimit(limit);
//        }
//    }
//
//    /**
//     * 處理分組
//     */
//    private void processGroupBy(QSelect select, PlainSelect body) {
//        if (CollectionUtils.isNotEmpty(select.getGroupByColumns())) {
//            for (QColumn qColumn : select.getGroupByColumns()) {
//                body.addGroupByColumnReference(ConverterButler
//                        .getInstance(QColumn.class.getName()).convert(qColumn));
//            }
//        }
//    }
//
//    /**
//     * 處理排序
//     */
//    private void processOrderBy(QSelect select, PlainSelect body) {
//        if (CollectionUtils.isNotEmpty(select.getOrderByColumns())) {
//            List<OrderByElement> orderByElements = Lists.newArrayList();
//            for (QOrderByExpression column : select.getOrderByColumns()) {
//                OrderByElement orderByElement = new OrderByElement();
//                orderByElement.setAsc(column.isAsc());
//                orderByElement.setExpression(ConverterButler.getInstance(QColumn.class.getName())
//                        .convert(column.getColumn()));
//                orderByElements.add(orderByElement);
//            }
//            body.setOrderByElements(orderByElements);
//        }
//    }
//
//    /**
//     * 處理聚合
//     */
//    private void processDistinct(QSelect select, PlainSelect body) {
//        if (select.isDistinct()) {
//            Distinct distinct = new Distinct(false);
//            body.setDistinct(distinct);
//        }
//    }
}
