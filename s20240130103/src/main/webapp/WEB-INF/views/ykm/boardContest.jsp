<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="boardConfig.jsp"%>
<link href="assets/css/ykm/boardContest.css" rel="stylesheet">
</head>
<body>
	<!-- ======= header ======= -->
	<%@ include file="../header.jsp"%>

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
					<span>공모전 게시판</span>
				</div>
				<div class="community-body">
					<div class="contest-title">
						<span>함께 성장할 공모전 팀원을 모집해보세요</span>
					</div>
					<div class="search-bar d-flex justify-content-end">
						<form class="search-form d-flex align-items-center" method="POST"
							action="#">
							<input type="text" name="query" placeholder="Search"
								title="Enter search keyword">
							<button type="submit" title="Search">
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
							<tr>
								<th scope="row">1</th>
								<td>여긴 공모전 페이지다</td>
								<td>Designer</td>
								<td>2016-05-25</td>
								<td>28</td>
								<td>0</td>
							</tr>
							<tr>
								<th scope="row">2</th>
								<td>Bridie Kessler</td>
								<td>Developer</td>
								<td>2014-12-05</td>
								<td>35</td>
								<td>0</td>
							</tr>
							<tr>
								<th scope="row">3</th>
								<td>Ashleigh Langosh</td>
								<td>Finance</td>
								<td>2011-08-12</td>
								<td>45</td>
								<td>0</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- End Table -->
				<div class="community-bottom">
					<div class="btn-container">
						<form action="boardStuConWrite" method="get">
							<input type="submit" class="btn btn-primary custom-btn wriBtn"
								value="글쓰기">
						</form>
					</div>
					<!-- ======= Pagination ======= -->
					<nav aria-label="Page navigation example">
						<ul class="pagination justify-content-center">
							<li class="page-item"><a class="page-link" href="#"
								aria-label="Previous"> <span aria-hidden="true">«</span>
							</a></li>
							<li class="page-item"><a class="page-link" href="#">1</a></li>
							<li class="page-item"><a class="page-link" href="#">2</a></li>
							<li class="page-item"><a class="page-link" href="#">3</a></li>
							<li class="page-item"><a class="page-link" href="#">4</a></li>
							<li class="page-item"><a class="page-link" href="#">5</a></li>
							<li class="page-item"><a class="page-link" href="#"
								aria-label="Next"> <span aria-hidden="true">»</span>
							</a></li>
						</ul>
					</nav>
				</div>
			</div>
		</section>



		<!-- ======= aSidebar ======= -->
		<%@ include file="../asidebar.jsp"%>




	</main>

	<!-- ======= Footer ======= -->
	<%@ include file="../footer.jsp"%>
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Vendor JS Files -->
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>

</body>
</html>