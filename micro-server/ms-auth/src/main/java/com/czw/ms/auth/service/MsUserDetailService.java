package com.czw.ms.auth.service;

import com.czw.ms.auth.manager.UserManager;
import com.czw.ms.common.entity.MsAuthUser;
import com.czw.ms.common.entity.system.SystemUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author: czw
 * @CreateTime: 2019-10-08 08:40
 * @UpdeteTime: 2019-10-08 08:40
 * @Description:
 */
@Service
public class MsUserDetailService implements UserDetailsService {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserManager userManager;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SystemUser systemUser = userManager.findByName(username);
		if (systemUser != null) {
			String permissions = userManager.findUserPermissions(systemUser.getUsername());
			boolean notLocked = false;
			if (StringUtils.equals(SystemUser.STATUS_VALID, systemUser.getStatus()))
				notLocked = true;
			MsAuthUser authUser = new MsAuthUser(systemUser.getUsername(), systemUser.getPassword(),
					true, true, true, notLocked, AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
			BeanUtils.copyProperties(systemUser, authUser);
			return authUser;
		} else {
			throw new UsernameNotFoundException("");
		}

	}
}
