/**
 * 厕位信息
 *
 * @author ldq
 * @date 2019-03-25 13:46
 */
package com.ldq.graduation.design.dao;


import org.apache.ibatis.annotations.Param;


import java.sql.Timestamp;


public interface ToiletPositionMapper {


	/**
	 * 插入一条新的厕位使用记录
	 * @param startTime				开始时间
	 * @param toiletPositionCode	厕位编号
	 * @param gender				性别
	 * @param toiletCode			厕所代号
	 * @param regionalCode			所在区域代号
	 * @return						受影响的条数
	 */
	int insert(@Param("startTime") Timestamp startTime, @Param("toiletPositionCode") String toiletPositionCode, @Param("gender") Boolean gender, @Param("toiletCode") String toiletCode, @Param("regionalCode") String regionalCode);

	/**
	 * 数据更新
	 * @param regionalCode			区域代号
	 * @param toiletCode			厕所代号
	 * @param gender				性别
	 * @param toiletPositionCode	厕位代号
	 * @param endTime				结束时间
	 * @return						受影响条数
	 */
	Integer update(@Param("regionalCode") String regionalCode, @Param("toiletCode") String toiletCode, @Param("gender") boolean gender, @Param("toiletPositionCode") String toiletPositionCode, @Param("endTime") Timestamp endTime);
}
