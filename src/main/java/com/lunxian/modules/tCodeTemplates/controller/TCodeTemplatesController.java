package com.lunxian.modules.tCodeTemplates.controller;


import com.lunxian.modules.tCodeTemplates.entity.TCodeTemplatesEntity;
import com.lunxian.modules.tCodeTemplateGroup.service.TCodeTemplateGroupService;
import com.lunxian.modules.tCodeTemplates.service.TCodeTemplatesService;
import com.lunxian.common.utils.PageUtils;
import com.lunxian.common.utils.Query;
import com.lunxian.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 模板
 * @author wang
 */
@Controller
@RequestMapping("TCodeTemplates/tcodetemplates")
public class TCodeTemplatesController {

    @Autowired
    private TCodeTemplatesService tCodeTemplatesService;

    @Autowired
    private TCodeTemplateGroupService tCodeTemplateGroupService;


    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<Map<String, Object>> list = tCodeTemplatesService.queryList(query);
        int total = tCodeTemplatesService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 新增
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public R save(@RequestBody TCodeTemplatesEntity tCodeTemplatesEntity) {
        return R.ok().put("result", tCodeTemplatesService.save(tCodeTemplatesEntity));
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @ResponseBody
    public R update(@RequestBody TCodeTemplatesEntity tCodeTemplatesEntity) {
        return R.ok().put("result", tCodeTemplatesService.update(tCodeTemplatesEntity));
    }

    /**
     * 信息
     */
    @ResponseBody
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") int id) {
        TCodeTemplatesEntity tCodeTemplatesEntity = tCodeTemplatesService.getInfo(id);
        return R.ok().put("tCodeTemplatesEntity", tCodeTemplatesEntity);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public R delete(@RequestBody String[] ids) {
        return R.ok().put("result", tCodeTemplatesService.delectByIds(ids));
    }

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/getDbInfo")
    public R getDbInfo() {
        //查询列表数据
        List<TCodeTemplatesEntity> list = tCodeTemplatesService.getDbInfo(new TCodeTemplatesEntity());
        return R.ok().put("list", list);
    }

}
