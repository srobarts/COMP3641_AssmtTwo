<jsp:include page="header.jsp"></jsp:include>

<%@ page errorPage="/WEB-INF/jsp/errorPage.jsp" %>

<div id="content">

	<h1>Result</h1><br />
	
	<div id="tabblock">
		<form id="form" name="form" method="post" action="query_database">
					<input type="radio" name="action" value="query"  />Query Again
					<input type="radio" name="action" value="add"  />Add Record
			<button type="submit">Submit</button>
		</form>
	</div>

	<!--  Output result messaging: -->
	<% 
		String requestType = (String)session.getAttribute("requestType");
		if(requestType.equals("query")) {
	%>
		<h2>${initParam.qmessage}</h2>
		Query: <%= session.getAttribute("queryString") %>
	<%
		} else if (requestType.equals("insert")) {
	%>
		<h2>${initParam.imessage}</h2>
	<%	
		} else if (requestType.equals("update")) {
	%>
		<h2>${initParam.updatemessage}</h2>
	<% 
		} else if (requestType.equals("delete")) {
	%>
		<h2>${initParam.deletemessage}</h2><br />
	<% } %>
	
	<!-- Output query and results table: -->
	<% 
		String insertStatus = (String)session.getAttribute("insertStatus");
		if( insertStatus == null || insertStatus.equals("valid") ) {
		//keep going - not all queries are inserts
		%>
			<div id="tablecontent">
				${sqlResult }
			</div>
		<%	
		//reset status to valid to reset variable
		session.setAttribute("insertStatus", "valid");
		} else if (insertStatus.equals("invalid")) {
	%>
		<p class="bigredtext">Your input did not validate - insert failed</p>
		<div id="spacer">&nbsp;</div>

	<% } %>
		
</div>
	
<jsp:include page="footer.jsp"></jsp:include>