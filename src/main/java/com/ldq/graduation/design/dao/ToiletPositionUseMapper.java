/**
 * 厕位使用信息
 *
 * @author ldq
 * @date 2019-03-25 13:46
 */
package com.ldq.graduation.design.dao;


import com.ldq.graduation.design.pojo.ToiletPositionInfo;
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
	 * @param startTime             开始时间
	 * @param duration           使用时长
	 * @return 受影响条数
	 */
	int update(@Param("regionalName") String regionalName, @Param("toiletCode") String toiletCode, @Param("gender") String gender, @Param("toiletPositionCode") String toiletPositionCode, @Param("endTime") Timestamp endTime, @Param("startTime") Timestamp startTime, @Param("duration") Long duration);

	/**
	 * 通过区域名称删除一条记录
	 *
	 * @param regionalName 区域名称
	 * @return 受影响的条数
	 */
	int deleteByregionalName(@Param("regionalName") String regionalName);


	/**
	 * 通过区域名称查询该区域指定时间段内的所有厕位使用数据
	 *
	 * @param regionalName 区域名称
	 * @param date         时间段
	 * @return 所有数据
	 */
	List<ToiletPositionUseInfo> selectAllByRegionalName(@Param("regionalName") String regionalName, @Param("date") String date);

//	List<ToiletPositionUseInfo> selectAllDurationByRegionalName(@Param("regionalName") String regionalName, @Param("date") String date);


	/**
	 * 查询某厕所指定时间段的所有使用数据
	 *
	 * @param regionalName 区域名称
	 * @param toiletCode   厕所代号
	 * @param startDate    开始时间
	 * @param endDate      结束时间
	 * @param gender       性别
	 * @return 某厕所指定时间段的所有使用数据
	 */
	int selectCountByToiletCodeAndGender(@Param("regionalName") String regionalName, @Param("toiletCode") String toiletCode, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("gender") String gender);

	Long selectDurationByToiletCodeAndGender(@Param("regionalName") String regionalName, @Param("toiletCode") String toiletCode, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("gender") String gender);


	/**
	 * 查询某厕所指定时间段的所有使用数据
	 *
	 * @param regionalName 区域名称
	 * @param toiletCode   厕所代号
	 * @param startDate    开始时间
	 * @param endDate      结束时间
	 * @param gender       性别
	 * @return 某厕所指定时间段的所有使用数据
	 */
	int selectCountByToiletCodeAndGenderLike(@Param("regionalName") String regionalName, @Param("toiletCode") String toiletCode, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("gender") String gender);

	Long selectDurationByToiletCodeAndGenderLike(@Param("regionalName") String regionalName, @Param("toiletCode") String toiletCode, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("gender") String gender);


	/**
	 * 查询某个区域指定时间段的使用数据
	 *
	 * @param regionalName 区域名称
	 * @param startDate    开始时间
	 * @param endDate      结束时间
	 * @param gender       性别
	 * @return 查询到的数据
	 */
	int selectCountByRegionalNameBetween(@Param("regionalName") String regionalName, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("gender") String gender);

	Long selectDurationByRegionalNameBetween(@Param("regionalName") String regionalName, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("gender") String gender);


	/**
	 * 查询某个区域指定时间段的使用数据
	 *
	 * @param regionalName 区域名称
	 * @param startDate    开始时间
	 * @param endDate      结束时间
	 * @param gender       性别
	 * @return 查询到的数据
	 */
	int selectCountByRegionalNameLike(@Param("regionalName") String regionalName, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("gender") String gender);


	Long selectDurationByRegionalNameLike(@Param("regionalName") String regionalName, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("gender") String gender);


	/**
	 * 查询某个厕位最新的一条数据
	 *
	 * @param regionalName       区域名称
	 * @param toiletCode         厕所代号(名称）
	 * @param gender             性别
	 * @param toiletPositionCode 厕位编号
	 * @param startTime          开始时间
	 * @return 查询到的数据
	 */
	ToiletPositionUseInfo selectCurrentUseInfo(@Param("regionalName") String regionalName, @Param("toiletCode") String toiletCode, @Param("gender") String gender, @Param("toiletPositionCode") String toiletPositionCode, @Param("startTime") Timestamp startTime);

	/**
	 * 获取指定厕所已有的使用信息（每个厕位一条）
	 *
	 * @param regionalName 区域名称
	 * @param toiletCode   厕所代号（名称）
	 * @return 查询到的数据
	 */
	List<ToiletPositionInfo> selectExistsInfo(@Param("regionalName") String regionalName, @Param("toiletCode") String toiletCode);

	/**
	 * 查询某个厕位最近的一次使用时间
	 *
	 * @param regionalName       区域名称
	 * @param toiletCode         厕所代号（名称）
	 * @param gender             性别
	 * @param toiletPositionCode 厕位编号
	 * @return 查询到的数据
	 */
	Timestamp selectMaxTime(@Param("regionalName") String regionalName, @Param("toiletCode") String toiletCode, @Param("gender") String gender, @Param("toiletPositionCode") String toiletPositionCode);

}

