<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="boardConfig.jsp"%>
<link href="assets/css/ykm/boardUpdateForm.css" rel="stylesheet">
</head>
<body>
<script>
    function back() {
        window.history.back();
    }
</script>
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
		<section class="section">
			<div class="row card card-body">
				<form action="updatePost" method="post">
					<input type="hidden" name="cboard_no" value="${getPost.cboard_no}">
					<div class="community-header">
						<p>글 수정</p>
					</div>
						<div class="title-input">
							<label for="boradTitle" class="form-label"></label>
							<input type="text" name="cboard_title" id="boardTitle" class="form-control" value="${getPost.cboard_title}"/>
						</div>
						<!-- 파일 첨부 -->
						<div class="upload-files">
							<label for="inputNumber" class="form-label">
								<span class="upload-file-title">파일 첨부</span>
							</label> 
							<input class="form-control" type="file" id="formFile" name="cboard_file_name" >
						</div>
						<div class="content-input">
							<label for="boardContent" class="form-label"></label>
							<textarea class="form-control" id="boardContent" name="cboard_content" rows="15">${getPost.cboard_content}</textarea>
						</div>
						<div class="btn-container">
							<input type="submit" class="btn btn-primary" value="확인">
							<button type="button" class="btn btn-secondary" onclick="back()">취소</button>			
						</div>
				</form>
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