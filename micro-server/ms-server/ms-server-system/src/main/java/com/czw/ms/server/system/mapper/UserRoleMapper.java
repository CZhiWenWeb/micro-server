package com.czw.ms.server.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czw.ms.common.entity.system.UserRole;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: czw
 * @CreateTime: 2019-10-14 14:13
 * @UpdeteTime: 2019-10-14 14:13
 * @Description:
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {
	/**
	 * 根据用户Id删除用户的角色关系
	 * @param userId
	 * @return
	 */
	Boolean deleteByUserId(@Param("userId") Long userId);

	/**
	 * 根据角色Id删除角色的用户关系
	 * @param roleId
	 * @return
	 */
	Boolean deleteByRoleId(@Param("roleId") Long roleId);
}
