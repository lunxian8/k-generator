package com.lunxian.modules.TSystemDict.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典表
 *
 * @author wangxuekai
 * @email 2674518127@qq.com
 * @date 2018-06-04 16:57:53
 */
@TableName("t_system_dict")
public class TSystemDictEntity extends Model<TSystemDictEntity>  {
     private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	/**
	 * 排序
	 */
	@TableField("num")
	private Integer num;
	/**
	 * 父级字典
	 */
	@TableField("pid")
	private Integer pid;
	/**
	 * 名称
	 */
	@TableField("name")
	private String name;
	/**
	 * 提示
	 */
	@TableField("tips")
	private String tips;
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
	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getNum() {
		return num;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getPid() {
		return pid;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getTips() {
		return tips;
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
      return "t_system_dict{" +
	 		 "id=" + id +
			 		", num=" + num +
	    	 		", pid=" + pid +
	    	 		", name=" + name +
	    	 		", tips=" + tips +
	    	 		", crtTime=" + crtTime +
	    	 		", mdfTime=" + mdfTime +
	    	      "}";
     }
}
