package com.conqueror.blacklist.dao.blacklist;

import com.conqueror.blacklist.entity.blacklist.TokenEntity;

/**
 * 用户Token
 * 
 */
public interface TokenDao extends BaseDao<TokenEntity> {
    
    TokenEntity queryByUserId(Long userId);

    TokenEntity queryByToken(String token);
	
}
