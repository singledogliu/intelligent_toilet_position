/**
 * 厕位信息入库
 *
 * @author ldq
 * @date 2019-03-25 20:08
 */
package com.ldq.graduation.design.services.impl;

import com.ldq.graduation.design.dao.ToiletPositionMapper;
import com.ldq.graduation.design.dao.ToiletPositionUseMapper;
import com.ldq.graduation.design.pojo.ToiletPositionInfo;
import com.ldq.graduation.design.services.IToiletPositionService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.List;


@Service
public class ToiletPositionImpl implements IToiletPositionService {
	@Autowired
	private ToiletPositionUseMapper toiletPositionUseMapper;
	@Autowired
	private ToiletPositionMapper toiletPositionMapper;

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
	 * @param duration             开始使用时间
	 * @return 受影响的条数
	 */
	@Override
	public int update(String regionalName, String toiletCode, String gender, String toiletPositionCode, Timestamp endTime, Timestamp startTime, Long duration) {
		int result = toiletPositionUseMapper.update(regionalName, toiletCode, gender, toiletPositionCode, endTime, startTime, duration);
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

	/**
	 * 添加厕位信息
	 *
	 * @param regionalName 区域名称
	 * @param ToiletInfo   厕所信息
	 * @return 添加结果
	 */
	@Override
	public int add(String regionalName, JSONArray ToiletInfo) {
		int resultTotal = 0;
		int result = 0;
		String men = "男";
		String women = "女";
		for (int i = 0; i < ToiletInfo.size(); i++) {
//			添加男性厕位数据
			for (int j = 0; j < ToiletInfo.getJSONObject(i).getInt("menToiletPositionCounter"); j++) {
				result = toiletPositionMapper.insert(regionalName, ToiletInfo.getJSONObject(i).getString("toiletCode"), men, j + 1);
				resultTotal += result;
			}
//			添加女性厕位数据
			for (int k = 0; k < ToiletInfo.getJSONObject(i).getInt("womenToiletPositionCounter"); k++) {
				result = toiletPositionMapper.insert(regionalName, ToiletInfo.getJSONObject(i).getString("toiletCode"), women, k + 1);
				resultTotal += result;
			}
		}
		return resultTotal;
	}

	/**
	 * 获取指定厕所的厕位信息
	 *
	 * @param regionalName 区域名称
	 * @param toiletCode   厕所代号
	 * @return 厕位信息
	 */
	@Override
	public List<ToiletPositionInfo> getToiletPositionInfo(String regionalName, String toiletCode) {
		List<ToiletPositionInfo> toiletPositionInfos = toiletPositionMapper.select(regionalName, toiletCode);
		return toiletPositionInfos;
	}


}
