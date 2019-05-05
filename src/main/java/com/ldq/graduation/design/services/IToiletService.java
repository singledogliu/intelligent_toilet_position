/**
 * 厕所相关操作
 *
 * @author ldq
 * @date 2019-04-09 13:10
 */
package com.ldq.graduation.design.services;

import com.ldq.graduation.design.pojo.ToiletPositionInfo;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public interface IToiletService {
	/**
	 * 管理员注销
	 *
	 * @param regionalName 区域名称
	 * @return 受影响的条数
	 */
	int logout(String regionalName);

	/**
	 * 获取某个厕所指定时间段的所有使用数据
	 *
	 * @param regionalName 区域名称
	 * @param toiletCode   厕所代号
	 * @param date         指定时间
	 * @return 某个厕所指定时间段的所有使用数据
	 */
	List<ToiletPositionInfo> getToiletStatistics(String regionalName, String toiletCode, String date);
}
