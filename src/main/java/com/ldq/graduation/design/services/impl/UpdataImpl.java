/**
 * 厕位信息入库
 *
 * @author ldq
 * @date 2019-03-25 20:08
 */
package com.ldq.graduation.design.services.impl;

import com.ldq.graduation.design.dao.ToiletPositionMapper;
import com.ldq.graduation.design.pojo.ToiletPositionInfo;
import com.ldq.graduation.design.services.IUpdataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;


@Service
public class UpdataImpl implements IUpdataService {
	@Autowired
	private ToiletPositionMapper toiletPositionMapper;

	/**
	 * 插入一条新的厕位使用记录
	 *
	 * @param StartTime          开始使用时间
	 * @param ToiletPositionCode 厕位编号
	 * @param Gender             性别
	 * @param ToiletCode         厕所代号
	 * @param RegionalCode       所在区域代号
	 * @return 受影响条数
	 */
	@Override
	public int insert(Timestamp StartTime, String ToiletPositionCode, Boolean Gender, String ToiletCode, String RegionalCode) {
		int result = toiletPositionMapper.insert(StartTime,ToiletPositionCode,Gender,ToiletCode,RegionalCode);
		return result;
	}

	/**
	 * 厕位信息更新入库
	 * @param regionalCode       区域代号
	 * @param toiletCode         厕所代号
	 * @param gender             性别
	 * @param toiletPositionCode 厕位编号
	 * @param endTime            结束使用时间
	 * @return 					 受影响的条数
	 */
	@Override
	public int update(String regionalCode, String toiletCode, boolean gender, String toiletPositionCode,  Timestamp endTime) {
		Integer result = toiletPositionMapper.update(regionalCode,toiletCode,gender,toiletPositionCode,endTime);
		int resultNum;
		if (result == null){
			resultNum = 0;
		}
		else {
			resultNum = result;
		}
		return resultNum;
	}


}
