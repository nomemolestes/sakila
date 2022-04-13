package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBUtil;
import vo.SalesByFilmCategory;

public class SalesByFilmCategoryDao {
	//페이징
	public ArrayList<SalesByFilmCategory> selectSalesByFilmCategoryByPage(int beginRow, int rowPerPage) {
		ArrayList<SalesByFilmCategory> list = new ArrayList<SalesByFilmCategory>();//데이터 arrayList에 저장함
		//초기화
		Connection conn = null;
		PreparedStatement stmt = null;
		//DBUtil호출,static이 되며 객체생성 불필요, 공통부분의 묶음
		conn = DBUtil.getConnection();
		//텍스트 쿼리생성, 컬럼이름 오타확인 !!!!!!!! 오타생기면 rs is null오류뜸...
		String sql = "select category, total_sales totalSales from sales_by_film_category order by category limit ?,?";
		ResultSet rs = null;
		try {
			//쿼리전송, 물음표로 있는 데이터 전달
			stmt = conn.prepareStatement(sql);
			//set은 작성한 쿼리문에 있는 물음표를 값으로 바꿈
			stmt.setInt(1, beginRow);
			//쿼리문 물음표를 값으로 바꿈
			stmt.setInt(2, rowPerPage);
			//쿼리실행.. 값을저장함, PreparedStatement와 함께..
			rs = stmt.executeQuery();
			//next()는 커서가 잡히고 표에서 선택되는 행바꿈, 내려갈 행이있으면 값들을 가져오고 아니면 while문 빠져나감.
			while(rs.next()) {
				SalesByFilmCategory s = new SalesByFilmCategory();
				s.setCategory(rs.getString("category"));
				s.setTotalSales(rs.getDouble("totalSales"));
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
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		//반환
		return list;
	}
	//전체 행 반환 메서드	
	public int selectSalesByFilmCategoryTotalRow() {
		int row = 0;//초기화
		Connection conn = null;
		//텍스트쿼리문 생성, 전체행을 조회함.
		String sql = "select count(*) cnt from sales_by_film_category";
		//호출
		conn = DBUtil.getConnection();
		//execute-와 함께사용, 구하려는 값을 물음표로 쓸수있음, 길어질경우 가독성 높아짐 /statement와 비교
		PreparedStatement stmt = null;
		//쿼리를 실행하면 ResultSet타입으로 반환하고 결과값을 저장함
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);//쿼리실행
			rs = stmt.executeQuery();//결과값 저장, 저장한 값을 타입을 지정해서 한행단위로 불러옴
			if(rs.next()) {
				row = rs.getInt("cnt");
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
		return row;//반환
	}
}
