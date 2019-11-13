package com.czw.ms.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czw.ms.common.entity.system.SystemUser;

/**
 * @Author: czw
 * @CreateTime: 2019-10-10 11:25
 * @UpdeteTime: 2019-10-10 11:25
 * @Description:
 */
public interface UserMapper extends BaseMapper<SystemUser> {
	SystemUser findByName(String username);
}
