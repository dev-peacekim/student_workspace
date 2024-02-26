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

<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css"rel="stylesheet">
<link href="assets/vendor/boxicons/css/boxicons.min.css"rel="stylesheet">
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
<link href="assets/css/kdw/writeMessage.css" rel="stylesheet">

<script type="text/javascript">
/* 첨부파일 박스 텍스트 수정 */
function updateFileText() {
	  var fileInput = document.getElementById("formFileMultiple");
	  var customFileText = document.getElementById("customFileText");

	  if (fileInput.files.length > 0) {
	    // 파일이 선택되었을 때
	    customFileText.style.display = "none"; // 사용자 정의 텍스트 감춤
	  } else {
	    // 파일이 선택되지 않았을 때
	    customFileText.style.display = "inline"; // 사용자 정의 텍스트 표시
	  }
	}
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
			<section class="writebox-section">
				<div class="form-container">
					<form id="write-form">
						<!-- 보내기 버튼 -->
						<div class="form-group">
							<button type="submit" class="sendBtn">보내기</button>
						</div>
						<!-- 받는사람 -->
						<div class="form-group">
							<div class="input-group">
								<div class="user-address-form-control">
									<input class="form-control" list="userAddressListOptions" id="addressList" placeholder="user search...">
								</div>
								<select id="userAddressListOptions" size="5">
								  <option value="userAddress1">userAddress1</option>
								  <option value="userAddress2">userAddress2</option>
								  <option value="userAddress3">userAddress3</option>
								  <option value="userAddress4">userAddress4</option>
								  <option value="userAddress5">userAddress5</option>
								</select>
								<div class="userAddressbtn">
									<button class="btn btn-outline-secondary" type="button">주소록</button>
								</div>
							</div>
						</div>
						<!-- 제목 -->
						<div class="form-group">
							<div class="mb-3">
								<label for="subject" class="form-label">제목:</label>
								<input type="text" class="subject-form-control" id="subject" name="subject" required>
							</div>
						</div>
						<!-- 첨부파일 -->
						<div class="form-group">
							<div class="mb-3">
								<div class="file-form-control">
									<input class="form-control" type="file" id="formFileMultiple"
										multiple>
									<!-- 아직 안되는 부분 -->
									<span class="fas fa-file-circle-plus"></span>
									<span id="customFileText">파일을 마우스로 끌어 오세요</span>
								</div>
							</div>
						</div>
						<!-- 내용 -->
						<div class="form-group">
							<label for="message">내용:</label>
							<textarea id="message" name="message" rows="5" required></textarea>
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

	<svg id="SvgjsSvg1001" width="2" height="0"
		xmlns="http://www.w3.org/2000/svg" version="1.1"
		xmlns:xlink="http://www.w3.org/1999/xlink"
		xmlns:svgjs="http://svgjs.dev"
		style="overflow: hidden; top: -100%; left: -100%; position: absolute; opacity: 0;">
		<defs id="SvgjsDefs1002"></defs>
		<polyline id="SvgjsPolyline1003" points="0,0"></polyline>
		<path id="SvgjsPath1004" d="M0 0 "></path>
	</svg>

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