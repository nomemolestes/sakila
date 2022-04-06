package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.Film;

public class FilmDao {
	//임대료 0.99 2.99 4.99를 가져오려고 ,
	public List<Double> selectFilmPriceList() {
		List<Double> list = new ArrayList<Double>();
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = DBUtil.getConnection();
		String sql = "select distinct price from film_list order by price";
		ResultSet rs = null;
		try {
			//sql구문전송
			stmt = conn.prepareStatement(sql);
			//매개변수값대입
			rs = stmt.executeQuery();//조회한 결과물들을 resultset에 저장
			while(rs.next()) {//rs.next로 다음행 내려가면 t, 아니면 f
				list.add(rs.getDouble(1));//숫자를 적으면 select의 순서, price써도 가능	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				//반납
				stmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;//반환 !
		}
	//페이징작업 필요
	public ArrayList<Film> selectFilmListByPage(int beginRow, int rowPerPage) {
		ArrayList<Film> list = new ArrayList<Film>();
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = DBUtil.getConnection();
		String sql = "select FID, title, description, category, price, length, rating, actors from film_list order by FID limit ?,?";
		ResultSet rs = null;
		try {
			//sql구문전송
			stmt = conn.prepareStatement(sql);
			//매개변수값대입
			stmt.setInt(1, beginRow);//몇번째 물음표가 무엇인지
			stmt.setInt(2, rowPerPage);//몇번째 물음표가 무엇인지
			rs = stmt.executeQuery();//조회한 결과물들을 resultset에 저장
			while(rs.next()) {//rs.next로 다음행 내려가면 t, 아니면 f
				Film f = new Film();
				f.setFID(rs.getInt("FID"));//컬럼FID 값을 int타입으로 가져옴
				f.setTitle(rs.getString("title"));
				f.setDescription(rs.getString("description"));
				f.setCategory(rs.getString("category"));
				f.setPrice(rs.getString("price"));
				f.setLength(rs.getInt("length"));
				f.setRating(rs.getString("rating"));
				f.setActors(rs.getString("actors"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				//반납
				stmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;//반환 !
	}
	public int selectFilmTotalRow() {
		int row = 0;
		Connection conn = null;
		String sql = "select count(*) cnt from film_list";
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
				//반납
				stmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				
			}
		}
		return row;//반환
	}
}
