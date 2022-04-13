package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBUtil;

public class RewardDao { //rewards_report call
		 public Map<String, Object> rewardsReportCall(int minMonthlyPurchase, double minDollarAmountPurchase) {
			  List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			  Map<String, Object> map = null;
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
		         count = stmt.getInt(3); // 프로시저 3번째 out변수 값 
		         while(rs.next()) {
		        	Map<String,Object> tempMap = new HashMap<String,Object>();
		        	tempMap.put("customerId",rs.getInt("customer_id"));
		        	tempMap.put("storeId",rs.getInt("store_id"));
		        	tempMap.put("firstName",rs.getString("first_name"));
		        	tempMap.put("lastName",rs.getString("last_name"));
		        	tempMap.put("email",rs.getString("email"));
		        	tempMap.put("addressId",rs.getInt("address_id"));
		        	tempMap.put("active",rs.getInt("active"));
		        	tempMap.put("createDate",rs.getString("create_date"));
		        	tempMap.put("lastUpdate",rs.getString("last_update"));
		            list.add(tempMap);
		         }
		         map = new HashMap<String, Object>(); 
		         map.put("count", stmt.getInt(3)); 
		         map.put("list", list); 
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }  finally {
		    	  try {
		            //반납
		            rs.close();
		            stmt.close();
		            conn.close();
		         } catch (SQLException e) {
		            e.printStackTrace();
		         }
		      }
		      return map;
		   }
}
