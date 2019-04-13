/**
 * 管理员信息
 *
 * @author ldq
 * @date 2019-03-25 13:48
 */
package com.ldq.graduation.design.dao;

import com.ldq.graduation.design.pojo.AdminInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {
	/**
	 * 通过账号查找
	 * @param account	管理员账号
	 * @return			查找记录
	 */
	AdminInfo selectByaccount(@Param("account") String account);


	/**
	 * 插入一条管理员注册记录
	 * @param adminaccount		管理员账号
	 * @param adminpassword		管理员密码
	 * @param adminname			管理员姓名
	 * @param adminphone		管理员电话
	 * @param regionalname		所负责区域名称
	 * @return					受影响条数
	 */
	int insert(@Param("adminaccount") String adminaccount, @Param("adminpassword") String adminpassword, @Param("adminname") String adminname, @Param("adminphone") String adminphone, @Param("regionalname") String regionalname);

	/**
	 * 检查账号是否已存在
	 * @param AdminAccount		管理员账号
	 * @return					是否存在（1表示存在，0表示不存在）
	 */
	int check(@Param("AdminAccount") String AdminAccount);

	/**
	 * 删除指定管理员信息
	 * @param adminAccount		管理员账号
	 * @return					受影响条数
	 */
	int deleteByaccount(@Param("adminAccount") String adminAccount);
}
