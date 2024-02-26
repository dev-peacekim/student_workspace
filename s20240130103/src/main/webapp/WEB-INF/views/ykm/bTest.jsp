<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="boardConfig.jsp"%>
<link href="assets/css/ykm/popup.css" rel="stylesheet">
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

		<!-- 버튼 -->
		<div class="modify-container">
			<form class="modDelBtn" method="get">
				<button id="modBtn" class="badge bg-light text-dark" type="button">
					<i class="bi bi-pencil-fill"></i> 수정
				</button>
				<button id="delBtn" class="badge bg-light text-dark">
					<i class="bi bi-trash"></i> 삭제
				</button>
			</form>
		</div>

		<!-- ======= popup ======= -->
		<div class="card card-body confirmPopup">
			<div class="popup-container">
				<i class="bi bi-exclamation-triangle popup-icon"></i> <span
					class="popup1">정말로 삭제하시겠습니까?</span> <span class="popup2">확인을
					누르면 글이 삭제됩니다.</span>
			</div>
			<form action="confirm-deletion" method="get">
				<button type="button" id="cancelButton" class="btn btn-outline-dark">취소</button>
				<button type="button" id="confirmButton" class="btn btn-primary">확인</button>
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