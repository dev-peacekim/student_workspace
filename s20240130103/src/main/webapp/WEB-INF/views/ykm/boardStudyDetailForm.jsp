<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="boardConfig.jsp"%>
<link href="assets/css/ykm/boardStudyDetailForm.css" rel="stylesheet">
</head>
<body>
	<!-- ======= header ======= -->
	<%@ include file="../header.jsp"%>

	<main id="main" class="main">
		<div class="pagetitle">
			<h1>스터디 게시판</h1>
			<nav style="--bs-breadcrumb-divider: '-';">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="main">Home</a></li>
					<li class="breadcrumb-item active"><a href="boardContest">공모전</a></li>
					<li class="breadcrumb-item active"><a href="boardStudy">스터디</a></li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->
		<section class="section">
			<div class="row">
				<div class="card">
					<div class="card-body">
						<div class="d-flex justify-content-between align-items-center">
							<div class="contest-title">함께 성장할 공모전 팀원을 모집해보세요</div>
							<div class="search-bar d-flex justify-content-end">
								<form class="search-form d-flex align-items-center"
									method="POST" action="#">
									<input type="text" name="query" placeholder="Search"
										title="Enter search keyword">
									<button type="submit" title="Search">
										<i class="bi bi-search"></i>
									</button>
								</form>
							</div>
						</div>
						<!--  검색 탭 끝 -->
						<div class="study-post">
							<div class="study-post-header">
								<div class="header-title">제목이요 공모전 정보드립니다.</div>
								<div class="header-subtitle">
									<div class="user-name">admin00</div>
									<div class="created-at">
										<span class="sub-title__title">작성일</span> <span
											class="sub-title__value">24.02.23 14:23</span>
									</div>
									<div class="view-count">
										<span class="sub-title__title">조회수</span> <span
											class="sub-title__value">30</span>
									</div>
								</div>
							</div>

							<div class="study-post-content">
								<div class="content-body">
									<div class="markdown-body">내용이 들어가면 됨</div>
								</div>
							</div>
							<div class="study-post-comment">댓글</div>
						</div>
					</div>
				</div>
			</div>
		</section>

		<!-- ======= aSidebar ======= -->
		<%@ include file="../asidebar.jsp"%>




		<!-- ======= Pagination ======= -->
		<section>
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
		</section>
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
