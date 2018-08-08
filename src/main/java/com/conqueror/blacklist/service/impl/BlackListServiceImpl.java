package com.conqueror.blacklist.service.impl;

import com.conqueror.blacklist.dao.blacklist.BlackListDao;
import com.conqueror.blacklist.entity.blacklist.BlackListEntity;
import com.conqueror.blacklist.service.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;



@Service("blackListService")
public class BlackListServiceImpl implements BlackListService {
	@Autowired
	private BlackListDao blackListDao;
	@Override
	public BlackListEntity queryObject(Integer id){
		return blackListDao.queryObject(id);
	}
	
	@Override
	public List<BlackListEntity> queryList(Map<String, Object> map){
		return blackListDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return blackListDao.queryTotal(map);
	}
	
	@Override
	public void save(BlackListEntity blackList){
		blackList.setCreateTime(new Date());
		blackListDao.save(blackList);
	}
	
	@Override
	public void update(BlackListEntity blackList){
		blackListDao.update(blackList);
	}
	
	@Override
	public void delete(Integer id){
		blackListDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids,String updateUserName){
		blackListDao.deleteBatch2(ids,updateUserName);
	}

	@Override
	public List<BlackListEntity> queryList2(Map<String, Object> map) {
		return blackListDao.queryList2(map);
	}

	@Override
	public int queryTotal2(Map<String, Object> map) {
		return blackListDao.queryTotal2(map);
	}
	
}
