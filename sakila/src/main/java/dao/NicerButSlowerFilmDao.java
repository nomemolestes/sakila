package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBUtil;
import vo.NicerButSlowerFilmList;

public class NicerButSlowerFilmDao {
	//페이징 필요함, 다량의 데이터
	public ArrayList<NicerButSlowerFilmList> selectNicerButSlowerFilmListByPage (int beginRow, int rowPerPage) {
		ArrayList<NicerButSlowerFilmList> list = new ArrayList<NicerButSlowerFilmList>();//데이터 arrayList에 저장함
		//초기화
		Connection conn = null;
		PreparedStatement stmt = null;
		//DBUtil호출, static이 되며 객체생성 불필요, 공통부분있어서 묶음
		conn = DBUtil.getConnection();
		//텍스트 쿼리생성, 쿼리문 오타있음 rs is null오류생김, 쿼리전송해서 실행안됐는데 next실행되서 rs is null?이런오류뜸.
		String sql = "select fid, title, description, category, price, length, rating, actors from nicer_but_slower_film_list order by fid limit ?,?"; 
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);//쿼리전송, ?로 데이터를 전달
			stmt.setInt(1, beginRow);//set>작성한 sql문에 있는 물음표를 값으로 바꿔줌.
			stmt.setInt(2, rowPerPage);//set>작성한 sql문에 있는 물음표를 값으로 바꿔줌.
			rs = stmt.executeQuery();//쿼리실행,값을저장,PreparedStatement와 세트...
			while(rs.next()) {//커서가 잡힘, 표에서 선택되는 행을 바꿈, 내려갈 다음행이있다면 true, 아니면 false 되어서 빠져나감.
				//객체생성
				NicerButSlowerFilmList n = new NicerButSlowerFilmList();
				n.setFid(rs.getInt("fid"));//get>컬럼의 숫자,이름을 지정해서 데이터를 불러옴.
				n.setTitle(rs.getString("title"));//
				n.setDescription(rs.getString("description"));//
				n.setCategory(rs.getString("category"));
				n.setPrice(rs.getDouble("price"));
				n.setLength(rs.getInt("length"));
				n.setRating(rs.getString("rating"));
				n.setActors(rs.getString("actors"));
				list.add(n);//값들을 모두 저장함.
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
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		//반환
		return list;

	}
	//전체 행 반환 메서드.
	public int selectNicerButSlowerFilmTotalRow() {
		//변수지정, 초기화
		int row = 0;
		Connection conn = null;
		//텍스트쿼리문 생성, 뷰테이블의 전체 행을 조회
		String sql = "select count(*) cnt from nicer_but_slower_film_list";
		//DBUtil을 호출
		conn = DBUtil.getConnection();
		//execute와 함께, 구하려는 값을 물음표로 쓸수있게됨. 가독성 up
		PreparedStatement stmt = null;
		//쿼리를 실행하면 ResultSet타입으로 반환, 결과값 저장
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);//쿼리실행
			rs = stmt.executeQuery();//결과값저장, 저장값을 타입을 지정해서 한행단위로 불러옴
			if(rs.next()) {//커서가 잡히며 표에서 선택되는 행 바꿀수있음, 커서가 있는 곳 다음행에 값이 읽다면 읽어옴, 아니면 빠져나감.
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
				e.printStackTrace();
			}
		}
		//반환
		return row;
	}
}
