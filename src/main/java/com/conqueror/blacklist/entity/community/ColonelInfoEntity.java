package com.conqueror.blacklist.entity.community;

import java.io.Serializable;
import java.util.Date;



/**
 * 团长信息
 * 
 * @author Sven
 * @email 1050676672@qq.com
 * @date 2018-03-30 17:02:21
 */
public class ColonelInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//所属小组T1-TN
	private String belongGroup;
	//开团名称
	private String nickName;
	//冒险团名称
	private String groupName;
	//冒险团日期
	private String groupDate;
	//QQ号
	private String contactsQq;
	//创建人
	private String createUserId;
	//创建时间
	private Date createDate;
	//最后修改人
	private String lastUpdateUserId;
	//最后修改时间
	private Date lastUpdateDate;
	//删除标识:0：未删，1已删 
	private Integer delflag;

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
	 * 设置：所属小组T1-TN
	 */
	public void setBelongGroup(String belongGroup) {
		this.belongGroup = belongGroup;
	}
	/**
	 * 获取：所属小组T1-TN
	 */
	public String getBelongGroup() {
		return belongGroup;
	}
	/**
	 * 设置：开团名称
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * 获取：开团名称
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * 设置：冒险团名称
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * 获取：冒险团名称
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * 设置：冒险团日期
	 */
	public void setGroupDate(String groupDate) {
		this.groupDate = groupDate;
	}
	/**
	 * 获取：冒险团日期
	 */
	public String getGroupDate() {
		return groupDate;
	}
	/**
	 * 设置：QQ号
	 */
	public void setContactsQq(String contactsQq) {
		this.contactsQq = contactsQq;
	}
	/**
	 * 获取：QQ号
	 */
	public String getContactsQq() {
		return contactsQq;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：创建人
	 */
	public String getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：最后修改人
	 */
	public void setLastUpdateUserId(String lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}
	/**
	 * 获取：最后修改人
	 */
	public String getLastUpdateUserId() {
		return lastUpdateUserId;
	}
	/**
	 * 设置：最后修改时间
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	/**
	 * 获取：最后修改时间
	 */
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	/**
	 * 设置：删除标识:0：未删，1已删 
	 */
	public void setDelflag(Integer delflag) {
		this.delflag = delflag;
	}
	/**
	 * 获取：删除标识:0：未删，1已删 
	 */
	public Integer getDelflag() {
		return delflag;
	}
	
}
