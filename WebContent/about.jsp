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
			<span class="header_text"><%= session.getAttribute("header_text") %></span>
		</div>

		<div id="content">
		<!-- do this with requests a EL instead of this -->
			<h1><%= session.getAttribute("page_title") %></h1>
			<br />
			<h2><%= session.getAttribute("welcome") %></h2>
			<p><%= session.getAttribute("intro_sentence1") %></p>
			<h3><%= session.getAttribute("intro_sentence2") %></h3>
			<p><%= session.getAttribute("supported_ops") %></p>
			<div class="left_float">
				<ul>
					<li><img src="images/system-search.png" /><%= session.getAttribute("search") %></li>
					<li><img src="images/document-new.png" /><%= session.getAttribute("add") %></li>		
				</ul>
			</div>
			<div class="left_float">
				<ul>
					<li><img src="images/document-edit.png" /><%= session.getAttribute("update") %></li>
					<li><img src="images/edit-delete.png" /><%= session.getAttribute("delete") %></li>			
				</ul>
			</div>
		</div>

		<div class="clear"></div>
		
		<div id="footer">
			<div id="footer_links"><a href="./index.jsp"><%= session.getAttribute("start_page") %></a>
			 | <a href="./about.jsp"><%= session.getAttribute("about_page") %></a></div>
			<p><%= session.getAttribute("copyright") %> &copy;2011 Scott Robarts, COMP 3641</p>
		</div>
	
	</div> <!-- wrapper div close -->
	
</body>
	
</html>