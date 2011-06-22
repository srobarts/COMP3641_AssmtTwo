<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", -1); 
%>
<link rel="stylesheet" type="text/css" href="./styles/styles.css" >
<title>Java Address Book</title>
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<img class="header_image" src="./images/Mammoth-Back-icon.png" />
			<span class="header_text">Mammoth Address Book</span>
		</div>

<div id="content">
	<h1>Login Error</h1>
	<h3>Sorry, you are not authorized to access this resource</h3>
</div>


<div class="clear"></div>
		
		<div id="footer">
			<form action="translate" method="post">
				<input type="submit" value="Go To About">
			</form>
			<form action="query_database" method="post">
				<input type="hidden" name="action" value="start" />
				<input type="submit" value="Go To Start" />
			</form>
		
			<p>Copyright &copy;2011 Scott Robarts, COMP 3641</p>
		</div>
	
	</div> <!-- wrapper div close -->
	
</body>
	
</html>