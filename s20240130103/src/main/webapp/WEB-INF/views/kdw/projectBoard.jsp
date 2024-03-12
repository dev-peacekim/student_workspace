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
					<ul class="nav nav-tabs nav-tabs-bordered" id="borderedTab"
						role="tablist">
						<c:choose>
							<c:when test="${clickedNav == 'comp'}">
								<li class="nav-item">
									<button class="nav-link" id="all-tab" data-bs-toggle="tab"
										type="button" role="tab" value="all">전체</button>
								</li>
								<li class="nav-item">
									<button class="nav-link active" id="comp-tab"
										data-bs-toggle="tab" type="button" role="tab" value="comp">완료</button>
								</li>
								<li class="nav-item" role="presentation">
									<button class="nav-link" id="uncomp-tab" data-bs-toggle="tab"
										type="button" role="tab" value="uncomp">미완료</button>
								</li>
							</c:when>
							<c:when test="${clickedNav == 'uncomp'}">
								<li class="nav-item">
									<button class="nav-link" id="all-tab" data-bs-toggle="tab"
										type="button" role="tab" value="all">전체</button>
								</li>
								<li class="nav-item">
									<button class="nav-link" id="comp-tab" data-bs-toggle="tab"
										type="button" role="tab" value="comp">완료</button>
								</li>
								<li class="nav-item" role="presentation">
									<button class="nav-link active" id="uncomp-tab"
										data-bs-toggle="tab" type="button" role="tab" value="uncomp">미완료</button>
								</li>
							</c:when>
							<c:otherwise>
								<li class="nav-item">
									<button class="nav-link active" id="all-tab"
										data-bs-toggle="tab" type="button" role="tab" value="all">전체</button>
								</li>
								<li class="nav-item">
									<button class="nav-link" id="comp-tab" data-bs-toggle="tab"
										type="button" role="tab" value="comp">완료</button>
								</li>
								<li class="nav-item" role="presentation">
									<button class="nav-link" id="uncomp-tab" data-bs-toggle="tab"
										type="button" role="tab" value="uncomp">미완료</button>
								</li>
							</c:otherwise>
						</c:choose>
					</ul>
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
						<select name="sort-filter" class="sort-filter form-select">
							<c:choose>
								<c:when test="${sortFilter == 'sort_project'}">
									<option value="sort_deadline">sorted by deadline</option>
									<option value="sort_project" selected>sorted by
										project</option>
									<option value="sort_task">sorted by task</option>
								</c:when>
								<c:when test="${sortFilter == 'sort_task'}">
									<option value="sort_deadline">sorted by deadline</option>
									<option value="sort_project">sorted by project</option>
									<option value="sort_task" selected>sorted by task</option>
								</c:when>
								<c:otherwise>
									<option value="sort_deadline" selected>sorted by
										deadline</option>
									<option value="sort_project">sorted by project</option>
									<option value="sort_task">sorted by task</option>
								</c:otherwise>
							</c:choose>

						</select>
					</div>
					<div class="table-nav">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th>프로젝트명</th>
									<th>과업명</th>
									<th>시작일</th>
									<th>종료일</th>
								</tr>
							</thead>
							<c:set var="num" value="${kphPaging.start }"></c:set>
							<tbody>
								<c:forEach var="projectTask" items="${totalProjectTaskList}">
									<tr>
										<th>${num}</th>
										<td><a href="#">${projectTask.project_title}</a></td>
										<td>${projectTask.task_title}</td>
										<td>${projectTask.task_start.substring(0, projectTask.task_start.indexOf(" "))}</td>
										<td>${projectTask.task_end.substring(0, projectTask.task_end.indexOf(" "))}</td>
									</tr>
									<c:set var="num" value="${num + 1 }"></c:set>
								</c:forEach>
							</tbody>
						</table>
						<nav class="page-navigation">
							<ul class="pagination">
								<c:if test="${kphPaging.startPage > kphPaging.pageBlock }">
									<li class="page-item"><a class="page-link"
										href="totalTaskList?currentPage=${kphPaging.startPage-kphPaging.pageBlock }&keyword=${keyword}&searchFilter=${searchFilter}&sortFilter=${sortFilter}&clickedNav=${clickedNav}"><span>&laquo;</span></a></li>
								</c:if>
								<c:forEach var="i" begin="${kphPaging.startPage }"
									end="${kphPaging.endPage }">
									<li class="page-item"><a class="page-link"
										href="totalTaskList?currentPage=${i}&keyword=${keyword}&searchFilter=${searchFilter}&sortFilter=${sortFilter}&clickedNav=${clickedNav}">${i}</a></li>
								</c:forEach>
								<c:if test="${kphPaging.endPage < kphPaging.totalPage }">
									<li class="page-item"><a class="page-link"
										href="totalTaskList?currentPage=${kphPaging.startPage+kphPaging.pageBlock }&keyword=${keyword}&searchFilter=${searchFilter}&sortFilter=${sortFilter}&clickedNav=${clickedNav}"><span>&raquo;</span></a></li>
								</c:if>
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