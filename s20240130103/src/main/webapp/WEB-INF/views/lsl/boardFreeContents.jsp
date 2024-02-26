<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <title>글쓰기</title> <!-- 페이지 제목은 변경하지 않았습니다. -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> <!-- jQuery CDN -->
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
  <link href="assets/css/lsl/lslboardFreeContents.css" rel="stylesheet"> 
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
        <h1>게시판 </h1>
        <nav style="--bs-breadcrumb-divider: '-';">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="main">Home</a></li>
                <li class="breadcrumb-item"><a href="boardFree">FREE</a></li>
                <li class="breadcrumb-item"><a href="boardAsk">ASK</a></li>
            </ol>
        </nav>
    </div><!-- End Page Title -->

    <section class="community-post-detail">
        <div class="row card main-card card-body">
            <div class="card-header community-post-header">
                <h3 class="card-title post-header-title">질문 있어요!</h3>
                <div class="card-subtitle post-user-container">
                    <i class="bi bi-person-circle post-user-profile" alt="유저 프로필"></i>
                    <div class="card-title-header">
                        <h5 class="card-title post-user-name">
                            <a href="#">유저네임</a>
                        </h5>
                        <div class="card-subtitle post-subtitle">
                            <p class="post-updated-at">작성일 2024.02.23 오후 5:24</p>
                            <p class="post-veiw-count">조회수 11</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="community-post-header-body">
                <span class="post-content">게시판 내용</span>
            </div>
            <section class="community-post-answer">
                <div class="answer-info-header">
                    <div class="answer-info-title">
                        댓글 <span class="answer-info-title-count">23</span>
                    </div>
                    <div class="comment-editor">
                        <i class="bi bi-person-circle comment-user-profile" alt="유저 프로필"></i>
                        <input type="text" name="comment" id="inputField"
                            placeholder="id님, 댓글을 작성해보세요." class="form-control" required="">
                    </div>
                    <div class="btn-container is-editor-open">
                        <form action="boardCommentreset" method="get">
                            <button id="resetBtn" class="hidden btn btn-secondary">취소</button>
                        </form>
                        <form action="boardCommentsubmit" method="get">
                            <button id="submitBtn" class="hidden btn btn-primary">등록</button>
                        </form>
                    </div>
                </div>
            </section>
            <div class="re-comment-body">
                <div class="comment-card">
                    <div class="comment-header">
                        <i class="bi bi-person-circle comment-user-profile" alt="유저 프로필"></i>
                        <div class="comment-user-container">
                            <p class="card-title comment-user-name">
                                <a href="#">이새잎</a>
                            </p>
                            <p class="card-subtitle comment-updated-at">작성일 2024.02.24
                                오후 2:24</p>
                        </div>
                        <div class="re-btn-container">
                            <form action="/boardFreeCommentRepl" method="GET">
                                <button type="submit" class="btn btn-outline-primary">
                                    <i class="bi bi-reply-fill">Replay</i>
                                </button>
                            </form>
                        </div>
                    </div>
                    <div class="card-body comment-body">
                        <p class="markdown-body">언니 이거 내가 뽀려가용ㅎㅎ</p>
                    </div>
                </div>
                <div class="reply-comment">
					<form action="ref-reply" method="get">
						<input type="hidden" name="user-profile" value="${profile}">
						<input type="hidden" name="user-name" value="${user-name}">
						<input type="hidden" name="group" value="${group}"> <input
							type="hidden" name="level" value="${level}"> <input
							type="hidden" name="indent" value="${indent}">
						<div class="reply-header">
							<i class="bi bi-person-circle reply-user-profile" alt="유저 프로필"></i>
							<span class="reply-user-name">유경미</span> <span
								class="reply-updated-at">작성일 2024.02.24 오후 11:20</span>
						</div>
						<div class="reply-body">
							<span class="reply-content">구랩!!!</span><i class="bi bi-reply-fill"></i>
						</div>
					</form>
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
