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
			<img class="header_image" src="./images/Mammoth-Happy-icon.png" />
			<span class="header_text">Mammoth Address Book</span>
		</div>

<div id="homecontent">
		<h1>Welcome to the Mammoth Address Book!</h1>
		<h3>A book big enough for all the addresses you can throw at it - 
		and it never forgets!</h3>
		<br />
	
		<h3>View Translated About Page:</h3>
			<form action="translate" method="post">
			English <input type="radio" name="lang" value="English" checked>
			Deutsch <input type="radio" name="lang" value="German">
			<input type="submit" value="Continue">
		</form>
	
		<br /><br />
		<p class="bigtext"><a href="./start.jsp">Access Database</a></p>
		<br /><br />
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