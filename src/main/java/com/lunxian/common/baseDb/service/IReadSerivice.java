package com.lunxian.common.baseDb.service;

import com.lunxian.common.baseDb.entity.GenBeanEntity;

import java.util.List;
import java.util.Map;

/**
 * 读取数据库表,获取数据库表的属性
 *
 * @author JueYue
 * @date 2014年12月21日
 */
public interface IReadSerivice {
    /**
     * 读取数据库表格属性
     */
    public GenBeanEntity read(String dbName, String tableName) throws Exception;

    /**
     * 查询所有的库
     */
    public List<String> getAllDB() throws Exception;

    /**
     * 查询所有的表
     */
    public List<GenBeanEntity> getAllTable(String dbName) throws Exception;


    /**
     * 获取Table列表
     * @param map
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getTableByDb(Map<String, Object> map) throws Exception;

    /**
     * 查询总Table条数
     * @param map
     * @return
     * @throws Exception
     */
    public int queryTotal(Map<String, Object> map) throws Exception;


    /**
     * 查询表信息
     * @param tableName
     * @return
     */
    public Map<String, String> queryTable(String tableName) throws Exception;


    /**
     * 查询列信息
     * @param tableName
     * @return
     */
    public List<Map<String, String>> queryColumns(String tableName) throws Exception;

}
