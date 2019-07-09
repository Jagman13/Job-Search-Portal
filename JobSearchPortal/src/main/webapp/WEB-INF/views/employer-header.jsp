<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<c:set var="path" 	value=" ${pageContext.request.contextPath}"/>
  <meta charset="utf-8">
  <title>Plato - Clean responsive bootstrap website template</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="Clean responsive bootstrap website template">
  <meta name="author" content="">
  <!-- styles -->
   
  <link href="${path}/resources/assets/css/bootstrap.css" rel="stylesheet">
  <link href="${path}/resources//assets/css/bootstrap-responsive.css" rel="stylesheet">
  <link href="${path}/resources/assets/css/docs.css" rel="stylesheet">
  <link href="${path}/resources/assets/css/prettyPhoto.css" rel="stylesheet">
  <link href="${path}/resources/assets/js/google-code-prettify/prettify.css" rel="stylesheet">
  <link href="${path}/resources/assets/css/flexslider.css" rel="stylesheet">
  <link href="${path}/resources/assets/css/refineslide.css" rel="stylesheet">
  <link href="${path}/resources/assets/css/font-awesome.css" rel="stylesheet">
  <link href="${path}/resources/assets/css/font-awesome.min.css" rel="stylesheet">
  <link href="${path}/resources/assets/css/animate.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:400italic,400,600,700" rel="stylesheet">

  <link href="${path}/resources/assets/css/style.css" rel="stylesheet">
  <link href="${path}/resources/assets/color/default.css" rel="stylesheet">

  <!-- fav and touch icons -->
  <link rel="shortcut icon" href="${path}/resources/assets/ico/favicon.ico">
  <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${path}/resources/assets/ico/apple-touch-icon-144-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${path}/resources/assets/ico/apple-touch-icon-114-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${path}/resources/assets/ico/apple-touch-icon-72-precomposed.png">
  <link rel="apple-touch-icon-precomposed" href="${path}/resources/assets/ico/apple-touch-icon-57-precomposed.png">
</head>

<body>
<header>
    <div class="cbp-af-header">
      <div class=" cbp-af-inner">
        <div class="container">
          <div class="row">

            <div class="span4">
              <!-- logo -->
              <div class="logo">
                <h1><a href="#">Job Search</a></h1>
                <!-- <img src="assets/img/logo.png" alt="" /> -->
              </div>
              <!-- end logo -->
            </div>

            <div class="span8">
              <!-- top menu -->
              <div class="navbar">
                <div class="navbar-inner">
                  <nav>
                    <ul class="nav topnav">
                      <li class="dropdown active">
                        <a href="${path}/employer">Home</a>
                      </li>
                      <li class="dropdown">
                        <a href="${path}/employer/postJob">Post a Job</a>
                      </li>
                      <li class="dropdown">
                        <a href="${path}/employer/viewPost">View my Posts</a>
                      </li>
                      <li>
                        <a href="contact.html">Contact</a>
                      </li>
                      	<c:choose>
							<c:when test="${sessionScope.userSession==null}">
								<li>
								<a href="${path}/login">Login</a>
								</li>
							</c:when>
							<c:when test="${sessionScope.userSession != null }">
								<li class="dropdown">
									<a> ${userSession.firstName} </a> 
										<ul class="dropdown-menu">
											<li><a href="${path}/logout">Logout</a></li>
										</ul>
											
								</li>
							</c:when>
						</c:choose>
                        
                      
                    </ul>
                  </nav>
                </div>
              </div>
              <!-- end menu -->
            </div>

          </div>
        </div>
      </div>
    </div>
  </header>
</body>
</html>