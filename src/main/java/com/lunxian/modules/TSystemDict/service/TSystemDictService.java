package com.lunxian.modules.TSystemDict.service;

import com.baomidou.mybatisplus.service.IService;
import com.lunxian.modules.TSystemDict.entity.TSystemDictEntity;

import java.util.List;
import java.util.Map;

/**
 * 字典表
 *
 * @author wangxuekai
 * @email 2674518127@qq.com
 * @date 2018-06-04 16:57:53
 */
public interface TSystemDictService extends IService<TSystemDictEntity> {


  /**
      * 查询列表
      *
      * @param map
      * @return
      */
     public List<Map<String, Object>> queryList(Map<String, Object> map);


     /**
      * 获取详情
      * @return
      */
     public List<TSystemDictEntity> getDbInfo() ;

     /**
      * 根据ID获取详情
      *
      * @param id
      * @return
      */
     public TSystemDictEntity getInfo(Integer id);


}

