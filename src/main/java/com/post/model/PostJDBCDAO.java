package com.post.model;

import java.util.*;
import java.sql.*;

public class PostJDBCDAO implements PostDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/ciag07?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "Asbod3102";

	private static final String INSERT_STMT = 
		"INSERT INTO post (POST_ID , CAFE_ID, MEM_ID , TIME, TITLE , CONTENT, COUNT, STATUS) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT POST_ID , CAFE_ID, MEM_ID , TIME, TITLE , CONTENT, COUNT, STATUS FROM post order by POST_ID";
	private static final String GET_ONE_STMT = 
		"SELECT POST_ID , CAFE_ID, MEM_ID , TIME, TITLE , CONTENT, COUNT, STATUS FROM post where POST_ID = ?";
	private static final String DELETE = 
		"DELETE FROM post where POST_ID = ?";
	private static final String UPDATE = 
		"UPDATE post set POST_ID=? , CAFE_ID=?, MEM_ID=? , TIME?, TITLE=? , CONTENT=?,  COUNT=? ,STATUS=?  where POST_ID = ?";

	@Override
	public void insert(PostVO postVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1,postVO.getPostId());
			pstmt.setInt(2, postVO.getCafeId());
			pstmt.setInt(3, postVO.getMemId());
			pstmt.setDate(4,postVO.getTime());
			pstmt.setString(5, postVO.getTitle());
			pstmt.setString(6, postVO.getContent());
			pstmt.setInt(7, postVO.getCount());
			pstmt.setByte(8, postVO.getStatus());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(PostVO postVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, postVO.getPostId());
			pstmt.setInt(2, postVO.getCafeId());
			pstmt.setInt(3, postVO.getMemId());
			pstmt.setDate(4, postVO.getTime());
			pstmt.setString(5, postVO.getTitle());
			pstmt.setString(6, postVO.getContent());
			pstmt.setInt(7, postVO.getCount());
			pstmt.setByte(8, postVO.getStatus());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer postId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, postId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public PostVO findByPrimaryKey(Integer postId) {

		PostVO  postVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, postId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				postVO = new PostVO();
				postVO.setPostId(rs.getInt("POST_ID"));
				postVO.setCafeId(rs.getInt("CAFE_ID"));
				postVO.setMemId(rs.getInt("MEM_ID"));
				postVO.setTime(rs.getDate("TIME"));
				postVO.setTitle(rs.getString("TITLE"));
				postVO.setContent(rs.getString("CONTENT"));
				postVO.setCount(rs.getInt("COUNT"));
				postVO.setStatus(rs.getByte("STATUS"));
				
			}
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return postVO;
	}

	@Override
	public List<PostVO> getAll() {
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO postVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				postVO = new PostVO();
				postVO.setPostId(rs.getInt("POST_ID"));
				postVO.setCafeId(rs.getInt("CAFE_ID"));
				postVO.setMemId(rs.getInt("MEM_ID"));
				postVO.setTime(rs.getDate("TIME"));
				postVO.setTitle(rs.getString("TITLE"));
				postVO.setContent(rs.getString("CONTENT"));
				postVO.setCount(rs.getInt("COUNT"));
				postVO.setStatus(rs.getByte("STATUS"));
				list.add(postVO); // Store the row in the list
			}
			

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		PostJDBCDAO dao = new PostJDBCDAO();

		// 新增
		PostVO postVO1 = new PostVO();
		postVO1.setPostId(1);
		postVO1.setCafeId(1);
		postVO1.setMemId(1);
		postVO1.setTime(java.sql.Date.valueOf("2005-01-01"));
		postVO1.setTitle("");
		postVO1.setContent("");
		postVO1.setCount(10);
		postVO1.setStatus((byte) 1);
		dao.insert(postVO1);

		// 修改
		PostVO postVO2 = new PostVO();
		postVO2.setPostId(1);
		postVO2.setCafeId(1);
		postVO2.setMemId(1);
		postVO2.setTime(java.sql.Date.valueOf("2005-01-01"));
		postVO2.setTitle("");
		postVO2.setContent("");
		postVO2.setCount(10);
		postVO2.setStatus((byte) 1);
		dao.update(postVO2);

		// 刪除
		dao.delete(7014);
		
		// 查詢
		PostVO empVO3 = dao.findByPrimaryKey(7001);
		System.out.print(empVO3.getPostId() + ",");
		System.out.print(empVO3.getCafeId() + ",");
		System.out.print(empVO3.getMemId() + ",");
		System.out.print(empVO3.getTime() + ",");
		System.out.print(empVO3.getTitle() + ",");
		System.out.print(empVO3.getContent() + ",");
		System.out.println(empVO3.getCount());
		System.out.println(empVO3.getStatus());
		System.out.println("---------------------");

		// 查詢
		List<PostVO> list = dao.getAll();
		for (PostVO aEmp : list) {
			System.out.print(aEmp.getPostId() + ",");
			System.out.print(aEmp.getCafeId() + ",");
			System.out.print(aEmp.getMemId() + ",");
			System.out.print(aEmp.getTime() + ",");
			System.out.print(aEmp.getTitle() + ",");
			System.out.print(aEmp.getContent() + ",");
			System.out.println(aEmp.getCount());
			System.out.println(aEmp.getStatus());
			System.out.println();
		}
	}
}