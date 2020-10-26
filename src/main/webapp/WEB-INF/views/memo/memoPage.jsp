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
	<h1>Memo Page</h1>
	<button id="btn">List</button>
	<div>
		<div class="form-group">
		<label for="writer">Writer:</label>
		<input type="text" class="form-control" value="${member.id}" id="writer" placeholder="Enter writer" name="writer">
	</div>
   
	<div class="form-group">
		<label for="contents">Contents:</label>
		<textarea class="form-control" rows="5" id="contents" name ="contents"></textarea>
	</div>
	
	<div class="form-group">
       <input type="button" class="btn btn-primary" value="WRITE" id="write">

</div>

  <div id="result"></div>
  <button class="btn btn-danger del">더보기</button>

</div>

<script type="text/javascript">
	getList();
	
	//*******************************************
	$("#result").on("click", ".del", function() {
		var num = $(this).attr("id");
		$.post("./memoDelete", {num:num},function(data) {
			data=data.trim();
			if(data>0){
				getList();
				alert("Delete Success!")
			}else{
				alert("Delete Fail!");
			}
		});
	});
	
	//*******************************************
	$("#write").click(function() {
		var writer = $("#writer").val();
		var contents = $("#contents").val();
		$.post("./memoWrite",{writer:writer, contents:contents}, function(result) {
			alert(result);
			$("#writer").val('');
			$("#contents").val('');
			getList();
		});
	});
	
	//{파라미터이름:writer에 들어가있는 변수명}
	//*******************************************

	function getList() {
		$.get("./memoList", function(data) {
			$("#result").html(data);
		});
	}
		
	
</script>

</body>
</html>