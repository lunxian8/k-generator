package com.lunxian.common.baseDb.service;


import com.lunxian.common.baseDb.entity.GenBeanEntity;
import com.lunxian.common.baseDb.entity.GenFieldEntity;
import com.lunxian.common.utils.ConnectionUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读表基础类
 *
 * @author JueYue
 * @date 2014年12月25日
 */
public abstract class BaseReadTable {

     private static final Logger LOGGER = LoggerFactory.getLogger(BaseReadTable.class);

     protected List<GenFieldEntity> getTableFields(String dbName, String tableName, String sql) throws Exception {
          List<GenFieldEntity> list = new ArrayList<GenFieldEntity>();
          Statement statement = null;
          try {
               ResultSet rs = ConnectionUtil.createStatement()
                   .executeQuery(String.format(sql, tableName, dbName));
               GenFieldEntity field;
               while (rs.next()) {
                    field = new GenFieldEntity();
                    if (StringUtils.isNotEmpty(rs.getString("charmaxLength"))) {
                         field.setFieldLength(rs.getInt("charmaxLength"));
                    } else {
                         field.setFieldLength(rs.getInt("numeric_precision"));
                    }
                    field.setComment(rs.getString("column_comment"));
                    field.setFieldName(rs.getString("fieldName"));
                    field.setNotNull("Y".equalsIgnoreCase(rs.getString("nullable")) ? 2 : 1);
                    field.setFieldType(rs.getString("fieldType"));
                    //field.setPrecision(rs.getString("numeric_precision"));
                    field.setFieldPointLength(rs.getInt("scale"));
                    list.add(field);
               }
          } catch (Exception e) {
               LOGGER.error(e.getMessage(), e);
               throw new Exception("查询表是否存在发生异常", e);
          } finally {
               if (statement != null) {
                    statement.close();
                    statement = null;
               }
          }
          return list;
     }

     protected GenBeanEntity getTableEntiy(String dbName, String tableName, String sql) throws Exception {
          Statement statement = null;
          try {
               ResultSet rs = ConnectionUtil.createStatement().executeQuery(String.format(sql, tableName, dbName));
               String dbTableName = null;
               String comment = null;
               while (rs.next()) {
                    dbTableName = rs.getString(1);
                    comment = rs.getString(2);
               }
               if (StringUtils.isEmpty(dbTableName)) {
                    throw new Exception("表不存在");
               } else {
                    GenBeanEntity entity = new GenBeanEntity();
                    if (StringUtils.isNotEmpty(comment)) {
                         entity.setChinaName(handlerTableComment(comment));
                    }
                    entity.setTableName(dbTableName);
                    return entity;
               }
          } catch (Exception e) {
               LOGGER.error(e.getMessage(), e);
               throw new Exception("查询表是否存在发生异常", e);
          } finally {
               if (statement != null) {
                    statement.close();
                    statement = null;
               }
          }
     }

     public List<String> getAllDB(String sql) throws SQLException {
          Statement statement = null;
          List<String> list = new ArrayList<String>();
          try {
               ResultSet rs = ConnectionUtil.createStatement().executeQuery(String.format(sql));
               while (rs.next()) {
                    list.add(rs.getString(1));
               }
          } catch (Exception e) {
               LOGGER.error(e.getMessage(), e);
               try {
                    throw new Exception("查询表是否存在发生异常", e);
               } catch (Exception e1) {
                    e1.printStackTrace();
               }
          } finally {
               if (statement != null) {
                    statement.close();
                    statement = null;
               }
          }
          return list;
     }

     protected abstract String handlerTableComment(String comment);

     protected List<GenBeanEntity> getAllTableEntiy(String dbName, String sql) throws Exception {
          Statement statement = null;
          List<GenBeanEntity> list = new ArrayList<GenBeanEntity>();
          try {
               ResultSet rs = ConnectionUtil.createStatement().executeQuery(String.format(sql, dbName));
               String dbTableName = null;
               String comment = null;
               while (rs.next()) {
                    dbTableName = rs.getString(1);
                    comment = rs.getString(2);
                    if (StringUtils.isEmpty(dbTableName)) {
                         throw new Exception("表不存在");
                    } else {
                         GenBeanEntity entity = new GenBeanEntity();
                         if (StringUtils.isNotEmpty(comment)) {
                              entity.setChinaName(handlerTableComment(comment));
                         }
                         entity.setTableName(dbTableName);
                         list.add(entity);
                    }
               }
          } catch (Exception e) {
               LOGGER.error(e.getMessage(), e);
               throw new Exception("查询表是否存在发生异常", e);
          } finally {
               if (statement != null) {
                    statement.close();
                    statement = null;
               }
          }
          return list;
     }

