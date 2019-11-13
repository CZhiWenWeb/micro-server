package com.czw.ms.server.system.properties;

import lombok.Data;

/**
 * @Author: czw
 * @CreateTime: 2019-10-15 08:46
 * @UpdeteTime: 2019-10-15 08:46
 * @Description:
 */
@Data
public class MsSwaggerProperties {
	private String basePackage;
	private String title;
	private String description;
	private String version;
	private String author;
	private String url;
	private String email;
	private String license;
	private String licenseUrl;
	private String grantUrl;
	private String name;
	private String scope;
}
