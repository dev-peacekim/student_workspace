<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>공유 게시판 : 블루베리</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="assets/img/blueberry-favicon.png" rel="icon">
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
<link href="assets/css/style.css" rel="stylesheet">
<script src="https://kit.fontawesome.com/0b22ed6a9d.js"
	crossorigin="anonymous"></script>

<!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: Jan 29 2024 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->

<!-- jQuery를 포함 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- KDW Main CSS File -->
<link href="assets/css/kdw/projectBoard.css" rel="stylesheet">
<!-- KDW Main JS File -->
<link href="assets/js/kdw/projectBoard.js" rel="stylesheet">

</head>

<body>
	<!-- 여기에 헤더, 사이드바 등 공통 요소를 import-->
	<%@ include file="../header.jsp"%>
	<%@ include file="../asidebar.jsp"%>

    <main id="main" class="main">

		<div class="pagetitle">
			<h1>워크 스페이스</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="main">워크 스페이스</a></li>
					<li class="breadcrumb-item active">공유 게시판</li>
				</ol>
			</nav>
		</div>
		<div class="top">
			<div class="top-btn">
				<form action="detailProject" method="get">
					<input class="project_no" type="hidden" name="project_no"
						value="${projectNo }" />
					<button type="submit" class="project-home-btn btn btn-secondary">프로젝트
						홈</button>
				</form>
				<form action="boardProject" method="get">
					<input class="project_no" type="hidden" name="project_no"
						value="${projectNo }" />
					<button type="submit" class="project-board-btn btn btn-primary">공유
						게시판</button>
				</form>
			</div>
		</div>
		<!-- End Page Title -->

		<section class="section dashboard">
			<div class="card">
				<div class="card-body">
					<div class="controller">
						<div class="search-bar">
							<div class="search-form">
								<input id="task-search-text" type="text"
									placeholder="검색어를 입력하세요" value="${searchKeyword }">
								<button id="task-search-btn">
									<i class="bi bi-search"></i>
								</button>
							</div>
							<select name="search-filter" class="search-filter form-select">
								<c:choose>
									<c:when test="${searchFilter == 'project_title'}">
										<option value="all">전체</option>
										<option value="project_title" selected>프로젝트명</option>
										<option value="task_title">과업명</option>
									</c:when>
									<c:when test="${searchFilter == 'task_title'}">
										<option value="all">전체</option>
										<option value="project_title">프로젝트명</option>
										<option value="task_title" selected>과업명</option>
									</c:when>
									<c:otherwise>
										<option value="all" selected>전체</option>
										<option value="project_title">프로젝트명</option>
										<option value="task_title">과업명</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
					</div>
					<div class="table-nav">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th>제목</th>
									<th>작성자</th>
									<th>조회수</th>
									<th>댓글</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="pboardList" items="${pboardList }">
									<tr class="list-item">
										<td class="boardNo">${pboardList.pboard_no }</td>
										<td class="subject">${pboardList.pboard_title }</td>
										<td class="author">${pboardList.userNic}</td>
										<td class="views">0</td>
										<td class="comments">0</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- 프로젝트 공유게시판 페이징 -->
						<nav aria-label="Page navigation"
							class="projectBoard-pagination-container">
							<ul class="pagination">
								<li class="page-item ${page.startPage <= 1 ? 'disabled' : ''}">
									<a class="page-link prev-page"
									href="?currentPage=${page.startPage > 1 ? page.startPage - 1 : '#'}"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
								</a>
								</li>

								<c:forEach var="i" begin="${page.startPage}"
									end="${page.endPage}">
									<li class="page-item ${i == page.currentPage ? 'active' : ''}">
										<a class="page-link" href="?currentPage=${i}">${i}</a>
									</li>
								</c:forEach>

								<li
									class="page-item ${page.endPage >= page.totalPage ? 'disabled' : ''}">
									<a class="page-link next-page"
									href="?currentPage=${page.endPage < page.totalPage ? page.endPage + 1 : '#'}"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a>
								</li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</section>

	</main>
    <!-- End #main -->
	<!-- ======= Footer ======= -->
	<%@ include file="../footer.jsp"%>
	<!-- End Footer -->

	<!-- 스크롤하면 우측 아래 생기는 버튼 : 클릭하면 페이지의 맨 위로 이동 -->
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center">
		<i class="bi bi-arrow-up-short"></i>
	</a>

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