/**
 * 厕所相关操作
 *
 * @author ldq
 * @date 2019-04-09 13:10
 */
package com.ldq.graduation.design.services;

import com.ldq.graduation.design.pojo.ToiletInfo;
import com.ldq.graduation.design.pojo.ToiletPositionUseInfo;
import net.sf.json.JSONArray;

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
	 * @param endDate      结束时间时间
	 * @param startDate       开始时间时间
	 * @return 某个厕所指定时间段的所有使用数据
	 */
	List getToiletStatistics(String regionalName, String toiletCode, String startDate, String endDate, String action, String unit);

	/**
	 * 添加厕所信息
	 *
	 * @param regionalName
	 * @param ToiletInfo
	 * @return
	 */
	int add(String regionalName, JSONArray ToiletInfo);

	/**
	 * 获取指定管理员负责的厕所信息
	 *
	 * @param regionalName 管理员账号
	 * @return 查询到的数据
	 */
	List<ToiletInfo> getToiletInfo(String regionalName);

}


