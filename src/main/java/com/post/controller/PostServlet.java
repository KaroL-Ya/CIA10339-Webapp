package com.post.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.post.model.PostService;
import com.post.model.PostVO;

@WebServlet("/back_end/post/post.do")
public class PostServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("postId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/post/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer postId = null;
				try {
					postId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/post/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				PostService postSvc = new PostService();
				PostVO postVO = postSvc.getOnePost(postId);
				if (postVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/post/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("postVO", postVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/post/listOnePost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer postId = Integer.valueOf(req.getParameter("postId"));
				
				/***************************2.開始查詢資料****************************************/
				PostService postSvc = new PostService();
				PostVO postVO = postSvc.getOnePost(postId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("postVO", postVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back_end/post/update_post_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
Integer postId = Integer.valueOf(req.getParameter("postId").trim());
Integer cafeId = Integer.valueOf(req.getParameter("cafeId"));
Integer memId = Integer.valueOf(req.getParameter("memId"));
				
//String ename = req.getParameter("ename");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.add("員工姓名: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
				
	java.sql.Date time = null;
	try {
	time = java.sql.Date.valueOf(req.getParameter("time").trim());
	} catch (IllegalArgumentException e) {
		time=new java.sql.Date(System.currentTimeMillis());
		errorMsgs.add("請輸入日期!");
	}
	
String title = req.getParameter("title").trim();
				if (title == null || title.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}	
String content = req.getParameter("content").trim();
				if (content == null || content.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}	
				
		Integer count = Integer.valueOf(req.getParameter("count"));
		
		Byte status = Byte.valueOf(req.getParameter("status"));
//				Double sal = null;
//				try {
//sal = Double.valueOf(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					sal = 0.0;
//					errorMsgs.add("薪水請填數字.");
//				}

//				Double comm = null;
//				try {
//comm = Double.valueOf(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					comm = 0.0;
//					errorMsgs.add("獎金請填數字.");
//				}

Integer deptno = Integer.valueOf(req.getParameter("post").trim());

				PostVO postVO = new PostVO();
				postVO.setPostId(postId);
				postVO.setCafeId(cafeId);
				postVO.setMemId(memId);
				postVO.setTime(time);
				postVO.setTitle(title);
				postVO.setContent(content);
				postVO.setCount(count);
				postVO.setStatus(status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("postVO", postVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/post/update_post_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				PostService empSvc = new PostService();
				postVO = empSvc.updatePost( postId ,cafeId, memId ,time, title , content,  count , status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("postVO", postVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back_end/post/listOnePost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//String ename = req.getParameter("ename");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.add("員工姓名: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
				
//String job = req.getParameter("job").trim();
//				if (job == null || job.trim().length() == 0) {
//					errorMsgs.add("職位請勿空白");
//				}
//				
//				java.sql.Date hiredate = null;
//				try {
//hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
//				
//				Double sal = null;
//				try {
//sal = Double.valueOf(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					sal = 0.0;
//					errorMsgs.add("薪水請填數字.");
//				}
//				
//				Double comm = null;
//				try {
//comm = Double.valueOf(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					comm = 0.0;
//					errorMsgs.add("獎金請填數字.");
//				}
//				
//Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());
			Integer postId = Integer.valueOf(req.getParameter("postId").trim());
			Integer cafeId = Integer.valueOf(req.getParameter("cafeId"));
			Integer memId = Integer.valueOf(req.getParameter("memId"));
			java.sql.Date time = null;
			try {
			time = java.sql.Date.valueOf(req.getParameter("time").trim());
			} catch (IllegalArgumentException e) {
				time=new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}
			
		String title = req.getParameter("title").trim();
						if (title == null || title.trim().length() == 0) {
							errorMsgs.add("請勿空白");
						}	
		String content = req.getParameter("content").trim();
						if (content == null || content.trim().length() == 0) {
							errorMsgs.add("請勿空白");
						}	
						
				Integer count = Integer.valueOf(req.getParameter("count"));
				
				Byte status = Byte.valueOf(req.getParameter("status"));
			
				PostVO postVO = new PostVO();
				postVO.setPostId(postId);
				postVO.setCafeId(cafeId);
				postVO.setMemId(memId);
				postVO.setTime(time);
				postVO.setTitle(title);
				postVO.setContent(content);
				postVO.setCount(count);
				postVO.setStatus(status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("postVO", postVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				PostService postSvc = new PostService();
				postVO = postSvc.addPost(postId, cafeId, memId, time, title, content,count,status);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/post/listAllPost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer postId = Integer.valueOf(req.getParameter("postId"));
				
				/***************************2.開始刪除資料***************************************/
				PostService postSvc = new PostService();
				postSvc.deletePost(postId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/post/listAllPost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
}
