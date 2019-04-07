/**
 * 区域相关操作
 *
 * @author ldq
 * @date 2019-03-27 14:33
 */
package com.ldq.graduation.design.services;

import com.ldq.graduation.design.pojo.RegionalInfo;
import org.springframework.stereotype.Service;


public interface IRegionalService {
	/**
	 * 增加一条区域记录
	 * @param regionalInfo	区域信息
	 * @return				受影响条数
	 */
	int addRegional(RegionalInfo regionalInfo);
}
