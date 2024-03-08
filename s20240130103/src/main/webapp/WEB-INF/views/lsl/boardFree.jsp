<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>자유게시판</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<meta content="" name="description">
<meta content="" name="keywords">
<!-- Favicons -->
<link href="assets/img/favicon.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Vendor CSS Files -->
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="assets/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
<link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
<link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
<link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
<!-- Template Main CSS File -->
<link href="assets/css/lsl/lslboardFree.css" rel="stylesheet">
<link href="assets/css/style.css" rel="stylesheet">

<!-- 헤더, 푸터, 사이드바 css -->
<!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: Jan 29 2024 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>
<body>
	<!-- Header -->
	<%@ include file="../header.jsp"%>
	<!-- Sidebar -->
	<%@ include file="../asidebar.jsp"%>
	<!-- Main Content -->

	<main id="main" class="main">
		<div class="pagetitle">
			<h1>자유 질문 게시판</h1>
			<nav style="--bs-breadcrumb-divider: '-';">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="main">Home</a></li>
					<li class="breadcrumb-item"><a href="boardFree">FREE</a></li>
					<li class="breadcrumb-item"><a href="boardAsk">ASK</a></li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->
		<div class="row justify-content-center">
			<div class="col-lg-13">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">자유 게시판</h5>

						<!-- Search Bar -->

						<form action="boardFreeSearch"
							class="search-form d-flex align-items-center">
							<div class="search-bar">

								<input type="text" name="keyword" placeholder="키워드를 입력하세요"
									title="Enter search keyword">
								<button type="submit" title="Search">
									<i class="bi bi-search"></i>
								</button>

							</div>
						</form>
						<!-- End Search Bar -->


						<!-- 게시판 테이블 -->
							<table class="table table-hover">
								<thead>
									<tr>
										<th scope="col">#</th>
										<th scope="col">제목</th>
										<th scope="col">작성자</th>
										<th scope="col">작성일</th>
										<th scope="col">조회수</th>
										<th scope="col">댓글</th>
									</tr>
									</thead>
									<tbody>
									<c:forEach var="boardFree" items="${boardFreeList}">
										<tr>
											<td>${boardFree.cboard_no}</td>
											<td><a href="boardFreeContents?cboard_no=${boardFree.cboard_no}">${boardFree.cboard_title}</a></td>
											<td>${boardFree.user_nic}</td>
											<td><fmt:formatDate value="${boardFree.cboard_date}" pattern="yyyy-MM-dd"/></td>
											<td>${boardFree.cboard_viewcnt}</td>
											<td>${boardFree.creply_cnt}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
			
							<!-- 글쓰기 버튼  -->
							<div class="text-end">
    								<a href="boardFreeWrite">
       							 	<button type="button" class="btn bfWrite">글쓰기</button>
							    	</a>
								</div>
							<!-- 글쓰기 버튼 끝  -->

							<!-- 페이지 표시 -->
				<nav aria-label="Page navigation example">
    				<ul class="pagination justify-content-center">
        				<c:if test="${bfpage.startPage > bfpage.pageBlock}"><li class="page-item">
               				 <a class="page-link" href="boardFree?currentPage=${bfpage.startPage-bfpage.pageBlock}" aria-label="Previous">
                    			<span aria-hidden="true">«</span>
                			</a>
           			 	</li>
        				</c:if>
        
        			<c:forEach var="i" begin="${bfpage.startPage}" end="${bfpage.endPage}">
           				 <li class="page-item">
               				 <a class="page-link" href="boardFree?currentPage=${i}">${i}</a>
           				 </li>
        			</c:forEach>
        
        			<c:if test="${bfpage.endPage < bfpage.totalPage}">
            			<li class="page-item">
                			<a class="page-link" href="boardFree?currentPage=${bfpage.startPage+bfpage.pageBlock}" aria-label="Next">
                    			<span aria-hidden="true">»</span>
                			</a>
          		 		</li>
        			</c:if>
   			 </ul>
	</nav>

							<!-- 페이지 표시 끝 -->
						</div>
					</div>
				</div>
			</div>
	</main>
	<!-- End #main -->
	<!-- Footer -->
	<%@ include file="../footer.jsp"%>
	<!-- End Footer -->
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>
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
