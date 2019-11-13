package com.czw.ms.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: czw
 * @CreateTime: 2019-11-12 14:02
 * @UpdeteTime: 2019-11-12 14:02
 * @Description:
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VueRouter<T> implements Serializable {
	private static final long serialVersionUID = 634597116601458913L;
	//生成json时排除
	@JsonIgnore
	private String id;
	@JsonIgnore
	private String parentId;
	/**
	 * 路由名称
	 */
	private String name;
	/**
	 * 对应路由组件component
	 */
	private String component;
	/**
	 * 重定向地址
	 */
	private String redirect;
	/**
	 * 路由元信息
	 */
	private RouterMeta meta;
	/**
	 * 是否渲染在菜单上
	 */
	private Boolean hidden=false;
	/**
	 * 是否一直显示跟路由
	 */
	private Boolean alwaysShow=false;
	/**
	 * 子路由
	 */
	private List<VueRouter<T>> children;
	@JsonIgnore
	private Boolean hasParent=false;
	@JsonIgnore
	private Boolean hasChildren=false;

	public void initChildren(){
		this.children=new ArrayList<>();
	}
}
