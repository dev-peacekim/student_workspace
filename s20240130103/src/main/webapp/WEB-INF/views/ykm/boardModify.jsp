<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="boardConfig.jsp"%>
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
<link href="assets/css/ykm/boardModify.css" rel="stylesheet">
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
		<section class="post-modify">
			<div class="row card card-body">
				<div class="modify-header">
					<p>수정페이지</p>
				</div>
				<div class="modify-body">
					<div class="title-input">
						<label for="modifyTitle" class="form-label"></label><input
							type="text" id="modifyTitle" class="form-control"
							placeholder="수정할 제목입니다.">
					</div>
					<div class="modify-files">
						<label for="inputNumber" class="form-label"><span
							class="modify-file-title">파일 첨부</span></label> <input
							class="form-control" type="file" id="formFile">
					</div>
					<div class="modify-input">
						<label for="modify-content" class="form-label"></label>
						<textarea class="form-control" id="modifyContent" rows="15"
							placeholder="작성했던 내용이 적혀있음"></textarea>
					</div>
				</div>
				<!-- End Quill Editor Default -->
				<div class="btn-container">
					<form action="modifyBtn" method="get">
						<input type="submit" class="btn btn-primary" value="수정">
					</form>
					<form action="modifyResBtn" method="get">
						<input type="submit" class="btn btn-secondary" value="취소">
					</form>
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