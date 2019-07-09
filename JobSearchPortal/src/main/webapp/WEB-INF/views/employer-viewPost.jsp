<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>View Posts</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

</head>
<%@ include file = "employer-header.jsp" %>    
<body>
<c:set var="path" 	value=" ${pageContext.request.contextPath}"/>



 <section id="maincontent">

<div class="container" style="margin-top: 10%">
	<h3>Job Posted By ${sessionScope.userSession.firstName} </h3>
	<div style="color: red">${message}</div>	
	
		<table class="table table-bordered">
			<tr>
				<td><b>JobId</b></td>
				<td><b>Title</b></td>
				<td><b>Company Name</b></td>
				<td><b>Job Type</b></td>
				<td><b>Location</b>
				<td><b>Industry</b>
				<td><b>Major</b>
				<td><b>Minimum GPA</b></td>
				<td style="width: 5%;"><b>Job URL</b></td>
				<td style="width: 10%;"><b>Description</b></td>
				<td style="width: 25%;"><b>Action to be Performed</b></td>
			</tr>
			<c:forEach var="list" items="${list}">
				<tr>
					<td>${list.jobId}</td>
					<td>${list.title}</td>
					<td>${list.companyName}</td>
					<td>${list.typeOfJob}</td>
					<td>${list.state} , ${list.country}</td>
					<td>${list.industry}</td>
					<td>${list.major}</td>
					<td>${list.minGpa}</td>
					<td>${list.jobUrl}</td>
					<td>${list.description}</td>
					<td><a href="${path}/employer/updateJobDetails.htm?jobID=${list.jobId}" class="btn btn-primary btn-sm active"  role="button" aria-pressed="true" >Update</a>
						&nbsp;&nbsp;
						<a href="${path}/employer/deleteJob.htm?jobID=${list.jobId}" class="btn btn-primary btn-sm active" role="button" >Delete</a>
						&nbsp;&nbsp;<br></br>
						<a href="${path}/employer/viewApplicants.htm?jobID=${list.jobId}" class="btn btn-primary btn-sm active"  role="button" aria-pressed="true" >View Applicants</a>
						
				</tr>
				
			</c:forEach>
                 
		</table>
		
		
 </div>
</section>    

	
</body>
<footer class="footer">
    <div class="container">
      <div class="row">
        
        <p class="center">
              &copy; LibraryManagmentApp - All right reserved
            </p>
       
       
    
            
          </div>
        </div>
     
  </footer>
	
  <script src="${path}/resources/assets/js/jquery.js"></script>
  <script src="${path}/resources/assets/js/modernizr.js"></script>
  <script src="${path}/resources/assets/js/jquery.easing.1.3.js"></script>
  <script src="${path}/resources/assets/js/google-code-prettify/prettify.js"></script>
  <script src="${path}/resources/assets/js/bootstrap.js"></script>
  <script src="${path}/resources/assets/js/jquery.prettyPhoto.js"></script>
  <script src="${path}/resources/assets/js/portfolio/jquery.quicksand.js"></script>
  <script src="${path}/resources/assets/js/portfolio/setting.js"></script>
  <script src="${path}/resources/assets/js/hover/jquery-hover-effect.js"></script>
  <script src="${path}/resources/assets/js/jquery.flexslider.js"></script>
  <script src="${path}/resources/assets/js/classie.js"></script>
  <script src="${path}/resources/assets/js/cbpAnimatedHeader.min.js"></script>
  <script src="${path}/resources/assets/js/jquery.refineslide.js"></script>
  <script src="${path}/resources/assets/js/jquery.ui.totop.js"></script>

  <!-- Template Custom Javascript File -->
  <script src="${path}/resources/assets/js/custom.js"></script>
  

</html>