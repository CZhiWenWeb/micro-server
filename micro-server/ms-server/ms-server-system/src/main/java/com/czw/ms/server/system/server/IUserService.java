package com.czw.ms.server.system.server;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.czw.ms.common.entity.QueryRequest;
import com.czw.ms.common.entity.system.SystemUser;

/**
 * @Author: czw
 * @CreateTime: 2019-10-14 11:15
 * @UpdeteTime: 2019-10-14 11:15
 * @Description:
 */
public interface IUserService extends IService<SystemUser> {
	/**
	 * 查询用户详细信息
	 * @param user  用户对象用于传递条件
	 * @param request
	 * @return
	 */
	IPage<SystemUser> findUserDetail(SystemUser user, QueryRequest request);

	/**
	 * 新增用户
	 * @param user
	 */
	void createUser(SystemUser user);

	/**
	 * 修改用户
	 * @param user
	 */
	void updateUser(SystemUser user);

	/**
	 * 删除用户
	 * @param userIds
	 */
	void deleteUsers(String... userIds);
}
