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
import com.ldq.graduation.design.pojo.ToiletPositionInfo;
import com.ldq.graduation.design.services.IAdminService;
import com.ldq.graduation.design.services.IRegionalService;
import com.ldq.graduation.design.services.IToiletPositionService;
import com.ldq.graduation.design.services.IToiletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Random;

import static jdk.nashorn.internal.objects.Global.println;


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
	 * @param regionalName					区域名称
	 * @param regionalResponsibleName		区域负责人名称
	 * @return								受影响条数
	 */
	public int addRegional(String regionalName, String regionalResponsibleName) {
		int result = iRegionalService.add(regionalName, regionalResponsibleName);
		return result;
	}

	/**
	 * 获取短信验证码
	 * @param httpServletRequest		前端发来的请求数据（电话号码）
	 * @return							随机生成的验证码
	 */
	@RequestMapping("/verification")
	@ResponseBody
	public String getIdentifyingCode(HttpServletRequest httpServletRequest){
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
		request.putQueryParameter("TemplateParam", "{\"code\":"+identifyingCode+"}");
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
	public String login(HttpServletRequest request) {
//		账号
		String adminAccount = (String) request.getSession().getAttribute("account");
//		密码
		String adminPassword = (String) request.getSession().getAttribute("password");
		AdminInfo adminInfo = iAdminService.selectByaccount(adminAccount);
		String reason;
		if (adminInfo == null) {
			reason = "账号错误";
			return reason;
		} else if (adminInfo.getAdminPassword() != adminPassword) {
			reason = "密码错误";
			return reason;
		} else {
			reason = "登录成功";
			request.getSession().setAttribute("adminAccount", adminAccount);
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
//		验证码
//		String identifyingCode = request.getParameter("identifyingCode");
		int checkedResult = checkAccount(adminAccount);
		if (checkedResult == 0) {
			int result = iAdminService.register(adminAccount, adminPassword, adminName, adminPhone, regionalName);
			if (result != 0) {
				request.getSession().setAttribute("adminAccount", adminAccount);
				int resultRegional = addRegional(regionalName, adminName);
				if (resultRegional != 0){
					return "注册成功";
				}
				else{
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
	 * 	管理员注销
	 * @param request		网络请求
	 * @return				注销结果
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public String logout(HttpServletRequest request) {
		String adminAccount = (String) request.getSession().getAttribute("adminAccount");
		AdminInfo adminInfo = iAdminService.selectByaccount(adminAccount);
		String regionalName = adminInfo.getRegionalName();
		int resultAdmin = iAdminService.logout(adminAccount);
		int resultRegional = iRegionalService.logout(regionalName);
		int resultToilet = iToiletService.logout(regionalName);
		int resultToiletPosition = iToiletPositionService.logout(regionalName);
		String stat = "注销失败";
		if(resultAdmin > 0 && resultRegional > 0 && resultToilet > 0 && resultToiletPosition > 0) {
			stat = "注销成功";
		}
		return stat;
	}

	/**
	 * 获取某个区域的所有使用信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/getRegionalStatistics")
	@ResponseBody
	public List getRegionalStatistics(HttpServletRequest request){
		String regionalName = request.getParameter("regionalName");
		String date = request.getParameter("date");
		date = date +"%";
		List<ToiletPositionInfo> regionalStatistics = iRegionalService.getRegionalStatistics(regionalName,date);
		return regionalStatistics;
	}

}
