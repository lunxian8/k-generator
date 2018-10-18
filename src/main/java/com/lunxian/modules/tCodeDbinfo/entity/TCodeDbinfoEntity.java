package com.lunxian.modules.tCodeDbinfo.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * 数据库链接信息
 * @author wang
 */
public class TCodeDbinfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Integer id;
    /**
     *
     */
    private String alias;
    /**
     * 数据库驱动
     */
    private String dbDriver;
    /**
     * 数据库地址
     */
    private String dbUrl;
    /**
     * 数据库账户
     */
    private String dbUserName;
    /**
     * 连接密码
     */
    private String dbPassword;
    /**
     *
     */
    private String userId;
    /**
     * 数据库类型
     */
    private String dbType;
    /**
     * 创建人
     */
    private String crtUserId;
    /**
     * 创建时间
     */
    private Date crtTime;
    /**
     * 修改人
     */
    private String mdfUserId;
    /**
     * 修改时间
     */
    private Date mdfTime;

    /**
     * 设置：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * 获取ss：
     */
    public String getAlias() {
        return alias;
    }

    /**
     * 设置：数据库驱动
     */
    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }

    /**
     * 获取：数据库驱动
     */
    public String getDbDriver() {
        return dbDriver;
    }

    /**
     * 设置：数据库地址
     */
    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    /**
     * 获取：数据库地址
     */
    public String getDbUrl() {
        return dbUrl;
    }

    /**
     * 设置：数据库账户
     */
    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    /**
     * 获取：数据库账户
     */
    public String getDbUserName() {
        return dbUserName;
    }

    /**
     * 设置：连接密码
     */
    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    /**
     * 获取：连接密码
     */
    public String getDbPassword() {
        return dbPassword;
    }

    /**
     * 设置：
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取：
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置：数据库类型
     */
    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    /**
     * 获取：数据库类型
     */
    public String getDbType() {
        return dbType;
    }

    /**
     * 设置：创建人
     */
    public void setCrtUserId(String crtUserId) {
        this.crtUserId = crtUserId;
    }

    /**
     * 获取：创建人
     */
    public String getCrtUserId() {
        return crtUserId;
    }

    /**
     * 设置：创建时间
     */
    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    /**
     * 获取：创建时间
     */
    public Date getCrtTime() {
        return crtTime;
    }

    /**
     * 设置：修改人
     */
    public void setMdfUserId(String mdfUserId) {
        this.mdfUserId = mdfUserId;
    }

    /**
     * 获取：修改人
     */
    public String getMdfUserId() {
        return mdfUserId;
    }

    /**
     * 设置：修改时间
     */
    public void setMdfTime(Date mdfTime) {
        this.mdfTime = mdfTime;
    }

    /**
     * 获取：修改时间
     */
    public Date getMdfTime() {
        return mdfTime;
    }
}
