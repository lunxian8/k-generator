package com.lunxian.modules.sysGenerator.controller;

import com.alibaba.fastjson.JSON;
import com.lunxian.modules.tCodeDbinfo.entity.TCodeDbinfoEntity;
import com.lunxian.modules.tCodeTemplates.entity.TCodeTemplatesEntity;
import com.lunxian.common.utils.*;
import com.lunxian.common.baseDb.service.IReadSerivice;
import com.lunxian.common.baseDb.service.ReadTableFactory;
import com.lunxian.modules.sysGenerator.service.SysGeneratorService;
import com.lunxian.modules.tCodeDbinfo.service.TCodeDbinfoService;
import com.lunxian.modules.tCodeTemplates.service.TCodeTemplatesService;
import com.lunxian.common.utils.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午9:12:58
 */
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController {
     @Autowired
     private SysGeneratorService sysGeneratorService;

     @Autowired
     private TCodeDbinfoService tCodeDbinfoService;

     @Autowired
     private TCodeTemplatesService tCodeTemplatesService;

     /**
      * 列表
      */
     @ResponseBody
     @RequestMapping("/list")
     public R list(@RequestParam Map<String, Object> params) {
          //查询列表数据
          Query query = new Query(params);
          List<Map<String, Object>> list = sysGeneratorService.queryList(query);
          int total = sysGeneratorService.queryTotal(query);

          PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());

          return R.ok().put("page", pageUtil);
     }

     /**
      * 生成代码
      */
     @RequestMapping("/code")
     public void code(HttpServletRequest request, HttpServletResponse response) throws IOException {
          String[] tableNames = new String[]{};
          String tables = request.getParameter("tables");
          String packageUrl = request.getParameter("packageUrl");
          tableNames = JSON.parseArray(tables).toArray(tableNames);

          byte[] data = sysGeneratorService.generatorCode(tableNames, packageUrl);

          response.reset();
          String fileName = tables + DateUtils.getDate() + ".zip";
          String tmp = "attachment; filename=" + "\"" + fileName + "\"";
          response.setHeader("Content-Disposition", tmp);
          response.addHeader("Content-Length", "" + data.length);
          response.setContentType("application/octet-stream; charset=UTF-8");

          IOUtils.write(data, response.getOutputStream());
     }

     /**
      * 代码预览
      *
      * @param request
      * @param response
      * @return
      */
     @RequestMapping("/codeView")
     @ResponseBody
     public R codeView(HttpServletRequest request, HttpServletResponse response) {
          String table = request.getParameter("table");
          String packageUrl = request.getParameter("packageUrl");
          return R.ok().put("codeView", sysGeneratorService.generatorCodeView(table, packageUrl));
     }


     /****************************************************分割线*********************************************************/
     /**
      * 动态查询数据库列表
      */
     @ResponseBody
     @RequestMapping("/listJb")
     public R listJb(@RequestParam Map<String, Object> params) throws Exception {
          TCodeDbinfoEntity dbTmp = tCodeDbinfoService.getInfo(Integer.valueOf((String) params.get("dbID")));
          ConnectionUtil.init(dbTmp.getDbDriver(), dbTmp.getDbUrl(), dbTmp.getDbUserName(),
              dbTmp.getDbPassword());
          IReadSerivice readSerivice = ReadTableFactory.getReadTable(dbTmp.getDbType());
          //查询列表数据
          Query query = new Query(params);
          List<Map<String, Object>> list = readSerivice.getTableByDb(query);
          int total = readSerivice.queryTotal(query);
          PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
          return R.ok().put("page", pageUtil);
     }


     /**
      * 生成代码
      */
     @RequestMapping("/codeJb")
     public void codeJb(HttpServletRequest request, HttpServletResponse response) throws Exception {

          String[] tableNames = new String[]{};
          String tables = request.getParameter("tables");
          String packageUrl = request.getParameter("packageUrl");
          Integer dbID = Integer.valueOf(request.getParameter("dbID"));
          tableNames = JSON.parseArray(tables).toArray(tableNames);
          Integer tempId =  Integer.valueOf(request.getParameter("tempId"));
          if (tables==null||tables.equals("")||dbID==null||tempId==null){
               return;
          }
          TCodeTemplatesEntity tmpCode=new TCodeTemplatesEntity();
          tmpCode.setGroupId(tempId);
          List<TCodeTemplatesEntity>list=tCodeTemplatesService.getDbInfo(tmpCode);
          if (list.size()==0||list==null){
               return;
          }

          byte[] data = sysGeneratorService.generatorCodeJb(tableNames, packageUrl, dbID,list);
          response.reset();
          String fileName = StringUtils.jsonArryToString(tables, "_") + DateUtils.getDate() + ".zip";
          String tmp = "attachment; filename=" + "\"" + fileName + "\"";
          response.setHeader("Content-Disposition", tmp);
          response.addHeader("Content-Length", "" + data.length);
          response.setContentType("application/octet-stream; charset=UTF-8");
          IOUtils.write(data, response.getOutputStream());
     }

     /**
      * 代码预览
      *
      * @param request
      * @param response
      * @return
      */
     @RequestMapping("/codeViewJb")
     @ResponseBody
     public R codeViewJb(HttpServletRequest request, HttpServletResponse response) throws Exception {
          String table = request.getParameter("table");
          String packageUrl = request.getParameter("packageUrl");
          Integer dbID = Integer.valueOf(request.getParameter("dbID"));
          Integer tempId =  Integer.valueOf(request.getParameter("tempId"));
          if (table==null||table.equals("")||dbID==null||tempId==null){
               return R.error("请检查参数");
          }
          TCodeTemplatesEntity tmp=new TCodeTemplatesEntity();
          tmp.setGroupId(tempId);
          List<TCodeTemplatesEntity>list=tCodeTemplatesService.getDbInfo(tmp);
          if (list.size()==0||list==null){
               return R.error("该模板组未定义数据");
          }
          return R.ok().put("codeView", sysGeneratorService.generatorCodeViewJb(table, packageUrl, dbID,list));
     }

}
