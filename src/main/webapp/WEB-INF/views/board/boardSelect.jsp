<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/bootStrap.jsp"></c:import>
<title>Insert title here</title>
</head>
<body>

<c:import url="../template/header.jsp"></c:import>
  
<div class="container">
  <h3>${board} Select Page</h3>
  <table class="table table-hober">
     <tr>
        <td>Num</td>
        <td>Title</td>
        <td>Writer</td>
        <td>Contents</td>
        <td>Date</td>
        <td>Hit</td>   
     </tr>
     
      <tr>
        <td id="num">${dto.num}</td>
        <td>${dto.title}</td>
        <td>${dto.writer}</td>
        <td>${dto.contents}</td>
        <td>${dto.regDate}</td>
        <td>${dto.hit}</td>
      </tr>
  
  </table>
  <input type="button" title="${dto.num}" value="Delete" class="btn btn-warning" id="del">
  <input type="button" value="Update" id="update" class="btn btn-primary">
  <c:if test="${board ne 'notice' }">
  <a href="./${board}Reply?num=${dto.num}" class="btn btn-info">Reply</a>
  </c:if>
</div>

<script type="text/javascript">
   $("#update").click(function() {
      location.href="./noticeUpdate?num=${dto.num}";
   })

   //$("css선택자").action();
   $("#del").click(function() {
      
      //$("선택자").html() : 선택한 요소의 html 노드를 반환
      //var num =$("#num").html();
      
      //$("선택자").attr("속성명")
      var num =$(this).attr("title");
      
      location.href="./noticeDelete?num="+num;
   })

   
   

</script>


</body>
</html>