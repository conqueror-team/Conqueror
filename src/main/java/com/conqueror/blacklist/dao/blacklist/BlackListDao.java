package com.conqueror.blacklist.dao.blacklist;

import com.conqueror.blacklist.entity.blacklist.BlackListEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 黑名单
 * 
 * @author Sven
 * @email 1050676672@qq.com
 * @date 2018-02-06 21:11:58
 */
public interface BlackListDao extends BaseDao<BlackListEntity> {
	List<BlackListEntity> queryList2(Map<String, Object> map);

	int deleteBatch2(@Param("ids") Object[] id,@Param("updateUserName") String updateUserName);
	int queryTotal2(Map<String, Object> map);
}
