<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="boardConfig.jsp"%>
<link href="assets/css/ykm/boardPost.css" rel="stylesheet">

<!-- jQuery -->
<script defer
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<!-- Vendor JS Files -->
<script defer src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Template Main JS File -->
<script defer src="assets/js/main.js"></script>

<!-- Reply JS File -->
<script defer src="assets/js/ykm/comment.js"></script>

<!-- Confirm Pop-up JS File -->
<script defer src="assets/js/ykm/confirmPopup.js"></script>

<script>

window.onload = function() {
	getUserNo();
	getCommentList(${getPost.cboard_no});
};

</script>
 
</head>
<body>

	<!-- ======= header ======= -->
	<%@ include file="../header.jsp"%>

	<main id="main" class="main">
		<div class="pagetitle">
			<h1>공모전 스터디 게시판</h1>
			<nav style="--bs-breadcrumb-divider: '-';">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="main">Home</a></li>
					<li class="breadcrumb-item active"><a href="boardContest">공모전</a></li>
					<li class="breadcrumb-item active"><a href="boardStudy">스터디</a></li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->
		<section class="community-post-detail">
			<div class="row card card-body">
				<div class="card-header community-post-header">
					<h3 class="card-title post-title">${getPost.cboard_title}</h3>
					<div class="card-subtitle post-user-container">
						<i class="bi bi-person-circle post-user-profile"
							alt="${getPost.user_profile}"></i>
						<div class="card-title-header">
							<h5 class="card-title post-user-info">
								<a href="#">${getPost.user_id}</a>
							</h5>
							<div class="card-subtitle post-subtitle">
								<p class="post-updated-at">
									작성일
									<fmt:formatDate value="${getPost.cboard_date}"
										pattern="yyyy.MM.dd a hh:mm" />
								</p>
								<p class="post-veiw-count">조회수
									${getPost.cboard_viewcnt}</p>
							</div>
						</div>

						<div class="modify-delete-container">
							<a href="/updateForm?cboard_no=${getPost.cboard_no}" class="badge bg-light text-dark"> <i
								class="bi bi-pencil-fill"></i> 수정</a>
							<button type="button" id="postDeleteBtn" class="badge bg-light text-dark">
								<i class="bi bi-trash"></i> 삭제
							</button>
						</div>
						
					</div>
				</div>
				
				<div class="community-post-body">
					<span class="post-content">${getPost.cboard_content}</span>
				</div>


				<div class="community-post-answer">
					<div class="comment-form">
						댓글 <span class="answer-count">${countComment}</span>
					</div>
					<!-- 댓글 REST API -->
					<div class="boardPostComment">
						<input type="hidden" name="cboard_no" value="${getPost.cboard_no}" /> 
						<input type="hidden" name="user_no" value="${getPost.user_no}"> 
						<input type="hidden" name="creply_no" value="" />
						
						<div class="comment-editor">
							<i class="bi bi-person-circle comment-user-profile" alt="유저 프로필"></i>
							<input type="text" id="creply_content"
								placeholder="${getPost.user_id}님, 댓글을 작성해보세요."
								class="form-control commentEditor" required="required">
						</div>
						<div class="btn-container">
							<button id="commentResetBtn" type="button"
								class="hidden btn btn-secondary">취소</button>
							<button id="commentSubmitBtn" type="submit"
								class="hidden btn btn-primary">등록</button>
						</div>
					</div>
				</div>
				<!-- 댓글 리스트 -->
				<div id="replyContainer">
					<!-- ajax 비동기 갱신 -->
				
			</div>

			<!-- ======= 삭제 확인 pop up ======= -->
			<div class="card card-body confirmPopup">
				<div class="popup-container">
					<i class="bi bi-exclamation-triangle popup-icon"></i> 
					<span class="popup1">정말로 삭제하시겠습니까?</span> 
					<span class="popup2">확인을 누르면 글이 삭제됩니다.</span>
				</div>
				<div class="confirm-deletion">
					<button type="button" id="cancelButton" class="btn btn-outline-dark">취소</button>
					<a href="/deletePost?cboard_no=${getPost.cboard_no}">
						<button type="button" id="confirmButton" class="btn btn-primary">확인</button>
					</a>
				</div>
			</div>

		</section>

		<!-- ======= aSidebar ======= -->
		<%@ include file="../asidebar.jsp"%>

	</main>

	<!-- ======= Footer ======= -->
	<%@ include file="../footer.jsp"%>
	<a href="#" class="back-to-top d-flex align-items-center justify-content-center">
		<i class="bi bi-arrow-up-short"></i>
	</a>
</body>
</html>
