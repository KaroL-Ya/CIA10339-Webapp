<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.emp.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>���u��� - listOneEmp.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���u��� - listOneEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�K��s��</th>
		<th>�@���U�s��</th>
		<th>�|���s��</th>
		<th>�o��ɶ�</th>
		<th>�K����D</th>
		<th>�K�夺�e</th>
		<th>���g�H��</th>
		<th>�K�媬�A</th>
		<th>�ק�</th>
		<th>�R��</th>
		
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