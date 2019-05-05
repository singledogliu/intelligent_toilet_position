/**
 * 厕位信息入库
 *
 * @author ldq
 * @date 2019-03-25 20:08
 */
package com.ldq.graduation.design.services.impl;

import com.ldq.graduation.design.dao.ToiletPositionUseMapper;
import com.ldq.graduation.design.services.IToiletPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;


@Service
public class ToiletPositionImpl implements IToiletPositionService {
	@Autowired
	private ToiletPositionUseMapper toiletPositionUseMapper;

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
	@Override
	public int insert(Timestamp StartTime, String ToiletPositionCode, String Gender, String ToiletCode, String RegionalName) {
		int result = toiletPositionUseMapper.insert(StartTime, ToiletPositionCode, Gender, ToiletCode, RegionalName);
		return result;
	}

	/**
	 * 厕位信息更新入库
	 *
	 * @param regionalName       区域名称
	 * @param toiletCode         厕所代号
	 * @param gender             性别
	 * @param toiletPositionCode 厕位编号
	 * @param endTime            结束使用时间
	 * @param duration           使用时长
	 * @return 受影响的条数
	 */
	@Override
	public int update(String regionalName, String toiletCode, String gender, String toiletPositionCode, Timestamp endTime, Long duration) {
		int result = toiletPositionUseMapper.update(regionalName, toiletCode, gender, toiletPositionCode, endTime, duration);
		return result;
	}

	/**
	 * 管理员注销
	 *
	 * @param regionalName 区域名称
	 * @return 受影响的条数
	 */
	@Override
	public int logout(String regionalName) {
		int result = toiletPositionUseMapper.deleteByregionalName(regionalName);
		return result;
	}

	/**
	 * 获取开始时间
	 *
	 * @param regionalName       区域名称
	 * @param toiletCode         厕所代号
	 * @param gender             性别
	 * @param toiletPositionCode 厕位编号
	 * @return 开始时间
	 */
	@Override
	public Timestamp getStartTime(String regionalName, String toiletCode, String gender, String toiletPositionCode) {
		Timestamp startTime = toiletPositionUseMapper.selectStartTime(regionalName, toiletCode, gender, toiletPositionCode);
		return startTime;
	}


}
