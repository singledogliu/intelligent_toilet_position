/**
 * 厕位信息
 *
 * @author ldq
 * @date 2019-03-25 12:26
 */
package com.ldq.graduation.design.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ToiletPositionInfo {
	/**
	 * 厕位代号
	 */
	private String toiletPositionCode;
	/**
	 * 厕位一次使用开始时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	/**
	 *厕位一次使用结束时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	/**
	 * 	 *厕位所在厕所的性别（男0/女1）
	 */
	private Boolean gender;
	/**
	 * 厕位所在厕所代号
	 */
	private String toiletCode;
	/**
	 * 厕位所在区域代号
	 */
	private String regionalCode;

}
