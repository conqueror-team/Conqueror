package com.conqueror.blacklist.service.community;

import java.util.List;
import java.util.Map;

import com.conqueror.blacklist.entity.community.ColonelInfoEntity;
import com.conqueror.blacklist.utils.Query;

/**
 * 团长信息
 * 
 * @author Sven
 * @email 1050676672@qq.com
 * @date 2018-03-30 17:02:21
 */
public interface ColonelInfoService {
	
	ColonelInfoEntity queryObject(Integer id);
	
	List<ColonelInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ColonelInfoEntity colonelInfo);
	
	void update(ColonelInfoEntity colonelInfo);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<ColonelInfoEntity> queryList2(Map<String, Object> map);

    void expelBatch(Integer[] ids,String name);

    void warningBatch(Integer[] ids, String name);

	void cancelWarningBatch(Integer[] ids, String name);
}
