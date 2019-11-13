package com.czw.ms.common.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: czw
 * @CreateTime: 2019-10-14 11:19
 * @UpdeteTime: 2019-10-14 11:19
 * @Description:
 */
@Data
@ToString
public class QueryRequest implements Serializable {
	private static final long serialVersionUID = -7495553135705795249L;
	/**
	 * 当前页面数据量
	 */
	private int pageSize=10;

	private int pageNum=1;

	private String field;
	/**
	 * 排序规则，asc升序，desc降序
	 */
	private String order;
}
