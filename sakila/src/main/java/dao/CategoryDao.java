package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.Category;

public class CategoryDao {
	//category list가 필요
	public List<Category> selectCategoryList() {
		List<Category> list = new ArrayList<Category>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;//set은 중복된 행이 들어올수없음, 
		conn = DBUtil.getConnection();//중복된 코드가 많아서 DBUtil 안에 static형태로 묶음
		String sql = "select category_id categoryId, name, last_update lastUpdate from category";//sql 키워드 대문자(키워드,함수)
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Category c = new Category();
				c.setCategoryId(rs.getInt("categoryId"));
				c.setName(rs.getString("name"));
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();//예외처리하지 않았을때 발생하는 것들을 스택메모리에 찍히게되면 예외를 모두 프린트, 예외여부 확인가능
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				
			}
		} 
		return list;
	}
}
