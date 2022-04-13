package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBUtil;
import vo.StaffViewList;

public class StaffViewDao {
	public ArrayList<StaffViewList> selectStaffViewListByPage(int beginRow, int rowPerPage) {
		ArrayList<StaffViewList> list = new ArrayList<StaffViewList>();
		//초기화
		Connection conn = null;
		PreparedStatement stmt = null;
		//DBUtil호출
		conn = DBUtil.getConnection();
		//텍스트 쿼리생성, 컬럼이름 오타확인중요..
		String sql = "select id, name, address, 'zip code' zipCode, phone, city, country, sid from staff_list order by id limit ?,?";
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			//set은 작성한 쿼리문에 있는 물음표를 값으로 바꿈.
			stmt.setInt(1, beginRow);
			//set은 작성한 쿼리문에 있는 물음표를 값으로 바꿈.
			stmt.setInt(2, rowPerPage);
			//쿼리실행 값을 저장, PreparedStatement와 함께사용..
			rs = stmt.executeQuery();
			//next()는 커서가 잡히고 표에서 선택되는 행바꿈, 내려갈 행이 있음 값들을 갖고옥 아니면 반복문 빠져나감
			while(rs.next()) {
				StaffViewList s = new StaffViewList();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setAddress(rs.getString("address"));
				s.setZipCode(rs.getString("zipCode"));
				s.setPhone(rs.getLong("phone"));
				s.setCity(rs.getString("city"));
				s.setCountry(rs.getString("country"));
				s.setSid(rs.getInt("sid"));
				list.add(s);
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
		return list;
}
	//전체행반환 메서드
	public int selectSalesByStoreTotalRow() {
		int row = 0;//초기화
		Connection conn = null;
		//텍스트 쿼리문생성, 컬럼이름 오타확인중요
		String sql = "select count(*) cnt from staff_list";
		//호출
		conn = DBUtil.getConnection();
		//execute-와 함께사용, 구하려는 값을 물음표로 쓸수있음, 길어질 경우 가독성이 높음 !/ statement와 비교
		PreparedStatement stmt = null;
		//쿼리를 실행하면 ResultSet타입으로 반환하고 결과값을 저장함
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				row = rs.getInt("cnt");
			}
		} catch (SQLException e) {
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
		return row;
	}	
}