     /**
      * 获取Table列表
      * @param map
      * @return
      * @throws Exception
      */
     protected List<Map<String, Object>> getTableByDbs(Map<String, Object> map) throws Exception {
          Statement statement = null;
          List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
          String tableName = (String) map.get("tableName");
          Integer limit = (Integer) map.get("limit");
          Integer offset = (Integer) map.get("offset");
          String sql = "select table_name tableName, " +
              "engine, table_comment tableComment, create_time createTime from " +
              "information_schema.tables " +
              "where table_schema = (select database()) ";
          if (tableName != null && tableName.trim() != "") {
               sql = sql.concat("and table_name like concat('%', '").concat(tableName).concat("', '%')");
          }
          sql = sql.concat("order by create_time desc ");
          if (offset != null && limit != null) {
               sql = sql.concat("limit ").concat(offset.toString()).concat(",").concat(limit.toString());
          }
          try {
               ResultSet rs = ConnectionUtil.createStatement().executeQuery(sql);
               while (rs.next()) {
                    Map<String, Object> mapTmp = new HashMap<>();
                    mapTmp.put("engine", rs.getString("engine"));//表类型
                    mapTmp.put("createTime", rs.getString("createTime"));//创建时间
                    mapTmp.put("tableComment", rs.getString("tableComment"));//表备注
                    mapTmp.put("tableName",rs.getString("tableName"));//表名
                    list.add(mapTmp);
               }
          } catch (Exception e) {
               LOGGER.error(e.getMessage(), e);
               throw new Exception("查询表是否存在发生异常", e);
          } finally {
               if (statement != null) {
                    statement.close();
                    statement = null;
               }
          }
          return list;
     }

     /**
      * 查询总Table条数
      * @param map
      * @return
      * @throws Exception
      */
     public int queryTotals(Map<String, Object> map) throws Exception {
          int total=0;
          Statement statement = null;
          String tableName = (String) map.get("tableName");
          String sql = "select count(*) from information_schema.tables where table_schema = (select database()) ";
          if (tableName != null && tableName.trim() != "") {
               sql = sql.concat("and table_name like concat('%', '").concat(tableName).concat("', '%')");
          }
          try {
               ResultSet rs = ConnectionUtil.createStatement().executeQuery(sql);
               while (rs.next()) {
                    total= rs.getInt(1);
               }
          } catch (Exception e) {
               LOGGER.error(e.getMessage(), e);
               throw new Exception("查询表是否存在发生异常", e);
          } finally {
               if (statement != null) {
                    statement.close();
                    statement = null;
               }
          }
          return total;
     }


     /**
      * 查询表信息
      * @param tableName
      * @return
      */
     public Map<String, String> queryTables(String tableName) throws Exception {
          Statement statement = null;
          Map<String, String> map= new HashMap<>();
          String sql = ("select table_name tableName, engine, table_comment tableComment," +
              " create_time createTime from information_schema.tables " +
              "where table_schema = (select database()) and table_name = ").concat("'").concat(tableName).concat("'");
          try {
               ResultSet rs = ConnectionUtil.createStatement().executeQuery(sql);
               while (rs.next()) {
                    map.put("engine",rs.getString("engine"));
                    map.put("createTime",rs.getString("createTime"));
                    map.put("tableComment",rs.getString("tableComment"));
                    map.put("tableName",rs.getString("tableName"));
               }
          } catch (Exception e) {
               LOGGER.error(e.getMessage(), e);
               throw new Exception("查询表是否存在发生异常", e);
          } finally {
               if (statement != null) {
                    statement.close();
                    statement = null;
               }
          }
          return map;
     }
     /**
      * 查询列信息
      * @param tableName
      * @return
      */
     public List<Map<String, String>> queryColumnss(String tableName) throws Exception{
          Statement statement = null;
          List<Map<String, String>> list = new ArrayList<Map<String, String>>();
          String sql = "select column_name columnName, data_type dataType," +
              " column_comment columnComment, column_key columnKey," +
              " extra from information_schema.columns " +
              "where table_name = ".concat("'").concat(tableName).concat("'").concat(" and table_schema = (select database()) order by ordinal_position");
          try {
               ResultSet rs = ConnectionUtil.createStatement().executeQuery(sql);
               while (rs.next()) {
                    Map<String,String> tmp=new HashMap<>();
                    tmp.put("dataType",rs.getString("dataType"));
                    tmp.put("extra",rs.getString("extra"));
                    tmp.put("columnComment",rs.getString("columnComment"));
                    tmp.put("columnKey",rs.getString("columnKey"));
                    tmp.put("columnName",rs.getString("columnName"));
                    list.add(tmp);
               }
          } catch (Exception e) {
               LOGGER.error(e.getMessage(), e);
               throw new Exception("查询表是否存在发生异常", e);
          } finally {
               if (statement != null) {
                    statement.close();
                    statement = null;
               }
          }
          return list;
     }
}
