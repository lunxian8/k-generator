package com.lunxian.modules.tCodeTemplateGroup.controller;

import com.lunxian.modules.tCodeTemplateGroup.entity.TCodeTemplateGroupEntity;
import com.lunxian.modules.tCodeTemplateGroup.service.TCodeTemplateGroupService;
import com.lunxian.common.utils.PageUtils;
import com.lunxian.common.utils.Query;
import com.lunxian.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 模板组
 * @author wang
 */
@RestController
@RequestMapping("TCodeTemplateGroup/tcodetemplategroup")
public class TCodeTemplateGroupController {


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
        List<Map<String, Object>> list = tCodeTemplateGroupService.queryList(query);
        int total = tCodeTemplateGroupService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 信息
     */
    @ResponseBody
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        TCodeTemplateGroupEntity tCodeTemplateGroupEntity = tCodeTemplateGroupService.getInfo(id);
        return R.ok().put("tCodeTemplateGroupEntity", tCodeTemplateGroupEntity);
    }

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/getDbInfo")
    public R getDbInfo() {
        //查询列表数据
        List<TCodeTemplateGroupEntity> list = tCodeTemplateGroupService.getDbInfo();
        return R.ok().put("list", list);
    }

    /**
     * 新增
     * @return
     */
    @ResponseBody
    @RequestMapping("/save")
    public R save(@RequestBody TCodeTemplateGroupEntity tCodeTemplateGroupEntity) {
        return R.ok().put("result", tCodeTemplateGroupService.save(tCodeTemplateGroupEntity));
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @ResponseBody
    public R update(@RequestBody TCodeTemplateGroupEntity tCodeTemplateGroupEntity) {
        tCodeTemplateGroupService.update(tCodeTemplateGroupEntity);
        return R.ok().put("result", tCodeTemplateGroupService.update(tCodeTemplateGroupEntity));
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public R delete(@RequestBody String[] ids) {
        return R.ok().put("result", tCodeTemplateGroupService.delectByIds(ids));
    }
}
