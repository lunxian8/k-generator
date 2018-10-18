package com.lunxian.modules.tCodeDbinfo.service;


import com.lunxian.modules.tCodeDbinfo.dao.TCodeDbinfoDao;
import com.lunxian.modules.tCodeDbinfo.entity.TCodeDbinfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 数据库链接信息
 * @author wang
 */
@Service
public class TCodeDbinfoService {

    @Autowired
    private TCodeDbinfoDao tCodeDbinfoDao;


    /**
     * 查询列表
     * @return
     */
    public List<Map<String, Object>> queryList(Map<String, Object> map) {
        return tCodeDbinfoDao.queryList(map);
    }


    public List<TCodeDbinfoEntity> getDbInfo() {
        return tCodeDbinfoDao.getDbInfo();
    }

    /**
     * 新增
     * @return
     */
    @Transactional
    public boolean save(TCodeDbinfoEntity tCodeDbinfoEntity) {
        return tCodeDbinfoDao.save(tCodeDbinfoEntity);
    }

    /**
     * 获取详情
     * @return
     */
    public TCodeDbinfoEntity getInfo(Integer id) {
        return tCodeDbinfoDao.getInfo(id);
    }

    /**
     * 修改
     * @return
     */
    @Transactional
    public boolean update(TCodeDbinfoEntity tCodeDbinfoEntity) {
        return tCodeDbinfoDao.update(tCodeDbinfoEntity);
    }

    @Transactional
    public boolean delectIds(String[] ids) {
        boolean flag = false;
        for (int i = 0; i < ids.length; i++) {
            flag = tCodeDbinfoDao.delect(ids[i]);
        }
        return flag;
    }

    /**
     * 查询条数
     * @return count
     */
    public int queryTotal(Map<String, Object> map) {
        return tCodeDbinfoDao.queryTotal(map);
    }
}

