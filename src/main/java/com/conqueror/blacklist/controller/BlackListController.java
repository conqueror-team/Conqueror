package com.conqueror.blacklist.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.conqueror.blacklist.entity.BlackListEntity;
import com.conqueror.blacklist.service.BlackListService;
import com.conqueror.blacklist.utils.PageUtils;
import com.conqueror.blacklist.utils.Query;
import com.conqueror.blacklist.utils.R;
import com.conqueror.blacklist.utils.validator.ValidatorUtils;
import com.conqueror.blacklist.utils.validator.group.AddGroup;
import com.conqueror.blacklist.utils.validator.group.UpdateGroup;


/**
 * 黑名单
 * 
 * @author Sven
 * @email 1050676672@qq.com
 * @date 2018-02-06 21:11:58
 */
@RestController
@RequestMapping("blacklist")
public class BlackListController extends AbstractController {
	@Autowired
	private BlackListService blackListService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
//	@RequiresPermissions("blacklist:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BlackListEntity> blackListList = blackListService.queryList(query);
		int total = blackListService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(blackListList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("blacklist:info")
	public R info(@PathVariable("id") Integer id){
		BlackListEntity blackList = blackListService.queryObject(id);
		
		return R.ok().put("blackList", blackList);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("blacklist:save")
	public R save(@RequestBody BlackListEntity blackList){
		ValidatorUtils.validateEntity(blackList, AddGroup.class);
		blackList.setCreateUserId(getUserId());
		
		blackListService.save(blackList);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("blacklist:update")
	public R update(@RequestBody BlackListEntity blackList){
		ValidatorUtils.validateEntity(blackList, UpdateGroup.class);
		blackListService.update(blackList);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("blacklist:delete")
	public R delete(@RequestBody Integer[] ids){
		blackListService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
