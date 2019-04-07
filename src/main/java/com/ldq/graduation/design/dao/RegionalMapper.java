/**
 * 区域信息
 *
 * @author ldq
 * @date 2019-03-25 13:47
 */
package com.ldq.graduation.design.dao;

import com.ldq.graduation.design.pojo.RegionalInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionalMapper {
	int add(RegionalInfo regionalInfo);
}
