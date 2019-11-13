package com.czw.ms.server.system.configure;

import com.czw.ms.common.utils.DateUtil;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 * @Author: czw
 * @CreateTime: 2019-10-14 10:25
 * @UpdeteTime: 2019-10-14 10:25
 * @Description:
 */
public class P6spySqlFormatConfigure implements MessageFormattingStrategy {
	@Override
	public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
		return StringUtils.isNotBlank(sql) ? DateUtil.formatFullTime(LocalDateTime.now(), DateUtil.FULL_TIME_SPLIT_PATTERN)
				+ " | 耗时 " + elapsed + " ms | SQL 语句：" + StringUtils.LF + sql.replaceAll("[\\s]+", StringUtils.SPACE) + ";" : StringUtils.EMPTY;
	}
}
