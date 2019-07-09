<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>AddMember</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
 <script>
 /*Jquery for Job Description*/
        $(document).ready(function()  {
          
            $(".alert").hide();

            $('button').click(function(){
                $('.alert').show();
            })

 
        });

    </script>
</head>
<%@ include file = "employer-header.jsp" %>    
<body>
<c:set var="path" 	value=" ${pageContext.request.contextPath}"/>

 <section id="maincontent">

<div class="container" style="margin-top: 10%">
<h2>Applicant Info</h2>
<div style="color: red">${message}</div>
<table class="table table-bordered">
			<tr>
				<td><b>FirstName</b></td>
				<td><b>LastName</b></td>
				<td><b>Email</b>
				<td><b>Resume</b>
				<td><b>CoverLetter</b>
			</tr>
			<%-- <c:forEach var="user" items="${listuser}" --%>
				
			
			<c:forEach var="app" items="${listapp}">
				<tr>
					<td>${app.user.firstName}</td>
					<td>${app.user.lastName}</td>
					<td>${app.user.username}</td>
				<td><a href="${path}/employer/downloadResume.htm?path=${app.resumePath}&email=${app.user.username}" class="btn btn-default"  role="button" aria-pressed="true" >Download Resume</a></td>
				<td><a href="${path}/employer/downloadCoverLetter.htm?path=${app.coverLetterPath}&email=${app.user.username}" class="btn btn-default"  role="button" aria-pressed="true" >Download CoverLetter</a></td>
			</tr>
		</c:forEach>
		<%-- </c:forEach> --%>
                 
		</table>
		
		<div class="container" id="">
		<c:if test="${not empty message}">
			<font color="blue"><h4>${ssmessage}</h4></font>
		</c:if>
		
		<div class="alert alert-danger alert-dismissible fade show" role="alert">
		  <strong>${message}</strong>
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    X
		  </button>
		</div>
	</div>
	</div>
</section>
</body>
<footer class="footer" style="margin-top: 20%">
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
</html>