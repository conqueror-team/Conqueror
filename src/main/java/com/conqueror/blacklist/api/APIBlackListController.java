package com.conqueror.blacklist.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.conqueror.blacklist.entity.blacklist.BlackListEntity;
import com.conqueror.blacklist.service.BlackListService;
import com.conqueror.blacklist.utils.PageUtils;
import com.conqueror.blacklist.utils.Query;
import com.conqueror.blacklist.utils.R;
import com.conqueror.blacklist.utils.annotation.IgnoreAuth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin
@RequestMapping("/api/apiBlackList")
@Api("黑名单接口")
public class APIBlackListController {
	@Autowired
	private BlackListService blackListService;
	/**
	 * 列表
	 */
	@IgnoreAuth
	@ApiOperation(value = "黑名单列表",notes = "黑名单列表")
	@GetMapping("/list")
	public R list(
			@ApiParam("黑名单名称") @RequestParam(required = false) String name,
			@ApiParam("页数") @RequestParam(defaultValue = "1", required = false) Integer page,
			@ApiParam("条数") @RequestParam(defaultValue = "10", required = false) Integer limit){
		Map<String, Object> params=new HashMap<>();
		params.put("name", name);
		params.put("page", page);
		params.put("limit", limit);
		params.put("sidx", "");
		params.put("order", "desc");
		//查询列表数据
        Query query = new Query(params);

		List<BlackListEntity> blackListList = blackListService.queryList(query);
		int total = blackListService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(blackListList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	@IgnoreAuth
	@ApiOperation(value = "只能根据角色名称查询的黑名单列表",notes = "只能根据角色名称查询的黑名单列表")
	@GetMapping("/queryListByName")
	public R queryListByName(
			@ApiParam("黑名单名称") @RequestParam(required = false) String name,
			@ApiParam("页数") @RequestParam(defaultValue = "1", required = false) Integer page,
			@ApiParam("条数") @RequestParam(defaultValue = "10", required = false) Integer limit){
		Map<String, Object> params=new HashMap<>();
		params.put("name", name);
		params.put("page", page);
		params.put("limit", limit);
		params.put("sidx", "");
		params.put("order", "desc");
		//查询列表数据
        Query query = new Query(params);

		List<BlackListEntity> blackListList = blackListService.queryList2(query);
		int total = blackListService.queryTotal2(query);
		
		PageUtils pageUtil = new PageUtils(blackListList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	/**
	 * 信息
	 */
	@IgnoreAuth
	@GetMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		BlackListEntity blackList = blackListService.queryObject(id);
		
		return R.ok().put("blackList", blackList);
	}
	
}
