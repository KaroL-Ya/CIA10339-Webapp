<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.emp.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - listOneEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>貼文編號</th>
		<th>咖啡廳編號</th>
		<th>會員編號</th>
		<th>發表時間</th>
		<th>貼文標題</th>
		<th>貼文內容</th>
		<th>按讚人數</th>
		<th>貼文狀態</th>
		<th>修改</th>
		<th>刪除</th>
		
	</tr>
	<tr>
		<td><%=empVO.getPostId()%></td>
		<td><%=empVO.getCafeId()%></td>
		<td><%=empVO.getMemId()%></td>
		<td><%=empVO.getTime()%></td>
		<td><%=empVO.getTitle()%></td>
		<td><%=empVO.getContent()%></td>
		<td><%=empVO.getCount()%></td>
		<td><%=empVO.getStatus()%></td>
	</tr>
</table>

</body>
</html>