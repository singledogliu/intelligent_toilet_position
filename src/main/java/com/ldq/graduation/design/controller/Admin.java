/**
 * 管理员相关操作
 *
 * @author ldq
 * @date 2019-03-18 19:51
 */
package com.ldq.graduation.design.controller;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.ldq.graduation.design.pojo.AdminInfo;
import com.ldq.graduation.design.pojo.ToiletInfo;
import com.ldq.graduation.design.pojo.ToiletPositionInfo;
import com.ldq.graduation.design.pojo.ToiletPositionUseInfo;
import com.ldq.graduation.design.services.IAdminService;
import com.ldq.graduation.design.services.IRegionalService;
import com.ldq.graduation.design.services.IToiletPositionService;
import com.ldq.graduation.design.services.IToiletService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//import org.json.JSONArray;


@Controller
@RequestMapping("/Admin")
public class Admin {
	@Autowired
	IAdminService iAdminService;
	@Autowired
	IRegionalService iRegionalService;
	@Autowired
	IToiletService iToiletService;
	@Autowired
	IToiletPositionService iToiletPositionService;

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
	 * 增加一条区域记录
	 *
	 * @param regionalName            区域名称
	 * @param regionalResponsibleName 区域负责人名称
	 * @return 受影响条数
	 */
	public int addRegional(String regionalName, String regionalResponsibleName) {
		int result = iRegionalService.add(regionalName, regionalResponsibleName);
		return result;
	}

