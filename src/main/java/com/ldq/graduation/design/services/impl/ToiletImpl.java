/**
 * 厕所相关操作
 *
 * @author ldq
 * @date 2019-03-27 14:36
 */
package com.ldq.graduation.design.services.impl;

//@Repository("iToiletService")

import com.ldq.graduation.design.dao.ToiletMapper;
import com.ldq.graduation.design.services.IToiletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToiletImpl implements IToiletService {

	@Autowired
	ToiletMapper toiletMapper;

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
}
