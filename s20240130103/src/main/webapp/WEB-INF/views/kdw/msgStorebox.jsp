<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>쪽지 보관함 : 블루베리</title>
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
<link href="assets/css/kdw/msgStorebox.css" rel="stylesheet">

<!-- 검색바&드롭박스 JS -->
<script type="text/javascript">
	function changeDropdownItem(value) {
		var dropdown = document.getElementById('dropdownSelect');
		dropdown.value = value;
	}

	document.addEventListener('DOMContentLoaded', function() {
		var dropdown = document.getElementById('dropdownSelect');

		dropdown.addEventListener('click', function(event) {
			event.stopPropagation();
			dropdown.classList.toggle('active');
		});

		document.addEventListener('click', function(event) {
			if (!dropdown.contains(event.target)) {
				dropdown.classList.remove('active');
			}
		});
	});

</script>
<!-- 검색바&드롭박스 JS END-->
</head>
<body>
	<!-- ======= Header ======= -->
	<%@ include file="../header.jsp"%>

	<!-- ======= Sidebar ======= -->
	<%@ include file="../asidebar.jsp"%>
	
	<main id="main" class="main">
	
		<!-- 받은 쪽지함 pageTitle -->
		<div class="pagetitle">
			<h1>쪽지보관함</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="main">Home</a></li>
					<li class="breadcrumb-item active">쪽지보관함</li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->
		<div class="card">
			<!-- 받은 쪽지함 세션 부분 -->
			<section class="receivebox-section">

				<!-- 쪽지 쓰기 -->
				<div class="msg-create">
					<a href="/msgWrite" class="msg-create-btn">쪽지 쓰기</a>
				</div>
				<!-- 읽은 쪽지 개수와 전체 받은 쪽지 개수를 표시하는 영역 -->
				<div id="noteCount" class="note-count">
				    전체쪽지&nbsp;&nbsp;<span id="readCount">3</span>/<span id="totalCount">15</span>
				</div>
				<!-- 검색바&드롭박스 -->
				<div class="search-container">
					<div class="search-bar">
						<form class="search-form d-flex align-items-center" method="POST"
							action="#">
							<select id="dropdownSelect"
								onchange="changeDropdownItem(this.value)">
								<option value="전체">전체</option>
								<option value="아이디">아이디</option>
								<option value="제목+내용">제목+내용</option>
							</select> <input type="text" name="query" placeholder="Search"
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
								<th><input type="checkbox" id="select-all-checkbox">
									<label for="select-all-checkbox"></label></th>
								<th scope="col" class="readStatus">읽음</th>
								<th scope="col" class="attachment">첨부</th>
								<th scope="col" class="mailType">유형</th>
								<th scope="col" class="author">아이디</th>
								<th scope="col" class="subject">제목</th>
								<th scope="col" class="date">일시</th>
							</tr>
						</thead>
						<!-- 나중에 구현할때 읽은건 폰트에 bold 빼야함 -->
						<!-- 테이블 내용 부분 -->
						<tbody id="mailList">
						    <c:forEach var="message" items="${storedMessages}">
						        <tr class="list-item">
						            <td><input type="checkbox"></td>
						            <td class="readStatus">
						                <c:choose>
						                    <c:when test="${message.msg_readdate ne null}">
						                        <img src="assets/img/kdw/read-icon.png" width="17" height="17">
						                    </c:when>
						                    <c:otherwise>
						                        <img src="assets/img/kdw/unread-icon.png" width="17" height="14">
						                    </c:otherwise>
						                </c:choose>
						            </td>
						            <td class="attachment">
						                <img src="" alt="첨부 여부">
						            </td>
									<td class="mailType">
										<!-- 로그인유저와 보낸사람이(user_no = msg_sender) 같다면 -->
									    <c:choose>
									        <c:when test="${message.msg_sender == storeboxUserNo}">
									            발신
									        </c:when>
									        <c:otherwise>
									            수신
									        </c:otherwise>
									    </c:choose>
									</td>
									<td class="author">
										<!-- 로그인유저와 보낸사람이(user_no = msg_sender) 같다면 -->
									    <c:choose>
									        <c:when test="${message.msg_sender == storeboxUserNo}">
									            ${message.msg_receiver}
									        </c:when>
									        <c:otherwise>
									            ${message.msg_sender}
									        </c:otherwise>
									    </c:choose>
									</td>
						            <td class="subject" onclick="markAsRead(${message.msg_no})">
						                ${message.msg_title}
						            </td>
						            <td class="date">${message.msg_createdate}</td>
						        </tr>
						    </c:forEach>
						</tbody>
						<tbody class="mailList-whiteSpace">
							<tr>
								<td colspan="7"></td>
							</tr>
						</tbody>
					</table>
				</div>

			</section>

			<!-- 받은 쪽지함 세션 END -->
			<!-- 리스트 하단 버튼 -->
			<div class="btn-container">
				<button type="button" class="btn-msg-trashbox">삭제</button>
			</div>
			<!-- 리스트 번호 -->
			<nav aria-label="Page navigation"
				class="msgStorebox-pagination-container">
				<ul class="pagination">
					<li class="page-item"><a class="page-link" href="#"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
					<li class="page-item active"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item"><a class="page-link" href="#">4</a></li>
					<li class="page-item"><a class="page-link" href="#">5</a></li>
					<li class="page-item"><a class="page-link" href="#"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</ul>
			</nav>
		</div> <!-- card END -->
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
