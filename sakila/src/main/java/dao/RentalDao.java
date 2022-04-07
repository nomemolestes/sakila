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
import vo.FilmList;

public class RentalDao {
	public List<Map<String, Object>> selectRentalSearchList(int beginRow, int rowPerPage, int storeId, String customerName, String beginDate, String endDate) {
		List<Map<String, Object>> rentalList = new ArrayList<>();
		//초기화
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//util호출
		conn = DBUtil.getConnection();
		try {
		//필요한 값의 조인 쿼리문 
			/*
			 --고객이름 조인쿼리
	SELECT  
		r.*,
		concat(c.first_name,' ',c.last_name) cName,
		s.staff_id storeId,
		i.film_id filmId,
		f.title title
	FROM rental r 
	INNER JOIN customer c
	ON r.customer_id = c.customer_id
	--위의 결과물과 staff를 조인
		INNER JOIN staff s
		ON r.staff_id = s.staff_id
	--inventory_id를 통해 film_id출력
			INNER JOIN inventory i
			ON r.inventory_id = i.inventory_id
	--film_id까지
				INNER JOIN film f
					ON i.film_id = f.film_id

			 */
		String sql = "SELECT"
			+ "	r.rental_id rentalId, r.rental_date rentalDate, r.inventory_id inventoryId,"
			+ " r.customer_id customerId, r.return_date returnDate, r.staff_id staffId, r.last_update lastUpdate"
	    	+ "	CONCAT(c.first_name ,' ', c.last_name) cName,"
			+ " FROM rental r"
			+ " INNER JOIN customer c"
			+ " on r.customer_id = c.customer_id"
			+ " INNER JOIN staff s"
			+ " ON r.staff_id = s.staff_id"
			+ " INNER JOIN inventory i"
			+ " ON i.inventory_id = r.inventory_id"
			+ " INNER JOIN film f"
			+ " ON f.film_id = i.film_id;"
			+ " where concat(c.first_name,' ',c.last_name) like ? ";
		//조건문과 무슨타입의 어떤값을 넣는지...
		if(storeId == -1 && beginDate.equals("") && endDate.equals("")) {//경우의수; 모두선택안함 000
			sql += " order by rental_id limit ?,?";
			stmt = conn.prepareStatement(sql);//sql호출
			stmt.setString(1, "%"+customerName+"%");//값 대입
			stmt.setInt(2, beginRow);//값 대입
			stmt.setInt(3, rowPerPage);//값 대입
			
		} else if (storeId == -1 && !beginDate.equals("") && endDate.equals("")) {//시작날짜선택 010
			sql += " and r.rental_date between STR_TO_DATE(?, '%Y-%m-%d') AND now() order by rental_id limit ?,?";
			stmt = conn.prepareStatement(sql);//sql호출
			stmt.setString(1, "%"+customerName+"%");//값 대입
			stmt.setString(2, beginDate);//값 대입
			stmt.setInt(3, beginRow);//값 대입
			stmt.setInt(4, rowPerPage);//값 대입
			
		} else if (storeId == -1 && beginDate.equals("") && !endDate.equals("")) { //끝나는날 선택 001
			sql += " AND r.rental_date BETWEEN STR_TO_DATE('0000-01-01','%Y-%m-%d') and STR_TO_DATE(?, '%Y-%m-%d') order by rental_id limit ?,?";
			stmt = conn.prepareStatement(sql);//sql호출
			stmt.setString(1, "%"+customerName+"%");//값 대입
			stmt.setString(2, endDate);//값 대입
			stmt.setInt(3, beginRow);//값 대입
			stmt.setInt(4, rowPerPage);//값 대입
			
		} else if (storeId == -1 && !beginDate.equals("") && !endDate.equals("")) {//시작과 끝나는날 선택 011
			sql += " AND r.rental_date between STR_TO_DATE(?, '%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') order by rental_id limit?,?";
			stmt = conn.prepareStatement(sql);//sql호출
			stmt.setString(1, "%"+customerName+"%");//값 대입
			stmt.setString(2, beginDate);//값 대입
			stmt.setString(3, endDate);//값 대입
			stmt.setInt(4, beginRow);//값 대입
			stmt.setInt(5, rowPerPage);//값 대입
			
		} else if (storeId != -1 && beginDate.equals("") && endDate.equals("")) {//가게번호 선택 100
			sql += " and s.store_id=? order by rental_id limit ?,?";
			stmt = conn.prepareStatement(sql);//sql호출
			stmt.setString(1, "%"+customerName+"%");//값 대입
			stmt.setInt(2, storeId);//값 대입
			stmt.setInt(3, beginRow);//값 대입
			stmt.setInt(4, rowPerPage);//값 대입
			
		} else if (storeId != -1 && !beginDate.equals("") && endDate.equals("")) {//가게번호, 시작날짜 선택 110
			sql += " and s.store_id=? and r.rental_date between STR_TO_DATE(?, '%Y-%m-%d') AND now() order by rental_id limit ?,?";
			stmt = conn.prepareStatement(sql);//sql호출
			stmt.setString(1, "%"+customerName+"%");//값 대입
			stmt.setInt(2, storeId);//값 대입
			stmt.setString(3, beginDate);//값대입
			stmt.setInt(4, beginRow);//값 대입
			stmt.setInt(5, rowPerPage);//값 대입
			
		} else if (storeId != -1 && beginDate.equals("") && !endDate.equals("")) {//가게번호, 끝나는날 선택 101
			sql += " and and s.store_id=? and r. rental_date between STR_TO_DATE(0000-01-01, '%Y-%m-%d') AND STR_TO_DATE(?, '%Y-%m-%d') order by rental_id limit ?,?";
			stmt = conn.prepareStatement(sql);//sql호출
			stmt.setString(1, "%"+customerName+"%");//값 대입
			stmt.setInt(2, storeId);//값 대입
			stmt.setString(3, endDate);//값대입
			stmt.setInt(4, beginRow);//값 대입
			stmt.setInt(5, rowPerPage);//값 대입
			
		} else if (storeId != -1 && !beginDate.equals("") && !endDate.equals("")) {//모두선택안함 111
			sql += " and s.store_id=? and r.rental_date between STR_TO_DATE(?, '%Y-%m-%d') AND STR_TO_DATE(?, '%Y-%m-%d') order by rental_id limit ?,?";
			stmt = conn.prepareStatement(sql);//sql 호출
			stmt.setString(1, "%"+customerName+"%");//값 대입
			stmt.setInt(2, storeId);//값 대입
			stmt.setString(3, beginDate);//값 대입
			stmt.setString(4, endDate);//값대입
			stmt.setInt(5, beginRow);//값 대입
			stmt.setInt(6, rowPerPage);//값 대입	
		}
		   rs = stmt.executeQuery();//가져올 결과값 저장, 조인된쿼리컬럼
		   while(rs.next()) {
			  HashMap<String, Object> rentalMap = new  HashMap<String, Object>(); 
				rentalMap.put("rentalId", rs.getInt("rentalId"));
				rentalMap.put("rentalDate", rs.getString("rentalDate"));
				rentalMap.put("inventoryId", rs.getInt("inventoryId"));
				rentalMap.put("customerId", rs.getInt("customerId"));
				rentalMap.put("returnDate", rs.getString("returnDate"));
				rentalMap.put("staffId", rs.getInt("staffId"));
				rentalMap.put("lastUpdate", rs.getString("lastUpdate"));
				rentalMap.put("cName", rs.getString("cName"));
				rentalMap.put("storeId", rs.getInt("storeId"));
				rentalMap.put("filmId", rs.getInt("filmId"));
				rentalMap.put("title", rs.getString("title"));
				rentalList.add(rentalMap);
		   }			
		} catch (Exception e) { // ClassNotFoundException, SQLException두개의 예외를 부모타입 Exception으로 처리 -> 다형성
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
	public int selectRentalSearchTotalRow(int storeId, String customerName, String beginDate, String endDate) {
		int totalRow = 0;
		//초기화
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//호출
		conn = DBUtil.getConnection();
		try {
			String sql = "SELECT"
					+ "	count(*) cnt"
					+ " FROM rental r"
					+ " INNER JOIN customer c"
					+ " on r.customer_id = c.customer_id"
					+ " INNER JOIN staff s"
					+ " ON r.staff_id = s.staff_id"
					+ " INNER JOIN inventory i"
					+ " ON i.inventory_id = r.inventory_id"
					+ " INNER JOIN film f"
					+ " ON f.film_id = i.film_id;"
					+ " where concat(c.first_name,' ', c.last_name) like ?";
			//조건문과 무슨타입의 어떤값을 넣는지...
			if(storeId == -1 && beginDate.equals("") && endDate.equals("") ) {//경우의수; 모두선택안함 000
				stmt = conn.prepareStatement(sql);//sql호출
				stmt.setString(1, "%"+customerName+"%");//값 대입
				
			} else if (storeId == -1 && !beginDate.equals("") && endDate.equals("")) {//시작날짜선택 010
				sql += " AND r.rental_date between STR_TO_DATE(?, '%Y-%m-%d') and now()";
				stmt = conn.prepareStatement(sql);//sql호출
				stmt.setString(1, "%"+customerName+"%");//값 대입
				stmt.setString(2, beginDate);//값 대입
				
			} else if (storeId == -1 && beginDate.equals("") && !endDate.equals("")) { //끝나는날 선택 001
				sql += " AND r.rental_date between STR_TO_DATE('0000-01-01', '%Y-%m-%d') and STR_TO_DATE(?,'%Y-%m-%d')";
				stmt = conn.prepareStatement(sql);//sql호출
				stmt.setString(1, "%"+customerName+"%");//값 대입
				stmt.setString(2, endDate);//값 대입
				
			} else if (storeId == -1 && !beginDate.equals("") && !endDate.equals("")) {//시작과 끝나는날 선택 011
				sql += " AND r.rental_date between STR_TO_DATE(?, '%Y-%m-%d') AND STR_TO_DATE(?, '%Y-%m-%d')";
				stmt = conn.prepareStatement(sql);//sql호출
				stmt.setString(1, "%"+customerName+"%");//값 대입
				stmt.setString(2, beginDate);//값 대입
				stmt.setString(3, endDate);//값 대입
				
			} else if (storeId != -1 && beginDate.equals("") && endDate.equals("")) {//가게번호 선택 100
				sql += " and s.store_id=?";
				stmt = conn.prepareStatement(sql);//sql호출
				stmt.setString(1, "%"+customerName+"%");//값 대입
				stmt.setInt(2, storeId);//값 대입
				
			} else if (storeId != -1 && !beginDate.equals("") && endDate.equals("")) {//가게번호, 시작날짜 선택 110
				sql += " and s.store_id=? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND NOW()";
				stmt = conn.prepareStatement(sql);//sql호출
				stmt.setString(1, "%"+customerName+"%");//값 대입
				stmt.setInt(2, storeId);//값 대입
				stmt.setString(3, beginDate);//값대입
				
			} else if (storeId != -1 && beginDate.equals("") && !endDate.equals("")) {//가게번호, 끝나는날 선택 101
				sql += " and s.store_id=? and r.rental_date between STR_TO_DATE(0000-01-01, '%Y-%m-%d') and str_to_date(?, '%Y-%m-%d')";
				stmt = conn.prepareStatement(sql);//sql호출
				stmt.setString(1, "%"+customerName+"%");//값 대입
				stmt.setInt(2, storeId);//값 대입
				stmt.setString(3, endDate);//값대입
				
			} else if (storeId != -1 && !beginDate.equals("") && !endDate.equals("")) {//모두선택안함 111
				sql += " and s.store_id=? and r.rental_date between STR_TO_DATE(?, '%Y-%m-%d') AND STR_TO_DATE(?, '%Y-%m-%d')";
				stmt = conn.prepareStatement(sql);//sql 호출
				stmt.setString(1, "%"+customerName+"%");//값 대입
				stmt.setInt(2, storeId);//값 대입
				stmt.setString(3, beginDate);//값 대입
				stmt.setString(4, endDate);//값대입
			} 
			rs = stmt.executeQuery();
			if(rs.next()) {
				totalRow = rs.getInt("cnt");
			}
		}	catch (SQLException e) {
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
		return totalRow;
	}
	
}