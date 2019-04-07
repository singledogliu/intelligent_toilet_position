/**
 * 区域信息
 *
 * @author ldq
 * @date 2019-03-25 13:02
 */
package com.ldq.graduation.design.pojo;

import lombok.Data;

@Data
public class RegionalInfo {

	/**
	 * 区域代号
	 */
	private String regionalCode;
	/**
	 * 区域名称
	 */
	private String regionalName;
	/**
	 * 区域负责人名称
	 */
	private String regionalResponsibleName;
}
