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

//@Repository("iAdminService")
@Service
public class AdminImpl implements IAdminService {
	@Autowired
	private AdminMapper adminMapper;
	/**
	 * 根据账号查找记录
	 *
	 * @param account 管理员账号
	 * @return 查找到的记录
	 */
	@Override
	public AdminInfo selectByaccount(String account) {
		AdminInfo adminInfo = adminMapper.selectByaccount(account);
		return adminInfo;
	}

	/**
	 * 管理员注册
	 * @param AdminAccount  管理员账号
	 * @param AdminPassword 管理员密码
	 * @param AdminName     管理员姓名
	 * @param AdminPhone    管理员电话
	 * @param RegionalCode  所负者区域代号
	 * @return 受影响的条数
	 */
	@Override
	public int register(String AdminAccount, String AdminPassword, String AdminName, String AdminPhone, String RegionalCode) {
		int result = adminMapper.insert(AdminAccount,AdminPassword,AdminName,AdminPhone,RegionalCode);
		return result;
	}

	/**
	 * 检查账号是否已存在
	 *
	 * @param AdminAccount 	管理员账号
	 * @return 				是否存在（1表示存在，0表示不存在）
	 */
	@Override
	public int check(String AdminAccount) {
		int result = adminMapper.check(AdminAccount);
		return result;
	}


}
