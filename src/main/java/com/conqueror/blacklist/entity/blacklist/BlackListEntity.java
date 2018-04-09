package com.conqueror.blacklist.entity.blacklist;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.conqueror.blacklist.utils.validator.group.AddGroup;
import com.conqueror.blacklist.utils.validator.group.UpdateGroup;



/**
 * 黑名单
 * 
 * @author Sven
 * @email 1050676672@qq.com
 * @date 2018-02-06 21:11:58
 */
public class BlackListEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//角色名称
	@NotBlank(message="角色名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	//冒险团名称
	@NotBlank(message="冒险团名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String groupName;
	//图片地址
	private String imgUrl;
	//大区名称
	@NotBlank(message="大区名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String regionName;
	//严重程度(1、一般2、较重3、严重4、特别严重)
	private int severity;
	//备注
	@NotBlank(message="备注不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String remarks;
	//状态  0：禁用   1：正常
	private Integer status;
	//创建者ID
	private Long createUserId;
	//创建人姓名
	private String createUserName;
	//创建时间
	private Date createTime;

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：冒险团名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：冒险团名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：图片地址
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	/**
	 * 获取：图片地址
	 */
	public String getImgUrl() {
		return imgUrl;
	}
	/**
	 * 设置：大区名称
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	/**
	 * 获取：大区名称
	 */
	public String getRegionName() {
		return regionName;
	}
	/**
	 * 设置：备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 设置：状态  0：禁用   1：正常
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态  0：禁用   1：正常
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：创建者ID
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：创建者ID
	 */
	public Long getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getSeverity() {
		return severity;
	}
	public void setSeverity(int severity) {
		this.severity = severity;
	}
}
