package com.Jordan.Example.parserSql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SqlDataEntity {
    private String table;
    private SqlQueryEntity query;
}
