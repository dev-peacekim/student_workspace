<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="boardConfig.jsp"%>
<link href="assets/css/ykm/boardDetail.css" rel="stylesheet">
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
					<c:forEach items="${renderPostContent}" var="post">
						<h3 class="card-title post-title">${post.cboard_title}</h3>
						<div class="card-subtitle post-user-container">
							<i class="bi bi-person-circle post-user-profile"
								alt="${post.user_profile}"></i>
							<div class="card-title-header">
								<h5 class="card-title post-user-info">
									<a href="#">${post.user_id}</a>
								</h5>
								<div class="card-subtitle post-subtitle">
									<p class="post-updated-at">${post.cboard_date}</p>
									<p class="post-veiw-count">조회수 ${post.cboard_viewcnt}</p>
								</div>
							</div>
							<div class="modify-container">
								<form class="modDelBtn" method="get">
									<button type="button" class="badge bg-light text-dark">
										<i class="bi bi-pencil-fill"></i> 수정
									</button>
									<button type="button" class="badge bg-light text-dark">
										<i class="bi bi-trash"></i> 삭제
									</button>
								</form>
							</div>
						</div>
				</div>
				<div class="community-post-body">
					<span class="post-content">${post.cboard_content}</span>
				</div>
				</c:forEach>

				<div class="community-post-answer">
					<div class="answer-form">
						댓글 <span class="answer-count">23</span>
					</div>
					<form action="boardComment">
						<div class="comment-editor">
							<i class="bi bi-person-circle comment-user-profile" alt="유저 프로필"></i>
							<input type="text" name="comment" id="inputField"
								placeholder="id님, 댓글을 작성해보세요." class="form-control" required="">
						</div>
						<div class="btn-container">
							<button id="resetBtn" type="button"
								class="hidden btn btn-secondary">취소</button>
							<button id="submitBtn" type="button"
								class="hidden btn btn-primary">등록</button>
						</div>
					</form>
				</div>

				<div class="comment-card">
					<div class="comment-header">
						<i class="bi bi-person-circle comment-user-profile" alt="유저 프로필"></i>
						<div class="comment-user-container">
							<p class="card-title comment-user-name">
								<a href="#">댓글작성자</a>
							</p>
							<p class="card-subtitle comment-updated-at">작성일 2024.02.24 오후
								2:24</p>
						</div>
						<div class="replyBtn badge bg-light text-dark">
							<i class="bi bi-reply-fill"></i> 댓글
						</div>
					</div>
					<div class="comment-body">
						<p>근데 이거.. 어떻게 참여 원하시는 분들한테 연락을 드리는 건가요?</p>
					</div>
				</div>

				<div class="reply-comment">
					<form action="ref-reply" method="get">
						<input type="hidden" name="user-profile" value="${profile}">
						<input type="hidden" name="user-name" value="${user-name}">
						<input type="hidden" name="group" value="${group}"> <input
							type="hidden" name="level" value="${level}"> <input
							type="hidden" name="indent" value="${indent}">
						<div class="reply-header">
							<i class="bi bi-person-circle reply-user-profile" alt="유저 프로필"></i>
							<span class="reply-user-name">대댓글작성자</span> <span
								class="reply-updated-at">작성일 2024.02.24 오후 11:20</span>
						</div>
						<div class="reply-body">
							<span class="reply-content">쪽지 남겨놓으면 될까요?</span><i
								class="bi bi-reply-fill"></i>
						</div>
					</form>
				</div>
				<!-- ======= popup ======= -->
				<div class="card card-body confirmPopup">
					<div class="popup-container">
						<i class="bi bi-exclamation-triangle popup-icon"></i> <span
							class="popup1">정말로 삭제하시겠습니까?</span> <span class="popup2">확인을
							누르면 글이 삭제됩니다.</span>
					</div>
					<form action="confirm-deletion" method="get">
						<button type="button" id="cancelButton"
							class="btn btn-outline-dark">취소</button>
						<button type="button" id="confirmButton" class="btn btn-primary">확인</button>
					</form>
				</div>
			</div>
		</section>


		<!-- ======= aSidebar ======= -->
		<%@ include file="../asidebar.jsp"%>

	</main>

	<!-- ======= Footer ======= -->
	<%@ include file="../footer.jsp"%>
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Vendor JS Files -->
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>

	<!-- Confirm Pop-up JS File -->
	<script src="assets/js/ykm/confirmPopup.js" type="text/javascript"
		defer></script>
</body>
</html>