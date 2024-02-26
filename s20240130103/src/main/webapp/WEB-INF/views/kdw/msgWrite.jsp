<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>쪽지 쓰기 : 블루베리</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="assets/img/blueberry-favicon.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Vendor CSS Files -->
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="assets/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
<link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
<link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
<link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="assets/css/style.css" rel="stylesheet">
<script src="https://kit.fontawesome.com/0b22ed6a9d.js"
	crossorigin="anonymous"></script>

<!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: Jan 29 2024 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->



<!-- KDW Main CSS File -->
<link href="assets/css/kdw/msgWrite.css" rel="stylesheet">

<script type="text/javascript">
	
</script>

</head>

<body>
	<!-- ======= Header ======= -->
	<%@ include file="../header.jsp"%>

	<!-- ======= Sidebar ======= -->
	<%@ include file="../asidebar.jsp"%>

	<main id="main" class="main">
		<div class="pagetitle">
			<h1>쪽지쓰기</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="main">Home</a></li>
					<li class="breadcrumb-item active">쪽지쓰기</li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->
		<div class="card">
			<!-- 쪽지쓰기 세션 부분 -->
			<section class="msgWrite-section">
				<div class="form-container">
					<form id="write-form">
						<!-- 보내기 버튼 -->
						<div class="form-group">
							<a href="/msgSent" class="msgSentBtn">보내기</a>
						</div>
						<!-- 제목 -->
						<div class="form-group">
							<div class="subject-group">
								<!-- 제목 텍스트 -->
								<div class="subject-group-prepend">
									<span class="subject-group-text">제목</span>
								</div>
								<!-- 인풋 -->
								<input type="text" class="form-control"
								aria-label="Text input with segmented dropdown button">
							</div>
						</div>
						<!-- 받는사람 -->
						<div class="form-group">
							<div class="input-group">
								<!-- 받는사람 텍스트 -->
								<div class="input-group-prepend">
									<span class="input-group-text">받는사람</span>
								</div>
								<!-- 인풋 -->
								<input type="text" class="form-control"
									aria-label="Text input with segmented dropdown button">
								<div class="receiver-dropdown">
									<!-- 드롭다운 토글 버튼 -->
									<button type="button"
										class="btn btn-outline-secondary dropdown-toggle dropdown-toggle-split"
										data-bs-toggle="dropdown" aria-expanded="false">
										<span class="visually-hidden">주소록 드롭다운</span>
									</button>
									<!-- 드롭다운 주소록 리스트 -->
									<ul class="dropdown-menu dropdown-menu-end">
										<li><a class="dropdown-item" href="#">userAddressList1</a></li>
										<li><a class="dropdown-item" href="#">userAddressList2</a></li>
										<li><a class="dropdown-item" href="#">userAddressList3</a></li>
										<li><a class="dropdown-item" href="#">userAddressList4</a></li>
									</ul>
								</div>
								<!-- 주소록 버튼 -->
								<div class="receiver-addressBtn">
									<button type="button" class="btn btn-outline-secondary">주소록</button>
								</div>
							</div>

						</div>
						<!-- 첨부파일 -->
							<div class="form-group">
								<div class="mb-3">
									<div class="file-form-control">
										<input class="form-control" type="file" id="formFileMultiple"
											multiple>
										<!-- 아직 안되는 부분 -->
										<!-- <span class="fas fa-file-circle-plus"></span>
									<span id="customFileText">파일을 마우스로 끌어 오세요</span> -->
									</div>
								</div>
							</div>
							<!-- 내용 -->
							<div class="form-group">
								<div class="content-group">
									<textarea id="message" name="message" rows="5" required></textarea>
								</div>
							</div>
					</form>
				</div>
			</section>
		</div>
	</main>
	<!-- ======= Footer ======= -->
	<%@ include file="../footer.jsp"%>
	<!-- End Footer -->

	<!-- 스크롤하면 우측 아래 생기는 버튼 : 클릭하면 페이지의 맨 위로 이동 -->
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center">
		<i class="bi bi-arrow-up-short"></i>
	</a>

	<!-- Vendor JS Files -->
	<script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/vendor/chart.js/chart.umd.js"></script>
	<script src="assets/vendor/echarts/echarts.min.js"></script>
	<script src="assets/vendor/quill/quill.min.js"></script>
	<script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
	<script src="assets/vendor/tinymce/tinymce.min.js"></script>
	<script src="assets/vendor/php-email-form/validate.js"></script>

	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>
</body>
</html>