<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


	<c:forEach items="${list}" var="dto">
		<tr>
			<td id="num${dto.num}">${dto.num}</td>
			<td>${dto.contents}</td>
			<td>${dto.writer}</td>
			<td>${dto.regDate}</td>
			<td> <button class="btn btn-danger del" id="${dto.num}">DEL</button> </td>
		</tr>
	</c:forEach>