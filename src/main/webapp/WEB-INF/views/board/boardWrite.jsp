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

<c:import url="../template/header.jsp"></c:import>>


<div class="container">
  <h2>${board} Write From</h2>
  <form id="frm" action="./${board}Write" method="post" enctype="multipart/form-data">
    <div class="form-group">
      <label for="title">Title:</label>
      <input type="text" class="form-control" id="title" placeholder="Enter title" name="title">
    </div>
    
    <div class="form-group">
      <label for="writer">Writer:</label>
      <input type="text" class="form-control" readonly="readonly" value="${member.id}" id="writer" placeholder="Enter writer" name="writer">
    </div>
   
   <div class="form-group">
      <label for="contents">Contents:</label>
      <textarea class="form-control" rows="5" id="contents" name ="contents"></textarea>
    </div>
    
    <div class="form-group">
      <label for="files">File:</label>
      <input type="file" class="form-control" id="file" name="files">
    </div>
    
    <input type="button" class="btn btn-primary" value="Write" id="btn">
    <button type="submit" class="btn btn-default"">Write</button>
  </form>
</div>

<script type="text/javascript" src="../resources/js/boardWrite.js"></script>

</body>
</html>