/**
 * 区域信息
 *
 * @author ldq
 * @date 2019-03-25 13:47
 */
package com.ldq.graduation.design.dao;

import com.ldq.graduation.design.pojo.RegionalInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionalMapper {

	/**
	 * 增加一条区域记录
	 * @param regionalName				区域名称
	 * @param regionalResponsibleName	区域负责人名称
	 * @return							受影响的条数
	 */
	int insert(@Param("regionalName") String regionalName, @Param("regionalResponsibleName") String regionalResponsibleName);

	/**
	 * 通过区域代号删除一条记录
	 * @param regionalName		区域名称
	 * @return					受影响的条数
	 */
	int deleteByregionalName(@Param("regionalName") String regionalName);
}
