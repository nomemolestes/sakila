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

public class RewardDao { //rewards_report call
	public Map<String, Object> rewardsReportCall(int minMonthlyPurchase, int minDollarAmountPurchase) {
		  Map<String, Object> map = new HashMap<String,Object>();//해시맵에 데이터를 저장
		  ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();//데이터 저장
		  //db 초기화
	      Connection conn = null;
	      CallableStatement stmt = null; //프로시저 실행용 인터페이스
	      ResultSet rs = null;
	      //쿼리의 count 변수 초기화
	      Integer count = 0;
	      //DBUtil 호출
	      conn = DBUtil.getConnection();
	      //try catch
	      try {
	    	  //쿼리호출?전송?
	         stmt = conn.prepareCall("{CALL rewards_report(?,?,?)}");//프로시저 호출하는 메서드
	         stmt.setInt(1, minMonthlyPurchase);//몇번째 물음표가 무엇인지, 프로시저에 사용할 인자값?이 무엇인지에대해
	         stmt.setDouble(2, minDollarAmountPurchase);//몇번째 물음표가 무엇인지
	         stmt.registerOutParameter(3, Types.INTEGER);//프로시저에서 넘어오는값
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
