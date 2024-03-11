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

<link href="assets/css/ykm/boardStudy.css" rel="stylesheet">
<script defer src="assets/js/ykm/comment.js"></script>

</head>
<body>
	<!-- ======= header ======= -->
	<%@ include file="../header.jsp"%>
	
	<!-- ======= Sidebar ======= -->
    <%@ include file="../asidebar.jsp" %>

	<main id="main" class="main">
		<div class="pagetitle">
			<h1>공모전 스터디 게시판</h1>
			<nav style="--bs-breadcrumb-divider: '-';">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="main">Home</a></li>
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
					<span>스터디 게시판</span>
				</div>
				<!-- 카테고리 탭 시작  -->
				<ul class="nav nav-tabs nav-tabs-bordered" id="borderedTab" role="tablist">
					<li class="nav-item" role="presentation">
						<button class="nav-link ${comm_mid2=='0' ? 'active' : ''}" id="home-tab" data-bs-toggle="tab"
							data-bs-target="#bordered-home" type="button" role="tab"
							aria-controls="home" aria-selected="${comm_mid2=='0' ? 'true' : 'false'}"
							onclick="location.href='/boardStudy';">전체</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link ${comm_mid2=='10' ? 'active' : ''}" id="recruiting-tab" data-bs-toggle="tab"
							data-bs-target="#bordered-profile" type="button" role="tab"
							aria-controls="profile" aria-selected="${comm_mid2=='10' ? 'true' : 'false'}"
							onclick="location.href='/boardStudy?comm_mid2=10';">모집중</button>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link ${comm_mid2=='20' ? 'active' : ''}" id="recruited-tab" data-bs-toggle="tab"
							data-bs-target="#bordered-contact" type="button" role="tab"
							aria-controls="contact" aria-selected="false"
							onclick="location.href='/boardStudy?comm_mid2=20';">모집완료</button>
					</li>

					<!-- 검색 시작  -->
					<li class="nav-item ms-auto" role="presentation">
						<div class="search-bar d-flex justify-content-end">
							<form action="/board/search" method="POST" class="search-form d-flex align-items-center">
								<select class="form-select" name="type" aria-label="Default select example" required="required">
								    <option selected="">검색 기준</option>
								    <option value="TC">제목+내용</option>
								    <option value="W">작성자</option>
								</select>
								<input type="text" name="keyword" placeholder="관심 스터디를 검색해보세요" title="Enter search keyword">
								<button type="submit">
									<i class="bi bi-search"></i>
								</button>
							</form>
						</div>
					</li>
				</ul>
				
				<!-- 게시글 리스트 -->
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
							<c:forEach items="${getPostList}" var="PostList" varStatus="loop">
								<tr id="postTable">
									<th scope="row">${loop.index+1}</th>
									<td>
										<c:choose>
											<c:when test="${PostList.comm_mid2 == 10}">
											<span id="recruitment_${PostList.cboard_no}" class="recruiting">모집중</span>
											</c:when>
											<c:when test="${PostList.comm_mid2 == 20}">
											<span id="recruitment_${PostList.cboard_no}" class="recruited">모집완료</span>
											</c:when>
										</c:choose>
										<a href="/post?cboard_no=${PostList.cboard_no}">${PostList.cboard_title}</a>
									</td>					
									<td>${PostList.user_nic}</td>
									<td><fmt:formatDate value="${PostList.cboard_date}" pattern="yyyy-MM-dd"/></td>
									<td>${PostList.cboard_viewcnt}</td>
									<td>${PostList.reply_count}</td>
								</tr>
								<!-- <span class="recruited">모집완료</span> -->
							</c:forEach>

					        <c:forEach items="${searchResult}" var="post" varStatus="loop">
					            <tr id="postTable">
					                <th scope="row">${loop.index+1}</th>
					                <td>
					                    <c:choose>
					                        <c:when test="${post.comm_mid2 == 10}">
					                            <span id="recruitment_${post.cboard_no}" class="recruiting">모집중</span>
					                        </c:when>
					                        <c:when test="${post.comm_mid2 == 20}">
					                            <span id="recruitment_${post.cboard_no}" class="recruited">모집완료</span>
					                        </c:when>
					                    </c:choose>
					                    <a href="/post?cboard_no=${post.cboard_no}">${post.cboard_title}</a>
					                </td>                    
					                <td>${post.user_nic}</td>
					                <td><fmt:formatDate value="${post.cboard_date}" pattern="yyyy-MM-dd"/></td>
					                <td>${post.cboard_viewcnt}</td>
					                <td>${post.reply_count}</td>
					            </tr>
					        </c:forEach>
			
						</tbody>
					</table>
				</div>
				<!-- 글쓰기 -->
				<div class="community-bottom">
					<div class="btn-container">
						<a href="/writeForm"><button class="btn btn-primary custom-btn wriBtn">글쓰기</button></a>
					</div>

					<!-- ======= Pagination ======= -->
					<nav aria-label="Page navigation example">
						<ul class="pagination justify-content-center">
						<c:if test="${stuPage.startPage > stuPage.pageBlock}">
							<li class="page-item"><a class="page-link" href="/boardStudy?currentPage=${stuPage.startPage - stuPage.pageBlock}"><span aria-hidden="true">«</span></a></li>
						</c:if>
						<c:forEach var="i" begin="${stuPage.startPage}" end="${stuPage.endPage}">
							<li class="page-item"><a class="page-link" href="/boardStudy?currentPage=${i}">${i}</a></li>
						</c:forEach>
						<c:if test="${stuPage.startPage > stuPage.pageBlock}">
							<li class="page-item"><a class="page-link" href="/boardStudy?currentPage=${stuPage.startPage + stuPage.pageBlock}"><span aria-hidden="true">»</span></a></li>
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