package com.lunxian.common.baseDb.service;


/**
 * 读取库的工厂
 *
 * @author JueYue
 * @date 2014年12月25日
 */
public class ReadTableFactory {

    public static IReadSerivice getReadTable(String dbType) throws Exception {
        if ("mysql".equalsIgnoreCase(dbType)) {
            return new ReadTableForMysqlImpl();
        }
        throw new Exception("数据库不支持");
    }

    public static String getDeiver(String dbType) throws Exception {
        if ("mysql".equalsIgnoreCase(dbType)) {
            return "com.mysql.jdbc.Driver";
        }
        throw new Exception("数据库不支持");
    }
}
