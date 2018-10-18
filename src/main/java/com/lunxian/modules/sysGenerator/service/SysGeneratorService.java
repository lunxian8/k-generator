package com.lunxian.modules.sysGenerator.service;

import com.lunxian.common.baseDb.service.IReadSerivice;
import com.lunxian.common.baseDb.service.ReadTableFactory;
import com.lunxian.modules.sysGenerator.dao.SysGeneratorDao;
import com.lunxian.modules.tCodeDbinfo.dao.TCodeDbinfoDao;
import com.lunxian.modules.tCodeDbinfo.entity.TCodeDbinfoEntity;
import com.lunxian.modules.tCodeTemplates.entity.TCodeTemplatesEntity;
import com.lunxian.common.utils.ConnectionUtil;
import com.lunxian.common.utils.gen.GenUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午3:33:38
 */
@Service
public class SysGeneratorService {
     @Autowired
     private SysGeneratorDao sysGeneratorDao;

     @Autowired
     private TCodeDbinfoDao tCodeDbinfoDao;

     public List<Map<String, Object>> queryList(Map<String, Object> map) {
          return sysGeneratorDao.queryList(map);
     }

     public int queryTotal(Map<String, Object> map) {
          return sysGeneratorDao.queryTotal(map);
     }

     public Map<String, String> queryTable(String tableName) {
          return sysGeneratorDao.queryTable(tableName);
     }

     public List<Map<String, String>> queryColumns(String tableName) {
          return sysGeneratorDao.queryColumns(tableName);
     }

     /**
      * 代码生成Zip
      *
      * @param tableNames
      * @param packageUrl
      * @return
      */
     public byte[] generatorCode(String[] tableNames, String packageUrl) {
          ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
          ZipOutputStream zip = new ZipOutputStream(outputStream);

          for (String tableName : tableNames) {
               //查询表信息
               Map<String, String> table = queryTable(tableName);
               //查询列信息
               List<Map<String, String>> columns = queryColumns(tableName);
               //生成代码
               GenUtils.generatorCode(table, packageUrl, columns, zip,null);
          }
          IOUtils.closeQuietly(zip);
          return outputStream.toByteArray();
     }

     /**
      * 代码生成预览
      *
      * @param tableName
      * @param packageUrl
      * @return
      */
     public Map<String, String> generatorCodeView(String tableName, String packageUrl) {
          //查询表信息
          Map<String, String> table = queryTable(tableName);
          //查询列信息
          List<Map<String, String>> columns = queryColumns(tableName);
          //生成代码
          return GenUtils.generatorCodeView(table, packageUrl, columns, null);
     }

     /****************************************************分割线*********************************************************/
     /**
      * 代码生成Zip
      *
      * @param tableNames
      * @param packageUrl
      * @return
      */
     public byte[] generatorCodeJb(String[] tableNames, String packageUrl,Integer tbId,List<TCodeTemplatesEntity>list) throws Exception {
          TCodeDbinfoEntity dbTmp=  tCodeDbinfoDao.getInfo(tbId);
          ConnectionUtil.init(dbTmp.getDbDriver(), dbTmp.getDbUrl(), dbTmp.getDbUserName(),
              dbTmp.getDbPassword());
          IReadSerivice readSerivice = ReadTableFactory.getReadTable(dbTmp.getDbType());

          ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
          ZipOutputStream zip = new ZipOutputStream(outputStream);

          for (String tableName : tableNames) {
               //查询表信息
               Map<String, String> table = readSerivice.queryTable(tableName);
               //查询列信息
               List<Map<String, String>> columns = readSerivice.queryColumns(tableName);
               //生成代码
               GenUtils.generatorCode(table, packageUrl, columns, zip,list);
          }
          IOUtils.closeQuietly(zip);
          return outputStream.toByteArray();
     }

     /**
      * 代码生成预览
      *
      * @param tableName
      * @param packageUrl
      * @return
      */
     public Map<String, String> generatorCodeViewJb(String tableName, String packageUrl,Integer tbId, List<TCodeTemplatesEntity>list) throws Exception {
          TCodeDbinfoEntity dbTmp=  tCodeDbinfoDao.getInfo(tbId);
          ConnectionUtil.init(dbTmp.getDbDriver(), dbTmp.getDbUrl(), dbTmp.getDbUserName(),
              dbTmp.getDbPassword());
          IReadSerivice readSerivice = ReadTableFactory.getReadTable(dbTmp.getDbType());
          //查询表信息
          Map<String, String> table = readSerivice.queryTable(tableName);
          //查询列信息
          List<Map<String, String>> columns = readSerivice.queryColumns(tableName);
          //生成代码
          return GenUtils.generatorCodeView(table, packageUrl, columns,list);
     }


}
