package com.lunxian.modules.tCodeTemplates.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * 模板
 * @author wangxuekai
 */
@TableName("t_code_templates")
public class TCodeTemplatesEntity extends Model<TCodeTemplatesEntity> {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 模板名称
     */
    @TableField("name")
    private String name;
    /**
     * 模板内容
     */
    @TableField("content")
    private String content;
    /**
     * 模板保存名称
     */
    @TableField("file_name")
    private String fileName;

    /**
     * 保存包名
     */
    @TableField("sava_package")
    private String savaPackage;
    /**
     * 所属模板组
     */
    @TableField("group_id")
    private Integer groupId;
    /**
     * 模板类型
     */
    @TableField("file_type")
    private String fileType;
    /**
     * 创建时间
     */
    @TableField("crt_time")
    private Date crtTime;
    /**
     * 修改时间
     */
    @TableField("mdf_time")
    private Date mdfTime;
    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;
    /**
     * 描述
     */
    @TableField("remarks")
    private String remarks;
    /**
     * 状态
     */
    @TableField("del_flag")
    private String delFlag;


    /**
     * 模板组名称
     */
    private transient String tempName;


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setMdfTime(Date mdfTime) {
        this.mdfTime = mdfTime;
    }

    public Date getMdfTime() {
        return mdfTime;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSort() {
        return sort;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public String getTempName() {
        return tempName;
    }

    public void setTempName(String tempName) {
        this.tempName = tempName;
    }

    public String getSavaPackage() {
        return savaPackage;
    }

    public void setSavaPackage(String savaPackage) {
        this.savaPackage = savaPackage;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return " t_code_templates{" +
            "id=" + id +
            ", name=" + name +
            ", content=" + content +
            ", fileName=" + fileName +
            ", groupId=" + groupId +
            ", fileType=" + fileType +
            ", crtTime=" + crtTime +
            ", mdfTime=" + mdfTime +
            ", sort=" + sort +
            ", remarks=" + remarks +
            ", delFlag=" + delFlag +
            "}";
    }
}
