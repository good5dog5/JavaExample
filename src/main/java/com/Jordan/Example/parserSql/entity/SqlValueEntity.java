package com.Jordan.Example.parserSql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SqlValueEntity {
    private String table;
    private String column;
    private String operation;
    private String alias;
}
