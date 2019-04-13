/**
 * 管理员系列操作
 *
 * @author ldq
 * @date 2019-03-26 16:59
 */
package com.ldq.graduation.design.services;

import com.ldq.graduation.design.pojo.AdminInfo;
import org.springframework.stereotype.Service;


public interface IAdminService {
	/**
	 * 根据账号查找记录
	 * @param adminAccount 管理员账号
	 * @return		   查找到的记录
	 */
	AdminInfo selectByaccount(String adminAccount);


	/**
	 * 管理员注册
	 * @param adminAccount		管理员账号
	 * @param adminPassword		管理员密码
	 * @param adminName			管理员姓名
	 * @param adminPhone		管理员电话
	 * @param regionalCode		所负者区域代号
	 * @return					受影响的条数
	 */
	int register(String adminAccount,String adminPassword,String adminName,String adminPhone,String regionalCode);

	/**
	 * 检查账号是否已存在
	 * @param adminAccount		管理员账号
	 * @return					是否存在（1表示存在，0表示不存在）
	 */
	int check(String adminAccount);

	/**
	 * 管理员注销
	 * @param adminAccount		管理员账号
	 * @return					受影响的条数
	 */
	int logout(String adminAccount);
}

