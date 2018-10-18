package com.lunxian.modules.tCodeDbinfo.controller;

import java.util.List;
import java.util.Map;

import com.lunxian.modules.tCodeDbinfo.entity.TCodeDbinfoEntity;
import com.lunxian.modules.tCodeDbinfo.service.TCodeDbinfoService;
import com.lunxian.common.utils.PageUtils;
import com.lunxian.common.utils.Query;
import com.lunxian.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * 数据库链接信息
 * @author wang
 */
@Controller
@RequestMapping("TCodeDbinfo/tcodedbinfo")
public class TCodeDbinfoController {
    @Autowired
    private TCodeDbinfoService tCodeDbinfoService;


    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<Map<String, Object>> list = tCodeDbinfoService.queryList(query);
        int total = tCodeDbinfoService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    @ResponseBody
    @RequestMapping("/save")
    public R save(@RequestBody TCodeDbinfoEntity tCodeDbinfoEntity) {
        return R.ok().put("result", tCodeDbinfoService.save(tCodeDbinfoEntity));
    }


    /**
     * 信息
     */
    @ResponseBody
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        TCodeDbinfoEntity tCodeDbinfoEntity = tCodeDbinfoService.getInfo(id);
        return R.ok().put("tCodeDbinfoEntity", tCodeDbinfoEntity);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @ResponseBody
    public R update(@RequestBody TCodeDbinfoEntity tCodeDbinfoEntity) {
        return R.ok().put("result", tCodeDbinfoService.update(tCodeDbinfoEntity));
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public R delete(@RequestBody String[] ids) {
        return R.ok().put("result", tCodeDbinfoService.delectIds(ids));
    }


    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/getDbInfo")
    public R getDbInfo() {
        //查询列表数据
        List<TCodeDbinfoEntity> list = tCodeDbinfoService.getDbInfo();
        return R.ok().put("list", list);
    }


}

