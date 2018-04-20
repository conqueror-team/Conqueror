package com.conqueror.blacklist.service.community.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conqueror.blacklist.dao.community.ColonelInfoDao;
import com.conqueror.blacklist.entity.community.ColonelInfoEntity;
import com.conqueror.blacklist.service.community.ColonelInfoService;



@Service("colonelInfoService")
public class ColonelInfoServiceImpl implements ColonelInfoService {
	@Autowired
	private ColonelInfoDao colonelInfoDao;
	
	@Override
	public ColonelInfoEntity queryObject(Integer id){
		return colonelInfoDao.queryObject(id);
	}
	
	@Override
	public List<ColonelInfoEntity> queryList(Map<String, Object> map){
		return colonelInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return colonelInfoDao.queryTotal(map);
	}
	
	@Override
	public void save(ColonelInfoEntity colonelInfo){
		colonelInfoDao.save(colonelInfo);
	}
	
	@Override
	public void update(ColonelInfoEntity colonelInfo){
		colonelInfoDao.update(colonelInfo);
	}
	
	@Override
	public void delete(Integer id){
		colonelInfoDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		colonelInfoDao.deleteBatch(ids);
	}

	@Override
	public List<ColonelInfoEntity> queryList2(Map<String, Object> map) {
		return colonelInfoDao.queryList2(map);
	}

}
