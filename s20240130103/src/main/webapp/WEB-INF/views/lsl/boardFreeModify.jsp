<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <title>글 수정하기</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> <!-- jQuery CDN -->
  <meta content="" name="description">
  <meta content="" name="keywords">
  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
 
  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
  
  
  <!-- Template Main CSS File -->
  <link href="assets/css/lsl/lslboardFreeModify.css" rel="stylesheet"> 
   <link href="assets/css/style.css" rel="stylesheet"> <!-- 헤더, 푸터, 사이드바 css -->
  
  
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
    <%@ include file="../header.jsp" %>
    
    
    <!-- ======= Sidebar ======= -->
    <%@ include file="../asidebar.jsp" %>

    <!-- ======= Main ======= -->
 <main id="main" class="main">
    <div class="pagetitle">
        <h1>글 수정하기</h1>
        <nav style="--bs-breadcrumb-divider: '-';">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="main">Home</a></li>
                <li class="breadcrumb-item"><a href="boardFree">FREE</a></li>
                <li class="breadcrumb-item"><a href="boardAsk">ASK</a></li>
            </ol>
        </nav>
    </div><!-- End Page Title -->

    <section class="section">
        <div class="row justify-content-center">
            <div class="col-lg-13">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">글 수정</h5>

                        <!-- General Form Elements -->
                     
                        <form id="boardFreeUpdate" method="post" action="/boardFreeAskUpdate">
   						 <input type="hidden" name="cboard_no" value="${boardCommFileList.cboard_no}" />
    					<input type="hidden" name="boardType" value="${boardType}" />
						    <div class="mb-3">
						        <label for="inputText" class="form-label">제목</label>
						        <input type="text" class="form-control" name="cboard_title" value="${boardCommFileList.cboard_title}">
						    </div>
                            <div class="upload-files">
                                <label for="files" class="form-label">파일 첨부</label>
                                <input type="file" name="files" id="files" class="files form-control form-control-sm" multiple>
                            </div>
						    <div class="mb-3">
						        <textarea class="form-control" style="height: 550px;" name="cboard_content">${boardCommFileList.cboard_content}</textarea>
						    </div>
						    <div class="mb-3">
						        <button type="button" class="btn bfmCancle" onclick="goBack()">취소</button>
						        <script>
						            function goBack() {
						                window.history.back();
						            }
						        </script>
						        <button type="submit" class="btn bfmModify">저장</button>
						    </div>
						</form><!-- End General Form Elements -->
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>

    <!-- ======= End Main ======= -->
    
    
    <!-- ======= Footer ======= -->
    <%@ include file="../footer.jsp" %>
    <!-- End Footer -->
   
   
    <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>
   
   
    <!-- Vendor JS Files -->
    <script defer src="assets/vendor/apexcharts/apexcharts.min.js"></script>
    <script defer src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script defer src="assets/vendor/chart.js/chart.umd.js"></script>
    <script defer src="assets/vendor/echarts/echarts.min.js"></script>
    <script defer src="assets/vendor/quill/quill.min.js"></script>
    <script defer src="assets/vendor/simple-datatables/simple-datatables.js"></script>
    <script defer src="assets/vendor/tinymce/tinymce.min.js"></script>
    <script defer src="assets/vendor/php-email-form/validate.js"></script>
    
    
    <!-- Template Main JS File -->
    <script defer src="assets/js/lsl/main.js"></script>
</body>
</html>
