package com.lunxian.common.baseDb.entity;

/**
 * 数据库管理
 *
 * @author WangXueKai
 * @Date 2018-5-25 11:15
 */
public class DbInfoEntity{

    private static final long serialVersionUID = 1L;
    /**
     * Id
     */
    private Integer id;

    /**
     * 别名
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
     * UserId
     */
    private Integer userId;

    /**
     * 数据库类型
     */
    private String dbType;

    /**
     * 获取: Id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置: Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取: 别名
     */
    public String getAlias() {
        return alias;
    }

    /**
     * 设置: 别名
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * 获取: 数据库驱动
     */
    public String getDbDriver() {
        return dbDriver;
    }

    /**
     * 设置: 数据库驱动
     */
    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }

    /**
     * 获取: 数据库地址
     */
    public String getDbUrl() {
        return dbUrl;
    }

    /**
     * 设置: 数据库地址
     */
    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    /**
     * 获取: 数据库账户
     */
    public String getDbUserName() {
        return dbUserName;
    }

    /**
     * 设置: 数据库账户
     */
    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    /**
     * 获取: 连接密码
     */
    public String getDbPassword() {
        return dbPassword;
    }

    /**
     * 设置: 连接密码
     */
    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    /**
     * 获取: UserId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置: UserId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取: 数据库类型
     */
    public String getDbType() {
        return dbType;
    }

    /**
     * 设置: 数据库类型
     */
    public void setDbType(String dbType) {
        this.dbType = dbType;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "DbInfoEntity{" +
            "id=" + id +
            ", alias='" + alias + '\'' +
            ", dbDriver='" + dbDriver + '\'' +
            ", dbUrl='" + dbUrl + '\'' +
            ", dbUserName='" + dbUserName + '\'' +
            ", dbPassword='" + dbPassword + '\'' +
            ", userId=" + userId +
            ", dbType='" + dbType + '\'' +
            '}';
    }
}
