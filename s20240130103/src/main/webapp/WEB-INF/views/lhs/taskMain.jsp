<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link href="assets/img/blueberry-favicon.png" rel="icon">
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
<link href="assets/css/lhs/taskMain.css" rel="stylesheet"
	type="text/css">
<!-- 이건 복사해서 사용하지 마세요 헤더 푸터가 아닙니다.-->

<script src="https://kit.fontawesome.com/0b22ed6a9d.js"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>

<body>

	<!-- ======= Header ======= -->
	<%@ include file="../header.jsp"%>

	<!-- ======= Sidebar ======= -->
	<%@ include file="../asidebar.jsp"%>

	<!-- End Sidebar-->

	<main id="main" class="main">
		<section>
			<div class="main-mindiv">
				<div class="main-top">
					<div class="top-btnsdiv">
						<button type="button"
							class="btn top-btn bg-blue fnt-nanum cl-white">
							프로젝트 홈</button>
						<button type="button"
							class="btn top-btn bg-blue fnt-nanum cl-white">
							프로젝트 게시판</button>
					</div>
					<div class="main-top-right">
						<button type="button"
							class="btn btn-outline-secondary dropdown-toggle border0"
							data-bs-toggle="dropdown" aria-expanded="false">
							팀원목록</button>
						<ul class="dropdown-menu">
							<li class="dropdown-item">이한수</li>
							<li class="dropdown-item">김평화</li>
							<li class="dropdown-item">이새잎</li>
							<li class="dropdown-item">유현석</li>
							<li class="dropdown-item">김동욱</li>
							<li class="dropdown-item">유경미</li>
						</ul>
						<a href="#">
							<button type="button"
								class="btn btn-outline-dark fl-right bordergray">
								<i class="bi bi-gear-fill"></i>
							</button>
						</a>
					</div>
				</div>
				<div class="main-mid">
					<div class="main-side">
						<div class="main-side-top">
							<div class="main-side-top-title fnt-nanum">과업목록</div>
							<div class="main-side-top-btn">
								<a href="#">
									<button type="button" class="btn bg-realblue cl-white plus-btn">
										<i class="bi bi-plus"></i>
									</button>
								</a>
							</div>
						</div>
						<div class="main-side-mid">
							<!-- 여기부터 과업 목록 한개-->
							<div class="main-side-mid-content">
								<div class="mid-content-top">
									<div class="fnt-nanum mid-content-title">[시장조사]온라인 자료 조사
										및 취합</div>
									<div>
										<button
											class="btn btn-outline-secondary border0 dropdown-toggle"
											data-bs-toggle="dropdown" aria-expanded="false"></button>
										<ul class="dropdown-menu">
											<a href="">
												<li class="dropdown-item">더보기리그</li>
											</a>
											<a href="">
												<li class="dropdown-item">1등첼시</li>
											</a>
										</ul>
									</div>
								</div>
								<hr />
								<div class="mid-content-mid">
									<div class="content">
										<div>
											<i class="bi bi-check-circle"></i>
										</div>
										<div class="content-first bg-blue cl-white">완료</div>
									</div>
									<div class="content">
										<div>
											<i class="bi bi-people"></i>
										</div>
										<div class="content-second">이한수</div>
										<div class="content-second">유경미</div>
										<div class="content-second">외 4명</div>
									</div>
									<div class="content">
										<div>
											<i class="bi bi-calendar3"></i>
										</div>
										<div class="content-third">2024-03-05</div>
									</div>
								</div>
							</div>
							<!-- 여기까지 과업 목록 한개-->
							<div class="main-side-mid-content">
								<div class="mid-content-top">
									<div class="fnt-nanum mid-content-title">[시장조사]아 존나 하기 싫다
									</div>
									<div>
										<button
											class="btn btn-outline-secondary border0 dropdown-toggle"
											data-bs-toggle="dropdown" aria-expanded="false"></button>
										<ul class="dropdown-menu">
											<a href="">
												<li class="dropdown-item">더보기리그</li>
											</a>
											<a href="">
												<li class="dropdown-item">1등첼시</li>
											</a>
										</ul>
									</div>
								</div>
								<hr />
								<div class="mid-content-mid">
									<div class="content">
										<div>
											<i class="bi bi-check-circle"></i>
										</div>
										<div class="content-first cl-white"
											style="background-color: gray">
											미완료</div>
									</div>
									<div class="content">
										<div>
											<i class="bi bi-people"></i>
										</div>
										<div class="content-second">이한수</div>
										<div class="content-second">유경미</div>
										<div class="content-second">외 4명</div>
									</div>
									<div class="content">
										<div>
											<i class="bi bi-calendar3"></i>
										</div>
										<div class="content-third">2024-03-05</div>
									</div>
								</div>
							</div>

							<div class="main-side-mid-content">
								<div class="mid-content-top">
									<div class="fnt-nanum mid-content-title">[시장조사]아 존나 하기 싫다
									</div>
									<div>
										<button
											class="btn btn-outline-secondary border0 dropdown-toggle"
											data-bs-toggle="dropdown" aria-expanded="false"></button>
										<ul class="dropdown-menu">
											<a href="">
												<li class="dropdown-item">더보기리그</li>
											</a>
											<a href="">
												<li class="dropdown-item">1등첼시</li>
											</a>
										</ul>
									</div>
								</div>
								<hr />
								<div class="mid-content-mid">
									<div class="content">
										<div>
											<i class="bi bi-check-circle"></i>
										</div>
										<div class="content-first cl-white"
											style="background-color: gray">
											미완료</div>
									</div>
									<div class="content">
										<div>
											<i class="bi bi-people"></i>
										</div>
										<div class="content-second">이한수</div>
										<div class="content-second">유경미</div>
										<div class="content-second">외 4명</div>
									</div>
									<div class="content">
										<div>
											<i class="bi bi-calendar3"></i>
										</div>
										<div class="content-third">2024-03-05</div>
									</div>
								</div>
							</div>

							<div class="main-side-mid-content">
								<div class="mid-content-top">
									<div class="fnt-nanum mid-content-title">[시장조사]아 존나 하기 싫다
									</div>
									<div>
										<button
											class="btn btn-outline-secondary border0 dropdown-toggle"
											data-bs-toggle="dropdown" aria-expanded="false"></button>
										<ul class="dropdown-menu">
											<a href="">
												<li class="dropdown-item">더보기리그</li>
											</a>
											<a href="">
												<li class="dropdown-item">1등첼시</li>
											</a>
										</ul>
									</div>
								</div>
								<hr />
								<div class="mid-content-mid">
									<div class="content">
										<div>
											<i class="bi bi-check-circle"></i>
										</div>
										<div class="content-first cl-white"
											style="background-color: gray">
											미완료</div>
									</div>
									<div class="content">
										<div>
											<i class="bi bi-people"></i>
										</div>
										<div class="content-second">이한수</div>
										<div class="content-second">유경미</div>
										<div class="content-second">외 4명</div>
									</div>
									<div class="content">
										<div>
											<i class="bi bi-calendar3"></i>
										</div>
										<div class="content-third">2024-03-05</div>
									</div>
								</div>
							</div>
							<!-- 여기까지 main-side-mid -->
						</div>
					</div>

					<!-- 여기부터 main 몸체 -->
					<div class="main-middle">
						<div class="main-middle-top">
							<div class="main-middle-top-title fnt-nanum">프로젝트 리포트</div>
							<div class="d-flex justify-content-around">
								<div class="middle-top-content">
									<p>전체 과업</p>
									<p>
										<a href="#">0</a>
									</p>
									<div class="middle-top-content-innerdiv">0%</div>
								</div>
								<div class="middle-top-content">
									<p>완료 과업</p>
									<p>
										<a href="#">0</a>
									</p>
								</div>
								<div class="middle-top-content">
									<p>미완료 과업</p>
									<p>
										<a href="#">0</a>
									</p>
								</div>
							</div>
						</div>
						<div class="main-middle-mid">
							<div class="main-middle-mid-title">프로젝트 일정</div>
							<div class="main-middle-calender">대충 달력 들어가는 자리</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</main>
	<!-- End #main -->

	<!-- ======= Footer ======= -->
	<%@ include file="../footer.jsp"%>
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