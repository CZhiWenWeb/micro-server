package com.czw.ms.auth.properties;

import lombok.Data;

/**
 * @Author: czw
 * @CreateTime: 2019-10-08 15:08
 * @UpdeteTime: 2019-10-08 15:08
 * @Description:
 */
@Data
public class MsClientsProperties {
	private String client;
	private String secret;
	private String grantType="password,authorization_code,refresh_token";
	private String scope="all";
}
