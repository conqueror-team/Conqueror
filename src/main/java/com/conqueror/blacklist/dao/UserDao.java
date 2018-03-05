package com.conqueror.blacklist.dao;

import com.conqueror.blacklist.entity.UserEntity;

/**
 * 用户
 * 
 */
public interface UserDao extends BaseDao<UserEntity> {

    UserEntity queryByMobile(String mobile);
}
