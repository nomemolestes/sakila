package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import util.DBUtil;

public class RewardDao {
	public Map<String, Object> rewardsReportCall(int minMonthlyPurchase, int minDollarAmountPurchase) {
		  Map<String, Object> map = new HashMap<String,Object>();
		  ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
	      Connection conn = null;
	      CallableStatement stmt = null; 
	      ResultSet rs = null;
	      Integer count = 0;
	      conn = DBUtil.getConnection();
	      try {
	         stmt = conn.prepareCall("{CALL rewards_report(?,?,?)}");
	         stmt.setInt(1, minMonthlyPurchase);
	         stmt.setDouble(2, minDollarAmountPurchase);
	         stmt.registerOutParameter(3, Types.INTEGER);
	         rs = stmt.executeQuery();
	         count = stmt.getInt(3);
			while(rs.next()) {
				HashMap<String, Object> rewardMap = new HashMap<String, Object>(); 
				rewardMap.put("customerId", rs.getInt("customerId"));
				rewardMap.put("storeId", rs.getInt("storeId"));
				rewardMap.put("firstName", rs.getString("firstName"));
				rewardMap.put("lastName", rs.getString("lastName"));
				rewardMap.put("email", rs.getString("email"));
				rewardMap.put("addressId", rs.getString("addressId"));
				rewardMap.put("acrive", rs.getString("active"));
				rewardMap.put("createDate", rs.getString("createDate"));
				rewardMap.put("lastUpdate", rs.getString("lastUpdate"));
				list.add(rewardMap);
			}
			count = stmt.getInt(3); 
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	      map.put("count", count);
	      map.put("list", list);
	
	      return map;
	}

}
