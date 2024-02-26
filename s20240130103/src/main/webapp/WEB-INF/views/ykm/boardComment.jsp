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

		<div class="main-card re-comment-body">
			<div class="comment-card">
				<div class="comment-header">
					<i class="bi bi-person-circle comment-user-profile" alt="유저 프로필"></i>
					<div class="comment-user-container">
						<p class="card-title comment-user-name">
							<a href="#">댓글작성자</a>
						</p>
						<p class="card-subtitle comment-updated-at">작성일 2024.02.24 오후
							2:24</p>
					</div>
					<div class="re-btn-container">
						<form action="/commentReplay" method="GET">
							<button type="submit" class="btn btn-outline-primary">
								<i class="bi bi-reply-fill">Reply</i>
							</button>
						</form>
					</div>
				</div>
				<div class="card-body comment-body">
					<p class="markdown-body">근데 이거.. 어떻게 참여 원하시는 분들한테 연락을 드리는 건가요?</p>
				</div>
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
					<span class="reply-user-name">대댓글작성자</span> <span
						class="reply-updated-at">작성일 2024.02.24 오후 11:20</span>
				</div>
				<div class="reply-content">쪽지 남겨놓으면 될까요?</div>
			</form>
		</div>




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