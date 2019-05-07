/**
 * 厕位使用信息
 *
 * @author ldq
 * @date 2019-03-25 13:46
 */
package com.ldq.graduation.design.dao;


import com.ldq.graduation.design.pojo.ToiletPositionUseInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ToiletPositionUseMapper {


	/**
	 * 插入一条新的厕位使用记录
	 *
	 * @param startTime          开始时间
	 * @param toiletPositionCode 厕位编号
	 * @param gender             性别
	 * @param toiletCode         厕所代号
	 * @param regionalName       所在区域名称
	 * @return 受影响的条数
	 */
	int insert(@Param("startTime") Timestamp startTime, @Param("toiletPositionCode") String toiletPositionCode, @Param("gender") String gender, @Param("toiletCode") String toiletCode, @Param("regionalName") String regionalName);

	/**
	 * 数据更新
	 *
	 * @param regionalName       区域名称
	 * @param toiletCode         厕所代号
	 * @param gender             性别
	 * @param toiletPositionCode 厕位代号
	 * @param endTime            结束时间
	 * @param duration           使用时长
	 * @return 受影响条数
	 */
	int update(@Param("regionalName") String regionalName, @Param("toiletCode") String toiletCode, @Param("gender") String gender, @Param("toiletPositionCode") String toiletPositionCode, @Param("endTime") Timestamp endTime, @Param("duration") Long duration);

	/**
	 * 通过区域名称删除一条记录
	 *
	 * @param regionalName 区域名称
	 * @return 受影响的条数
	 */
	int deleteByregionalName(@Param("regionalName") String regionalName);

	/**
	 * 查询开始时间
	 *
	 * @param regionalName       区域名称
	 * @param toiletCode         厕所代号
	 * @param gender             性别
	 * @param toiletPositionCode 厕位编号
	 * @return 开始时间
	 */
	Timestamp selectStartTime(@Param("regionalName") String regionalName, @Param("toiletCode") String toiletCode, @Param("gender") String gender, @Param("toiletPositionCode") String toiletPositionCode);

	/**
	 * 通过区域名称查询该区域指定时间段内的所有厕位使用数据
	 *
	 * @param regionalName 区域名称
	 * @param date         时间段
	 * @return 所有数据
	 */
	List<ToiletPositionUseInfo> selectAllByRegionalName(@Param("regionalName") String regionalName, @Param("date") String date);

	/**
	 * 查询某厕所指定时间段的所有使用数据
	 *
	 * @param regionalName 区域名称
	 * @param toiletCode   厕所代号
	 * @param date         指定时间
	 * @return 某厕所指定时间段的所有使用数据
	 */
	List<ToiletPositionUseInfo> selectAllByToiletCode(@Param("regionalName") String regionalName, @Param("toiletCode") String toiletCode, @Param("date") String date);

}

