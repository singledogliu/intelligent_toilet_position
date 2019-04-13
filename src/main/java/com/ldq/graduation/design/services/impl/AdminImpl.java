/**
 * 管理员相关操作
 *
 * @author ldq
 * @date 2019-03-26 21:22
 */
package com.ldq.graduation.design.services.impl;

import com.ldq.graduation.design.dao.AdminMapper;
import com.ldq.graduation.design.pojo.AdminInfo;
import com.ldq.graduation.design.services.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminImpl implements IAdminService {
	@Autowired
	private AdminMapper adminMapper;
	/**
	 * 根据账号查找记录
	 *
	 * @param adminAccount 管理员账号
	 * @return 查找到的记录
	 */
	@Override
	public AdminInfo selectByaccount(String adminAccount) {
		AdminInfo adminInfo = adminMapper.selectByaccount(adminAccount);
		return adminInfo;
	}

	/**
	 * 管理员注册
	 * @param adminAccount  管理员账号
	 * @param adminPassword 管理员密码
	 * @param adminName     管理员姓名
	 * @param adminPhone    管理员电话
	 * @param regionalCode  所负者区域代号
	 * @return 受影响的条数
	 */
	@Override
	public int register(String adminAccount, String adminPassword, String adminName, String adminPhone, String regionalCode) {
		int result = adminMapper.insert(adminAccount,adminPassword,adminName,adminPhone,regionalCode);
		return result;
	}

	/**
	 * 检查账号是否已存在
	 *
	 * @param adminAccount 	管理员账号
	 * @return 				是否存在（1表示存在，0表示不存在）
	 */
	@Override
	public int check(String adminAccount) {
		int result = adminMapper.check(adminAccount);
		return result;
	}

	/**
	 * 管理员注销
	 *
	 * @param adminAccount 管理员账号
	 * @return 受影响的条数
	 */
	@Override
	public int logout(String adminAccount) {
		int result = adminMapper.deleteByaccount(adminAccount);
		return result;
	}


}
