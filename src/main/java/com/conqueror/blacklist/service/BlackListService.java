package com.conqueror.blacklist.service;

import com.conqueror.blacklist.entity.blacklist.BlackListEntity;

import java.util.List;
import java.util.Map;

/**
 * 黑名单
 * 
 * @author Sven
 * @email 1050676672@qq.com
 * @date 2018-02-06 21:11:58
 */
public interface BlackListService {
	
	BlackListEntity queryObject(Integer id);
	
	List<BlackListEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BlackListEntity blackList);
	
	void update(BlackListEntity blackList);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids,String updateUserName);
	
	List<BlackListEntity> queryList2(Map<String, Object> map);
	
	
	int queryTotal2(Map<String, Object> map);
}
