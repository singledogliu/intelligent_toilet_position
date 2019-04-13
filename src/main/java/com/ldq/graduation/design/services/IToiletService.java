/**
 * 厕所相关操作
 *
 * @author ldq
 * @date 2019-04-09 13:10
 */
package com.ldq.graduation.design.services;

import org.springframework.stereotype.Service;

//@Service
public interface IToiletService {
	/**
	 * 管理员注销
	 * @param regionalName		区域名称
	 * @return					受影响的条数
	 */
	int logout(String regionalName);
}
