<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>받은 쪽지함</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="assets/img/blueberry-logo.png" rel="icon">
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
<style>
@import
	url('https://cdn.rawgit.com/moonspam/NanumSquare/master/nanumsquare.css')
	;

body {
	font-family: 'NanumSquare', sans-serif;
	margin: 0;
	padding: 0;
	background-color: #FCF3FF;
	box-shadow: 0 5px 30px 0 rgba(82, 63, 105, 0.2);
}

.table-container {
	margin: 20px;
	overflow-x: auto;
	box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
	box-shadow-sm: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075);
	box-shadow-lg: 0 1rem 3rem rgba(0, 0, 0, 0.175);
	box-shadow-inset: inset 0 1px 2px rgba(0, 0, 0, 0.075);
	background-color: #FFFFFF;
}

.table {
	width: 100%;
	border-collapse: collapse;
}

.table th, .table td {
	padding: 12px;
	text-align: left;
	border-bottom: 1px solid #DDDDDD;
	font-weight: bold;
}

/* 메일 툴바 */
.table th {
	background-color: #0D6DFD;
	color: #FFFFFF;
}
/* 메일 목록 리스트 */
.table td {
	font-size: 13px;
}

.table input[type="checkbox"] {
	margin-right: 5px;
	margin-top: 3px; /* 체크박스와 텍스트 사이에 여백 추가 */
}

/* 받은 메일함 메일 제목 */
.subject {
	white-space: nowrap; /* 텍스트가 줄 바꿈되지 않도록 설정 */
	overflow: hidden; /* 넘치는 부분을 감 */
	text-overflow: ellipsis;
	/* 텍스트가 너무 길어서 화면에 표시되지 않을 때, 말줄임표(...)로 표시되도록 설정 */
}
/* 메일 보관버튼 삭제버튼 */
.btn-container {
	margin-right: 21px;
	display: flex;
	justify-content: flex-end; /* 우측 정렬 */
}

.btn-msg-storebox, .btn-msg-trashbox {
	margin-left: 10px;
	color: #0d6efd;
	background-color: transparent;
	border: 1px solid #0D6DFD; 
  	border-radius: 7px; /* 모서리를 둥글게 만들기 */
  	padding: 2px 15px;
  	transition: background-color 0.3s ease; /* 부드러운 전환을 위한 트랜지션 추가 */
}

.btn-msg-storebox:hover,
.btn-msg-trashbox:hover {
  background-color: #B52BFC; /* 마우스를 올렸을 때의 배경색 */
  color: #ffffff; /* 마우스를 올렸을 때의 텍스트 색상 */
  border: 1px solid #B52BFC; 
}
</style>
</head>
<body>
	<!-- ======= Header ======= -->
	<%@ include file="../header.jsp"%>

	<!-- ======= Sidebar ======= -->
	<%@ include file="../asidebar.jsp"%>

	<!-- ======= 받은 쪽지함 Main ======= -->
	<main id="main" class="main">

		<!-- 받은 쪽지함 pageTitle -->
		<div class="receivebox_pagetitle">
			<h1>받은 쪽지함</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="main.html">Home</a></li>
					<li class="breadcrumb-item active">받은 쪽지함</li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->

		<!-- 받은 쪽지함 세션 부분 -->
		<section class="receivebox_section">
			<!-- 툴바와 테이블 -->
			<div class="table-container">
				<table class="table">
					<thead>
						<tr>
							<th><input type="checkbox" id="selectAll"> 
							<label for="selectAll">선택</label></th>
							<th scope="col">읽음</th>
							<th scope="col">제목</th>
							<th scope="col">보낸 사람</th>
							<th scope="col">일시</th>
						</tr>
					</thead>
					<!-- 나중에 구현할때 읽은건 폰트에 bold 빼야함 -->
					<tbody>
						<tr>
							<td><input type="checkbox"></td>
							<td>읽음 여부</td>
							<td class="subject">첫 번째 메일 제목이 여기에 들어갑니다.</td>
							<td>보낸이1</td>
							<td>2024-02-21 12:34</td>
						</tr>
						<tr>
							<td><input type="checkbox"></td>
							<td>읽음 여부</td>
							<td class="subject">두 번째 메일 제목이 여기에 들어갑니다.</td>
							<td>보낸이2</td>
							<td>2024-02-21 13:45</td>
						</tr>
						<tr>
							<td><input type="checkbox"></td>
							<td>읽음 여부</td>
							<td class="subject">세 번째 메일 제목이 여기에 들어갑니다.</td>
							<td>보낸이3</td>
							<td>2024-02-21 14:56</td>
						</tr>
						<tr>
							<td><input type="checkbox"></td>
							<td></td>
							<td class="subject"></td>
							<td></td>
							<td></td>
						</tr>						
						<tr>
							<td><input type="checkbox"></td>
							<td></td>
							<td class="subject"></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td><input type="checkbox"></td>
							<td></td>
							<td class="subject"></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td><input type="checkbox"></td>
							<td></td>
							<td class="subject"></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td><input type="checkbox"></td>
							<td></td>
							<td class="subject"></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td><input type="checkbox"></td>
							<td></td>
							<td class="subject"></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td><input type="checkbox"></td>
							<td></td>
							<td class="subject"></td>
							<td></td>
							<td></td>
						</tr>					
						<!--현재 10개의 메일 항목이 있고 추가할거면 이곳 -->
					</tbody>
				</table>
			</div>
		</section>
		<!-- 받은 쪽지함 세션 END -->
		<!-- 리스트 하단 버튼 -->
		<div class="btn-container">
		  <button type="button" class="btn-msg-storebox">보관</button>
		  <button type="button" class="btn-msg-trashbox">삭제</button>
		</div>
	</main>
	<!-- 받은 쪽지함 Main END-->


	<!-- ======= Footer ======= -->
	<%@ include file="../footer.jsp"%>
	<!-- End Footer -->

	<a href="#" class="back-to-top d-flex align-items-center justify-content-center">
		<i class="bi bi-arrow-up-short"></i>
	</a>

	<!-- Vendor JS Files -->
	<!-- <script src="assets/vendor/apexcharts/apexcharts.min.js"></script> -->
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- <script src="assets/vendor/chart.js/chart.umd.js"></script> -->
	<!-- <script src="assets/vendor/echarts/echarts.min.js"></script> -->
	<!-- <script src="assets/vendor/quill/quill.min.js"></script>
  	<script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
  	<script src="assets/vendor/tinymce/tinymce.min.js"></script>
  	<script src="assets/vendor/php-email-form/validate.js"></script> -->

	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>


</body>
</html>
