package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.FilmList;

public class FilmDao {
	/* public int totalRow(String category, String rating, double price, int length, String title, String actors) {//분기시켜서 카운트리턴.
		
	} */
	//Film vo~
		public List<FilmList> selectFilmListSearch(int beginRow, int rowPerPage,  String title, String category, double price, int length, String rating, String actor) {		
			List<FilmList> list = new ArrayList<FilmList>();
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			conn = DBUtil.getConnection();
			try {
				// 동적쿼리
				String sql = "SELECT fid, title, description, category, price, length, rating, actors FROM film_list WHERE title LIKE ? AND actors LIKE ?";
				if(category.equals("") && rating.equals("") && price==-1 && length==-1) {
					sql += " ORDER BY fid LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
					stmt.setInt(3, beginRow);
					stmt.setInt(4, rowPerPage);
				} else if(category.equals("") && rating.equals("") && price==-1 && length!=-1) { // length만 입력되었다
					if(length == 0) {
						sql += " AND length<60 ORDER BY fid LIMIT ?, ?";
					} else if(length == 1) {
						sql += " AND length>=60 ORDER BY fid LIMIT ?, ?";
					}
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
					stmt.setInt(3, beginRow);
					stmt.setInt(4, rowPerPage);
				} else if(category.equals("") && rating.equals("") && price!=-1 && length==-1) {
					sql += " AND price=? ORDER BY fid LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, "%"+title+"%");
					stmt.setString(2, "%"+actor+"%");
					stmt.setDouble(3, price);
					stmt.setInt(4, beginRow);
					stmt.setInt(5, rowPerPage);
				}   // 13(+알파)개의 쿼리 분기 추가
	            rs = stmt.executeQuery();
				while(rs.next()) {
					FilmList f = new FilmList();
					f.setFid(rs.getInt("fid"));
					f.setTitle(rs.getString("title"));
					f.setDescription(rs.getString("description"));
					f.setCategory(rs.getString("category"));
					f.setPrice(rs.getDouble("price"));
					f.setLength(rs.getInt("length"));
					f.setRating(rs.getString("rating"));
					f.setActors(rs.getString("actors"));
					list.add(f);
				}
			} catch(SQLException e) {
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
	//임대료 0.99 2.99 4.99를 가져오려고 ,
	public List<Double> selectfilmPriceList() {
	      List<Double> priceList = new ArrayList<Double>();
	      Connection conn = null;
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      conn = DBUtil.getConnection();
	      String sql = "SELECT DISTINCT price FROM film_list ORDER BY price";
	      try {
	         stmt = conn.prepareStatement(sql);
	         rs = stmt.executeQuery();
	         while(rs.next()) {
	        	 priceList.add(rs.getDouble("price"));
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
	      return priceList;
	   }
	//페이징작업 필요
	public ArrayList<FilmList> selectFilmListByPage(int beginRow, int rowPerPage) {
		ArrayList<FilmList> list = new ArrayList<FilmList>();
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
				FilmList f = new FilmList();
				f.setFid(rs.getInt("fid"));//컬럼FID 값을 int타입으로 가져옴
				f.setTitle(rs.getString("title"));
				f.setDescription(rs.getString("description"));
				f.setCategory(rs.getString("category"));
				f.setPrice(rs.getDouble("price"));
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
