/**
 * 管理员信息
 *
 * @author ldq
 * @date 2019-03-24 14:56
 */
package com.ldq.graduation.design.pojo;
import lombok.Data;

@Data
public class AdminInfo {
	/**
	 * 管理员账号
	 */
	private String adminAccount;
	/**
	 * 管理员登录密码
	 */
	private String adminPassword;
	/**
	 * 管理员名字
	 */
	private String adminName;
	/**
	 * 管理员电话号码
	 */
	private String adminPhone;
	/**
	 * 管理员所负责的区域名称
	 */
	private String regionalName;
}
