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

public class RentalDao {
	public List<Map<String, Object>> selectRentalSearchList(int storeId, String customerName, String beginDate, String endDate, int beginRow, int rowPerPage){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//초기화
		Connection conn = null;
		PreparedStatement stmt =null;
		ResultSet rs = null;
		try {
			//호출
			conn = DBUtil.getConnection();
			String sql = "SELECT r.rental_id rentalId, r.staff_id staffId, r.inventory_id inventoryId, r.customer_id customerId,"
					+ "		CONCAT (c.first_name,' ', c.last_name) customerName, r.rental_date rentalDate, r.return_date returnDate, s.store_id storeId, i.film_id filmId, f.title"
					+ "		FROM rental r INNER JOIN customer c"
					+ "		ON r.customer_id = c.customer_id"
					+ "		INNER JOIN staff s"
					+ "		ON r.staff_id = s.staff_id"
					+ "		INNER JOIN inventory i"
					+ "		ON r.inventory_id = i.inventory_id"
					+ "		INNER JOIN film f"
					+ "		ON i.film_id = f.film_id"
					+ "		AND CONCAT (c.first_name,' ', c.last_name) LIKE ?";

				if(storeId == -1 && beginDate.equals("") && endDate.equals("")) { //111
				sql += " ORDER BY rental_id limit ?,?"; 
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, beginRow);
				stmt.setInt(3, rowPerPage);
			} else if (storeId == -1 && beginDate.equals("") && !endDate.equals("")) {//110
				sql += " AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND NOW() ORDER BY rental_id limit ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, beginDate);
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			} else if (storeId == -1 && !beginDate.equals("") && endDate.equals("")) {//010
				sql += " AND r.rental_date BETWEEN STR_TO_DATE(2000-01-01,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ORDER BY rental_id limit ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, endDate);
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			} else if (storeId == -1 && !beginDate.equals("") && !endDate.equals("")) {//011
				sql += " AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d')";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, beginDate);
				stmt.setString(3, endDate);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if (storeId != -1 && beginDate.equals("") && endDate.equals("")) { //100
				sql += " AND s.store_id=? ORDER BY rental_id limit ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			} else if (storeId != -1 && beginDate.equals("") && !endDate.equals("")) { //101
				sql += " AND s.store_id =? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND NOW() ORDER BY rental_id limit ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				stmt.setString(3, beginDate);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if (storeId != -1 && !beginDate.equals("") && endDate.equals("")) { //110
				sql += " AND s.stroe_id =? AND r.rental_date BETWEEN STR_TO_DATE(2000-01-01,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ORDER BY rental_id limit ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				stmt.setString(3, endDate);
				stmt.setInt(4, beginRow);
				stmt.setInt(5,rowPerPage);
			} else if (storeId != -1 && !beginDate.equals("") && !endDate.equals("")) { //111
				sql += " AND s.store_id =? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ORDER BY rental_id limit ?,?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				stmt.setString(3, beginDate);
				stmt.setString(4, endDate);
				stmt.setInt(5, beginRow);
				stmt.setInt(6, rowPerPage);
			}
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String,Object> map = new HashMap<>();
				map.put("rentalId", rs.getInt("rentalId"));
				map.put("storeId", rs.getInt("storeId"));
				map.put("staffId", rs.getInt("staffId"));
				map.put("inventoryId", rs.getInt("inventoryId"));
				map.put("customerId", rs.getInt("customerId"));
				map.put("customerName", rs.getString("customerName"));
				map.put("rentalDate", rs.getString("rentalDate"));
				map.put("returnDate", rs.getString("returnDate"));
				map.put("filmId", rs.getString("filmId"));
				map.put("title", rs.getString("title"));
				list.add(map);			
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		return list;
	}


	public int selectRentalTotalRow(int storeId, String customerName, String beginDate, String endDate) { //검색결과 총갯수 메서드
		int totalRow = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		try {
			String sql = "SELECT COUNT(*) cnt"
					+ "		FROM rental r INNER JOIN customer c"
					+ "		ON r.customer_id = c.customer_id"
					+ "			INNER JOIN staff s"
					+ "			ON r.staff_id = s.staff_id"
					+ "				INNER JOIN inventory i"
					+ "				ON r.inventory_id = i.inventory_id"
					+ "					INNER JOIN film f"
					+ "					ON i.film_id = f.film_id"
					+ "		WHERE CONCAT (c.first_name,' ', c.last_name) LIKE ?";

			if(storeId==-1&&beginDate.equals("")&&endDate.equals("")){ 
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
			}else if(storeId==-1&&beginDate.equals("")&&!endDate.equals("")) {
				sql = sql + " AND r.rental_date BETWEEN STR_TO_DATE('2000-01-01','%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d')";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, endDate);
			}else if(storeId==-1&&!beginDate.equals("")&&endDate.equals("")) {
				sql = sql + " AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND NOW()";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, beginDate);
			}else if(storeId==-1&&!beginDate.equals("")&&!endDate.equals("")) {
				sql = sql + " AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d')";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, beginDate);
				stmt.setString(3, endDate);
			}else if(storeId!=-1&&beginDate.equals("")&&endDate.equals("")) {
				sql = sql + " AND s.store_id=? ";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
			}else if(storeId!=-1&&beginDate.equals("")&&!endDate.equals("")) {
				sql = sql + " AND s.store_id=? AND r.rental_date BETWEEN STR_TO_DATE('2000-01-01','%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d')  ";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				stmt.setString(3, endDate);
			}else if(storeId!=-1&&!beginDate.equals("")&&endDate.equals("")) {
				sql = sql + " AND s.store_id=? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND NOW()  ";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				stmt.setString(3, beginDate);
			}else if(storeId!=-1&&!beginDate.equals("")&&!endDate.equals("")) {
				sql = sql + " AND s.store_id=? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				stmt.setString(3, beginDate);
				stmt.setString(4, endDate);
			}
			rs = stmt.executeQuery();
			if(rs.next()) {
				totalRow=rs.getInt("cnt");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return totalRow;
	}

}