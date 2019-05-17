/**
 * 厕所相关操作
 *
 * @author ldq
 * @date 2019-03-27 14:36
 */
package com.ldq.graduation.design.services.impl;

//@Repository("iToiletService")

import com.ldq.graduation.design.dao.ToiletMapper;
import com.ldq.graduation.design.dao.ToiletPositionUseMapper;
import com.ldq.graduation.design.pojo.ToiletInfo;
import com.ldq.graduation.design.services.IToiletService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToiletImpl implements IToiletService {

	@Autowired
	ToiletMapper toiletMapper;
	@Autowired
	ToiletPositionUseMapper toiletPositionUseMapper;

	/**
	 * 管理员注销
	 *
	 * @param regionalName 区域名称
	 * @return 受影响的条数
	 */
	@Override
	public int logout(String regionalName) {
		int result = toiletMapper.deleteByregionalName(regionalName);
		return result;
	}

	/**
	 * 获取某个厕所指定时间段的所有使用数据
	 *
	 * @param regionalName 区域名称
	 * @param toiletCode   厕所代号
	 * @param endDate      结束时间时间
	 * @param startDate    开始时间时间
	 * @return 某个厕所指定时间段的所有使用数据
	 */
	@Override
	public List getToiletStatistics(String regionalName, String toiletCode, String startDate, String endDate, String action, String unit) {
//		List useCount = new ArrayList();
		List resultList = new ArrayList();
		int menCount = 0;
		int womenCount = 0;
		String between = "between";
		String like = "like";
		String yearUnit = "year";
		String monthUnit = "month";
		String dayUnit = "day";
		int loopDay = 31;
		String startYear = startDate.split("-")[0];
		String startMonth = startDate.split("-")[1];
		String startDay = startDate.split("-")[2];
		String endYear = endDate.split("-")[0];
		String endMonth = endDate.split("-")[1];
//		String endDay = endDate.split("-")[2];
		int theYear = Integer.parseInt(startYear);
		boolean isLeap = false;
		if ((theYear % 4 == 0 && theYear % 100 != 0) || theYear % 400 == 0) {
			isLeap = true;
		}

		if (between.equals(action)) {
			int localStartYear = Integer.parseInt(startYear);
			int localStartMonth = Integer.parseInt(startMonth);
//			int localStartDay = Integer.parseInt(startDay);
			//			按年查看
			if (yearUnit.equals(unit)) {
				int loop = Integer.parseInt(endYear) - Integer.parseInt(startYear);
				if (!toiletCode.equals("整个区域")) {
					for (int i = 0; i <= loop; i++) {
						startDate = String.valueOf(localStartYear + i) + "%";
						List localUseCount = new ArrayList();
						menCount = toiletPositionUseMapper.selectCountByToiletCodeAndGenderLike(regionalName, toiletCode, startDate, endDate, "男");
						localUseCount.add(menCount);
						womenCount = toiletPositionUseMapper.selectCountByToiletCodeAndGenderLike(regionalName, toiletCode, startDate, endDate, "女");
						localUseCount.add(womenCount);
						resultList.add(localUseCount);
					}
				} else {
					for (int i = 0; i <= loop; i++) {
						startDate = String.valueOf(localStartYear + i) + "%";
						List localUseCount = new ArrayList();
						menCount = toiletPositionUseMapper.selectCountByRegionalNameBetween(regionalName, startDate, endDate, "男");
						localUseCount.add(menCount);
						womenCount = toiletPositionUseMapper.selectCountByRegionalNameBetween(regionalName, startDate, endDate, "女");
						localUseCount.add(womenCount);
						resultList.add(localUseCount);
					}
				}
			}
//			按月查看
			if (monthUnit.equals(unit)) {
				int loop = (Integer.parseInt(endYear) - Integer.parseInt(startYear)) * 12 + (Integer.parseInt(endMonth) - Integer.parseInt(startMonth));
				if (!toiletCode.equals("整个区域")) {
					for (int i = 0; i <= loop; i++) {
						String theLocalStartMonth = String.valueOf(localStartMonth);
						String theLocalStartYear = String.valueOf(localStartYear);
						if (localStartMonth < 10) {
							theLocalStartMonth = "0" + theLocalStartMonth;
						}
						startDate = theLocalStartYear + "-" + theLocalStartMonth + "%";
//					System.out.println(startDate);
						List localUseCount = new ArrayList();
						menCount = toiletPositionUseMapper.selectCountByToiletCodeAndGenderLike(regionalName, toiletCode, startDate, endDate, "男");
						localUseCount.add(menCount);
						womenCount = toiletPositionUseMapper.selectCountByToiletCodeAndGenderLike(regionalName, toiletCode, startDate, endDate, "女");
						localUseCount.add(womenCount);
						resultList.add(localUseCount);
						localStartMonth += 1;
						if (Integer.parseInt(startMonth) > 12) {
							localStartYear += 1;
							localStartMonth = 1;
						}
					}
				} else {
					for (int i = 0; i <= loop; i++) {
						String theLocalStartMonth = String.valueOf(localStartMonth);
						String theLocalStartYear = String.valueOf(localStartYear);
						if (localStartMonth < 10) {
							theLocalStartMonth = "0" + theLocalStartMonth;
						}
						startDate = theLocalStartYear + "-" + theLocalStartMonth + "%";
//					System.out.println(startDate);
						List localUseCount = new ArrayList();
						menCount = toiletPositionUseMapper.selectCountByRegionalNameBetween(regionalName, startDate, endDate, "男");
						localUseCount.add(menCount);
						womenCount = toiletPositionUseMapper.selectCountByRegionalNameBetween(regionalName, startDate, endDate, "女");
						localUseCount.add(womenCount);
						resultList.add(localUseCount);
						localStartMonth += 1;
						if (Integer.parseInt(startMonth) > 12) {
							localStartYear += 1;
							localStartMonth = 1;
						}
					}
				}
			}

//			按天查看
//			if (monthUnit.equals(unit)) {
//				long localStartDate = new Date(startDate).getTime();
//				long localendDate = new Date(startDate).getTime();
//				long days = (localendDate - localStartDate) / (1000 * 60 * 60 * 24);
//				for (int i = 0; i < days; i++) {
//					String theLocalStartDay = String.valueOf(localStartDay);
//					startDate = localStartYear + "-" + localStartMonth + "-" + theLocalStartDay + "%";
//					List localUseCount = new ArrayList();
//					menCount = toiletPositionUseMapper.selectCountByToiletCodeAndGenderLike(regionalName, toiletCode, startDate, endDate, "男");
//					localUseCount.add(menCount);
//					womenCount = toiletPositionUseMapper.selectCountByToiletCodeAndGenderLike(regionalName, toiletCode, startDate, endDate, "女");
//					localUseCount.add(womenCount);
//					resultList.add(localUseCount);
//					localStartDay += 1;
//				}
//			}


//			menCount = toiletPositionUseMapper.selectCountByToiletCodeAndGender(regionalName, toiletCode, startDate, endDate, "男");
//			useCount.add(menCount);
//			womenCount = toiletPositionUseMapper.selectCountByToiletCodeAndGender(regionalName, toiletCode, startDate, endDate, "女");
//			useCount.add(womenCount);

			if (unit.equals("all")) {
				resultList = getAllByTime(regionalName, toiletCode, startDate, endDate, between);
			}
		}
//
		if (like.equals(action)) {
//			按年查看
			if (yearUnit.equals(unit)) {
				if (!toiletCode.equals("整个区域")) {
					for (int i = 1; i < 13; i++) {
						List localUseCount = new ArrayList();
						if (i < 10) {
							startDate = startYear + "-0" + i + "%";
						} else {
							startDate = startYear + "-" + i + "%";
						}
						menCount = toiletPositionUseMapper.selectCountByToiletCodeAndGenderLike(regionalName, toiletCode, startDate, endDate, "男");
						localUseCount.add(menCount);
						womenCount = toiletPositionUseMapper.selectCountByToiletCodeAndGenderLike(regionalName, toiletCode, startDate, endDate, "女");
						localUseCount.add(womenCount);
						resultList.add(localUseCount);
					}
				} else {
					for (int i = 1; i < 13; i++) {
						List localUseCount = new ArrayList();
						if (i < 10) {
							startDate = startYear + "-0" + i + "%";
						} else {
							startDate = startYear + "-" + i + "%";
						}
						menCount = toiletPositionUseMapper.selectCountByRegionalNameLike(regionalName, startDate, endDate, "男");
						localUseCount.add(menCount);
						womenCount = toiletPositionUseMapper.selectCountByRegionalNameLike(regionalName, startDate, endDate, "女");
						localUseCount.add(womenCount);
						resultList.add(localUseCount);
					}
				}
			}
//			按月查看
			if (monthUnit.equals(unit)) {
				String localMonth = startMonth;
				String localDay = null;
				if (startMonth.equals("02")) {
					if (isLeap == true) {
						loopDay = 30;
					} else {
						loopDay = 29;
					}
				} else if ("04".equals(startMonth) || "06".equals(startMonth) || "09".equals(startMonth) || "11".equals(startMonth)) {
					loopDay = 31;
				} else {
					loopDay = 32;
				}
				if (!toiletCode.equals("整个区域")) {
					for (int i = 1; i < loopDay; i++) {
						List localUseCount = new ArrayList();
						if (i < 10) {
							localDay = "0" + String.valueOf(i);
						} else {
							localDay = String.valueOf(i);
						}
						startDate = startYear + "-" + localMonth + "-" + localDay + "%";
						menCount = toiletPositionUseMapper.selectCountByToiletCodeAndGenderLike(regionalName, toiletCode, startDate, endDate, "男");
						localUseCount.add(menCount);
						womenCount = toiletPositionUseMapper.selectCountByToiletCodeAndGenderLike(regionalName, toiletCode, startDate, endDate, "女");
						localUseCount.add(womenCount);
						resultList.add(localUseCount);
					}
				} else {
					for (int i = 1; i < loopDay; i++) {
						List localUseCount = new ArrayList();
						if (i < 10) {
							localDay = "0" + String.valueOf(i);
						} else {
							localDay = String.valueOf(i);
						}
						startDate = startYear + "-" + localMonth + "-" + localDay + "%";
						menCount = toiletPositionUseMapper.selectCountByRegionalNameLike(regionalName, startDate, endDate, "男");
						localUseCount.add(menCount);
						womenCount = toiletPositionUseMapper.selectCountByRegionalNameLike(regionalName, startDate, endDate, "女");
						localUseCount.add(womenCount);
						resultList.add(localUseCount);
					}
				}
			}
//			按天查看
			if (dayUnit.equals(unit)) {
				String localStartDate = startDate;
				if (!toiletCode.equals("整个区域")) {
					for (int i = 1; i < 25; i++) {
						List localUseCount = new ArrayList();
						if (i < 10) {
							startDate = localStartDate + " 0" + i + "%";
						} else {
							startDate = localStartDate + " " + i + "%";
						}
//					System.out.println(startDate);
						menCount = toiletPositionUseMapper.selectCountByToiletCodeAndGenderLike(regionalName, toiletCode, startDate, endDate, "男");
						localUseCount.add(menCount);
						womenCount = toiletPositionUseMapper.selectCountByToiletCodeAndGenderLike(regionalName, toiletCode, startDate, endDate, "女");
						localUseCount.add(womenCount);
						resultList.add(localUseCount);
					}
				} else {
					for (int i = 1; i < 25; i++) {
						List localUseCount = new ArrayList();
						if (i < 10) {
							startDate = localStartDate + " 0" + i + "%";
						} else {
							startDate = localStartDate + " " + i + "%";
						}
//					System.out.println(startDate);
						menCount = toiletPositionUseMapper.selectCountByRegionalNameLike(regionalName, startDate, endDate, "男");
						localUseCount.add(menCount);
						womenCount = toiletPositionUseMapper.selectCountByRegionalNameLike(regionalName, startDate, endDate, "女");
						localUseCount.add(womenCount);
						resultList.add(localUseCount);
					}
				}
			}

			if (unit.equals("all")) {
				resultList = getAllByTime(regionalName, toiletCode, startDate, endDate, like);
			}
		}
		return resultList;
	}

	/**
	 * 查询某厕所指定时间（内）的使用记录数量
	 *
	 * @param regionalName 区域名称
	 * @param toiletCode   厕所代号（名称）
	 * @param startDate    开始时间
	 * @param endDate      结束时间
	 * @param action       查询方式
	 * @return 查询到的数据
	 */
	public List getAllByTime(String regionalName, String toiletCode, String startDate, String endDate, String action) {
		String like = "like";
//		String between = "between";
//		List resultList = new ArrayList();
		List localUseCount = new ArrayList();
		int menCount = 0;
		int womenCount = 0;
		if (like.equals(action)) {
			startDate = startDate + "%";
			if (!toiletCode.equals("整个区域")) {
				menCount = toiletPositionUseMapper.selectCountByToiletCodeAndGenderLike(regionalName, toiletCode, startDate, endDate, "男");
				localUseCount.add(menCount);
				womenCount = toiletPositionUseMapper.selectCountByToiletCodeAndGenderLike(regionalName, toiletCode, startDate, endDate, "女");
				localUseCount.add(womenCount);
			} else {
				menCount = toiletPositionUseMapper.selectCountByRegionalNameLike(regionalName, startDate, endDate, "男");
				localUseCount.add(menCount);
				womenCount = toiletPositionUseMapper.selectCountByRegionalNameLike(regionalName, startDate, endDate, "女");
				localUseCount.add(womenCount);
			}
//			resultList.add(localUseCount);
		} else {
			if (!toiletCode.equals("整个区域")) {
				menCount = toiletPositionUseMapper.selectCountByToiletCodeAndGender(regionalName, toiletCode, startDate, endDate, "男");
				localUseCount.add(menCount);
				womenCount = toiletPositionUseMapper.selectCountByToiletCodeAndGender(regionalName, toiletCode, startDate, endDate, "女");
				localUseCount.add(womenCount);
			} else {
				menCount = toiletPositionUseMapper.selectCountByRegionalNameBetween(regionalName, startDate, endDate, "男");
				localUseCount.add(menCount);
				womenCount = toiletPositionUseMapper.selectCountByRegionalNameBetween(regionalName, startDate, endDate, "女");
				localUseCount.add(womenCount);
			}
//			resultList.add(localUseCount);
		}
//		System.out.println(localUseCount);
		return localUseCount;
	}

//	public List getCountByRegional(String regionalName,String startDate, String endDate, String action){
//		String like = "like";
//		List localUseCount = new ArrayList();
//		int menCount = 0;
//		int womenCount = 0;
//		if (like.equals(action)){
//			startDate = startDate + "%";
//			menCount = toiletPositionUseMapper.selectCountByToiletCodeAndGenderLike(regionalName, toiletCode, startDate, endDate, "男");
//			localUseCount.add(menCount);
//			womenCount = toiletPositionUseMapper.selectCountByToiletCodeAndGenderLike(regionalName, toiletCode, startDate, endDate, "女");
//			localUseCount.add(womenCount);
//		}
//		else {
//			menCount = toiletPositionUseMapper.selectCountByToiletCodeAndGender(regionalName, toiletCode, startDate, endDate, "男");
//			localUseCount.add(menCount);
//			womenCount = toiletPositionUseMapper.selectCountByToiletCodeAndGender(regionalName, toiletCode, startDate, endDate, "女");
//			localUseCount.add(womenCount);
////			resultList.add(localUseCount);
//		}
////		System.out.println(localUseCount);
//		return localUseCount;
//	}

	/**
	 * 添加厕所信息
	 *
	 * @param regionalName
	 * @param ToiletInfo
	 * @return
	 */
	@Override
	public int add(String regionalName, JSONArray ToiletInfo) {
		int resultTotal = 0;
		int result = 0;
		for (int i = 0; i < ToiletInfo.size(); i++) {
			result = toiletMapper.insert(regionalName, ToiletInfo.getJSONObject(i).getString("toiletCode"), ToiletInfo.getJSONObject(i).getString("responsibleName"));
			resultTotal += result;
		}
		return resultTotal;
	}

	/**
	 * 获取指定管理员负责的厕所信息
	 *
	 * @param regionalName 管理员账号
	 * @return 查询到的数据
	 */
	@Override
	public List<ToiletInfo> getToiletInfo(String regionalName) {
		List<ToiletInfo> toiletInfos = toiletMapper.selectByAccount(regionalName);
		return toiletInfos;
	}
}
