package com.lunxian.modules.tCodeDbinfo.dao;

import com.lunxian.modules.tCodeDbinfo.entity.TCodeDbinfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 数据库链接信息
 * @author wang
 */
public interface TCodeDbinfoDao {

    List<Map<String, Object>> queryList(Map<String, Object> map);

    boolean save(TCodeDbinfoEntity tCodeDbinfoEntity);

    TCodeDbinfoEntity getInfo(Integer id);

    boolean update(TCodeDbinfoEntity tCodeDbinfoEntity);

    boolean delect(String id);

    List<TCodeDbinfoEntity> getDbInfo();

    int queryTotal(Map<String, Object> map);
}
