package com.czw.ms.server.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czw.ms.common.entity.VueRouter;
import com.czw.ms.common.entity.system.Menu;

import java.util.List;
import java.util.Set;

public interface MenuService extends BaseMapper<Menu> {
	/**
	 * 通过用户名查询权限
	 * @param username username
	 * @return 权限信息
	 */
	Set<String> findUserPermissions(String username);

	/**
	 * 通过用户名创建对应的Vue路由信息
	 * @param username 用户名
	 * @return 路由信息
	 */
	List<VueRouter<Menu>> getUserRouters(String username);
}
