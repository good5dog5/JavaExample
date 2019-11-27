package com.shark.example.parserSql;

import com.shark.example.parserSql.entity.*;
import com.shark.example.parserSql.util.SqlUtil;

import java.util.Arrays;

public class ParserSqlExample {

    public static void main(String argv[]) {
        SqlQueryEntity query = SqlQueryEntity.builder()
                .valueList(Arrays.asList(
                        new SqlValueEntity("user", "name", null, null),
                        new SqlValueEntity("user", "name", "count", "name_count")))
                .data(new SqlDataEntity("user", null))
                .conditionSetList(Arrays.asList(new SqlConditionSetEntity(Arrays.asList(new SqlConditionEntity("user", "age", "between", "12", "24", null)), null)))
                .groupByList(Arrays.asList("name"))
                .orderList(Arrays.asList(new SqlOrderEntity(new SqlValueEntity(null, "name_count", null, null), SqlOrderType.ASC)))
                .offset(20)
                .limit(20)
                .build();

        String sql = SqlUtil.parse(query);
        System.out.println("sql1: " + sql);
    }
}
