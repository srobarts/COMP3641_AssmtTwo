<jsp:include page="/WEB-INF/jsp-common/header.jsp"></jsp:include>

<%@ page errorPage="/WEB-INF/jsp/errorPage.jsp" %>
<%@ taglib uri="/WEB-INF/Assmt2_taglib.tld" prefix="assmt2" %>

<div id="content">

	<h1>Result</h1><br />
	
	<div id="tabblock">
		<form id="form" name="form" method="post" action="query_database">
						<input type="radio" name="action" value="query"  />Query Again
					<% if (request.isUserInRole("admin")) { %>
						<input type="radio" name="action" value="add"  />Add Record
					<% } %>
			<button type="submit">Submit</button>
		</form>
	</div>

	<!--  Output result messaging: -->
	<% 
		String requestType = (String)session.getAttribute("requestType");
		if(requestType.equals("query")) {
	%>
		<h2>${initParam.qmessage}</h2>
		<h3><em>Your Query: ${requestScope.queryString}</em></h3>
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
			<!-- Output Query Results -->
			<div id="tablecontent">
				<assmt2:headertags headerNames="${requestScope.columnNames}" backgroundColor="tan"></assmt2:headertags>
				<assmt2:rowtags rowData="${requestScope.results}" evenRowColor="tan" oddRowColor="white"></assmt2:rowtags>
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
	
<jsp:include page="/WEB-INF/jsp-common/footer.jsp"></jsp:include>