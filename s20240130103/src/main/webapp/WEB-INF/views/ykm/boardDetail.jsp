<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="boardConfig.jsp"%>
<link href="assets/css/ykm/boardDetail.css" rel="stylesheet">
</head>
<body>
	<!-- 	<script type="text/javascript">
		document.addEventListener("DOMContentLoaded", function() {
			// 입력 필드 가져오기
			var inputField = document.getElementById('inputField');

			// 버튼들 가져오기
			var submitBtn = document.getElementById('submitBtn');
			var resetBtn = document.getElementById('resetBtn');

			// 입력 필드에 클릭 이벤트 리스너 추가
			inputField.addEventListener('click', function() {
				// 버튼들이 숨겨져 있으면 보이게 하고, 보이고 있으면 숨기기
				if (submitBtn.classList.contains('hidden')) {
					submitBtn.classList.remove('hidden');
					resetBtn.classList.remove('hidden');
				} else {
					submitBtn.classList.add('hidden');
					resetBtn.classList.add('hidden');
				}
			});

		});
	</script> -->
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
			<div class="row card main-card card-body">
				<div class="card-header community-post-header">
					<h3 class="card-title post-header-title">함께 공부할 스티디원 모집합니다!</h3>
					<div class="card-subtitle post-user-container">
						<i class="bi bi-person-circle post-user-profile" alt="유저 프로필"></i>
						<div class="card-title-header">
							<h5 class="card-title post-user-name">
								<a href="#">유저네임</a>
							</h5>
							<div class="card-subtitle post-subtitle">
								<p class="post-updated-at">작성일 2024.02.23 오후 5:24</p>
								<p class="post-veiw-count">조회수 11</p>
							</div>
						</div>
						<form class="modify-icon" method="get">
							<span class="badge bg-light text-dark"><i class="bi bi-pencil-fill"></i> 수정</span>
						</form>
					</div>
				</div>
				<div class="community-post-header-body">
					<span class="post-content">함께 할 수 있으면 좋을 것 같습니다!</span>
				</div>
				<section class="community-post-answer">
					<div class="answer-info-header">
						<div class="answer-info-title">
							댓글 <span class="answer-info-title-count">23</span>
						</div>
						<div class="comment-editor">
							<i class="bi bi-person-circle comment-user-profile" alt="유저 프로필"></i>
							<input type="text" name="comment" id="inputField"
								placeholder="id님, 댓글을 작성해보세요." class="form-control" required="">
						</div>
						<div class="btn-container is-editor-open">
							<form action="boardCommentreset" method="get">
								<button id="resetBtn" class="hidden btn btn-secondary">취소</button>
							</form>
							<form action="boardCommentsubmit" method="get">
								<button id="submitBtn" class="hidden btn btn-primary">등록</button>
							</form>
						</div>
					</div>
				</section>
				<div class="re-comment-body">
					<div class="comment-card">
						<div class="comment-header">
							<i class="bi bi-person-circle comment-user-profile" alt="유저 프로필"></i>
							<div class="comment-user-container">
								<p class="card-title comment-user-name">
									<a href="#">댓글작성자</a>
								</p>
								<p class="card-subtitle comment-updated-at">작성일 2024.02.24
									오후 2:24</p>
							</div>
							<div class="re-btn-container">
								<form action="/commentReplay" method="GET">
									<button type="submit" class="btn btn-outline-dark">
										<i class="bi bi-reply-fill">Reply</i>
									</button>
								</form>
							</div>
						</div>
						<div class="card-body comment-body">
							<p class="markdown-body">근데 이거.. 어떻게 참여 원하시는 분들한테 연락을 드리는
								건가요?</p>
						</div>
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
							<span class="reply-content">쪽지 남겨놓으면 될까요?</span><i class="bi bi-reply-fill"></i>
						</div>
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

</body>
</html>
