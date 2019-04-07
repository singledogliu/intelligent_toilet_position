/**
 * 将厕位实时数据入库
 *
 * @author ldq
 * @date 2019-03-16 22:25
 */
package com.ldq.graduation.design.controller;

import com.ldq.graduation.design.services.IUpdataService;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;


@Controller
@RequestMapping("/ToiletPosition")
public class Update {
	@Autowired
	IUpdataService iUpdataService;

	/**
	 * 插入一条新数据到数据库
	 *
	 * @param request 前端发送过来的数据
	 * @return 受影响的条数
	 */
	@RequestMapping("/start")
	@ResponseBody
	public int insert(HttpServletRequest request) {
		Date currentDate = new Date();
		Timestamp startTime = new Timestamp(currentDate.getTime());
		String toiletPositionCode = request.getParameter("toiletPositionCode");
		Boolean gender = Boolean.valueOf(request.getParameter("gender"));
		String toiletCode = request.getParameter("toiletCode");
		String regionalCode = request.getParameter("regionalCode");
		int resultNum = iUpdataService.insert(startTime, toiletPositionCode, gender, toiletCode, regionalCode);
		GoEasy goEasy = new GoEasy("https://rest-hangzhou.goeasy.io", "BC-974faf5d63d14169bffac6b4aba38848");
		goEasy.publish("action", "start");
		return resultNum;
	}

	/**
	 * 更新厕位信息
	 *
	 * @param request 前端发来的数据
	 * @return 受影响的条数
	 */
	@RequestMapping("/end")
	@ResponseBody
	public int update(HttpServletRequest request) {
		Date currentDate = new Date();
		Timestamp endTime = new Timestamp(currentDate.getTime());
//		厕位编号
		String toiletPositionCode = request.getParameter("toiletPositionCode");
//		性别
		boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
//		厕所代号
		String toiletCode = request.getParameter("toiletCode");
//		区域代号
		String regionalCode = request.getParameter("regionalCode");
//		print(currentTime.toString());
		int resultNum = iUpdataService.update(regionalCode, toiletCode, gender, toiletPositionCode, endTime);
		GoEasy goEasy = new GoEasy("https://rest-hangzhou.goeasy.io", "BC-974faf5d63d14169bffac6b4aba38848");
		goEasy.publish("action", "end");
		return resultNum;
	}
}
