package com.emp.model;

import java.util.*;
import java.sql.*;

public class EmpJDBCDAO implements EmpDAO_interface {
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
	public void insert(EmpVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, empVO.getPostId());
			pstmt.setInt(2, empVO.getCafeId());
			pstmt.setInt(3, empVO.getMemId());
			pstmt.setDate(4, empVO.getTime());
			pstmt.setString(5, empVO.getTitle());
			pstmt.setString(6, empVO.getContent());
			pstmt.setInt(7, empVO.getCount());
			pstmt.setByte(8, empVO.getStatus());

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
	public void update(EmpVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, empVO.getPostId());
			pstmt.setInt(2, empVO.getCafeId());
			pstmt.setInt(3, empVO.getMemId());
			pstmt.setDate(4, empVO.getTime());
			pstmt.setString(5, empVO.getTitle());
			pstmt.setString(6, empVO.getContent());
			pstmt.setInt(7, empVO.getCount());
			pstmt.setByte(8, empVO.getStatus());
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
	public void delete(Integer empno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, empno);

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
	public EmpVO findByPrimaryKey(Integer empno) {

		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, empno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				empVO = new EmpVO();
				empVO.setPostId(rs.getInt("POST_ID"));
				empVO.setCafeId(rs.getInt("CAFE_ID"));
				empVO.setMemId(rs.getInt("MEM_ID"));
				empVO.setTime(rs.getDate("TIME"));
				empVO.setTitle(rs.getString("TITLE"));
				empVO.setContent(rs.getString("CONTENT"));
				empVO.setCount(rs.getInt("COUNT"));
				empVO.setStatus(rs.getByte("STATUS"));
				
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
		return empVO;
	}

	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

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
				empVO = new EmpVO();
				empVO.setPostId(rs.getInt("POST_ID"));
				empVO.setCafeId(rs.getInt("CAFE_ID"));
				empVO.setMemId(rs.getInt("MEM_ID"));
				empVO.setTime(rs.getDate("TIME"));
				empVO.setTitle(rs.getString("TITLE"));
				empVO.setContent(rs.getString("CONTENT"));
				empVO.setCount(rs.getInt("COUNT"));
				empVO.setStatus(rs.getByte("STATUS"));
				list.add(empVO); // Store the row in the list
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

		EmpJDBCDAO dao = new EmpJDBCDAO();

		// 新增
//		EmpVO empVO1 = new EmpVO();
//		empVO1.setEname("吳永志1");
//		empVO1.setJob("MANAGER");
//		empVO1.setHiredate(java.sql.Date.valueOf("2005-01-01"));
//		empVO1.setSal(new Double(50000));
//		empVO1.setComm(new Double(500));
//		empVO1.setDeptno(10);
//		dao.insert(empVO1);

		// 修改
//		EmpVO empVO2 = new EmpVO();
//		empVO2.setEmpno(7001);
//		empVO2.setEname("吳永志2");
//		empVO2.setJob("MANAGER2");
//		empVO2.setHiredate(java.sql.Date.valueOf("2002-01-01"));
//		empVO2.setSal(new Double(20000));
//		empVO2.setComm(new Double(200));
//		empVO2.setDeptno(20);
//		dao.update(empVO2);

		// 刪除
//		dao.delete(7014);
		
		// 查詢
		EmpVO empVO3 = dao.findByPrimaryKey(7001);
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
		List<EmpVO> list = dao.getAll();
		for (EmpVO aEmp : list) {
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