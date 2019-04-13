/**
 * 区域相关操作
 *
 * @author ldq
 * @date 2019-03-27 14:33
 */
package com.ldq.graduation.design.services;

import com.ldq.graduation.design.pojo.RegionalInfo;
import com.ldq.graduation.design.pojo.ToiletPositionInfo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IRegionalService {

	/**
	 * 增加一条区域信息
	 * @param regionalName				区域名称
	 * @param regionalResponsibleName	负责人姓名
	 * @return							受影响的条数
	 */
	int add(String regionalName,String regionalResponsibleName);

	/**
	 * 管理员注销
	 * @param regionalName		区域名称
	 * @return					受影响的条数
	 */
	int logout(String regionalName);

	/**
	 * 查询某个区域某个时间段内所有厕位使用数据
	 * @param regionalName		区域名称
	 * @param date				时间段
	 * @return					所有厕位使用数据
	 */
	List<ToiletPositionInfo> getRegionalStatistics(String regionalName,String date);
}
