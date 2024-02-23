<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="boardConfig.jsp"%>
<link href="assets/css/ykm/boardWriteForm.css" rel="stylesheet">
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
		<section class="section">
			<div class="row">
				<div class="card">
					<div class="card-body">
						<div class="community-header">
							<h5>글쓰기</h5>
						</div>
						<div class="community-body">
							<div class="title-input">
								<label for="boradTitle" class="form-label"></label><input
									type="text" id="boardTitle" class="form-control"
									aria-describedby="titleHelpBlock"
									placeholder="제목에 핵심 내용을 요약해보세요.">
							</div>
							 <div class="upload-files">
                                <label for="inputNumber" class="form-label">파일 첨부</label>
                                <input class="form-control" type="file" id="formFile">
                            </div>
							<div class="content-input">
								<label for="boardContent" class="form-label"></label>
								<textarea class="form-control" id="boardContent" rows="15"
									placeholder="내용을 입력하세요."></textarea>
							</div>
						</div>
						<!-- End Quill Editor Default -->
						<div class="btn-container">
							<form action="boardWritesubmit" method="get">
								<input type="submit" class="btn btn-primary" value="submit">
							</form>
							<form action="boardWritereset" method="get">
								<input type="submit" class="btn btn-secondary" value="reset">
							</form>
						</div>
					</div>ㄴ
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
