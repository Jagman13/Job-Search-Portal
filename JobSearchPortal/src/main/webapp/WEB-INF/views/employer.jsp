<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<html>    
<head>    
<title>Admin Profile Page | Beingjavaguys.com</title>    
</head>    
<%@ include file = "employer-header.jsp" %>    
<body>
<c:set var="path" 	value=" ${pageContext.request.contextPath}"/>
<h1 style="color:black;">Hello "${path}" </h1>
  <section id="intro">

    <div class="container">
      <div class="row">
        <div class="span6">
          <h2><strong></strong></h2>
          <p class="lead">
           
            
          </p>
          

        </div>
         <div class="span6">

          <div class="group section-wrap upper" id="upper">
            <div class="section-2 group">
              <ul id="images" class="rs-slider">
                <li class="group">
                  <a href="#">
				            <img src="${path}/resources/assets/img/slides/refine/slide1.jpg" alt="" />
				  </a>
                </li>
                <li class="group">
                  <a href="#" class="slide-parent">
				            <img src="${path}/resources/assets/img/slides/refine/slide2.jpg" alt=""/>
				  </a>
                </li>
                <li class="group">
                  <img src="${path}/resources/assets/img/slides/refine/slide3.jpg" alt="" />
                </li>
              </ul>
            </div>
            <!-- /.section-2 -->
          </div>

        </div>
      </div>
    </div>

  </section>
  <section id="maincontent">
    <div class="container">
      <div class="row">
        <div class="span4">
          <div class="features">

            <div class="icon" >
              <img src="${path}/resources/assets/web/codingicon.jpg" alt="codingicon" style="border-radius: 50%; width:40%" />    
              
            </div>
            <div class="features_content" style="margin-top: -150px; margin-left: 5%">
              <h3>Valid Coding</h3>
              <p class="left">
                Dolorem adipiscing definiebas ut nec. Dolore consectetuer eu vim, elit molestie ei has, petentium imperdiet in pri mel virtute nam.
              </p>
             
            </div>
            
          </div>
        </div>
        <div class="span4">
          <div class="features">
            <div class="icon">
            <img src="${path}/resources/assets/web/trophy.png" alt="" style="border-radius: 10%; width:40%" />
            </div>
            <div class="features_content" style="margin-top: -150px; margin-left: 5%">
              <h3>Compete and Win</h3>
              <p class="left">
                Dolorem adipiscing definiebas ut nec. Dolore consectetuer eu vim, elit molestie ei has, petentium imperdiet in pri mel virtute nam.
              </p>
              
            </div>
          </div>
        </div>
        <div class="span4">
          <div class="features">
            <div class="icon">
              <img src="${path}/resources/assets/web/blogs.png" alt="" style="border-radius: 10%; width:40%" />
            </div>
            <div class="features_content" style="margin-top: -150px; margin-left: 5%">
              <h3>Blogs</h3>
              <p class="left">
                Dolorem adipiscing definiebas ut nec. Dolore consectetuer eu vim, elit molestie ei has, petentium imperdiet in pri mel virtute nam.
              </p>
            
            </div>
          </div>
        </div>

      </div>

      <!-- blank divider -->
      <div class="row">
        <div class="span12">
          <div class="blank10"></div>
        </div>
      </div>


     
              
          </div>
          

       
       
  </section>
  <!-- Footer
 ================================================== -->
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
  

</body>

</html>

      
    
    
</body>    
</html> 