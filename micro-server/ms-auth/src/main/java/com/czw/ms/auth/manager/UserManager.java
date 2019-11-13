package com.czw.ms.auth.manager;

import com.czw.ms.auth.mapper.MenuMapper;
import com.czw.ms.auth.mapper.UserMapper;
import com.czw.ms.common.entity.system.Menu;
import com.czw.ms.common.entity.system.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: czw
 * @CreateTime: 2019-10-10 11:37
 * @UpdeteTime: 2019-10-10 11:37
 * @Description:
 */
@Service
public class UserManager {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private MenuMapper menuMapper;

	public SystemUser findByName(String username){
		return userMapper.findByName(username);
	}

	public String findUserPermissions(String username){
		List<Menu> userPermisstions=menuMapper.findUserPermissions(username);
		List<String> perms=new ArrayList<>();

		return userPermisstions.stream().map(Menu::getPerms).collect(Collectors.joining(","));

	}
}
