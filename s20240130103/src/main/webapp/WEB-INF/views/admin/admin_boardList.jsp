<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Blueberry</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="assets/img/blueberry-logo.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

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
<link href="assets/css/style.css" rel="stylesheet" type="text/css">
<link href="assets/css/lhs/admin_boardList.css" rel="stylesheet" type="text/css">
<script src="https://kit.fontawesome.com/0b22ed6a9d.js"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

</head>

<body>
	<!-- ======= Header ======= -->
	<%@ include file="admin_header.jsp"%>

	<!-- ======= Sidebar ======= -->
	<%@ include file="admin_aside.jsp"%>
	<!-- End Sidebar-->

	<main id="main" class="main">
		<section>
			<div class="main-div ">
				<div class="main-top">
					<div class="pagetitle">
						<h1>게시판 관리</h1>
					</div>
				</div>
				<div class="main-mid bg-white">
					<div class="card-body">
						<div class="card-top">
							<form action="#" class="d-flex">
								<select class="form-select searchSelete"
									aria-label="Default select example">
									<option selected="">제목</option>
									<option value="1">작성자</option>
								</select> <input type="text" class="form-control searchInput">
								<button class="btn btn-primary" type="submit">검색</button>
							</form>

						</div>
						<!-- Default Table -->
						<table class="table">
							<thead>
								<tr>
									<th scope="col" style="width: 5%;">NO</th>
									<th scope="col" style="width: 10%;">게시판</th>
									<th scope="col" style="width: 30%;">제목</th>
									<th scope="col" style="width: 15%;">작성자</th>
									<th scope="col" style="width: 25%;">작성일</th>
									<th scope="col" style="width: 15%;">비고</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th>1</th>
									<td>자유</td>
									<td>대충 긴 제목 입니다</td>
									<td>이한수<span class="span-smallId">#dlgkstnrn</span></td>
									<td>2016-05-25 06:55:55</td>
									<td>삭제 대기중</td>
								</tr>

							</tbody>
						</table>
						<!-- End Default Table Example -->
					</div>
					<div class="mid-bottom justify-content-center">
						<nav aria-label="Page navigation example">
							<ul class="pagination">
								<li class="page-item"><a class="page-link" href="#"><</a></li>
								<li class="page-item"><a class="page-link" href="#">1</a></li>
								<li class="page-item"><a class="page-link" href="#">2</a></li>
								<li class="page-item"><a class="page-link" href="#">3</a></li>
								<li class="page-item"><a class="page-link" href="#">4</a></li>
								<li class="page-item"><a class="page-link" href="#">5</a></li>
								<li class="page-item"><a class="page-link" href="#">6</a></li>
								<li class="page-item"><a class="page-link" href="#">7</a></li>
								<li class="page-item"><a class="page-link" href="#">8</a></li>
								<li class="page-item"><a class="page-link" href="#">9</a></li>
								<li class="page-item"><a class="page-link" href="#">></a></li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</section>
	</main>
	<!-- End #main -->

	<!-- ======= Footer ======= -->
	<%@ include file="admin_footer.jsp"%>
	<!-- End Footer -->

	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Vendor JS Files -->
	<!-- <script src="assets/vendor/apexcharts/apexcharts.min.js"></script> -->
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- <script src="assets/vendor/chart.js/chart.umd.js"></script> -->
	<!-- <script src="assets/vendor/echarts/echarts.min.js"></script> -->
	<script src="assets/vendor/quill/quill.min.js"></script>
	<script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
	<script src="assets/vendor/tinymce/tinymce.min.js"></script>
	<script src="assets/vendor/php-email-form/validate.js"></script>

	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>

</body>

</html>