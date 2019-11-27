package com.shark.example.parserSql.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SqlQueryEntity {
    private List<SqlValueEntity> valueList;
    private SqlDataEntity data;
    private List<SqlConditionSetEntity> conditionSetList;
    private List<String> groupByList;
    private List<SqlOrderEntity> orderList;
    private int limit;
    private int offset;
}