	/**
	 * 获取短信验证码
	 *
	 * @param httpServletRequest 前端发来的请求数据（电话号码）
	 * @return 随机生成的验证码
	 */
	@RequestMapping("/verification")
	@ResponseBody
	public String getIdentifyingCode(HttpServletRequest httpServletRequest) {
		String phone = httpServletRequest.getParameter("phone");
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIdF0xHjRDsHAD", "HAbQSwP2o1BZdrcQSxLhTfXkhoL5ic");
		IAcsClient client = new DefaultAcsClient(profile);
		Random random = new Random();
		int identifyingCodeInt = random.nextInt(1000000);
		String identifyingCode = String.valueOf(identifyingCodeInt);
		CommonRequest request = new CommonRequest();
		//request.setProtocol(ProtocolType.HTTPS);
		request.setMethod(MethodType.POST);
		request.setDomain("dysmsapi.aliyuncs.com");
		request.setVersion("2017-05-25");
		request.setAction("SendSms");
		request.putQueryParameter("RegionId", "cn-hangzhou");
		request.putQueryParameter("PhoneNumbers", phone);
		request.putQueryParameter("SignName", "厕位智能引导系统");
		request.putQueryParameter("TemplateCode", "SMS_163057305");
		request.putQueryParameter("TemplateParam", "{\"code\":" + identifyingCode + "}");
		try {
			CommonResponse response = client.getCommonResponse(request);
			System.out.println(response.getData());
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return identifyingCode;
	}

	/**
	 * 管理员登录
	 *
	 * @param request 前端发来的数据
	 * @return 登录结果
	 */
	@RequestMapping("/login")
	@ResponseBody
	public JSONObject login(HttpServletRequest request) {
//		账号
		String adminAccount = request.getParameter("account");
//		密码
		String adminPassword = request.getParameter("password");
		AdminInfo adminInfo = iAdminService.selectByaccount(adminAccount);
		String reason;
		JSONObject message = new JSONObject();
		if (adminInfo == null) {
			reason = "账号错误";
			String stat = "登录失败";
			message.clear();
			message.put("stat", stat);
			message.put("reason", reason);
			return message;
		} else if (!adminInfo.getAdminPassword().equals(adminPassword)) {
			reason = "密码错误";
			String stat = "登录失败";
			message.clear();
			message.put("stat", stat);
			message.put("reason", reason);
			return message;
		} else {
			String stat = "登录成功";
			message.put("stat", stat);
			message.put("adminAccount", adminAccount);
			message.put("regionalName", adminInfo.getRegionalName());
			message.put("adminName", adminInfo.getAdminName());
			request.getSession().setAttribute("adminAccount", adminAccount);
			return message;
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
//		管理员账号
		String adminAccount = request.getParameter("account");
//		管理员姓名
		String adminName = request.getParameter("name");
//		管理员密码
		String adminPassword = request.getParameter("password");
//		管理员电话
		String adminPhone = request.getParameter("phone");
//		管理员所负责区域名称
		String regionalName = request.getParameter("regionalName");
		int checkedResult = checkAccount(adminAccount);
		if (checkedResult == 0) {
			int result = iAdminService.register(adminAccount, adminPassword, adminName, adminPhone, regionalName);
			if (result != 0) {
				request.getSession().setAttribute("adminAccount", adminAccount);
				int resultRegional = addRegional(regionalName, adminName);
				if (resultRegional != 0) {
					return "注册成功";
				} else {
					return "注册失败，管辖区域注册失败";
				}
			} else {
				return "注册失败";
			}
		} else {
			return "该账号已存在";
		}
	}

	/**
	 * 修改管理员信息
	 *
	 * @param request 前端发来的数据请求
	 * @return 修改结果
	 */
	@RequestMapping("/modifyInformation")
	@ResponseBody
	public int modifyInformation(HttpServletRequest request) {
//		管理员账号
		String adminAccount = request.getParameter("account");
//		管理员姓名
		String adminName = request.getParameter("name");
//		管理员密码
		String adminPassword = request.getParameter("password");
//		原管理员密码
		String oldAdminPassword = request.getParameter("oldPassword");
//		管理员电话
		String adminPhone = request.getParameter("phone");
//		原管理员账号
		String oldAdminAccount = request.getParameter("adminAccount");
//		欲执行的操作
		String action = request.getParameter("action");
//		通过管理员账号查询到的记录
		AdminInfo adminInfo = iAdminService.selectByaccount(oldAdminAccount);
		int result = 0;
		if (!oldAdminPassword.equals(adminInfo.getAdminPassword())) {
			result = 2;
		} else {
			switch (action) {
				case "修改电话":
					result = iAdminService.modifyPhone(oldAdminAccount, adminPhone);
					if (result != 0) {
						request.getSession().setAttribute("adminAccount", adminPhone);
					}
					break;
				case "修改密码":
					result = iAdminService.modifyPassword(oldAdminAccount, oldAdminPassword, adminPassword);
					break;
				case "修改账号":
					result = iAdminService.modifyAccount(oldAdminAccount, adminAccount);
					break;
				case "修改全部":
					result = iAdminService.modifyInformation(oldAdminAccount, adminAccount, adminName, adminPassword, adminPhone);
					break;
				default:
					break;
			}
		}
		return result;
	}

	/**
	 * 管理员注销
	 *
	 * @param request 网络请求
	 * @return 注销结果
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public String logout(HttpServletRequest request) {
		String adminAccount = request.getParameter("adminAccount");
		AdminInfo adminInfo = iAdminService.selectByaccount(adminAccount);
		String regionalName = adminInfo.getRegionalName();
		int resultAdmin = iAdminService.logout(adminAccount);
		int resultRegional = iRegionalService.logout(regionalName);
		int resultToilet = iToiletService.logout(regionalName);
		int resultToiletPosition = iToiletPositionService.logout(regionalName);
		String stat = "注销失败";
		if (resultAdmin > 0 && resultRegional > 0 && resultToilet > 0 && resultToiletPosition > 0) {
			stat = "注销成功";
		}
		return stat;
	}

	/**
	 * 获取某个区域指定时间段的所有使用信息
	 *
	 * @param request 前端发来的数据
	 * @return 某个区域指定时间段的所有厕位使用数据
	 */
	@RequestMapping("/getRegionalStatistics")
	@ResponseBody
	public List getRegionalStatistics(HttpServletRequest request) {
		String regionalName = request.getParameter("regionalName");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		startDate = startDate + "%";
		endDate = endDate + "%";
		List<ToiletPositionUseInfo> regionalStatistics = iRegionalService.getRegionalStatistics(regionalName, startDate);
		return regionalStatistics;
	}

	/**
	 * 获取某个厕所指定时间段的所有使用数据
	 *
	 * @param request 前端发来的数据
	 * @return 某个厕所指定时间段内的所有厕位使用数据
	 */
	@RequestMapping("/getToiletStatistics")
	@ResponseBody
	public List getToiletStatistics(HttpServletRequest request) {
		String regionalName = request.getParameter("regionalName");
		String toiletCode = request.getParameter("toiletCode");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String action = request.getParameter("action");
		String unit = request.getParameter("unit");
		List toiletStatistics = iToiletService.getToiletStatistics(regionalName, toiletCode, startDate, endDate, action, unit);
		return toiletStatistics;
	}

	/**
	 * 添加厕所及厕位信息
	 *
	 * @param request 前端发来的数据
	 * @return 添加结果
	 */
	@RequestMapping("/AddToiletInfo")
	@ResponseBody
	public int AddToiletInfo(HttpServletRequest request) {
		String adminAccount = request.getParameter("adminAccount");
		String ToiletInfoStr = request.getParameter("toiletInfo");
		//		通过管理员账号查询到的记录
		AdminInfo adminInfo = iAdminService.selectByaccount(adminAccount);
//		区域名称
		String regionalName = adminInfo.getRegionalName();
		JSONArray ToiletInfo = JSONArray.fromObject(ToiletInfoStr);
//		添加厕所信息
		int resultToilet = iToiletService.add(regionalName, ToiletInfo);
//		添加厕位信息
		int resultToiletPosition = iToiletPositionService.add(regionalName, ToiletInfo);
		return resultToilet + resultToiletPosition;
	}

	/**
	 * 获取指定厕所的厕位信息
	 *
	 * @param request 前端发来的数据
	 * @return 查询到的数据
	 */
	@RequestMapping("/getToiletPositionInfo")
	@ResponseBody
	public List getToiletPositionInfo(HttpServletRequest request) {
//		区域名称
		String regionalName = request.getParameter("regionalName");
		//		厕所代号
		String toiletCode = request.getParameter("toiletCode");
		List<ToiletPositionInfo> toiletPositionInfos = iToiletPositionService.getToiletPositionInfo(regionalName, toiletCode);
		return toiletPositionInfos;
	}

	/**
	 * 获取指定管理员负责的厕所信息
	 *
	 * @param request 前端发来的数据
	 * @return 查询到的数据
	 */
	@RequestMapping("/getToiletInfo")
	@ResponseBody
	public List getToiletInfo(HttpServletRequest request) {
		String adminAccount = request.getParameter("adminAccount");
		String reginoalName = iAdminService.selectByaccount(adminAccount).getRegionalName();
		List<ToiletInfo> toiletInfos = iToiletService.getToiletInfo(reginoalName);
		return toiletInfos;
	}

	/**
	 * 获取当前厕位的使用状况
	 *
	 * @param request 前端发来的数据
	 * @return 查询到的数据
	 */
	@RequestMapping("/getCurrentUseInfo")
	@ResponseBody
	public List getCurrentUseInfo(HttpServletRequest request) {
		String regionalName = request.getParameter("regionalName");
		String toiletCode = request.getParameter("toiletCode");
		List<ToiletPositionUseInfo> toiletPositionUseInfos = iToiletPositionService.getCurrentUseInfo(regionalName, toiletCode);
		return toiletPositionUseInfos;
	}


}
