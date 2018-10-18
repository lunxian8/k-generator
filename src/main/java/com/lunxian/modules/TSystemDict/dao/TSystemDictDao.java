package com.lunxian.modules.TSystemDict.dao;

import com.lunxian.modules.TSystemDict.entity.TSystemDictEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;
/**
 * 字典表
 * 
 * @author wangxuekai
 * @email 2674518127@qq.com
 * @date 2018-06-04 16:57:53
 */
public interface TSystemDictDao extends BaseMapper<TSystemDictEntity> {

   List<Map<String, Object>> queryList(Map<String, Object> map);

   TSystemDictEntity getInfo(Integer id);

   List<TSystemDictEntity> getDbInfo();
	
}
