<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Blueberry</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link href="assets/img/blueberry-favicon.png" rel="icon">
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
    <link href="assets/css/style.css" rel="stylesheet"  type="text/css">

    <script src="https://kit.fontawesome.com/0b22ed6a9d.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: Jan 29 2024 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->


<!-- ******************************************************** -->

<link href="assets/css/ykm/boardContest.css" rel="stylesheet">
</head>
<body>
	<!-- ======= header ======= -->
	<%@ include file="../header.jsp"%>
	
	<!-- ======= Sidebar ======= -->
    <%@ include file="../asidebar.jsp" %>

	<main id="main" class="main">
		<div class="pagetitle">
			<h1>정보게시판</h1>
			<nav style="--bs-breadcrumb-divider: '/';">
				<ol class="breadcrumb">
					<li class="breadcrumb-item active"><a href="boardContest">공모전</a></li>
					<li class="breadcrumb-item active"><a href="boardStudy">스터디</a></li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->

		<!-- ======= Bordered Tabs ======= -->
		<section class="section">
			<div class="row card card-body">
				<div class="community-header">
					<span>공모전 게시판</span>
				</div>
				<div class="community-body">
					<div class="contest-title">
						<span>함께 성장할 공모전 팀원을 모집해보세요</span>
					</div>
					<div class="search-bar justify-content-end">
						<form action="/boardCntSearch" method="POST" class="search-form d-flex align-items-center">
							<select class="form-select" name="type" aria-label="Default select example" required="required">
							    <option selected="A">전체</option>
							    <option value="TC">제목+내용</option>
							    <option value="W">작성자</option>
							</select>
							<input type="text" name="keyword" placeholder="관심 스터디를 검색해보세요" title="Enter search keyword" class="keyword-bar">
							<button type="submit">
								<i class="bi bi-search"></i>
							</button>
						</form>
					</div>
				</div>
				<!-- End Page search -->

				<div class="table-body">
					<table class="table table-hover">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">제목</th>
								<th scope="col">작성자</th>
								<th scope="col">날짜</th>
								<th scope="col">조회수</th>
								<th scope="col">댓글</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${getCntPostList}" var="cntPostList" varStatus="loop"> 
							<tr>
								<th scope="row">${loop.index+1}</th>
								<td><a href="/post?cboard_no=${cntPostList.cboard_no}">${cntPostList.cboard_title}</a></td>
								<td>${cntPostList.user_nic}</td>
								<td><fmt:formatDate value="${cntPostList.cboard_date}" pattern="yyyy-MM-dd"/></td>
								<td>${cntPostList.cboard_viewcnt}</td>
								<td>${cntPostList.reply_count}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- 글쓰기 -->
				<div class="community-bottom">
					<div class="btn-container">
						<a href="/writeForm?comm_mid=10&comm_big=200"><button class="btn btn-primary custom-btn wriBtn">글쓰기</button></a>
					</div>
					<!-- ======= Pagination ======= -->
					<nav aria-label="Page navigation example">
						<ul class="pagination justify-content-center">
						<c:if test="${stuPage.startPage > stuPage.pageBlock}">
						<li class="page-item"><a class="page-link" href="/boardContent?currentPage=${stuPage.startPage - stuPage.pageBlock}"><span aria-hidden="true">«</span></a></li>
						</c:if>
						<c:forEach var="i" begin="${stuPage.startPage}" end="${stuPage.endPage}">
							<li class="page-item"><a class="page-link" href="/boardContent?currentPage=${i}&comm_mid2=${comm_mid2}">${i}</a></li>
						</c:forEach>
						<c:if test="${stuPage.startPage < stuPage.pageBlock}">
							<li class="page-item"><a class="page-link" href="/boardContent?currentPage=${stuPage.startPage + stuPage.pageBlock}"><span aria-hidden="true">»</span></a></li>
						</c:if>
						</ul>
					</nav>
				</div>
			</div>
		</section>
	</main>

	<!-- ======= Footer ======= -->
	<%@ include file="../footer.jsp"%>
	<a href="#" class="back-to-top d-flex align-items-center justify-content-center">
	<i class="bi bi-arrow-up-short"></i></a>
	
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Template Main JS File -->
    <script src="assets/js/main.js"></script>
</body>
</html>