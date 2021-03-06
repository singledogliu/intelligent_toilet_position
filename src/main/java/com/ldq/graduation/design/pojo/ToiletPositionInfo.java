/**
 * 厕位信息
 *
 * @author ldq
 * @date 2019-05-06 22:24
 */
package com.ldq.graduation.design.pojo;

import lombok.Data;

@Data
public class ToiletPositionInfo {
	/**
	 * 厕位所在区域名称
	 */
	private String regionalName;
	/**
	 * 厕位所在厕所代号
	 */
	private String toiletCode;
	/**
	 * *厕位所在厕所的性别
	 */
	private String gender;
	/**
	 * 厕位代号
	 */
	private String toiletPositionCode;
}
