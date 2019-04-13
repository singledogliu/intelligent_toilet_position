/**
 * 厕所信息
 *
 * @author ldq
 * @date 2019-03-25 12:34
 */
package com.ldq.graduation.design.pojo;

import lombok.Data;

@Data
public class ToiletInfo {
	/**
	 * 厕所所在区域名称
	 */
	private String regionalName;
	/**
	 * 厕所代号
	 */
	private String toiletCode;
	/**
	 * 厕所所在位置
	 */
	private String position;
	/**
	 * 厕所负责人名字
	 */
	private String toiletResponsibleName;
}
