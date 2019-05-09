/**
 * 厕所信息
 *
 * @author ldq
 * @date 2019-03-25 13:47
 */
package com.ldq.graduation.design.dao;

import com.ldq.graduation.design.pojo.ToiletInfo;
import net.sf.json.JSONArray;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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

	/**
	 * 获取指定管理员赋值的厕所信息
	 *
	 * @param regionalName 管理员账号
	 * @return 查询到的数据
	 */
	List<ToiletInfo> selectByAccount(@Param("regionalName") String regionalName);
}
