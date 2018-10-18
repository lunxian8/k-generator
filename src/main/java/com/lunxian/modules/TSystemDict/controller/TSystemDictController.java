package com.lunxian.modules.TSystemDict.controller;

import com.lunxian.modules.TSystemDict.entity.TSystemDictEntity;
import com.lunxian.modules.TSystemDict.service.TSystemDictService;
import com.lunxian.common.utils.PageUtils;
import com.lunxian.common.utils.Query;
import com.lunxian.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

import java.util.List;
import java.util.Map;

/**
 * 字典表
 *
 * @author wangxuekai
 * @email 2674518127@qq.com
 * @date 2018-06-04 16:57:53
 */
@RequestMapping("TSystemDict/tsystemdict")
@RestController
public class TSystemDictController {

     @Autowired
     private TSystemDictService tSystemDictService;

     /**
      * 列表
      */
     @ResponseBody
     @RequestMapping("/list")
     public R list(@RequestParam Map<String, Object> params) {
          //查询列表数据
          Query query = new Query(params);
          List<Map<String, Object>> list = tSystemDictService.queryList(query);
          int total = tSystemDictService.selectCount(new EntityWrapper<>());
          PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());

          return R.ok().put("page", pageUtil);
     }

     /**
      * 信息
      */
     @ResponseBody
     @RequestMapping("/info/{id}")
     public R info(@PathVariable("id") Integer id) {
          TSystemDictEntity tSystemDict = tSystemDictService.getInfo(id);
          return R.ok().put("tSystemDict", tSystemDict);
     }

     /**
      * 列表
      */
     @ResponseBody
     @RequestMapping("/getDbInfo")
     public R getDbInfo() {
          //查询列表数据
          List<TSystemDictEntity> list = tSystemDictService.getDbInfo();
          return R.ok().put("list", list);
     }

     /**
      * 新增 or 修改
      *
      * @param tSystemDict
      * @return
      */
     @ResponseBody
     @RequestMapping("/insertOrUpdate")
     public R insertOrUpdate(@RequestBody TSystemDictEntity tSystemDict) {
          return R.ok().put("result", tSystemDictService.insertOrUpdate(tSystemDict));
     }


     /**
      * 删除
      */
     @RequestMapping("/delete")
     @ResponseBody
     public R delete(@RequestBody String[] ids) {
          boolean flag = false;
          for (String str : ids) {
               flag = tSystemDictService.deleteById(Integer.valueOf(str));
          }
          return R.ok().put("result", flag);
     }

}
