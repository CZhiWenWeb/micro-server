package com.czw.ms.common.selector;

import com.czw.ms.common.configure.MsAuthExceptionConfigure;
import com.czw.ms.common.configure.MsOAuth2FeignConfigure;
import com.czw.ms.common.configure.MsServerProtectConfigure;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: czw
 * @CreateTime: 2019-10-09 13:54
 * @UpdeteTime: 2019-10-09 13:54
 * @Description:在spring中，要将多个类进行注册，可以使用selector的方式
 */
public class MsCloudApplicationSelector implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata annotationMetadata) {
		return new String[]{
				MsAuthExceptionConfigure.class.getName(),
				MsOAuth2FeignConfigure.class.getName(),
				MsServerProtectConfigure.class.getName()
		};
	}
}
