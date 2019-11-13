package com.czw.ms.server.system.server.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czw.ms.common.entity.system.UserRole;
import com.czw.ms.server.system.mapper.UserRoleMapper;
import com.czw.ms.server.system.server.IUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * @Author: czw
 * @CreateTime: 2019-10-14 14:25
 * @UpdeteTime: 2019-10-14 14:25
 * @Description:
 */
@Service("userRoleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserRoleService extends ServiceImpl<UserRoleMapper,UserRole> implements IUserRoleService {
	@Override
	@Transactional
	public void deleteUserRolesByRoleId(String... roleIds) {
		Arrays.stream(roleIds).forEach(id->baseMapper.deleteByRoleId(Long.valueOf(id)));
	}

	@Override
	@Transactional
	public void deleteUserRolesByUserId(String... userIds) {
		Arrays.stream(userIds).forEach(id->baseMapper.deleteByUserId(Long.valueOf(id)));
	}
}
