/**
 * 区域相关操作
 *
 * @author ldq
 * @date 2019-03-27 14:34
 */
package com.ldq.graduation.design.services.impl;

import com.ldq.graduation.design.dao.RegionalMapper;
import com.ldq.graduation.design.pojo.RegionalInfo;
import com.ldq.graduation.design.services.IRegionalService;
import org.springframework.stereotype.Service;

//@Repository("iRegionalService")
@Service
public class RegionalServiceImpl implements IRegionalService {
	RegionalMapper regionalMapper;
	/**
	 * 增加一条区域记录
	 *
	 * @param regionalInfo 区域信息
	 * @return 受影响条数
	 */
	@Override
	public int addRegional(RegionalInfo regionalInfo) {
		int result = regionalMapper.add(regionalInfo);
		return result;
	}
}
