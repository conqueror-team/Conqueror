package com.conqueror.blacklist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.conqueror.blacklist.dao.BlackListDao;
import com.conqueror.blacklist.entity.BlackListEntity;
import com.conqueror.blacklist.service.BlackListService;



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
	public void deleteBatch(Integer[] ids){
		blackListDao.deleteBatch(ids);
	}
	
}
