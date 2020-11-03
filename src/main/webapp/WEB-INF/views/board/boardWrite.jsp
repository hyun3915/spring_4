<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/bootStrap.jsp"></c:import>
<!-- include summernote css/js -->
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
  <h2>${board} Write From</h2>
  <form id="frm" action="./${board}Write" method="post" enctype="multipart/form-data">
    <div class="form-group">
      <label for="title">Title:</label>
      <input type="text" class="form-control" id="title" placeholder="Enter title" name="title">
    </div>
    
    <div class="form-group">
      <label for="writer">Writer:</label>
      <input type="text" class="form-control" value="${member.id}" id="writer" placeholder="Enter writer" name="writer">
    </div>
   
   <div class="form-group">
      <label for="contents">Contents:</label>
      <textarea class="form-control" rows="5" id="contents" name ="contents"></textarea>
    </div>
        <input type="button" value="FileAdd" class="btn btn-info" id="fileAdd">
 <div id="files"></div>
<div class="form-group"> 
 <label></label> 
   <input type="button" class="btn btn-primary form-control" value="Write" id="btn"> 
   <button type="submit" class="btn btn-default form-control">Write</button> 
    </div> 
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
			onImageUpload: function(files, editor) {
				var formData = new FormData(); //가상의 form태그 작성
				formData.append('file',files[0]); //파라미터 이름을 file로
				
				$.ajax({
                     data:formData,
					 type:"POST",
                     url:'summernote',
                     enctype:'multipart/form-data',
                     cache:false,
                     contentType:false,
                     processData: false,
                     	success: function(data) {
                        data=data.trim();
                        alert(data);

                        data="../resources/upload/${board}/"+data;
                        $("#contents").summernote('editor.insertImage', data);

					}
				})
			},//upload 끝
		
			onMediaDelete: function(files){
                var fileName =$(files[0]).attr("src");
                var a=fileName.split("/");
                fileName=a[a.length-1];
               $.ajax({
                  type:"POST",
                   url:"./summernoteDelete",
                   data:{
                      file:fileName
                   },
                   success:function(data){
                      if(data){
                         alert("삭제 성공");
                      }else{
                         alert("삭제 실패");
                      }
                   }
               });

        
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