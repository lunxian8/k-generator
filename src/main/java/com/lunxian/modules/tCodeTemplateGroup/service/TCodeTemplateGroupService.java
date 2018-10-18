package com.lunxian.modules.tCodeTemplateGroup.service;

import com.baomidou.mybatisplus.service.IService;
import com.lunxian.modules.tCodeTemplateGroup.entity.TCodeTemplateGroupEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 模板组
 * @author wangxuekai
 */
public interface TCodeTemplateGroupService extends IService<TCodeTemplateGroupEntity> {


    /**
     * 查询列表
     * @return
     */
    public List<Map<String, Object>> queryList(Map<String, Object> map);


    /**
     * 获取详情
     * @return
     */
    public List<TCodeTemplateGroupEntity> getDbInfo();

    /**
     * 根据ID获取详情
     * @return
     */
    public TCodeTemplateGroupEntity getInfo(Integer id);


    /**
     * 新增
     * @return
     */
    @Transactional
    public boolean save(TCodeTemplateGroupEntity tCodeTemplateGroupEntity);


    /**
     * 修改
     * @return
     */
    @Transactional
    public boolean update(TCodeTemplateGroupEntity tCodeTemplateGroupEntity);


    /**
     * 查询条数
     * @return count
     */
    public int queryTotal(Map<String, Object> map);

    @Transactional
    public int delectByIds(String[] ids);

}

