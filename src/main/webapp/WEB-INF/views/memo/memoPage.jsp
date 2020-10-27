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
	
</div>

  <div>
  	<table id="result" class="table table-border">
  		
  	</table>
  </div>
  <button class="btn btn-danger del" id="more">더보기</button>

</div>

<script type="text/javascript">
	var curPage=1;
	getList();
	
	//****************more***********************
	$("#more").click(function() {
		curPage++;
		getList();
	});
	
	
	//*******************************************
	$("#result").on("click", ".del", function() {
		var num = $(this).attr("id");
		
		$.ajax({
			url : "./memoDelete",
			type : "POST",
			data : {num:num},
			success : function(data) {
				data=data.trim();
				if(data>0){
					$("#result").html('');
					curPage=1;
					getList();
					alert("Delete Success!");
				}else{
					alert("Delete Fail!");
				}
			}
		});
	});
	
	//*******************************************
	$("#write").click(function() {
		var writer = $("#writer").val();
		var contents = $("#contents").val();
		
		$.ajax({
			url : "./memoWrite",
			type : "POST",
			data : {writer:writer, contents:contents},
			success : function(result) {
				alert(result);
				$("#writer").val('');
				$("#contents").val('');
				$("#result").html('');
				curPage=1;
				getList();
			}
		});
	});
	
	//{파라미터이름:writer에 들어가있는 변수명}
	//*******************************************

	function getList() {
		
		$.ajax({
			url : "./memoList",
			type : "GET",
			 data : {curPage:curPage},
			success : function(data) {
				  $("#result").append(data);
			}
		});
	}
		
	
</script>

</body>
</html>