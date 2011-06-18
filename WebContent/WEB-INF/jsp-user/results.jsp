<jsp:include page="/WEB-INF/jsp-common/header.jsp"></jsp:include>

<%@ page errorPage="/WEB-INF/jsp/errorPage.jsp" %>

<div id="content">
	<h1>Query Results</h1>
	<br />
	
	<div id="tablecontent">
		${sqlResult }
	</div>

</div>


<jsp:include page="/WEB-INF/jsp-common/footer.jsp"></jsp:include>