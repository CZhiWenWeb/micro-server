package com.czw.ms.server.system.server;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czw.ms.common.entity.system.UserRole;

/**
 * @Author: czw
 * @CreateTime: 2019-10-14 14:22
 * @UpdeteTime: 2019-10-14 14:22
 * @Description:
 */
public interface IUserRoleService extends IService<UserRole> {
	void deleteUserRolesByRoleId(String... roleIds);

	void deleteUserRolesByUserId(String... userIds);
}
