package com.conqueror.blacklist.dao.community;

import com.conqueror.blacklist.dao.blacklist.BaseDao;
import com.conqueror.blacklist.entity.community.ColonelInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 团长信息
 * 
 * @author Sven
 * @email 1050676672@qq.com
 * @date 2018-03-30 17:02:21
 */
public interface ColonelInfoDao extends BaseDao<ColonelInfoEntity> {

    List<ColonelInfoEntity> queryList2(Map<String, Object> map);
}
