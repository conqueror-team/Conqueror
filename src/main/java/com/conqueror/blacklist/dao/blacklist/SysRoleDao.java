package com.conqueror.blacklist.dao.blacklist;

import java.util.List;

import com.conqueror.blacklist.entity.blacklist.SysRoleEntity;

/**
 * 角色管理
 * 
 */
public interface SysRoleDao extends BaseDao<SysRoleEntity> {
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);
	
	int deleteBatch2(Object[] id);
	int deleteBatch3(Object[] id);
}
