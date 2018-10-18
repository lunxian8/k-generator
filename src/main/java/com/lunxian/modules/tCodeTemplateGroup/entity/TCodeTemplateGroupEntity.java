package com.lunxian.modules.tCodeTemplateGroup.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * 模板组
 * @author wangxuekai
 */
@TableName("t_code_template_group")
public class TCodeTemplateGroupEntity extends Model<TCodeTemplateGroupEntity> {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 组名称
     */
    @TableField("name")
    private String name;
    /**
     * 描述
     */
    @TableField("remarks")
    private String remarks;
    /**
     * 创建时间
     */
    @TableField("CRT_TIME")
    private Date crtTime;
    /**
     * 修改时间
     */
    @TableField("MDF_TIME")
    private Date mdfTime;


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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TCodeTemplateGroupEntity{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", remarks='" + remarks + '\'' +
            ", crtTime=" + crtTime +
            ", mdfTime=" + mdfTime +
            '}';
    }
}
