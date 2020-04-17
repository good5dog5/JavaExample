package com.shark.example.parserSql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SqlConditionEntity {
    private String table;
    private String column;
    private String operation;
    private String value1;
    private String value2;
    private String conjunction;
}
