package com.lunxian.modules.TSystemDict.service.impl;

import com.lunxian.modules.TSystemDict.dao.TSystemDictDao;
import com.lunxian.modules.TSystemDict.entity.TSystemDictEntity;
import com.lunxian.modules.TSystemDict.service.TSystemDictService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;



@Service("tSystemDictService" )
public class TSystemDictServiceImpl extends ServiceImpl<TSystemDictDao, TSystemDictEntity> implements TSystemDictService {
      @Autowired
     private TSystemDictDao dao;

      @Override
     public List<Map<String, Object>> queryList(Map<String, Object> map) {
          return dao.queryList(map);
     }

     @Override
     public List<TSystemDictEntity> getDbInfo() {
          return dao.getDbInfo();
     }

     @Override
     public TSystemDictEntity getInfo(Integer id) {
          return dao.getInfo(id);
     }
     



}
