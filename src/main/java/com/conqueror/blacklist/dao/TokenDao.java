package com.conqueror.blacklist.dao;

import com.conqueror.blacklist.entity.TokenEntity;

/**
 * 用户Token
 * 
 */
public interface TokenDao extends BaseDao<TokenEntity> {
    
    TokenEntity queryByUserId(Long userId);

    TokenEntity queryByToken(String token);
	
}
