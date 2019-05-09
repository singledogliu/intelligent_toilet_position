/**
 * 厕位信息入库
 *
 * @author ldq
 * @date 2019-03-25 20:02
 */
package com.ldq.graduation.design.services;

import com.ldq.graduation.design.pojo.ToiletPositionInfo;
import com.ldq.graduation.design.pojo.ToiletPositionUseInfo;
import net.sf.json.JSONArray;

import java.sql.Timestamp;
import java.util.List;


public interface IToiletPositionService {

	/**
	 * 插入一条新的厕位使用记录
	 *
	 * @param StartTime          开始使用时间
	 * @param ToiletPositionCode 厕位编号
	 * @param Gender             性别
	 * @param ToiletCode         厕所代号
	 * @param RegionalName       所在区域名称
	 * @return 受影响条数
	 */
	int insert(Timestamp StartTime, String ToiletPositionCode, String Gender, String ToiletCode, String RegionalName);

	/**
	 * 厕位信息更新入库
	 *
	 * @param regionalName       区域名称
	 * @param toiletCode         厕所代号
	 * @param gender             性别
	 * @param toiletPositionCode 厕位编号
	 * @param endTime            结束使用时间
	 * @param startTime             开始使用时间
	 * @param duration             使用时长
	 * @return 受影响的条数
	 */
	int update(String regionalName, String toiletCode, String gender, String toiletPositionCode, Timestamp endTime, Timestamp startTime, Long duration);

	/**
	 * 管理员注销
	 *
	 * @param regionalName 区域名称
	 * @return 受影响的条数
	 */
	int logout(String regionalName);


	/**
	 * 添加厕位信息
	 *
	 * @param regionalName 区域名称
	 * @param ToiletInfo   厕所信息
	 * @return 添加结果
	 */
	int add(String regionalName, JSONArray ToiletInfo);

	/**
	 * 获取指定厕所的厕位信息
	 * @param regionalName 区域名称
	 * @param toiletCode   厕所代号
	 * @return 厕位信息
	 */
	List<ToiletPositionInfo> getToiletPositionInfo(String regionalName, String toiletCode);

	/**
	 * 查询指定厕所当前的厕位使用情况
	 *
	 * @param regionalName 区域名称
	 * @param toiletCode   厕所代号
	 * @return 查询到的数据
	 */
	List<ToiletPositionUseInfo> getCurrentUseInfo(String regionalName, String toiletCode);

}

