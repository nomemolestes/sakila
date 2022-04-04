package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBUtil;
import vo.Customer;

public class CustomerDao {
	//페이징작업 필요
	public ArrayList<Customer> selectCustomerListByPage(int beginRow, int rowPerPage) {
		ArrayList<Customer> list = new ArrayList<Customer>();
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = DBUtil.getConnection();
		String sql = "select ID, name, address, zip_code zipCode, phone, city, country, notes, SID from customer_list order by ID limit ?,? ";
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) { 
				Customer c = new Customer();
				c.setId(rs.getInt("ID"));
				c.setName(rs.getString("name"));
				c.setAddress(rs.getString("address"));
				c.setZipCode(rs.getString("zipCode"));
				c.setPhone(rs.getString("phone"));
				c.setCity(rs.getString("city"));
				c.setCountry(rs.getString("country"));
				c.setNotes(rs.getString("notes"));
				c.setSID(rs.getInt("SID"));
				list.add(c);
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
		return list;//반환 !!!!!!!!
	}
	//전체 행의 수를 반환하는 메서드
	public int selectCustomerTotalRow() {
		int row = 0;
		Connection conn = null;
		String sql = "select count(*) cnt from customer_list";
		conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();	
			if(rs.next()) {
				row = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				
			}
		}
		return row; //반환 !!!!!!!!
		}
}
