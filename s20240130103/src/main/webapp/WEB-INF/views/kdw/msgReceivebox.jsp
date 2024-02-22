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
<link href="assets/css/kdw/msgReceivebox.css" rel="stylesheet">
<!-- 검색바&드롭박스 JS -->

<script type="text/javascript">

</script>
<!-- 검색바&드롭박스 JS END-->
</head>
<body>
	<!-- ======= Header ======= -->
	<%@ include file="../header.jsp"%>

	<!-- ======= Sidebar ======= -->
	<%@ include file="../asidebar.jsp"%>

	<!-- ======= 받은 쪽지함 Main ======= -->
	<main id="main" class="main">

		<!-- 받은 쪽지함 pageTitle -->
		<div class="receivebox-pagetitle">
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
		<section class="receivebox-section">
			<!-- 읽은 쪽지 개수와 전체 받은 쪽지 개수를 표시하는 영역 -->
			<div id="noteCount" class="note-count">
				읽은 쪽지 개수: [<span id="readCount">3</span>] / 전체 받은 쪽지 개수: [<span
					id="totalCount">10</span>]
			</div>
			<!-- 검색바&드롭박스 -->
			<div class="search-container">
				<div class="search-bar">
					<form class="search-form d-flex align-items-center" method="POST"
						action="#">
						<div class="dropdown">
							<span class="search-bar-dropdown-toggle" id="navbarDropdown"
								role="button" data-bs-toggle="dropdown" aria-expanded="false"
								onclick="toggleDropdown()"> <span id="selectedItem">전체</span>&nbsp;&nbsp;
								<i class="bi bi-caret-down-fill" id="dropdownIcon"></i>
							</span>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" href="#"
									onclick="changeDropdownItem('전체')">전체</a></li>
								<li><a class="dropdown-item" href="#"
									onclick="changeDropdownItem('아이디')">아이디</a></li>
								<li><a class="dropdown-item" href="#"
									onclick="changeDropdownItem('제목+내용')">제목+내용</a></li>
								<li><a class="dropdown-item" href="#"
									onclick="changeDropdownItem('기간')">기간</a></li>
							</ul>
						</div>
						<input type="text" name="query" placeholder="Search"
							title="Enter search keyword">
						<button type="submit" title="Search">
							<i class="bi bi-search"></i>
						</button>
					</form>
				</div>
			</div>
			<!-- 검색바&드롭박스 END -->
			<!-- 툴바와 테이블 -->
			<div class="table-container">
				<table class="table">
					<thead>
						<tr>
							<th><input type="checkbox" id="selectAll"> <label
								for="selectAll">선택</label></th>
							<th scope="col">읽음</th>
							<th scope="col" class="subject">제목</th>
							<th scope="col">보낸 사람</th>
							<th scope="col">일시</th>
						</tr>
					</thead>
					<!-- 나중에 구현할때 읽은건 폰트에 bold 빼야함 -->
					<tbody>
						<tr class="list-item">
							<td><input type="checkbox"></td>
							<td>읽음 여부</td>
							<td class="subject">첫 번째 메일 제목이 여기에 들어갑니다.</td>
							<td>보낸이1</td>
							<td>2024-02-21 12:34</td>
						</tr>
						<tr class="list-item">
							<td><input type="checkbox"></td>
							<td>읽음 여부</td>
							<td class="subject">두 번째 메일 제목이 여기에 들어갑니다.</td>
							<td>보낸이2</td>
							<td>2024-02-21 13:45</td>
						</tr>
						<tr class="list-item">
							<td><input type="checkbox"></td>
							<td>읽음 여부</td>
							<td class="subject">세 번째 메일 제목이 여기에 들어갑니다.</td>
							<td>보낸이3</td>
							<td>2024-02-21 14:56</td>
						</tr>
						<tr class="list-item">
							<td><input type="checkbox"></td>
							<td>읽음 여부</td>
							<td class="subject">네 번째 메일 제목이 여기에 들어갑니다.</td>
							<td>보낸이4</td>
							<td>2024-02-21 12:34</td>
						</tr>
						<tr class="list-item">
							<td><input type="checkbox"></td>
							<td>읽음 여부</td>
							<td class="subject">다섯 번째 메일 제목이 여기에 들어갑니다.</td>
							<td>보낸이5</td>
							<td>2024-02-21 13:45</td>
						</tr>
						<tr class="list-item">
							<td><input type="checkbox"></td>
							<td>읽음 여부</td>
							<td class="subject">여섯 번째 메일 제목이 여기에 들어갑니다.</td>
							<td>보낸이6</td>
							<td>2024-02-21 14:56</td>
						</tr>
						<tr class="list-item">
							<td><input type="checkbox"></td>
							<td>읽음 여부</td>
							<td class="subject">일곱 번째 메일 제목이 여기에 들어갑니다.</td>
							<td>보낸이7</td>
							<td>2024-02-21 12:34</td>
						</tr>
						<tr class="list-item">
							<td><input type="checkbox"></td>
							<td>읽음 여부</td>
							<td class="subject">여덟 번째 메일 제목이 여기에 들어갑니다.</td>
							<td>보낸이8</td>
							<td>2024-02-21 13:45</td>
						</tr>
						<tr class="list-item">
							<td><input type="checkbox"></td>
							<td>읽음 여부</td>
							<td class="subject">아홉 번째 메일 제목이 여기에 들어갑니다.</td>
							<td>보낸이9</td>
							<td>2024-02-21 14:56</td>
						</tr>
						<tr class="list-item">
							<td><input type="checkbox"></td>
							<td>읽음 여부</td>
							<td class="subject">열 번째 메일 제목이 여기에 들어갑니다.</td>
							<td>보낸이10</td>
							<td>2024-02-21 12:34</td>
						</tr>
						<tr class="list-item">
							<td><input type="checkbox"></td>
							<td>읽음 여부</td>
							<td class="subject">열 한 번째 메일 제목이 여기에 들어갑니다.</td>
							<td>보낸이11</td>
							<td>2024-02-21 13:45</td>
						</tr>
						<tr class="list-item">
							<td><input type="checkbox"></td>
							<td>읽음 여부</td>
							<td class="subject">열 두 번째 메일 제목이 여기에 들어갑니다.</td>
							<td>보낸이12</td>
							<td>2024-02-21 14:56</td>
						</tr>
						<tr class="list-item">
							<td><input type="checkbox"></td>
							<td>읽음 여부</td>
							<td class="subject">열 세 번째 메일 제목이 여기에 들어갑니다.</td>
							<td>보낸이13</td>
							<td>2024-02-21 12:34</td>
						</tr>
						<tr class="list-item">
							<td><input type="checkbox"></td>
							<td>읽음 여부</td>
							<td class="subject">열 네 번째 메일 제목이 여기에 들어갑니다.</td>
							<td>보낸이14</td>
							<td>2024-02-21 13:45</td>
						</tr>
						<tr class="list-item">
							<td><input type="checkbox"></td>
							<td>읽음 여부</td>
							<td class="subject">열 다섯 번째 메일 제목이 여기에 들어갑니다.</td>
							<td>보낸이15</td>
							<td>2024-02-21 14:56</td>
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
		<!-- 리스트 번호 -->
		<nav aria-label="...">
			<ul class="pagination">
				<li class="page-item disabled"><a class="page-link" href="#"
					tabindex="-1" aria-disabled="true">Previous</a></li>
				<li class="page-item"><a class="page-link" href="#">1</a></li>
				<li class="page-item active" aria-current="page"><a
					class="page-link" href="#">2</a></li>
				<li class="page-item"><a class="page-link" href="#">3</a></li>
				<li class="page-item"><a class="page-link" href="#">Next</a></li>
			</ul>
		</nav>
	</main>
	<!-- 받은 쪽지함 Main END-->


	<!-- ======= Footer ======= -->
	<%@ include file="../footer.jsp"%>
	<!-- End Footer -->

	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center">
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
