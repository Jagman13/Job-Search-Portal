<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Post</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	 $(".alert").hide();

	 if (window.location.href.indexOf("?jobID=") > -1) {
		    $(".alert").show();
		}
	 
	
		  $("#search").on("keyup", function() {
		    var value = $(this).val().toLowerCase();
		    $("#jobTable tr").filter(function() {
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		    });
		  });
		
	
	});


</script>


</head>
<%@ include file = "student-header.jsp" %>

<body>
<c:set var="path" 	value=" ${pageContext.request.contextPath}"/>
 
<section id="maincontent">

<div class="container" style="margin-top: 8%">		
		<h2>Open Job Listings</h2>
		<br><br>
	<div style="color: red">${error}</div>	
		<input type="text" id="search" placeholder="Search here.." style="border: 1px solid black;">
		<table id = "jobTable" class = "table table-bordered">
          		<%-- <input type="hidden" name = "jobID" value = "${j.id}" /> --%>
				
				<tr class="header">
					<th >Job ID</th>
					<th >Title</th>
					<th>Company Name</th>
					<th>Job Type</th>
					<th>Location</th>
					<th>Industry</th>
					<th>Job For..</th>
					<th>Job-URL</th>
					<th>Description</th>
					<th>Posted On</th>
					<th>Action</th>
				</tr>
				<c:forEach var="list" items="${allJobs}">
				<tr>
						<td>${list.jobId}</td>
						<td>${list.title}</td>
						<td>${list.companyName}</td>
						<td>${list.typeOfJob}</td>
						<td>${list.state}, ${list.country}</td>
						<td>${list.industry}</td>
						<td>${list.major}</td>
						
					<c:if test= "${empty list.jobUrl}"> 
					<td>No link available. Please check the company's website</td></c:if>
					<c:if test= "${not empty list.jobUrl}"> 
					<td>${list.jobUrl}</td></c:if>
					
					<c:if test= "${empty list.description}"> 
					<td>No description provided</td></c:if>
					<c:if test= "${not empty list.description}"> 
					<td>${list.description}</td></c:if>
					
					<td>${list.postedOn}</td>
					<td><a href="${path}/student/applyJob.htm?jobID=${list.jobId}"  class="btn btn-primary btn-sm active" role="button" aria-pressed="true">Apply for this job</a></td>
					</tr>
			
			</c:forEach>
			</table>
		
    
        
        </div>
        </section>
        
<footer class="footer" style="margin-top: 5%">
    <div class="container">
      <div class="row">
        
        <p class="center">
              &copy; Job Search Portal - All right reserved
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
  



      
    
    
</body>    
</html> 