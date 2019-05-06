/**
 * 厕所信息
 *
 * @author ldq
 * @date 2019-03-25 13:47
 */
package com.ldq.graduation.design.dao;

import net.sf.json.JSONArray;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ToiletMapper {

	/**
	 * 通过区域名称删除一条记录
	 *
	 * @param regionalName 区域名称
	 * @return 受影响的条数
	 */
	int deleteByregionalName(@Param("regionalName") String regionalName);


	/**
	 * 插入厕所信息
	 *
	 * @param regionalName          区域名称
	 * @param toiletCode            厕所代号
	 * @param toiletResponsibleName 厕所责任人
	 * @return 受影响的条数
	 */
	int insert(@Param("regionalName") String regionalName, @Param("toiletCode") String toiletCode, @Param("toiletResponsibleName") String toiletResponsibleName);
}
