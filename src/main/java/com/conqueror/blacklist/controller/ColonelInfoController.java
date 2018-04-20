package com.conqueror.blacklist.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.conqueror.blacklist.utils.validator.ValidatorUtils;
import com.conqueror.blacklist.utils.validator.group.AddGroup;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.conqueror.blacklist.entity.community.ColonelInfoEntity;
import com.conqueror.blacklist.service.community.ColonelInfoService;
import com.conqueror.blacklist.utils.PageUtils;
import com.conqueror.blacklist.utils.Query;
import com.conqueror.blacklist.utils.R;
import com.conqueror.blacklist.utils.annotation.SysLog;


/**
 * 团长信息
 * 
 * @author Sven
 * @email 1050676672@qq.com
 * @date 2018-03-30 17:02:21
 */
@RestController
@RequestMapping("colonelinfo")
public class ColonelInfoController extends AbstractController{
	@Autowired
	private ColonelInfoService colonelInfoService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("colonelinfo:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);

		List<ColonelInfoEntity> colonelInfoList = colonelInfoService.queryList(query);
		int total = colonelInfoService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(colonelInfoList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 不需要登录查询
	 * @param params
	 * @return
	 */
	@RequestMapping("/searchList")
	public R searchList(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);

		List<ColonelInfoEntity> colonelInfoList = colonelInfoService.queryList2(query);
		int total = colonelInfoService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(colonelInfoList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("colonelinfo:info")
	public R info(@PathVariable("id") Integer id){
		ColonelInfoEntity colonelInfo = colonelInfoService.queryObject(id);
		
		return R.ok().put("colonelInfo", colonelInfo);
	}
	
	/**
	 * 保存
	 */
	@SysLog("团长信息保存")
	@RequestMapping("/save")
	@RequiresPermissions("colonelinfo:save")
	public R save(@RequestBody ColonelInfoEntity colonelInfo){
		ValidatorUtils.validateEntity(colonelInfo, AddGroup.class);
		colonelInfo.setCreateUserId(getUser().getUsername());
		colonelInfo.setCreateDate(new Date());
		colonelInfoService.save(colonelInfo);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLog("团长信息修改")
	@RequestMapping("/update")
	@RequiresPermissions("colonelinfo:update")
	public R update(@RequestBody ColonelInfoEntity colonelInfo){
		ValidatorUtils.validateEntity(colonelInfo, AddGroup.class);
		colonelInfo.setLastUpdateUserId(getUser().getUsername());
		colonelInfo.setLastUpdateDate(new Date());
		colonelInfoService.update(colonelInfo);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SysLog("团长信息删除")
	@RequestMapping("/delete")
	@RequiresPermissions("colonelinfo:delete")
	public R delete(@RequestBody Integer[] ids){
		colonelInfoService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
