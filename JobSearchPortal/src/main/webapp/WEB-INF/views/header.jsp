<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<c:set var="path" 	value=" ${pageContext.request.contextPath}"/>
  <meta charset="utf-8">
  <title>Job Search Portal</title>
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
                <h2><a href="#">Job Search Portal</a></h2>
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
                        <a href="${path}/">Home</a>
                      </li>
                      
							
								<li>
								<a href="${path}/login">Login</a>
								</li>
							
                        
                      
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