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
	 *
	 * @param adminAccount  管理员账号
	 * @param adminPassword 管理员密码
	 * @param adminName     管理员姓名
	 * @param adminPhone    管理员电话
	 * @param regionalCode  所负者区域代号
	 * @return 受影响的条数
	 */
	@Override
	public int register(String adminAccount, String adminPassword, String adminName, String adminPhone, String regionalCode) {
		int result = adminMapper.insert(adminAccount, adminPassword, adminName, adminPhone, regionalCode);
		return result;
	}

	/**
	 * 检查账号是否已存在
	 *
	 * @param adminAccount 管理员账号
	 * @return 是否存在（1表示存在，0表示不存在）
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

	/**
	 * 修改管理员信息
	 *
	 * @param oldAdminAccount 原管理员账号
	 * @param adminAccount    新管理员账号
	 * @param adminName       管理员名称
	 * @param adminPassword   管理员密码
	 * @param adminPhone      管理员电话
	 * @return 修改结果（受影响条数）
	 */
	@Override
	public int modifyInformation(String oldAdminAccount, String adminAccount, String adminName, String adminPassword, String adminPhone) {
		int result = 0;
		if (oldAdminAccount.equals(adminAccount)) {
			result = adminMapper.updateButAccount(oldAdminAccount, adminName, adminPassword, adminPhone);
		}
		if (!oldAdminAccount.equals(adminAccount) && oldAdminAccount != null && adminAccount != null) {
			result = adminMapper.update(oldAdminAccount, adminAccount, adminName, adminPassword, adminPhone);
		}
		return result;
	}

	/**
	 * 修改电话
	 *
	 * @param adminPhone 新管理员电话
	 * @return 修改结果（受影响条数）
	 */
	@Override
	public int modifyPhone(String oldAdminAccount, String adminPhone) {
		int result = adminMapper.updatePhone(oldAdminAccount, adminPhone);
		return result;
	}

	/**
	 * 修改管理员密码
	 *
	 * @param adminAccount     管理员账号
	 * @param oldAdminPassword 原管理员密码
	 * @param adminPassword    新管理员密码
	 * @return 修改结果（受影响条数）
	 */
	@Override
	public int modifyPassword(String adminAccount, String oldAdminPassword, String adminPassword) {
		int result = adminMapper.updatePassword(adminAccount, oldAdminPassword, adminPassword);
		return result;
	}

	/**
	 * 修改管理员账号
	 *
	 * @param oldAdminAccount 原管理员账号
	 * @param adminAccount    新管理员账号
	 * @return 受影响的条数
	 */
	@Override
	public int modifyAccount(String oldAdminAccount, String adminAccount) {
		int result = adminMapper.updateAccount(oldAdminAccount, adminAccount);
		return result;
	}


}
