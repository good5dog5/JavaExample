package com.shark.example.parserSql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SqlConditionSetEntity {
    private List<SqlConditionEntity> conditionList;
    private String conjunction;
}
