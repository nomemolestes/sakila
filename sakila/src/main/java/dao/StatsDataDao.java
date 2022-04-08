package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBUtil;

public class StatsDataDao {
	//제일 많이 와서 빌렸던 사람
	public List<Map<String, Object>> amountByCoustomer() {
	      List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	      Connection conn = null;
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      conn = DBUtil.getConnection();
	      String sql = "SELECT p.customer_id customerId,"
	            + "      concat(c.first_name,' ', c.last_name) name,"
	            + "      sum(p.amount) total"
	            + "      FROM payment p INNER JOIN customer c"
	            + "      ON p.customer_id = c.customer_id"
	            + "      GROUP BY p.customer_id"
	            + "      having sum(p.amount) < 180"	            
	            + "      ORDER BY SUM(p.amount) DESC limit 1, 10";
	      try {
	         stmt = conn.prepareStatement(sql);
	         rs = stmt.executeQuery();
	         while(rs.next()) {
	            Map<String, Object> m = new HashMap<>();
	            m.put("customerId",rs.getInt("customerId"));
	            m.put("name",rs.getString("name"));
	            m.put("total",rs.getInt("total"));
	            list.add(m);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            rs.close();
	            stmt.close();
	            conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return list;
	   }
	//대여료별 영화수
	public List<Map<String, Object>> amountByRentalRate() {
		List<Map<String, Object>> rentalList = new ArrayList<Map<String,Object>>();
		//초기화
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    //호출
	     conn = DBUtil.getConnection();
		String sql = "SELECT rental_rate rentalRate, COUNT(*) cnt"
						+ " FROM film"
						+ " GROUP BY rental_rate"
						+ " ORDER BY COUNT(*) DESC";
		 try {
	         stmt = conn.prepareStatement(sql);
	         rs = stmt.executeQuery();
	         while(rs.next()) {
	            Map<String, Object> m = new HashMap<>();
	            m.put("rentalRate", rs.getString("rentalRate"));
	            m.put("cnt", rs.getInt("cnt"));
	            rentalList.add(m); // 여기!!!!!!!!!!!!!!!
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            rs.close();
	            stmt.close();
	            conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
		return rentalList;
	}
	//등급별 영화수
	public List<Map<String, Object>> amountByRating() {
		List<Map<String, Object>> ratingList = new ArrayList<Map<String,Object>>();
		//초기화
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    //호출
	     conn = DBUtil.getConnection();
		String sql = " SELECT rating, COUNT(*) cnt"
					+ " FROM film"
					+ " GROUP BY rating"
					+ " ORDER BY COUNT(*) DESC";
		 try {
	         stmt = conn.prepareStatement(sql);
	         rs = stmt.executeQuery();
	         while(rs.next()) {
	            Map<String, Object> m = new HashMap<>();
	            m.put("rating",rs.getString("rating"));
	            m.put("cnt",rs.getInt("cnt"));
	            ratingList.add(m); //여기도 !! list.add(p)
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            rs.close();
	            stmt.close();
	            conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
		 return ratingList;
	}
	//언어별 영화수
	public List<Map<String, Object>> amountByLanguage() {
		List<Map<String, Object>> languageList = new ArrayList<Map<String,Object>>();
		//초기화
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//호출
		conn = DBUtil.getConnection();
		String sql = " SELECT l.name, COUNT(*) cnt"
					+	" FROM film f INNER JOIN language l"
					+	" ON f.language_id = l.language_id"
					+	" GROUP BY l.name";
			try {
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("name",rs.getString("name"));
				m.put("cnt",rs.getString("cnt"));
				languageList.add(m);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
					rs.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return languageList;
	}
	//길이별 영화수
	public List<Map<String, Object>> amountByLength() {
		List<Map<String, Object>> lengthList = new ArrayList<Map<String,Object>>();
		//초기화
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//호출
		conn = DBUtil.getConnection();
		//쿼리문 작성
		String sql = "SELECT t.length2, COUNT(*) cnt"
				+" 		FROM (SELECT title, LENGTH,"
				+"		CASE when LENGTH <= 60 then 0"
				+" 		when length BETWEEN 60 AND 120 then 6"
				+"		when length between 121 AND 180 then 120"
				+"			  ELSE 180"	
				+"		end  LENGTH2"
				+"		FROM film) t"
				+"		GROUP BY t.length2";
		try {
			stmt = conn.prepareStatement(sql);//쿼리문 전송?호출?
			rs = stmt.executeQuery();//저장
			//실행
			while(rs.next()) {
				Map<String, Object> m = new HashMap<String, Object>();//저장된 쿼리문에서 hashmap리스트로 값을 가져옴
				m.put("length2", rs.getInt("length2"));//데이터타입을 써주고
				m.put("cnt", rs.getInt("cnt"));
				lengthList.add(m);//저장
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				//반납
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//반환
		return lengthList;
	}
}
