package com.conqueror.blacklist.dao.blacklist;

import java.util.List;

import com.conqueror.blacklist.entity.blacklist.SysUserRoleEntity;

/**
 * 用户与角色对应关系
 * 
 */
public interface SysUserRoleDao extends BaseDao<SysUserRoleEntity> {
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);
}
