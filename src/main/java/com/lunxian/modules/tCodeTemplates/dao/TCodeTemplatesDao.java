package com.lunxian.modules.tCodeTemplates.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lunxian.modules.tCodeTemplates.entity.TCodeTemplatesEntity;

import java.util.List;
import java.util.Map;

/**
 * 模板
 * @author wang
 */
public interface TCodeTemplatesDao extends BaseMapper<TCodeTemplatesEntity> {


    List<Map<String, Object>> queryList(Map<String, Object> map);

    boolean save(TCodeTemplatesEntity tCodeTemplatesEntity);

    TCodeTemplatesEntity getInfo(int id);

    boolean update(TCodeTemplatesEntity tCodeTemplatesEntity);

    int delect(String id);

    List<TCodeTemplatesEntity> getDbInfo(TCodeTemplatesEntity tCodeTemplatesEntity);

    int queryTotal(Map<String, Object> map);


}
