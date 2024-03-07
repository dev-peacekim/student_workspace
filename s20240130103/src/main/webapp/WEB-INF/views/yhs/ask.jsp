<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>문의사항</title>
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
	
.card-title{
	font-family: 'NanumSquare', sans-serif;
	color: #0D6DFD;
	font-weight: bold;
}


.search-bar {
	min-width: 360px;
	padding-top: 10px;
	font-family: 'NanumSquare', sans-serif;
	position: absolute; /* 절대 위치로 설정 */
    top: 30px; /* 상단에 배치 */
    right: -40px; /* 우측 여백 설정 */
    margin-bottom: 40px; /* 서치바 아래 여백 설정 */
    
}

@media (max-width : 1199px) {
.search-bar {
	position: fixed;
	top: 50px;
	left: 0;
	right: 0;
	padding-top: 20px;
	padding-bottom: 20px;
	box-shadow: 0px 0px 15px 0px rgba(1, 41, 112, 0.1);
	background: white;
	z-index: 9999;
	transition: 0.3s;
	visibility: hidden;
	opacity: 0;
	}

.search-bar-show {
	top: 60px;
	visibility: visible;
	opacity: 1;
	}
}

.search-form {
	width: 100%;
}

.search-form input {
	border: 0;
	font-size: 14px;
	color: #012970;
	border: 1px solid rgba(1, 41, 112, 0.2);
	padding: 7px 38px 7px 8px;
	border-radius: 3px;
	transition: 0.3s;
	width: 300px;
}

.search-form input:focus,
.search-form input:hover {
	outline: none;
	box-shadow: 0 0 10px 0 rgba(1, 41, 112, 0.15);
	border: 1px solid rgba(1, 41, 112, 0.3);
}

.search-form button {
	border: 0;
	padding: 0;
	margin-left: -30px;
	background: none;
}

.search-form button i {
	color: #012970;
}

/*--------------------------------------------------------------
# Search Bar
--------------------------------------------------------------*/
.section {
	font-family: 'NanumSquare', sans-serif;
}


.nav-link {
	font-weight: bold;
	font-size: large;
}


.table thead th{
	padding: 12px;
	text-align: center;
	border-bottom: 1px solid #0D6DFD;
	font-weight: bold;
	color: #0D6DFD;
}


.table tbody tr{
	text-align: center;
} 


.btn-msg-primary {
	color: #0d6efd;
	background-color: transparent;
	border: 1px solid #0D6DFD; 
  	border-radius: 7px; /* 모서리를 둥글게 만들기 */
  	padding: 2px 15px;
  	transition: background-color 0.3s ease; /* 부드러운 전환을 위한 트랜지션 추가 */
}

.btn-msg-primary:hover {
  background-color: #B52BFC; /* 마우스를 올렸을 때의 배경색 */
  color: #ffffff; /* 마우스를 올렸을 때의 텍스트 색상 */
  border: 1px solid #B52BFC; 
}
.floatright{
	float: right;
}


</style>
</head>
<body>
	<!-- ======= Header ======= -->
	<%@ include file="../header.jsp"%>

	<!-- ======= Sidebar ======= -->
	<%@ include file="../asidebar.jsp"%>

	<main id="main" class="main">
	 <div class="pagetitle">
      <h1>문의사항</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="main">Home</a></li>
		  <li class="breadcrumb-item"><a href="askList">문의사항</a></li>
        </ol>
      </nav>
    </div>
    
    <!-- End Page Title -->

    <section class="section">
    	<div class="row">
    		<div class="col-lg-12">
    			<div class="card">
    				<div class="card-body">
    					<h5 class="card-title">여러분의 질문은 Blueberry를 성장시키는 자양분입니다!</h5>
    						<p></p>
    						
    						<!-- Search Bar -->
						
					
							<form action="askListSearch" class="search-form d-flex align-items-center">
								<div class="search-bar" >
								<input type="text" name="keyword" placeholder="키워드를 입력하세요" title="Enter search keyword">
								<button type="submit" title="Search">
									<i class="bi bi-search"></i>
								</button>
						</div>
						</form>
						<!-- End Search Bar -->
						
						
    					<!-- <div class="search-bar d-flex justify-content-end">
    						<div class="col-md-2"> -->
    						<!-- <select id="dropdownSelect"
								onchange="changeDropdownItem(this.value)">
								<option value="전체">전체</option>
								<option value="아이디">아이디</option>
								<option value="제목+내용">제목+내용</option>
							</select>   		 -->				
    					<!-- 	</div>   						
    						<div class="col-md-3">
   							<form class="search-form d-flex align-items-center" 
   								method="POST" action="#">
    						<input type="text" name="query" placeholder="Search" 
    							title="Enter search keyword">
    						<button type="submit" title="Search">
    							<i class="bi bi-search"></i>
    						</button>
    						</form>
    						</div>
    					</div> -->
    					
	<!-- Table with stripped rows -->
	
              			<table class="table datatable">
                			<thead>
                  				<tr>
                    				<th>문의번호</th>
                    				<th>사용자번호</th>
                    				<th>제목</th>
                    				<th>작성일시</th>
                    				<th>발생일시</th>
                    				<th>답변여부</th>
                  				</tr>
                			</thead>
                			<tbody>
<%--                 				<tr th:each="ask : ${BOARD_ADMIN}">
									<td th:text="${ask.id}"></td>
									<td th:text="${ask.name}"></td>
								</tr> --%>
								
								
								<c:forEach items="${listAsk }" var="BOARD_ADMIN">
								<tr>
									<td>${BOARD_ADMIN.admin_no}</td>
									<td>${BOARD_ADMIN.user_no}</td>						
									<td><a href="askContent?admin_title=${BOARD_ADMIN.admin_title}">${BOARD_ADMIN.admin_title}</a></td>
									<td>${BOARD_ADMIN.admin_date}</td>
									<td>${BOARD_ADMIN.admin_start}</td>
									<td>${BOARD_ADMIN.admin_reply_chk}</td>	
								</tr>
								</c:forEach>
                			</tbody>
              			</table>
              			<div class="floatright">
		  				<button type="button" class="btn-msg-primary">
		  					<a href="askForm">글작성</a></button>
		  				</div>
              <!-- End Table with stripped rows -->
              <c:if test="${page.startPage > page.pageBlock }">
				<a href="listAsk?currentPage=${page.startPage-page.pageBlock}">[이전]</a>
			</c:if>
				<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
				<a href="listAsk?currentPage=${i}">[${i}]</a>
				</c:forEach>
				<c:if test="${page.endPage < page.totalPage }">
				<a href="listAsk?currentPage=${page.startPage+page.pageBlock}">[다음]</a>
				</c:if>	
              
            			</div>
          			</div>
          		</div>
      		</div>
		
    </section>
    
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