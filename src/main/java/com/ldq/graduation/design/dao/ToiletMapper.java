/**
 * 厕所信息
 *
 * @author ldq
 * @date 2019-03-25 13:47
 */
package com.ldq.graduation.design.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ToiletMapper {

	/**
	 * 通过区域名称删除一条记录
	 * @param regionalName		区域名称
	 * @return					受影响的条数
	 */
	int deleteByregionalName(@Param("regionalName") String regionalName);
}
