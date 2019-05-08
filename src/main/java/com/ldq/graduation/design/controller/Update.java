/**
 * 将厕位实时数据入库
 *
 * @author ldq
 * @date 2019-03-16 22:25
 */
package com.ldq.graduation.design.controller;

import com.ldq.graduation.design.services.IToiletPositionService;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping("/ToiletPosition")
public class Update {
	@Autowired
	IToiletPositionService iToiletPositionService;

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
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		开始时间的字符串
		String startTimeStr = request.getParameter("startTimeStr");
//		格式化字符串
		try {
			currentDate = sdf.parse(startTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
//		开始时间
		Timestamp startTime = new Timestamp(currentDate.getTime());
//		厕位编号
		String toiletPositionCode = request.getParameter("toiletPositionCode");
//		性别
		String gender = request.getParameter("gender");
//		厕所代号（名称）
		String toiletCode = request.getParameter("toiletCode");
//		区域名称
		String regionalName = request.getParameter("regionalName");
		int resultNum = iToiletPositionService.insert(startTime, toiletPositionCode, gender, toiletCode, regionalName);
//		向前端发送数据
		if (resultNum != 0) {
			GoEasy goEasy = new GoEasy("https://rest-hangzhou.goeasy.io", "BC-974faf5d63d14169bffac6b4aba38848");
			goEasy.publish("action", "start");
		}
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
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		开始时间的字符串
		String startTimeStr = request.getParameter("startTimeStr");
//		结束时间的字符串
		String endTimeStr = request.getParameter("endTimeStr");
//		格式化字符串
		try {
			currentDate = sdf.parse(startTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
//		开始时间
		Timestamp startTime = new Timestamp(currentDate.getTime());
		try {
			currentDate = sdf.parse(endTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
//		结束时间
		Timestamp endTime = new Timestamp(currentDate.getTime());
//		厕位编号
		String toiletPositionCode = request.getParameter("toiletPositionCode");
//		性别
		String gender = request.getParameter("gender");
//		厕所代号
		String toiletCode = request.getParameter("toiletCode");
//		区域代号
		String regionalName = request.getParameter("regionalName");
//		使用时长
		Long duration = endTime.getTime() - startTime.getTime();
		int resultNum = iToiletPositionService.update(regionalName, toiletCode, gender, toiletPositionCode, endTime, startTime, duration);
//		向前端发送数据
		if (resultNum != 0) {
			GoEasy goEasy = new GoEasy("https://rest-hangzhou.goeasy.io", "BC-974faf5d63d14169bffac6b4aba38848");
			goEasy.publish("action", "end");
		}
		return resultNum;
	}
}
