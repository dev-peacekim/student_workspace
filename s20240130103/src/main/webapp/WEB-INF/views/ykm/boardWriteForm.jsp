<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="boardConfig.jsp"%>
<link href="assets/css/ykm/boardWriteForm.css" rel="stylesheet">
</head>
<body>
	<script type="text/javascript">
		function back() {
			window.history.back()
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
		<section class="board-write">
			<div class="row card card-body">
				<div class="community-header">
					<p>글쓰기</p>
				</div>
				<form action="boardPost" method="post">
					<div class="community-body">
						<!-- 제목 -->
						<div class="title-input">
							<label for="boradTitle" class="form-label"></label> 
							<input type="text" class="form-control boardTitle " 
									name="cboard_title"	placeholder="제목에 핵심 내용을 요약해보세요." required="required">
						</div>

						<!-- 파일 첨부 -->
						<div class="upload-files">
							<label for="inputNumber" class="form-label">
								<span class="upload-file-title">파일 첨부</span>
							</label> 
							<input class="form-control" type="file" id="formFile" name="cboard_file_name" >
						</div>
						
						<!-- 내용 -->
						<div class="content-input">
							<label for="boardContent" class="form-label"></label>
							<textarea class="form-control" id="boardContent" rows="15" 
										placeholder="내용을 입력하세요." name="cboard_content" required="required"></textarea>
						</div>
						
						<!-- 버튼 -->
						<div class="btn-container">
							<input type="submit" class="btn btn-primary" value="확인">
							<button type="button" class="btn btn-secondary" onclick="back()">취소</button>
						</div>
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