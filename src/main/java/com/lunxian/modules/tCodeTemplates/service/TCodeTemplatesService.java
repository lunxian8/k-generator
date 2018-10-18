package com.lunxian.modules.tCodeTemplates.service;

import com.baomidou.mybatisplus.service.IService;
import com.lunxian.modules.tCodeTemplates.entity.TCodeTemplatesEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 模板
 * @author wang
 */
public interface TCodeTemplatesService extends IService<TCodeTemplatesEntity> {

    /**
     * 查询列表
     * @return
     */
    public List<Map<String, Object>> queryList(Map<String, Object> map);


    public List<TCodeTemplatesEntity> getDbInfo(TCodeTemplatesEntity tCodeTemplatesEntity);

    /**
     * 新增
     * @return
     */
    @Transactional
    public boolean save(TCodeTemplatesEntity tCodeTemplatesEntity);

    /**
     * 获取详情
     * @return
     */
    public TCodeTemplatesEntity getInfo(int id);

    /**
     * 修改
     * @return
     */
    @Transactional
    public boolean update(TCodeTemplatesEntity tCodeTemplatesEntity);


    /**
     * 查询条数
     * @return count
     */
    public int queryTotal(Map<String, Object> map);

    @Transactional
    public int delectByIds(String[] ids);

}

