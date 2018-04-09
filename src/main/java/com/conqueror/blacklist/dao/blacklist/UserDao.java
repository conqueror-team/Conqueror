package com.conqueror.blacklist.dao.blacklist;

import com.conqueror.blacklist.entity.blacklist.UserEntity;

/**
 * 用户
 * 
 */
public interface UserDao extends BaseDao<UserEntity> {

    UserEntity queryByMobile(String mobile);
}
