<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <title>글쓰기</title> <!-- 페이지 제목은 변경하지 않았습니다. -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script> <!-- jQuery CDN -->
  <meta content="" name="description">
  <meta content="" name="keywords">
  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
  
  <!-- Reply Js -->
  <script defer src="assets/js/lsl/boardFreeASkReply.js"></script>
  
  <!-- Template Main CSS File -->
  <link href="assets/css/lsl/lslboardFreeContents.css" rel="stylesheet"> 
   <link href="assets/css/style.css" rel="stylesheet"> <!-- 헤더, 푸터, 사이드바 css -->
  
 <script>
window.onload = function() {
	//replyBoardFreeAskList(${boardAskContents.cboard_no},  ${boardAskContents.user_no});
	replyBoardFreeAskList(${boardAskContents.cboard_no});
}
</script>
  
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

    <!-- ======= Main ======= -->
<main id="main" class="main">
    <div class="pagetitle">
        <h1>게시판</h1> 
        <nav style="--bs-breadcrumb-divider: '-';">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="main">Home</a></li>
                <li class="breadcrumb-item"><a href="boardFree">FREE</a></li>
                <li class="breadcrumb-item"><a href="boardAsk">ASK</a></li>
            </ol>
        </nav>
    </div><!-- End Page Title -->
    <section class="community-post-detail">
        <div class="row card main-card card-body">
            <div class="card-header community-post-header">
                <h3 class="card-title post-header-title">${boardAskContents.cboard_title}</h3>
                <div class="card-subtitle post-user-container">
                    <i class="bi bi-person-circle post-user-profile" 
                    alt="${boardAskContents.user_profile}"></i>
                    <div class="card-title-header">
                        <h5 class="card-title post-user-name">
                            <a href="#">${boardAskContents.user_nic}</a>
                        </h5>
                        <div class="card-subtitle post-subtitle">
                            <p class="post-updated-at">
                                작성일
                                <fmt:formatDate value="${boardAskContents.cboard_date}"
										pattern="yyyy.MM.dd a hh:mm" />
                            </p>
                            <p class="post-veiw-count">조회수 
                                ${boardAskContents.cboard_viewcnt}
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="community-post-header-body">
                <span class="post-content">${boardAskContents.cboard_content}</span>
            </div>

          <section class="community-post-answer">
            <div class="answer-info-header">
                <div class="answer-info-title">
                    댓글 <span class="answer-info-title-count">23</span>
                </div>
                
                
                <div class="boardPostComment">
						<input type="hidden" name="cboard_no" value="${boardAskContents.cboard_no}" /> 
							<input type="hidden" name="user_no" value="${boardAskContents.user_no}"> 
							<input type="hidden" name="creply_no" value="${boardAskContents.creply_no}" />
                <div class="comment-editor">
                    <i class="bi bi-person-circle comment-user-profile" alt="유저 프로필"></i>
                    <input type="text" name="comment" id="creply_content"
                        placeholder="회원님, 댓글을 작성해보세요." class="form-control" required="required">
                </div>
                <div class="btn-container is-editor-open">
                        <button type="button" id="submitBtn" class="btn submitBtn">등록</button>
                </div>
                </div>
                </div>
                 <!-- 댓글 리스트 -->
				<div id="boardReplyForm">
					<!-- ajax 비동기 갱신 -->
				
            </div>
        </section>
        
			  <c:if test="${sessionScope.user_no eq boardAskContents.user_no}">
                        <button  class="btn bacDelete">삭제</button>
                        <a href="boardFreeModify">
                            <button class="btn bacModify">수정</button>
                        </a>
                    </c:if>

                        <button type="button" class="btn bacList" onclick="goBack()">목록</button>
                    <script>
                        function goBack() {
                            window.history.back();
                        }
                    </script>
                    
            </div>
            </section>
</main>




    <!-- ======= End Main ======= -->
    
    
    <!-- ======= Footer ======= -->
    <%@ include file="../footer.jsp" %>
    <!-- End Footer -->
   
   
    <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>
   
   
    <!-- Vendor JS Files -->
    <script defer src="assets/vendor/apexcharts/apexcharts.min.js"></script>
    <script defer src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script defer src="assets/vendor/chart.js/chart.umd.js"></script>
    <script defer src="assets/vendor/echarts/echarts.min.js"></script>
    <script defer src="assets/vendor/quill/quill.min.js"></script>
    <script defer src="assets/vendor/simple-datatables/simple-datatables.js"></script>
    <script defer src="assets/vendor/tinymce/tinymce.min.js"></script>
    <script defer src="assets/vendor/php-email-form/validate.js"></script>
    
    
    <!-- Template Main JS File -->
    <script defer src="assets/js/lsl/main.js"></script>
</body>
</html>
