package com.czw.ms.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czw.ms.common.entity.system.Menu;

import java.util.List;

/**
 * @Author: czw
 * @CreateTime: 2019-10-10 11:27
 * @UpdeteTime: 2019-10-10 11:27
 * @Description:
 */
public interface MenuMapper extends BaseMapper<Menu> {
	List<Menu> findUserPermissions(String username);
}
