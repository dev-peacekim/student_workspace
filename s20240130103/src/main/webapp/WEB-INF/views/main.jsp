<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

    <!-- Vendor CSS Files -->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
    <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
    <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
    <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="assets/css/style.css" rel="stylesheet"  type="text/css">
    <link href="assets/css/kph/mainSecssion.css"  rel="stylesheet"  type="text/css"> <!-- 이건 복사해서 사용하지 마세요 헤더 푸터가 아닙니다.-->
     
    <script src="https://kit.fontawesome.com/0b22ed6a9d.js" crossorigin="anonymous"></script>

    <!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: Jan 29 2024 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
    
</head>

<body>

    <!-- ======= Header ======= -->
    <%@ include file="header.jsp" %>

    <!-- ======= Sidebar ======= -->
    <%@ include file="asidebar.jsp" %>
    
    <!-- End Sidebar-->

    <main id="main" class="main">

        <div class="pagetitle">
            <h1>워크 스페이스</h1>
            <nav>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="main">워크 스페이스</a></li>
                    <li class="breadcrumb-item active">Dashboard</li>
                </ol>
            </nav>
        </div><!-- End Page Title -->

        <section class="section dashboard">
        
			<div class="card1 info-card sales-card project">
		        <div class="card-body my-project">
		        	<div class="my-porject-title">
		            	<h5 class="card-title">나의 프로젝트</h5>
		            	<a href="projectAddForm"><button type="submit" class="plus-btn">+</button></a>
		          	</div>
		          	<c:forEach var="project" items="${projectList }">
		        		<div class="detail-card info-card sales-card project-detail">
		        			<div class="card-body my-project-detail">
		        				<div>
		            				<h5 class="my-project-detail-title">${project.project_title }</h5>
		            				<form action="detailProject" method="post">
		            					<input name="project_no" value="${project.project_no }" hidden="true" />
		            					<input type="submit" class="enter-btn" value="입장하기" />
		            				</form>
		          				</div>
		          				<div class="progress" style="margin-top:20px; height: 20px; border-radius: 10px; background-color: rgba(246, 249, 255, 0.3);">
				                <c:choose>
				                    <c:when test="${project.comp_task_count + project.uncomp_task_count != 0}">
				                        <div class="progress-bar" role="progressbar" style="color:white; font-weight: bold; width: ${(project.comp_task_count/(project.comp_task_count+project.uncomp_task_count))*100}%; height: 30px; line-height: 20px;" aria-valuenow="${(project.comp_task_count/(project.comp_task_count+project.uncomp_task_count))*100}" aria-valuemin="0" aria-valuemax="100">${(project.comp_task_count/(project.comp_task_count+project.uncomp_task_count))*100}%</div>
				                    </c:when>
				                    <c:otherwise>
				                        <div class="progress-bar" role="progressbar" style="color:white; font-weight: bold; width: 9%; height: 30px; line-height: 20px;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">0%</div>
				                    </c:otherwise>
				                </c:choose>
				            </div>
		       				</div>
	      				</div>
		        	</c:forEach>
		          	
		       </div>
	      	</div>
	
	      <div class="card2 info-card sales-card summary">
	        <div class="card-body">
	          <div class="all-project-report">
	            <h5 class="card-title">전체 프로젝트 리포트</h5>
	            	<div class="all-project-report-box">
	            		<div class="all-project-report-detail card-body">
			            	<div>
			            		<p>전체 과업</p>
			            		<p>전체 과업</p>
			            	</div>
			            	<div>sadf</div>
				        </div>
				        <div class="all-project-report-detail card-body">
			            	<div>
			            		<p>전체 과업</p>
			            		<p>전체 과업</p>
			            	</div>
			            	<div>sadf</div>
				        </div>
				        <div class="all-project-report-detail card-body">
			            	<div>
			            		<p>전체 과업</p>
			            		<p>전체 과업</p>
			            	</div>
			            	<div>sadf</div>
				        </div>
	            	</div>
			        
			     </div>
			     <div class="address">
			          <h5 class="card-title">주소록</h5>  
			        </div>
		      </div>
	      </div>
	      
        </section>
    </main>
    <!-- End #main -->

    <!-- ======= Footer ======= -->
    <%@ include file="footer.jsp" %>
    <!-- End Footer -->

    <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
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