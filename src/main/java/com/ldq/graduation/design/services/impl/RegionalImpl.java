/**
 * 区域相关操作
 *
 * @author ldq
 * @date 2019-03-27 14:34
 */
package com.ldq.graduation.design.services.impl;

import com.ldq.graduation.design.dao.RegionalMapper;
import com.ldq.graduation.design.dao.ToiletPositionUseMapper;
import com.ldq.graduation.design.pojo.ToiletPositionInfo;
import com.ldq.graduation.design.services.IRegionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionalImpl implements IRegionalService {
	@Autowired
	RegionalMapper regionalMapper;
	@Autowired
	ToiletPositionUseMapper toiletPositionUseMapper;

	/**
	 * 增加一条区域信息
	 *
	 * @param regionalName            区域名称
	 * @param regionalResponsibleName 负责人姓名
	 * @return 受影响的条数
	 */
	@Override
	public int add(String regionalName, String regionalResponsibleName) {
		int result = regionalMapper.insert(regionalName, regionalResponsibleName);
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
		int result = regionalMapper.deleteByregionalName(regionalName);
		return result;
	}

	/**
	 * 查询某个区域某个时间段内所有厕位使用数据
	 *
	 * @param regionalName 区域名称
	 * @param date         时间段
	 * @return 所有厕位使用数据
	 */
	@Override
	public List<ToiletPositionInfo> getRegionalStatistics(String regionalName, String date) {
		List<ToiletPositionInfo> regionalStatistics = toiletPositionUseMapper.selectAllByRegionalName(regionalName, date);
		return regionalStatistics;
	}
}
