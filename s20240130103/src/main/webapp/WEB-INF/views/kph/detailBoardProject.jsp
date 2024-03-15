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
    <link href="assets/img/blueberry-favicon.png" rel="icon">
    <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">
    
    <!-- Google Fonts -->
  	<link href="https://fonts.gstatic.com" rel="preconnect">
  	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
    
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
    <link href="assets/css/kph/detailBoardProject.css"  rel="stylesheet"  type="text/css"> <!-- 이건 복사해서 사용하지 마세요 헤더 푸터가 아닙니다.-->
    <script src="https://kit.fontawesome.com/0b22ed6a9d.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.11/index.global.min.js"></script>
    <script defer src="/assets/js/kph/detailBoardProject.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    
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
    <%@ include file="../header.jsp" %>

    <!-- ======= Sidebar ======= -->
    <%@ include file="../asidebar.jsp" %>
    
    <!-- End Sidebar-->

    <main id="main" class="main">

		<div class="pagetitle">
			<h1>워크 스페이스</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="main">워크 스페이스</a></li>
					<li class="breadcrumb-item active">공유 게시판</li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->

		<section class="section dashboard">
			<input class="projectLeader_no" type="hidden" name="projectLeader_no" value="${projectLeader_no }" />
			<div class="top">
				<div class="top-btn">
					<form action="detailProject" method="get">
						<input class="project_no" type="hidden" name="project_no" value="${project_no }" />
						<button type="button" class="project-home-btn btn btn-secondary">프로젝트
						홈</button>
					</form>
					<form action="boardProject" method="get">
						<input class="project_no" type="hidden" name="project_no" value="${project_no }" />
						<button type="submit" class="project-board-btn btn btn-primary">공유
						게시판</button>			
					</form>
					<form action="proejctSchedule" method="get">
						<input class="project_no" type="hidden" name="project_no" value="${project_no }" />
						<button type="submit" class="project-schedule-btn btn btn-secondary">프로젝트 일정</button>			
					</form>
				</div>
			</div>
			<div class="post">
				<div class="post-title">${board.pboard_title}</div>
				<div class="post-user">
					<c:if test="${empty board.user.user_profile }">
						<img
							src="${pageContext.request.contextPath}/upload/userImg/987654321487321564defaultImg.jpg"
							alt="Profile" class="rounded-circle">
					</c:if>
					<c:if test="${not empty board.user.user_profile }">
						<img
							src="${pageContext.request.contextPath}/upload/userImg/${board.user.user_profile}"
							alt="Profile" class="rounded-circle">
					</c:if>
					<div class="post-user-detail">
						<p class="user-name">${board.user.user_name}</p>
						<c:if test="${board.pboard_update_date == null }">
							<p class="user-write-day">${board.pboard_date}</p>
						</c:if>
						<c:if test="${board.pboard_update_date != null }">
							<p class="user-write-day">${board.pboard_update_date}</p>
						</c:if>
					</div>
				</div>
				<div class="post-content">
					${board.pboard_content }
				</div>
				<div class="post-meta">
					<div class="count">
						<div class="view-count">
							<i class="bi bi-eye-fill"></i>${board.pboard_cnt }
						</div>
						<div class="reply-count">
							<i class="bi bi-card-text"></i>${board.replyCnt}
						</div>
					</div>
					<div class="attached-file">
						<div class="attached-file-title">첨부파일</div>
						<div class="attached-file-list">
							<c:forEach var="file" items="${fileList }">
								<div class="file">
									<a
										href="boardProjectFileDownload?pboard_no=${board.pboard_no }&pboard_file_no=${file.pboard_file_no}"><i
										class="bi bi-file-earmark-arrow-down"></i> ${file.pboard_file_user_name }</a>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
				<hr>
				<div class="reply">
					<div class="reply-title">댓글</div>
					<div class="reply-write-box">
						<textarea class="form-control reply-write-area"
							placeholder="댓글을 입력하세요"></textarea>
						<button type="button" class="btn btn-primary reply-write-btn">등록</button>
					</div>
					<div class="reply-list">
						<c:forEach var="replyList" items="${replyListGroups }">
							<div class="reply-detail">
								<input type="hidden" name="user_no" value="${replyList.get(0).user_no}" />
								<div class="reply-detail-top">
									<div class="reply-writer">
										<input type="hidden" name="user_no" value="${replyList.get(0).user_no}" />
										<c:if test="${empty replyList.get(0).user.user_profile }">
											<img
												src="${pageContext.request.contextPath}/upload/userImg/987654321487321564defaultImg.jpg"
												alt="Profile" class="rounded-circle">
										</c:if>
										<c:if test="${not empty replyList.get(0).user.user_profile }">
											<img
												src="${pageContext.request.contextPath}/upload/userImg/${replyList.get(0).user.user_profile}"
												alt="Profile" class="rounded-circle">
										</c:if>
										<div class="reply-writer-detail">
											<p class="reply-user-name">${replyList.get(0).user.user_name }</p>
											<c:if test="${replyList.get(0).preply_update_date == null }">
												<p class="reply-write-day">${replyList.get(0).preply_date}</p>
											</c:if>
											<c:if test="${replyList.get(0).preply_update_date != null }">
												<p class="reply-write-day">${replyList.get(0).preply_update_date}</p>
											</c:if>
										</div>
									</div>
									<div class="reply-reply-write">
										<i class="bi bi-reply-fill"></i> 답글
									</div>
								</div>
								<c:choose>
									<c:when test="${replyList.get(0).preply_delete_chk == 0 }">
										<div class="reply-content">${replyList.get(0).preply_content }</div>
									</c:when>
									<c:otherwise>
										<div class="reply-content">삭제된 댓글입니다.</div>
									</c:otherwise>
								</c:choose>
								<div class="reply-reply-reply-write-box">
									<textarea class="form-control reply-reply-reply-write-area"
										placeholder="댓글을 입력하세요">
											</textarea>
									<button type="button"
										class="btn btn-primary reply-reply-reply-write-btn">
										등록</button>
								</div>
								<c:if test="${replyList.size() > 1 }">
									<div class="reply-reply-box">
										<p class="reply-reply-btn">답글 더보기</p>
										<div class="reply-reply-list">
											<c:forEach var="reply" items="${replyList }" begin="1">
												<div class="reply-reply-detail">
													<div class="reply-reply-detail-top">
														<input type="hidden" name="user_no"
															value="${reply.user_no }">
														<div class="reply-reply-writer">
															<c:if test="${empty reply.user.user_profile }">
																<img
																	src="${pageContext.request.contextPath}/upload/userImg/987654321487321564defaultImg.jpg"
																	alt="Profile" class="rounded-circle">
															</c:if>
															<c:if test="${not empty reply.user.user_profile }">
																<img
																	src="${pageContext.request.contextPath}/upload/userImg/${reply.user.user_profile}"
																	alt="Profile" class="rounded-circle">
															</c:if>
															<div class="reply-reply-writer-detail">
																<p class="reply-reply-user-name">${reply.user.user_name }</p>
																<c:if test="${reply.preply_update_date == null }">
																	<p class="reply-reply-write-day">${reply.preply_date }</p>
																</c:if>
																<c:if test="${reply.preply_update_date != null }">
																	<p class="reply-reply-write-day">${reply.preply_update_date }</p>
																</c:if>
															</div>
														</div>
														<div class="reply-reply-reply-write">
															<i class="bi bi-reply-fill"></i> 답글
														</div>
													</div>
													<div class="reply-reply-content">
														<c:if test="${reply.preply_indent == 0 }">
															<div>${reply.preply_content }</div>
														</c:if>
														<c:if test="${reply.preply_indent == 1 }">
															<span class="tag">${reply.preply_content.substring(0, reply.preply_content.indexOf(" ")) }</span>
															<div>${reply.preply_content.substring(reply.preply_content.indexOf(" "), reply.preply_content.length() - 1) }</div>
														</c:if>
													</div>
												</div>
											</c:forEach>
										</div>
									</div>
								</c:if>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</section>
	</main>
    <!-- End #main -->

    <!-- ======= Footer ======= -->
    <%@ include file="../footer.jsp" %>
    <!-- End Footer -->

    <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
            class="bi bi-arrow-up-short"></i></a>

    <!-- Vendor JS Files -->
    <!-- <script src="assets/vendor/apexcharts/apexcharts.min.js"></script> -->
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- <script src="assets/vendor/chart.js/chart.umd.js"></script> -->
    <!-- <script src="assets/vendor/echarts/echarts.min.js"></script> -->
    <!-- 
    <script src="assets/vendor/quill/quill.min.js"></script>
 	<script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
  	<script src="assets/vendor/tinymce/tinymce.min.js"></script>
  	<script src="assets/vendor/php-email-form/validate.js"></script> -->

    <!-- Template Main JS File -->
    <script src="assets/js/main.js"></script>

</body>

</html>