package com.czw.ms.common.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: czw
 * @CreateTime: 2019-10-14 14:08
 * @UpdeteTime: 2019-10-14 14:08
 * @Description:
 */
@Data
@TableName("t_user_role")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 4283717889384923854L;
	@TableField(value = "USER_ID")
	private Long userId;
	@TableField(value = "ROLE_ID")
	private Long roleId;
}
