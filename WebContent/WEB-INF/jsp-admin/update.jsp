<jsp:include page="/WEB-INF/jsp-common/header.jsp"></jsp:include>

<%@ page errorPage="/WEB-INF/jsp/errorPage.jsp" %>
<%@ taglib uri="/WEB-INF/Assmt2_taglib.tld" prefix="assmt2" %>

<div id="content">
	<h1>Update Record to Database</h1>
	<form method="post" action="logout">
				<input type="submit" value="Logout" />
			</form>
	
	<div id="tabblock">
		<form id="form" name="form" method="post" action="query_database">
					<input type="radio" name="action" value="query"  />Query
					<input type="radio" name="action" value="add"  />Add Record
			<button type="submit">Submit</button>
		</form>
	</div>
	
	<h2>Update record below:</h2>
	
		<div id="stylized" class="myform">
		<form id="form" name="form" method="post" action="query_database" onSubmit="return submitIt(this)">
		<input name="action" type="hidden" value="updaterecord" />
		
			<div id="tablecontent">
				<assmt2:updatetags rowData="${requestScope.results}"></assmt2:updatetags>
			</div>
			
			<button type="submit">Submit</button>
			
		</form>
	</div>
	
	<div id="spacer">&nbsp;</div>
	
</div>
	
<jsp:include page="/WEB-INF/jsp-common/footer.jsp"></jsp:include>