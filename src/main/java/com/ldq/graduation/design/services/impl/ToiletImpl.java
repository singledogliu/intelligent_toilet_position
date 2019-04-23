/**
 * 厕所相关操作
 *
 * @author ldq
 * @date 2019-03-27 14:36
 */
package com.ldq.graduation.design.services.impl;

//@Repository("iToiletService")

import com.ldq.graduation.design.dao.ToiletMapper;
import com.ldq.graduation.design.dao.ToiletPositionUseMapper;
import com.ldq.graduation.design.pojo.ToiletPositionInfo;
import com.ldq.graduation.design.services.IToiletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToiletImpl implements IToiletService {

	@Autowired
	ToiletMapper toiletMapper;
	@Autowired
	ToiletPositionUseMapper toiletPositionUseMapper;

	/**
	 * 管理员注销
	 *
	 * @param regionalName 区域名称
	 * @return 受影响的条数
	 */
	@Override
	public int logout(String regionalName) {
		int result = toiletMapper.deleteByregionalName(regionalName);
		return result;
	}

	/**
	 * 获取某个厕所指定时间段的所有使用数据
	 *
	 * @param regionalName 区域名称
	 * @param toiletCode   厕所代号
	 * @param date         指定时间
	 * @return 某个厕所指定时间段的所有使用数据
	 */
	@Override
	public List<ToiletPositionInfo> getToiletStatistics(String regionalName, String toiletCode, String date) {
		List<ToiletPositionInfo> toiletStatistics = toiletPositionUseMapper.selectAllByToiletCode(regionalName, toiletCode, date);
		return toiletStatistics;
	}
}
