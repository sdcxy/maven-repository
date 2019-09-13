package com.github.sdcxy.modules.common.enums;



/**
 * @className Constants
 * @description TODO
 * @author lxx
 * @version 1.0.1
 * @date 2019/9/12 9:23
 **/
public enum Constants {

    /**
     *  数据库配置文件常量
     */
    DEFAULT_DATABASE_XML_PATH("config/dataSource.xml"),
    DEFAULT_DATASOURCE_YAML_PATH("/dataSource.yml"),
    DEFAULT_APPLICATION_YAML_PATH("/application.yml"),

    /**
     *  数据库driver-class-name
     */
    MYSQL("com.mysql.jdbc.Driver"), // mysql 5.+ 版本
    MYSQL_PLUS("com.mysql.cj.jdbc.Driver"), // mysql 6.0 以上的版本
    SQLSERVER("com.microsoft.sqlserver.jdbc.SQLServerDriver")
    ;

    private String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
