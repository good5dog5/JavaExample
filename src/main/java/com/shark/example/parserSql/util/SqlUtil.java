package com.shark.example.parserSql.util;

import com.shark.example.parserSql.entity.*;
import util.StringUtil;

import java.util.List;

public class SqlUtil {

    public static String parse(SqlQueryEntity sqlQueryEntity) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select ");
        List<SqlValueEntity> valueList = sqlQueryEntity.getValueList();
        if(valueList != null && valueList.size() > 0) {
            int i = 0;
            for(SqlValueEntity valueEntity: sqlQueryEntity.getValueList()) {
                if(i != 0) {
                    stringBuilder.append(", ");
                }
                boolean hasOperation = !StringUtil.isEmpty(valueEntity.getOperation());
                if(hasOperation) {
                    stringBuilder.append(valueEntity.getOperation());
                    stringBuilder.append("(");
                }
                if(!StringUtil.isEmpty(valueEntity.getTable())) {
                    stringBuilder.append(valueEntity.getTable());
                    stringBuilder.append(".");
                }
                stringBuilder.append(valueEntity.getColumn());
                if(hasOperation) {
                    stringBuilder.append(")");
                }
                if(!StringUtil.isEmpty(valueEntity.getAlias())) {
                    stringBuilder.append(" as ");
                    stringBuilder.append(valueEntity.getAlias());
                }
                i ++;
            }
        } else {
            stringBuilder.append("*");
        }

        //from
        stringBuilder.append(" from ");
        if(sqlQueryEntity.getData().getQuery() != null) {
            stringBuilder.append("(");
            stringBuilder.append(parse(sqlQueryEntity.getData().getQuery()));
            stringBuilder.append(")");
        } else {
            stringBuilder.append(sqlQueryEntity.getData().getTable());
        }

        if(sqlQueryEntity.getConditionSetList() != null ||
                sqlQueryEntity.getConditionSetList().size() > 0) {
            stringBuilder.append(" where");
            for(SqlConditionSetEntity conditionSet: sqlQueryEntity.getConditionSetList()) {
                stringBuilder.append(" (");
                for(SqlConditionEntity condition: conditionSet.getConditionList()) {
                    if(!StringUtil.isEmpty(condition.getTable())) {
                        stringBuilder.append(condition.getTable());
                        stringBuilder.append(".");
                    }
                    stringBuilder.append(condition.getColumn());
                    if(condition.getOperation().equalsIgnoreCase("between")) {
                        stringBuilder.append(" between ");
                        stringBuilder.append(condition.getValue1());
                        stringBuilder.append(" and ");
                        stringBuilder.append(condition.getValue2());
                    } else {
                        stringBuilder.append(" = \'");
                        stringBuilder.append(condition.getValue1());
                        stringBuilder.append("\'");
                    }
                    if(!StringUtil.isEmpty(condition.getConjunction())) {
                        stringBuilder.append(" ");
                        stringBuilder.append(condition.getConjunction());
                    }
                }
                stringBuilder.append(")");
                if(!StringUtil.isEmpty(conditionSet.getConjunction())) {
                    stringBuilder.append(conditionSet.getConjunction());
                }
            }
        }

        //group
        if(sqlQueryEntity.getGroupByList() != null &&
                sqlQueryEntity.getGroupByList().size() > 0) {
            stringBuilder.append(" group by ");
            int i = 0;
            for(String column: sqlQueryEntity.getGroupByList()) {
                if(i != 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(column);
                i ++;
            }
        }

        if(sqlQueryEntity.getOrderList() != null &&
                sqlQueryEntity.getOrderList().size() > 0) {
            stringBuilder.append(" order by ");
            int i = 0;
            for(SqlOrderEntity orderEntity: sqlQueryEntity.getOrderList()) {
                if(i != 0) {
                    stringBuilder.append(", ");
                }
                if(!StringUtil.isEmpty(orderEntity.getValue().getTable())) {
                    stringBuilder.append(orderEntity.getValue().getTable());
                    stringBuilder.append(".");
                }
                stringBuilder.append(orderEntity.getValue().getColumn());
                stringBuilder.append(" ");
                stringBuilder.append(orderEntity.getType().toString());
                i ++;
            }
        }

        if(sqlQueryEntity.getLimit() > 0) {
            stringBuilder.append(" limit ");
            stringBuilder.append(sqlQueryEntity.getLimit());
        }

        if(sqlQueryEntity.getOffset() > 0) {
            stringBuilder.append(" offset ");
            stringBuilder.append(sqlQueryEntity.getOffset());
        }
        return stringBuilder.toString();
    }
}
