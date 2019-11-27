package com.shark.example.parserSql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SqlOrderEntity {
    private SqlValueEntity value;
    private SqlOrderType type;
}
