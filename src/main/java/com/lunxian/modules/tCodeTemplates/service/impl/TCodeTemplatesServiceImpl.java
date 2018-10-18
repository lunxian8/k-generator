package com.lunxian.modules.tCodeTemplates.service.impl;

import com.lunxian.modules.tCodeTemplates.dao.TCodeTemplatesDao;
import com.lunxian.modules.tCodeTemplates.entity.TCodeTemplatesEntity;
import com.lunxian.modules.tCodeTemplates.service.TCodeTemplatesService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("tCodeTemplatesService" )
public class TCodeTemplatesServiceImpl extends ServiceImpl<TCodeTemplatesDao, TCodeTemplatesEntity> implements TCodeTemplatesService {

     @Autowired
     TCodeTemplatesDao tCodeTemplatesDao;

     @Override
     public List<Map<String, Object>> queryList(Map<String, Object> map) {
          return tCodeTemplatesDao.queryList(map);
     }

     @Override
     public List<TCodeTemplatesEntity> getDbInfo(TCodeTemplatesEntity tCodeTemplatesEntity) {
          return tCodeTemplatesDao.getDbInfo(tCodeTemplatesEntity);
     }

     @Override
     public boolean save(TCodeTemplatesEntity tCodeTemplatesEntity) {
          return tCodeTemplatesDao.save(tCodeTemplatesEntity);
     }

     @Override
     public TCodeTemplatesEntity getInfo(int id) {
          return tCodeTemplatesDao.getInfo(id);
     }

     @Override
     public boolean update(TCodeTemplatesEntity tCodeTemplatesEntity) {
          return tCodeTemplatesDao.update(tCodeTemplatesEntity);
     }

     @Override
     public int queryTotal(Map<String, Object> map) {
          return tCodeTemplatesDao.queryTotal(map);
     }

     @Override
     public int delectByIds(String[] ids) {
          int count = 0;
          for (String tmp : ids) {
               int value = tCodeTemplatesDao.delect(tmp);
               count = count + value;
          }
          return count;
     }
}
