package com.hbtcsrzzx.test;

import java.util.Map;

public class ExSQLTest {
    public static void main(String[] args) {

        Map<String, String> map = BasicFileAttributesTest.proRead("db.properties");

        BasicFileAttributesTest.exportSql(map);
    }
}
