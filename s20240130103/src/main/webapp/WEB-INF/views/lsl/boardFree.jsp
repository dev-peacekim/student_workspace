<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script type="text/javascript" src="assets/js/lhsjoinemail.js" defer="defer"></script>
  <title>자유게시판</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">


  <!-- Template Main CSS File -->
  <link href="assets/css/lslboardFree.css" rel="stylesheet">
  

  <!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: Jan 29 2024 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>

    <!-- ======= Header ======= -->
    <%@ include file="header.jsp" %>

    <!-- ======= Sidebar ======= -->
    <%@ include file="asidebar.jsp" %>
    
    <!-- End Sidebar-->
     <!-- Page Title -->
  <main id="main" class="main">
  
    <div class="pagetitle">
      <h1>자유 게시판</h1>
      <!-- <nav>
        <ol class="breadcrumb">
          <a href="/board/free_ask">
          <i class="breadcrumb-item">ASK</i>
        </a>
        <h8 style="margin: 0 10px;"><span>/</span></h8>
        <a href="/board/free_free">
        <i class="breadcrumb-item">FREE</i>
        </a>
        </ol>
      </nav> -->
       <nav style="--bs-breadcrumb-divider: '-';">
                <ol class="breadcrumb">
                  <li class="breadcrumb-item"><a href="lsl/main.jsp">Home</a></li>
                  <li class="breadcrumb-item"><a href="/board/free_free">FREE</a></li>
                  <li class="breadcrumb-item"><a href="/board/free_ask">ASK</a></li>
                </ol>
              </nav>
    </div><!-- End Page Title -->

  <div class="card">
            <div class="card-body">
              <h5 class="card-title"style="font-weight: bold; font-size: 1.5rem;">자유 게시판</h5>
		<!-- Search Bar -->
		<div class="search-bar" style="text-align: right;">
   		 <form class="search-form d-flex align-items-center" method="POST" action="#" style="display: flex; justify-content: flex-end;">
        <input type="text" name="query" placeholder="Search" title="Enter search keyword">
        <button type="submit" title="Search"><i class="bi bi-search"></i></button>
    </form>
</div>

    	<!-- End Search Bar -->
    	
		<!-- Search Icon-->
    	<nav class="header-nav ms-auto">
    		  <ul class="d-flex align-items-center">
       			 <li class="nav-item d-block d-lg-none">
          		<a class="nav-link nav-icon search-bar-toggle " href="#">
           		 <i class="bi bi-search"></i>
          		</a>
       			 </li>
        <!-- End Search Icon-->
        
           
            <!-- 게시판 테이블 -->
              <table class="table table-hover">
                <thead>
                  <tr>
                    <th scope="col">#</th>
                    <th scope="col">제목</th>
                    <th scope="col">작성자</th>
                    <th scope="col">작성일</th>
                    <th scope="col">조회수</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <th scope="row">1</th>
                    <td>Brandon Jacob</td>
                    <td>Designer</td>
                    <td>28</td>
                    <td>2016-05-25</td>
                  </tr>
                  <tr>
                    <th scope="row">2</th>
                    <td>Bridie Kessler</td>
                    <td>Developer</td>
                    <td>35</td>
                    <td>2014-12-05</td>
                  </tr>
                  <tr>
                    <th scope="row">3</th>
                    <td>Ashleigh Langosh</td>
                    <td>Finance</td>
                    <td>45</td>
                    <td>2011-08-12</td>
                  </tr>
                  <tr>
                    <th scope="row">4</th>
                    <td>Angus Grady</td>
                    <td>HR</td>
                    <td>34</td>
                    <td>2012-06-11</td>
                  </tr>
                  <tr>
                    <th scope="row">5</th>
                    <td>Raheem Lehner</td>
                    <td>Dynamic Division Officer</td>
                    <td>47</td>
                    <td>2011-04-19</td>
                  </tr>
                </tbody>
              </table>
              <!-- 게시판 테이블 끝 -->
           
            </div>
             	<div class="text-end">
  					<button type="button" class="btn btn-primary" style="margin-bottom: 20px; margin-right: 20px;">글쓰기</button>
				</div>
          </div>
          
       <!-- 페이지 표시 -->
        <nav aria-label="Page navigation example" style="text-align: center;">
   		<ul class="pagination" style="display: inline-block;">
        <li class="page-item" style="display: inline-block;"><a class="page-link" href="#">Previous</a></li>
        <li class="page-item" style="display: inline-block;"><a class="page-link" href="#">1</a></li>
        <li class="page-item" style="display: inline-block;"><a class="page-link" href="#">2</a></li>
        <li class="page-item" style="display: inline-block;"><a class="page-link" href="#">3</a></li>
        <li class="page-item" style="display: inline-block;"><a class="page-link" href="#">Next</a></li>
    </ul>
</nav>
<!-- 페이지 표시 끝 -->
  </main><!-- End #main -->
    
    <!-- ======= Footer ======= -->
    <%@ include file="footer.jsp" %>
    <!-- End Footer -->

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Vendor JS Files -->
  <script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="assets/vendor/chart.js/chart.umd.js"></script>
  <script src="assets/vendor/echarts/echarts.min.js"></script>
  <script src="assets/vendor/quill/quill.min.js"></script>
  <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
  <script src="assets/vendor/tinymce/tinymce.min.js"></script>
  <script src="assets/vendor/php-email-form/validate.js"></script>

  <!-- Template Main JS File -->
  <script src="assets/js/main.js"></script>
</body>

</html>