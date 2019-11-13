package com.czw.ms.common.entity;

import java.util.HashMap;

/**
 * @Author: czw
 * @CreateTime: 2019-10-08 08:55
 * @UpdeteTime: 2019-10-08 08:55
 * @Description:
 */
public class MsResponse extends HashMap<String,Object> {

	private static final long serialVersionUID = -3618665642374022007L;

	public MsResponse message(String message){
		this.put("message",message);
		return this;
	}
	public MsResponse data(Object data){
		this.put("data",data);
		return this;
	}
	@Override
	public MsResponse put(String key,Object value){
		super.put(key,value);
		return this;
	}

	public String getMessage(){
		return String.valueOf(get("message"));
	}
	public Object getData(){
		return get("data");
	}
}
