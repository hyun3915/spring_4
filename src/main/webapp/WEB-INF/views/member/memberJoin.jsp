<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../template/bootStrap.jsp"></c:import>
<style type="text/css">
	.idCheck0{
		color: blue;
	}
	
	.idCheck1{
		color: red;
	}
	
</style>
<title>Insert title here</title>
</head>
<body>
<c:import url="../template/header.jsp"></c:import>

<div class="container">
	<h3>Member Join Page</h3>
<form action="./memberJoin" method="post" id="frm">

    <div class="form-group">
      <label for="id">Id:</label>
      <input type="text" class="form-control" id="id" placeholder="Enter id" name="id">
	  <div id="idResult"></div>
    </div>
    
    <div class="form-group">
      <label for="pw">Password:</label>
      <input type="password" class="form-control" id="pw" placeholder="Enter password" name="pw">
    </div>
    
    <div class="form-group">
      <label for="pw">Password:</label>
      <input type="password" class="form-control" id="pw2" placeholder="Enter password" name="pw2">
	  <div id="pwResult"></div>
    </div>
    
    <div class="form-group">
      <label for="name">Name:</label>
      <input type="text" class="form-control empty" id="name" placeholder="Enter name" name="name">
	  <div class="emptyResult"></div>
    </div>
    
    <div class="form-group">
      <label for="email">Email:</label>
      <input type="text" class="form-control empty" id="email" placeholder="Enter email" name="email">
	  <div class="emptyResult"></div>
    </div>
    
    <input type="button" value="Join" class="btn btn-default" id="join">
    
  </form>
</div>

<script type="text/javascript">
	var idCheck = false;
	var pwCheck = false;
	var emptyCheckResult=true;
	
	function emptyCheck() {
		emptyCheckResult=true;
		$(".emptyResult").removeClass("idCheck0").addClass("idCheck1");
		$(".empty").each(function() {
			var data = $(this).val();
			if(data==''){
				emptyCheckResult=false;
				$(this).next().html("필수항목입니다.");
			}
		});
	}

	
	$("#pw2").blur(function() {
		var pw = $("#pw").val();
		var pw2 = $(this).val();
		
		if(pw2==''){
			$("#pwResult").html("Password를 입력해주세요.");
			$("#pwResult").removeClass("idCheck0").addClass("idCheck1");

		}else if(pw==pw2){
			$("#pwResult").html("Password가 일치합니다.");
			$("#pwResult").removeClass("idCheck1").addClass("idCheck0");
			pwCheck = true;
		}else{
			$("#pwResult").html("Password가 일치하지 않습니다.");
			$("#pwResult").removeClass("idCheck0").addClass("idCheck1");

		}
	});

	$("#join").click(function() {
		//중복체크 했고, 사용가능한 ID라면
		//중복체크 안했거나, 사용불가능한 ID라면
		emptyCheck();
		if(idCheck && pwCheck && emptyCheckResult){
			alert("Ok");
		}else{
			alert("No");
		}
		
		//$("#frm").submit();
	});

	$("#id").blur(function() {
		idCheck=false;
		var id = $(this).val();
		if(id!=''){
		$.get("./memberIdCheck?id="+id, function(data) {
			//a/true/0는 사용가능, b/false/1는 사용불가
		
		data=data.trim();
			
		var str = "중복된 ID입니다.";
		$("#idResult").removeClass("idCheck0").addClass("idCheck1");
		
		if(data ==0) {
			str="사용 가능한 ID입니다.";
			$("#idResult").removeClass("idCheck1").addClass("idCheck0");
			idCheck=true;
		}
		
		$("#idResult").html(str);
		
		});
		}else{
			$("#idResult").html("ID는 필수항목입니다.");
			$("#idResult").removeClass("idCheck0").addClass("idCheck1");
		}
	});
	
	
</script>

</body>
</html>