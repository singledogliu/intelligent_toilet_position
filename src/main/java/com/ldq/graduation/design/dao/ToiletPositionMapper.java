/**
 * 厕位信息
 *
 * @author ldq
 * @date 2019-04-23 22:16
 */
package com.ldq.graduation.design.dao;

import com.ldq.graduation.design.pojo.ToiletPositionInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToiletPositionMapper {
	/**
	 * 插入厕位数据
	 *
	 * @param regionalName       区域名称
	 * @param toiletCode         厕所代号
	 * @param gender             性别
	 * @param toiletPositionCode 厕位编号
	 * @return 受影响的条数
	 */
	int insert(@Param("regionalName") String regionalName, @Param("toiletCode") String toiletCode, @Param("gender") String gender, @Param("toiletPositionCode") int toiletPositionCode);

	/**
	 * 查询指定区域指定厕所的所有厕位信息
	 * @param regionalName 区域名称
	 * @param toiletCode   厕所名称
	 * @return 查询到的厕位信息
	 */
	List<ToiletPositionInfo> select(@Param("regionalName") String regionalName, @Param("toiletCode") String toiletCode);
}
