<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>post: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>Post</h3><h4>( MVC )</h4></td></tr>
</table>

<!-- <p>This is the Home page for post: Home</p> -->

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllPost.jsp'>List</a> all Post.  <br><br></li>
  
  
   <li> 
    <FORM METHOD="post" ACTION="post.do" >
        <b>��J�K��s��:</b>
        <input type="text" name="postId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="postSvc" scope="page" class="com.post.model.PostService" />
   
  <li>
     <FORM METHOD="post" ACTION="post.do" >
       <b>��ܷ|���s��:</b>
       <select size="1" name="memId">
         <c:forEach var="postVO" items="${postSvc.all}" > 
          <option value="${postVO.postId}">${postVO.postId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="post.do" >
       <b>��ܩ@���U�s��:</b>
       <select size="1" name="cafeId">
         <c:forEach var="postVO" items="${postSvc.all}" > 
          <option value="${postVO.postId}">${postVO.postId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li> 
</ul>


<!-- <h3>�s�W</h3> -->

<ul>
  <li><a href='addPost.jsp'>Add</a> a new Post.</li>
</ul>

</body>
</html>