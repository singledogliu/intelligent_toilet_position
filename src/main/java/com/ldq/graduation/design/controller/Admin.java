/**
 * 管理员相关操作
 *
 * @author ldq
 * @date 2019-03-18 19:51
 */
package com.ldq.graduation.design.controller;

import com.ldq.graduation.design.pojo.AdminInfo;
import com.ldq.graduation.design.pojo.RegionalInfo;
import com.ldq.graduation.design.services.IAdminService;
import com.ldq.graduation.design.services.IRegionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/Admin")
public class Admin {
	@Autowired
	IAdminService iAdminService;
	@Autowired
	IRegionalService iRegionalService;

	/**
	 * 检查账号是否已存在
	 *
	 * @param AdminAccount 管理员账号
	 * @return 是否存在（1表示存在，0表示不存在）
	 */
	public int checkAccount(String AdminAccount) {
		int result = iAdminService.check(AdminAccount);
		return result;
	}


	/**
	 * 管理员登录
	 *
	 * @param request 前端发来的数据
	 * @return 登录结果
	 */
	@RequestMapping("/login")
	@ResponseBody
	public String login(HttpServletRequest request) {
//		账号
		String account = (String) request.getSession().getAttribute("account");
//		密码
		String password = (String) request.getSession().getAttribute("password");
		AdminInfo adminInfo = iAdminService.selectByaccount(account);
		String reason;
		if (adminInfo == null) {
			reason = "账号错误";
			return reason;
		} else if (adminInfo.getAdminPassword() != password) {
			reason = "密码错误";
			return reason;
		} else {
			reason = "登录成功";
			return reason;
		}
	}

	/**
	 * 管理员注册
	 *
	 * @param request 前端发来的数据
	 * @return 注册结果
	 */
	@RequestMapping("/register")
	@ResponseBody
	public String register(HttpServletRequest request) {
//		AdminInfo adminInfo = new AdminInfo();
		String AdminAccount = request.getParameter("account");
		String AdminName = request.getParameter("name");
		String AdminPassword = request.getParameter("password");
		String AdminPhone = request.getParameter("phone");
		String RegionalCode = request.getParameter("regionalCode");
//		println(RegionalCode);
		int checkedResult = checkAccount(AdminAccount);
		if (checkedResult == 0) {
			int result = iAdminService.register(AdminAccount, AdminPassword, AdminName, AdminPhone, RegionalCode);
			if (result != 0) {
				return "注册成功";
			} else {
				return "注册失败";
			}
		} else {
			return "改账号已存在";
		}
	}

	/**
	 * 增加区域
	 *
	 * @param request 前端发来的数据
	 * @return 受影响条数
	 */
	public int addRegional(HttpServletRequest request) {
		RegionalInfo regionalInfo = new RegionalInfo();
		regionalInfo.setRegionalCode((String) request.getSession().getAttribute("regionalCode"));
		regionalInfo.setRegionalName((String) request.getSession().getAttribute("regionalName"));
		regionalInfo.setRegionalResponsibleName((String) request.getSession().getAttribute("regionalResponsibleName"));
		int result = iRegionalService.addRegional(regionalInfo);
		return result;
	}
}
