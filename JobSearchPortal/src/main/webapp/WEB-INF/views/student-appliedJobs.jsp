<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Applied Jobs</title>
<script>
$(document).ready(function(){
	 $(".alert").hide();

	 if (window.location.href.indexOf("?jobID=") > -1) {
		    $(".alert").show();
		}
	 
	 if ( window.history.replaceState ) {
	        window.history.replaceState( null, null, window.location.href );
	    }
	
	});
</script>


</head>
<%@ include file = "student-header.jsp" %>

<body>
<c:set var="path" 	value=" ${pageContext.request.contextPath}"/>
<section id="maincontent">

<div class="container" style="margin-top: 9%">		
		<h2>Applied Job Listings</h2>
<a href="${path}/student/downloadPdf" class="btn btn-primary btn-sm active" role="button" aria-pressed="true">Download Applications List</a>
		<br><br>
	
	
		<table id = "jobTable" class = "table table-bordered">
          		<%-- <input type="hidden" name = "jobID" value = "${j.id}" /> --%>
				
				<tr class="header">
					<th style="width: 5%;">Job ID</th>
					<th style="width: 10%;">Title</th>
					<th style="width: 5%;">Company Name</th>
					<th style="width: 5%;">Job Type</th>
					<th style="width: 10%;">Location</th>
					<th style="width: 10%;">Industry</th>
					<th style="width: 10%;">Job For..</th>
					<th style="width: 20%;">Job-URL</th>
					<th style="width: 20%;">Description</th>
					<th style="width: 5%;">Posted On</th>
					<th style="width: 10%;">Action</th>
				</tr>
				<c:forEach var="list" items="${jobs}">
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
					<td><a href="${path}/student/deleteApplication?jobId=${list.jobId}" class="btn btn-primary btn-sm active" role="button" aria-pressed="true">Withdraw</a></td>
					</tr>
			
			</c:forEach>
			</table>
		<!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog" style="margin-top: 5%">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" style="color:red"><i class="fas fa-exclamation-triangle"></i> Warning: Withdraw application?</h4>
        </div>
        <div class="modal-body">
          <p>Are you sure you want withdraw this application? You would have to re-apply.</p>
        </div>
        <div class="modal-footer">
          
          <button type="submit" class="btn btn-danger btn-lg">Yes</button>
        
          <button type="button" class="btn btn-default btn-lg" data-dismiss="modal">No</button>
                  </div>
    </div></div></div>
          
  
        
        </div>
        </section>
        
<footer class="footer">
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