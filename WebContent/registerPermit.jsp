<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="db.DbManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="permit.PermitController"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="script.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>

<script>
	
</script>

</head>
<body>
<form name="regformPermit" action="permit" method="post">
	<h1>Parking Management Service</h1>
	<h2>Register Permit</h2>
	
	<div id="username_error"></div><br>
	OwnerId: <input type="text" name="userId" id="licnum"> <br>
    Price: <input type="text" name="price" id="make"> <br>
    Color: <input type="text" name="color" id="model"> <br>
    Exp Date: <input type="date" name="date" id="year"> <br>
    <input type="submit" name="submit" value="register" >
	<input type="reset" name="reset">
	</form>
	
</body>
</html>