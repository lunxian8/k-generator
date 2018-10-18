package com.lunxian.modules.tCodeTemplateGroup.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lunxian.modules.tCodeTemplateGroup.entity.TCodeTemplateGroupEntity;

import java.util.List;
import java.util.Map;

/**
 * 模板组
 * @author wangxuekai
 */
public interface TCodeTemplateGroupDao extends BaseMapper<TCodeTemplateGroupEntity> {


    List<Map<String, Object>> queryList(Map<String, Object> map);

    boolean save(TCodeTemplateGroupEntity tCodeTemplateGroupEntity);

    TCodeTemplateGroupEntity getInfo(Integer id);

    boolean update(TCodeTemplateGroupEntity tCodeTemplateGroupEntity);

    int delect(String id);

    List<TCodeTemplateGroupEntity> getDbInfo();

    int queryTotal(Map<String, Object> map);


}
