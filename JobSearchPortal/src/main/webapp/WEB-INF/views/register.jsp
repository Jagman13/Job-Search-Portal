<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://fonts.googleapis.com/css?family=Roboto:400,700" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>Registration Form</title>

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
<%@ include file = "header.jsp" %>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script>
$(document).ready(function(){
	 if ( window.history.replaceState ) {
	        window.history.replaceState( null, null, window.location.href );
	    }
	$("form").submit(function(e){
if (!$("input[name='role']:checked").val()) {
	   alert('Nothing is checked!Kindly choose role');
	   e.preventDefault();
	}
});
});
    function checkPass()
{
   
    var password = document.getElementById('password');
    var c_password = document.getElementById('c_password');
    
    var message = document.getElementById('confirmMessage');
   
    var goodColor = "#66cc66";
    var badColor = "#ff6666";
   
    if(password.value == c_password.value){
        
        c_password.style.backgroundColor = goodColor;
        message.style.color = goodColor;
        message.innerHTML = "Passwords Match!"
    }else{
        
        c_password.style.backgroundColor = badColor;
        message.style.color = badColor;
        message.innerHTML = "Passwords Do Not Match!"
    }
} 
    
   
   

  </script>
  
<div class="signup-form" style="margin-top: 10%">
    <form:form name="contact-form" method="post" action="${contextPath}/register" modelAttribute="user">
		<h2>Register</h2>
	<div style="color: red">${error}</div>
        <div class="form-group">
			<form:input type="text" class="form-control" path="firstName" placeholder="First Name" required="required" style="height : 30px"/>
			<form:errors path="firstName" cssClass="error"/>
		</div>
		<div class="form-group">
			<form:input type="text" class="form-control" path="lastName" placeholder="Last Name" required="required" style="height : 30px"/>        	
        	<form:errors path="lastName" cssClass="error"/>
        </div>
        <div class="form-group">
        	<form:input type="email" class="form-control" path="username" placeholder="Email" required="required" style="height : 30px"/>
       		<form:errors path="username" cssClass="error"/>
        </div>
		<div class="form-group">
            <form:input type="password" class="form-control" id="password" path="password" placeholder="Password" required="required" style="height : 30px" />
        	<form:errors path="password" cssClass="error"/>
        </div>
		<div class="form-group">
            <form:input type="password" class="form-control" id="c_password" path="confirmPassword" placeholder="Confirm Password" required="required" style="height : 30px" onkeyup="checkPass()" />
        	<p id="confirmMessage" ></p>
        </div>
        <div class="form-group">
        <label>Select what best suits you:</label><br> 
		<input type="radio" name="role" value="ROLE_STUDENT"/> I am a student &nbsp&nbsp
		<input type="radio"  name="role" value="ROLE_EMPLOYEE"/> I am an employer <br> <br> 
        </div> 
              
        <div class="form-group" style="margin-left:0%">
			<label class="checkbox-inline"><input type="checkbox" required="required"> I accept the <a href="#">Terms of Use</a> &amp; <a href="#">Privacy Policy</a></label>
		</div>
		<div class="form-group" style="margin-left:0%">
            <button type="submit" class="btn btn-success btn-lg btn-block" style="height : 40px" >Register Now</button>
        </div>
    </form:form>
	<div class="text-center">Already have an account? <a href="${path}/login">Sign in</a></div>
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