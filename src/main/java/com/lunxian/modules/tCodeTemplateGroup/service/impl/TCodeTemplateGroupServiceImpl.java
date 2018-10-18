package com.lunxian.modules.tCodeTemplateGroup.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lunxian.modules.tCodeTemplateGroup.dao.TCodeTemplateGroupDao;
import com.lunxian.modules.tCodeTemplateGroup.entity.TCodeTemplateGroupEntity;
import com.lunxian.modules.tCodeTemplateGroup.service.TCodeTemplateGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("tCodeTemplateGroupService")
public class TCodeTemplateGroupServiceImpl extends ServiceImpl<TCodeTemplateGroupDao, TCodeTemplateGroupEntity> implements TCodeTemplateGroupService {


     @Autowired
     private TCodeTemplateGroupDao dao;


     @Override
     public List<Map<String, Object>> queryList(Map<String, Object> map) {
          return dao.queryList(map);
     }

     @Override
     public List<TCodeTemplateGroupEntity> getDbInfo() {
          return dao.getDbInfo();
     }

     @Override
     public TCodeTemplateGroupEntity getInfo(Integer id) {
          return dao.getInfo(id);
     }

     @Override
     public boolean save(TCodeTemplateGroupEntity tCodeTemplateGroupEntity) {
          return dao.save(tCodeTemplateGroupEntity);
     }


     @Override
     public boolean update(TCodeTemplateGroupEntity tCodeTemplateGroupEntity) {
          return dao.update(tCodeTemplateGroupEntity);
     }


     @Override
     public int queryTotal(Map<String, Object> map) {
          return dao.queryTotal(map);
     }

     @Override
     public int delectByIds(String[] ids) {
          int count = 0;
          for (String tmp : ids) {
               int value = dao.delect(tmp);
               count = count + value;
          }
          return count;
     }
}
