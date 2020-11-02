<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
<c:import url="../template/bootStrap.jsp"></c:import>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
 <style type="text/css"> 
 	#f { 
 		display: none; 
 	} 
 	.del { 
 		color: red; 
 		font-weight: bold; 
 		cursor: pointer; 
 	} 
 </style>

</head>
<body>
<c:import url="../template/header.jsp"></c:import>

<div class="container">


  <h2>${board} Update From</h2>
  <form id="frm" action="./${board}Update" method="post">
  
  	<input type="hidden" name="num" value="${dto.num}">
  
    <div class="form-group">
      <label for="title">Title:</label>
      <input type="text" class="form-control" id="title" placeholder="Enter title" name="title" value="${dto.title}">
    </div>
    
    <div class="form-group">
      <label for="writer">Writer:</label>
      <input type="text" class="form-control" id="writer" readonly="readonly" placeholder="Enter writer" name="writer" value="${dto.writer}">
    </div>
   
   <div class="form-group">
      <label for="contents">Contents:</label>
      <textarea class="form-control" rows="5" id="contents" name ="contents">${dto.contents}</textarea>
    </div>
    <input type="button" value="FileAdd" class="btn btn-info" id="fileAdd">
 <div id="files"></div>
 
    <input type="button" class="btn btn-primary" value="Update" id="btn">
    <button type="submit" class="btn btn-default">Update</button>
    
  </form>
  
<div id="f"> 
  	  <div class="input-group"> 
       <input id="files" type="file" class="form-control" name="files"> 
        <span class="input-group-addon del">DEL</span> 
     </div> 
  </div>   
</div> 

</div>

<script type="text/javascript">
var count = 0;
$('#contents').summernote({
	height:300,
	callbacks:{
		onImageUpload: function() {
		      // upload image to server and create imgNode...
		      //$contents.summernote('insertNode', imgNode);
		      alert("Image Insert");
		    }
	}
});

$('#btn').click(function() {
	var contents = $('#contents').summernote('code');
	alert(contents);
});

$('#contents').summernote('code', 'Hello!');

$("#files").on("click",".del", function() {
	$(this).parent().remove();
	count--;
});

$("#fileAdd").click(function() {
	if(count<5){
	var f = $("#f").html().trim();
		$("#files").append(f);
		count++;
	}else{
		alert("첨부파일은 최대 5개입니다.")
	}
});

</script>

</body>
</html>