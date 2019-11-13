package com.czw.ms.server.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.czw.ms.common.entity.MsResponse;
import com.czw.ms.common.entity.QueryRequest;
import com.czw.ms.common.entity.system.SystemUser;
import com.czw.ms.common.utils.FebsUtil;
import com.czw.ms.server.system.server.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @Author: czw
 * @CreateTime: 2019-10-14 14:53
 * @UpdeteTime: 2019-10-14 14:53
 * @Description:
 */
@RestController
@Slf4j
@Validated
@RequestMapping("user")
	public class UserController {
	@Autowired
	private IUserService userService;

	@GetMapping
	@PreAuthorize("hasAnyAuthority('user:view')")
	public MsResponse userList(QueryRequest queryRequest, SystemUser user){
		Map<String,Object> dataTable= FebsUtil.getDataTable(userService.findUserDetail(user,queryRequest));
		return new MsResponse().data(dataTable);
	}
	@PostMapping
	@PreAuthorize("hasAnyAuthority('user:add')")
	public void addUser(@Valid SystemUser user){
		this.userService.createUser(user);
	}
	@PutMapping
	@PreAuthorize("hasAnyAuthority('user:update')")
	public void updateUser(@Valid SystemUser user){
		this.userService.updateUser(user);
	}
	@DeleteMapping("/{userIds}")
	@PreAuthorize("hasAnyAuthority('user:delete')")
	public void deleteUsers(@NotBlank(message = "{required}") @PathVariable String userIds){
		String[] ids=userIds.split(StringPool.COMMA);
		this.userService.deleteUsers(ids);
	}
}
