<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="boardConfig.jsp"%>
<link href="assets/css/ykm/boardComment.css" rel="stylesheet">
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
			<div class="row">
				<div class="card main-card">
					<div class="card-body">
						<div class="card card-body board-comment">
							<div class="card-header comment-header">
								<i class="bi bi-person-circle comment-user-profile" alt="유저 프로필"></i>
								<div class="comment-user-container">
									<h5 class="card-title comment-user-name">
										<a href="#">유저네임</a>
									</h5>
									<h6 class="card-subtitle comment-updated-at">작성일 2024.02.24 오후
										2:24</h6>
								</div>
								<div class="btn-container">
									<form action="/commentReplay" method="GET">
										<button type="submit" class="btn btn-outline-primary">
											<i class="bi bi-reply-fill">Replay</i>
										</button>
									</form>

								</div>
							</div>
							<div class="card-body comment-body">
								<p class="markdown-body">근데 이거.. 어떻게 참여 원하시는 분들한테 연락을 드리는
									건가요?</p>
							</div>
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
