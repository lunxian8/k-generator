package com.lunxian.common.baseDb.service;

import com.lunxian.common.baseDb.entity.GenBeanEntity;
import com.lunxian.common.utils.NameUtil;
import com.lunxian.common.utils.TableHandlerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * MySql数据库的实现类
 *
 * @author JueYue
 * @date 2014年12月21日
 */
public class ReadTableForMysqlImpl extends BaseReadTable implements IReadSerivice {

    private static String TABLE_SQL = "SELECT TABLE_NAME,TABLE_COMMENT FROM information_schema.TABLES WHERE TABLE_NAME = '%s' AND TABLE_SCHEMA = '%s'";

    private static String ALL_TABLE_SQL = "SELECT TABLE_NAME,TABLE_COMMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = '%s'";

    private static String FIELDS_SQL = "SELECT column_name as fieldName, data_type as fieldType, column_comment, numeric_precision, numeric_scale as scale, character_maximum_length as charmaxLength,is_nullable as nullable from information_schema.columns where table_name = '%s' and table_schema = '%s'";

    private static String SCHEMA_SQL = "SELECT SCHEMA_NAME FROM information_schema.SCHEMATA WHERE SCHEMA_NAME != 'information_schema' AND SCHEMA_NAME !=  'mysql'";


    private static final Logger LOGGER = LoggerFactory.getLogger(ReadTableForMysqlImpl.class);

    @Override
    public GenBeanEntity read(String dbName, String tableName) throws Exception {
        try {
            GenBeanEntity entity = getTableEntiy(dbName, tableName, TABLE_SQL);
            entity.setName(NameUtil.getEntityHumpName(entity.getTableName()));
            entity.setFields(getTableFields(dbName, tableName, FIELDS_SQL));
            TableHandlerUtil.handlerFields(entity.getFields());
            return entity;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new Exception("获取表格数据发生异常");
        }
    }

    @Override
    public List<String> getAllDB() throws Exception {
        try {
            return getAllDB(SCHEMA_SQL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new Exception("获取表格数据发生异常");
        }
    }

    @Override
    public List<GenBeanEntity> getAllTable(String dbName) throws Exception {
        try {
            return getAllTableEntiy(dbName, ALL_TABLE_SQL);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new Exception("获取表格数据发生异常");
        }
    }

    @Override
    public List<Map<String, Object>> getTableByDb(Map<String, Object> map) throws Exception {
        try {
            return getTableByDbs(map);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new Exception("获取表格数据发生异常");
        }
    }

    /**
     * 查询总Table条数
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public int queryTotal(Map<String, Object> map) throws Exception {
            return queryTotals(map);
    }

    /**
     * 查询表信息
     * @param tableName
     * @return
     */
    @Override
    public Map<String, String> queryTable(String tableName) throws Exception {
        return queryTables(tableName);
    }
    /**
     * 查询列信息
     * @param tableName
     * @return
     */
    @Override
    public List<Map<String, String>> queryColumns(String tableName) throws Exception {
        return queryColumnss(tableName);
    }


    @Override
    protected String handlerTableComment(String comment) {
        if (comment.contains(";")) {
            return comment.split(";")[0];
        }
        if (comment.startsWith("InnoDB free")) {
            return null;
        }
        return comment;
    }

}
