<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>Post a Job</title>
<style type="text/css">
	body{
		color: #fff;
		background: #63738a;
		font-family: 'Roboto', sans-serif;
	}
    .form-control{
		height: 50px;
		box-shadow: none;
		color: #969fa4;
	}
	.form-control:focus{
		border-color: #5cb85c;
	}
    .form-control, .btn{        
        border-radius: 3px;
    }
	.signup-form{
		width: 400px;
		margin: 0 auto;
		padding: 30px 0;
	}
	.signup-form h2{
		color: #636363;
        margin: 0 0 15px;
		position: relative;
		text-align: center;
    }
	.signup-form h2:before, .signup-form h2:after{
		content: "";
		height: 2px;
		width: 30%;
		background: #d4d4d4;
		position: absolute;
		top: 50%;
		z-index: 2;
	}	
	.signup-form h2:before{
		left: 0;
	}
	.signup-form h2:after{
		right: 0;
	}
    .signup-form .hint-text{
		color: #999;
		margin-bottom: 30px;
		text-align: center;
	}
    .signup-form form{
		color: #999;
		border-radius: 3px;
    	margin-bottom: 15px;
        background: #f2f3f7;
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        padding: 30px;
    }
	.signup-form .form-group{
		margin-bottom: 20px;
	}
	.signup-form input[type="checkbox"]{
		margin-top: 3px;
	}
	.signup-form .btn{        
        font-size: 16px;
        font-weight: bold;		
		min-width: 140px;
        outline: none !important;
    }
	.signup-form .row div:first-child{
		padding-right: 10px;
	}
	.signup-form .row div:last-child{
		padding-left: 10px;
	}    	
    
    .signup-form a:hover{
		text-decoration: none;
	}
	.signup-form form a{
		color: #5cb85c;
		text-decoration: none;
	}	
	.signup-form form a:hover{
		text-decoration: underline;
	} 
	
	.form-group{
		margin-left: 20%;
	}
	
	.error {
color: #ff0000;
font-style: italic;
}
	
	
</style>
</head>
<%@ include file = "employer-header.jsp" %>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script>
$(document).ready(function(){
	 if ( window.history.replaceState ) {
	        window.history.replaceState( null, null, window.location.href );
	    }
}); 
  </script>
  
<div class="signup-form" style="margin-top: 10%">
    <form:form name="contact-form" method="post" action="${contextPath}/employer/postJob" modelAttribute="job">
		<h2>Post Job</h2>
	
        <div class="form-group">
			<form:input type="text" class="form-control" path="title" placeholder="Title" required="required" style="height : 30px"/>
			<form:errors path="title" cssClass="error"/>
		</div>
			 <div class="form-group">
			<form:input type="text" class="form-control" path="companyName" placeholder="Company Name" required="required" style="height : 30px"/>        	
        	<form:errors path="companyName" cssClass="error"/>
        </div>
         <div class=" form-group row-of-radio">
                <p>Job Type <span class="asterisk">&#42;</span><p>
                <div>
                    <form:radiobutton required="required"  value="Full Time" path="typeOfJob" id="job_type_full"/>
                    <label for="job_type_full">Full Time</label>
                </div>

                <div>
                    <form:radiobutton required="required"  value="Co-op" path="typeOfJob" id="job_type_coop"/>
                    <label for="job_type_coop">Co-op</label>
                </div>

                <div>
                    <form:radiobutton required="required" value="Contract" path="typeOfJob" id="job_type_contract"/>
                    <label for="job_type_contract">Contract</label>
                </div>
            </div>
            <div class="form-group">
            <div class="locationDropdown">

                    <script type= "text/javascript" src = "${contextPath}/resources/assets/js/countries.js"></script>
                    <select required="required" id="job_type_country" name ="country"></select> <br>

                    <label for="job_type_state">State</label><br>
                    <select required="required" name ="state" id ="job_type_state"></select>

                </div>
                </div>
                
              <script type="text/javascript">
                populateCountries("job_type_country", "job_type_state"); // first parameter is id of country drop-down and second parameter is id of state drop-down
            </script>
		<div class="form-group">
            <form:input type="text" class="form-control" id="industry" path="industry" placeholder="industry" required="required" style="height : 30px" onkeyup="checkPass()" />
        	<form:errors path="industry" cssClass="error"/>
        </div>
        <div class="form-group">
             <script src = "${contextPath}/resources/assets/js/majorsList.js"></script>
              <select required="required" id="majorCat" name ="majCategory"></select> <br><br>
              <label for="majorList">Select the major applicable for this job  <span class="asterisk">&#42;</span></label><br>
               <select required="required" name ="major" id ="majorList"></select>
         </div>
         
         <script type="text/javascript">
                populateCategories("majorCat", "majorList"); // first parameter is id of country drop-down and second parameter is id of state drop-down
         </script>
            
        <div class="form-group">
            <form:input type="number" class="form-control" path="minGpa" step ="0.1" min="0" max="4" placeholder="minGpa" required="required" style="height : 30px" onkeyup="checkPass()" />
        	
        </div>
        <div class="form-group">
            <form:input type="text" class="form-control" path="jobUrl" placeholder="job_url" required="required" style="height : 30px" onkeyup="checkPass()" />
        	
        </div>
        <div class="form-group">
            <form:input type="text" class="form-control" path="description" placeholder="description" required="required" style="height : 30px" onkeyup="checkPass()" />
        	
        </div>
              
       
		<div class="form-group" style="margin-left:0%">
            <button type="submit" class="btn btn-success btn-lg btn-block" style="height : 40px">Post a Job</button>
        </div>
    </form:form>
	
</div>
</body>
<footer class="footer">
    <div class="container">
      <div class="row">
        
        <p class="center">
              &copy; BetaCode - All right reserved
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
</body>
</html>