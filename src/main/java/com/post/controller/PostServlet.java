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
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("postId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/post/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer postId = null;
				try {
					postId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/post/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				PostService postSvc = new PostService();
				PostVO postVO = postSvc.getOnePost(postId);
				if (postVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/post/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("postVO", postVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back_end/post/listOnePost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer postId = Integer.valueOf(req.getParameter("postId"));
				
				/***************************2.�}�l�d�߸��****************************************/
				PostService postSvc = new PostService();
				PostVO postVO = postSvc.getOnePost(postId);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("postVO", postVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back_end/post/update_post_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
Integer postId = Integer.valueOf(req.getParameter("postId").trim());
Integer cafeId = Integer.valueOf(req.getParameter("cafeId"));
Integer memId = Integer.valueOf(req.getParameter("memId"));
				
//String ename = req.getParameter("ename");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.add("���u�m�W: �ФŪť�");
//				} else if(!ename.trim().matches(enameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.add("���u�m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
//	            }
				
	java.sql.Date time = null;
	try {
	time = java.sql.Date.valueOf(req.getParameter("time").trim());
	} catch (IllegalArgumentException e) {
		time=new java.sql.Date(System.currentTimeMillis());
		errorMsgs.add("�п�J���!");
	}
	
String title = req.getParameter("title").trim();
				if (title == null || title.trim().length() == 0) {
					errorMsgs.add("�ФŪť�");
				}	
String content = req.getParameter("content").trim();
				if (content == null || content.trim().length() == 0) {
					errorMsgs.add("�ФŪť�");
				}	
				
		Integer count = Integer.valueOf(req.getParameter("count"));
		
		Byte status = Byte.valueOf(req.getParameter("status"));
//				Double sal = null;
//				try {
//sal = Double.valueOf(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					sal = 0.0;
//					errorMsgs.add("�~���ж�Ʀr.");
//				}

//				Double comm = null;
//				try {
//comm = Double.valueOf(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					comm = 0.0;
//					errorMsgs.add("�����ж�Ʀr.");
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
req.setAttribute("postVO", postVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/post/update_post_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				PostService empSvc = new PostService();
				postVO = empSvc.updatePost( postId ,cafeId, memId ,time, title , content,  count , status);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("postVO", postVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/back_end/post/listOnePost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
//String ename = req.getParameter("ename");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.add("���u�m�W: �ФŪť�");
//				} else if(!ename.trim().matches(enameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.add("���u�m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
//	            }
				
//String job = req.getParameter("job").trim();
//				if (job == null || job.trim().length() == 0) {
//					errorMsgs.add("¾��ФŪť�");
//				}
//				
//				java.sql.Date hiredate = null;
//				try {
//hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("�п�J���!");
//				}
//				
//				Double sal = null;
//				try {
//sal = Double.valueOf(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					sal = 0.0;
//					errorMsgs.add("�~���ж�Ʀr.");
//				}
//				
//				Double comm = null;
//				try {
//comm = Double.valueOf(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					comm = 0.0;
//					errorMsgs.add("�����ж�Ʀr.");
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
				errorMsgs.add("�п�J���!");
			}
			
		String title = req.getParameter("title").trim();
						if (title == null || title.trim().length() == 0) {
							errorMsgs.add("�ФŪť�");
						}	
		String content = req.getParameter("content").trim();
						if (content == null || content.trim().length() == 0) {
							errorMsgs.add("�ФŪť�");
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
req.setAttribute("postVO", postVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				PostService postSvc = new PostService();
				postVO = postSvc.addPost(postId, cafeId, memId, time, title, content,count,status);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/post/listAllPost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer postId = Integer.valueOf(req.getParameter("postId"));
				
				/***************************2.�}�l�R�����***************************************/
				PostService postSvc = new PostService();
				postSvc.deletePost(postId);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/post/listAllPost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
		}
	}
}
