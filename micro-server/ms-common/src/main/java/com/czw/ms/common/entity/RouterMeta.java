package com.czw.ms.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: czw
 * @CreateTime: 2019-11-12 13:58
 * @UpdeteTime: 2019-11-12 13:58
 * @Description:
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouterMeta implements Serializable {
	private static final long serialVersionUID = -3851410171863413783L;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 图标
	 */
	private String icon;
}
