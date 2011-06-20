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
<title>Mammoth Address Book</title>

<body>

	<div id="wrapper">
		<div id="header">
			<img class="header_image" src="./images/Mammoth-Happy-icon.png" />
			<span class="header_text">Mammoth Address Book</span>
		</div>

		<div id="content">
		<!-- do this with requests a EL instead of this -->
			<h1><%= session.getAttribute("page_title") %></h1> 
			<br />
			<h2>Welcome to the Mammoth Address Book!</h2>
			<p>This application will allow you to manage one of the tables of the Mammoth Address Book (at least in this version).</p>
			<h3>Click the Start Page link below to proceed with searching records or adding a record.</h3>
			<p>The following operations are currently supported:</p>
			<div class="left_float">
				<ul>
					<li><img src="images/system-search.png" />Search Records</li>
					<li><img src="images/document-new.png" />Add a Record</li>		
				</ul>
			</div>
			<div class="left_float">
				<ul>
					<li><img src="images/document-edit.png" />Update a Record</li>
					<li><img src="images/edit-delete.png" />Delete a Record</li>			
				</ul>
			</div>
		</div>

		<div class="clear"></div>
		
		<div id="footer">
			<div id="footer_links"><a href="/index.html">Start Page</a>&nbsp;|&nbsp;<a href="/about.jsp">About</a></div>
			<p>Copyright &copy;2011 Scott Robarts, COMP 3641</p>
		</div>
	
	</div> <!-- wrapper div close -->
	
</body>
	
</html>