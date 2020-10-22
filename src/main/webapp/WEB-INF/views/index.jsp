<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<c:import url="./template/bootStrap.jsp"></c:import>
</head>
<body>

<c:import url="./template/header.jsp"></c:import>

<div class="container">
  <h3>Right Aligned Navbar</h3>
  <p>The .navbar-right class is used to right-align navigation bar buttons.</p>
  <div>
  <img alt="main Image" src="./resources/images/index/suhyun.jpg">
  </div>
</div>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
