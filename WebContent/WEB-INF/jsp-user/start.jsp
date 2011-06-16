<jsp:include page="../jsp-unprotected/header.jsp"></jsp:include>

<%@ page errorPage="/WEB-INF/jsp/errorPage.jsp" %>
	
	<div id="content">
		<h1>Start</h1>
		<br />
		<h2>Please choose your operation from the choices below:</h2>
		
		<div id="stylized" class="myform">
			<form id="form" name="form" method="post" action="query_database">
				<div id="radioblock">
					
					<div id="left_float">
						<img src="images/system-search.png" /><input type="radio" name="action" value="query"  />Query Records
					</div>
					
					<% if (request.isUserInRole("admin")) { %>
					<div id="right_float">
						<img src="images/document-new.png" /><input type="radio" name="action" value="add"  />Add a Record
					</div>
					<% } %>
					
				</div>
				<button type="submit">Submit</button>
			</form>
		</div>
		<div id="spacer">&nbsp;</div>
	</div>
	
<jsp:include page="../jsp-unprotected/footer.jsp"></jsp:include>