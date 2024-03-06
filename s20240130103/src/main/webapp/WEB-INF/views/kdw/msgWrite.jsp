<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>쪽지 쓰기 : 블루베리</title>
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


<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- KDW Main CSS File -->
<link href="assets/css/kdw/msgWrite.css" rel="stylesheet">

<script type="text/javascript">
document.addEventListener('DOMContentLoaded', function() {
    var receiverInput = document.getElementById('receiverInput');
    var toggleButton = document.getElementById('toggleButton');
    var userListDropdown = document.getElementById('userListDropdown');

    var isToggleButtonClicked = false;

    receiverInput.addEventListener('click', function(event) {
        // receiverInput을 클릭하면 클릭 상태를 토글합니다.
        isToggleButtonClicked = !isToggleButtonClicked;

        // 클릭 상태에 따라 toggleButton의 클릭 상태를 설정합니다.
        toggleButton.classList.toggle('active', isToggleButtonClicked);

        // 클릭 상태에 따라 userListDropdown의 표시 상태를 설정합니다.
        userListDropdown.style.display = isToggleButtonClicked ? 'block' : 'none';

        // 이벤트 전파를 막기 위해 추가합니다.
        event.stopPropagation();
    });

    toggleButton.addEventListener('click', function() {
        // toggleButton을 클릭하면 클릭 상태를 토글합니다.
        isToggleButtonClicked = !isToggleButtonClicked;

        // 클릭 상태에 따라 receiverInput의 클릭 상태를 설정합니다.
        receiverInput.classList.toggle('active', isToggleButtonClicked);

        // 클릭 상태에 따라 userListDropdown의 표시 상태를 설정합니다.
        userListDropdown.style.display = isToggleButtonClicked ? 'block' : 'none';
    });

    // document에 대한 클릭 이벤트 리스너를 추가하여 dropdown이 닫히도록 합니다.
    document.addEventListener('click', function() {
        isToggleButtonClicked = false;
        toggleButton.classList.remove('active');
        userListDropdown.style.display = 'none';
    });
    
    
}); // 건들지말것

	// 선택한 유저 인풋창에 박기
	function selectReceiver(userNo, userNic, userName) {
		var selectedUserInfo = userNo;
		document.getElementById('receiverInput').value = selectedUserInfo;
	}
	
	document.addEventListener('DOMContentLoaded', function() {
	    var dropZone = document.getElementById('drop_zone');
	    var fileList = document.getElementById('fileList');
	    var filesInput = document.getElementById('files');
	    var initialMessage = document.getElementById('initial_message');
	    var fileListBar = document.getElementById('file_list_bar');
	    var deleteAllBtn = document.getElementById('delete_all');

	    // 파일 목록을 관리하기 위한 배열
	    var filesArray = [];

	    function updateUIForFiles() {
	        fileList.innerHTML = ''; // 기존 목록 초기화
	        if (filesArray.length > 0) {
	            initialMessage.style.display = 'none';
	            fileListBar.style.display = 'flex';
	            // 파일 목록 표시
	            filesArray.forEach(file => {
	                fileList.appendChild(createFileListItem(file));
	            });
	        } else {
	            initialMessage.style.display = 'block';
	            fileListBar.style.display = 'none';
	        }
	    }

	    function createFileListItem(file) {
	        var li = document.createElement('li');
	        li.className = 'file-list-item';
	        var fileNameSpan = document.createElement('span');
	        fileNameSpan.textContent = file.name;
	        var fileSizeSpan = document.createElement('span');
	        fileSizeSpan.textContent = (file.size / 1024).toFixed(2) + ' KB';
	        var deleteBtnSpan = document.createElement('span');
	        deleteBtnSpan.textContent = 'X';
	        deleteBtnSpan.style.cursor = 'pointer';
	        deleteBtnSpan.onclick = function() {
	            filesArray = filesArray.filter(f => f !== file); // 파일 배열에서 제거
	            updateUIForFiles(); // UI 업데이트
	        };

	        li.appendChild(fileNameSpan);
	        li.appendChild(fileSizeSpan);
	        li.appendChild(deleteBtnSpan);
	        return li;
	    }

	    function handleFiles(files) {
	        Array.from(files).forEach(file => filesArray.push(file)); // 파일 배열에 추가
	        updateUIForFiles(); // UI 업데이트
	    }

	    dropZone.addEventListener('dragover', function(e) {
	        e.stopPropagation();
	        e.preventDefault();
	        e.dataTransfer.dropEffect = 'copy';
	    });

	    dropZone.addEventListener('drop', function(e) {
	        e.stopPropagation();
	        e.preventDefault();
	        handleFiles(e.dataTransfer.files);
	    });

	    filesInput.addEventListener('change', function(e) {
	        handleFiles(e.target.files);
	    });

	    deleteAllBtn.addEventListener('click', function() {
	        filesArray = []; // 파일 배열 초기화
	        updateUIForFiles(); // UI 업데이트
	    });
	});
</script>

</head>

<body>
	<!-- ======= Header ======= -->
	<%@ include file="../header.jsp"%>

	<!-- ======= Sidebar ======= -->
	<%@ include file="../asidebar.jsp"%>

	<main id="main" class="main">
		<div class="pagetitle">
			<h1>쪽지쓰기</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="main">Home</a></li>
					<li class="breadcrumb-item active">쪽지쓰기</li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->
		<div class="card">
			<!-- 쪽지쓰기 세션 부분 -->
			<section class="msgWrite-section">
				<div class="form-container">
					<!-- 파일을 보내려면 form에서 encType = "multipart/form-data" 를 이용해서 보내야 한다 -->
					<!-- 그리고 하단에 <input type="file" name="fileName">로 파일을 보낼 수 있게 넣어준다 -->
					<form id="write-form" action="/msgSent" method="post"
						enctype="multipart/form-data">
						<!-- 보내기 버튼 -->
						<div class="form-group">
							<button type="submit" class="msg-Sent-Btn">보내기</button>
						</div>
						<!-- 취소 Button : 이전 페이지로 돌아가기 -->
						<!-- Referer 헤더는 사용자가 현재 요청을 보내기 전에 어떤 페이지에서 왔는지를 식별 -->
						<div class="form-group">
							<a href="<%=request.getHeader("Referer")%>"
								class="msg-cancel-btn">취소</a>
						</div>

						<!-- 받는사람 -->
						<div class="form-group">
							<div class="input-group">
								<!-- 받는사람 텍스트 -->
								<div class="input-group-prepend-received">
									<span class="input-group-text">받는사람</span>
								</div>

								<!-- 인풋 -->
								<input id="receiverInput" type="text" class="form-control"
									aria-label="Text input with segmented dropdown button"
									name="msg_receiver">

								<div class="receiver-dropdown">
									<!-- 드롭다운 토글 버튼 -->
									<button type="button" id="toggleButton"
										class="btn btn-outline-secondary dropdown-toggle dropdown-toggle-split"
										data-bs-toggle="dropdown" aria-expanded="false">
										<span class="visually-hidden">주소록 드롭다운</span>
									</button>

									<!-- 드롭다운 주소록 리스트 -->
									<ul class="dropdown-menu dropdown-menu-end"
										id="userListDropdown">
										<c:forEach var="user" items="${userList}">
											<li><a class="dropdown-item" href="#"
												onclick="selectReceiver('${user.user_no}', '${user.user_nic}', '${user.user_name}')">
													${user.user_no} ${user.user_nic}(${user.user_name}) </a></li>
										</c:forEach>
									</ul>
								</div>
								<!-- 주소록 버튼 -->
								<div class="receiver-addressBtn">
									<button type="button" class="btn btn-outline-secondary">주소록</button>
								</div>
							</div>
						</div>
						<!-- 제목 -->
						<div class="form-group">
							<div class="subject-group">
								<!-- 제목 텍스트 -->
								<div class="subject-group-prepend">
									<span class="subject-group-text">제목</span>
								</div>
								<!-- 인풋 -->
								<input type="text" id="msg_title" name="msg_title"
									class="form-control"
									aria-label="Text input with segmented dropdown button">
							</div>
						</div>
						<!-- 첨부파일 -->
						<div class="form-group">
							<div class="mb-3">
								<div class="file-form-control">
									<!-- 파일 선택 input, 다중 선택 가능 -->
									<input type="file" name="files" id="files"
										class="files form-control form-control-sm" multiple>
								</div>
								<!-- 드래그 앤 드롭 영역 -->
								<div id="drop_zone" class="file_drag"
									style="min-height: 145px; overflow: auto;">
									<!-- 초기 안내 문구 -->
									<div id="initial_message" style="margin-top: 45px;">여기에
										파일을 드래그하세요.</div>
									<!-- 파일 목록 상단 바, 초기에는 숨김 처리 -->
									<div id="file_list_bar" class="file-list-bar"
										style="display: none;">
										<span>파일명</span> <span>용량</span> <span id="delete_all"
											style="cursor: pointer;">X</span>
									</div>
									<!-- 업로드된 파일 목록 -->
									<ul id="fileList" class="file-list" ></ul>
								</div>
							</div>
						</div>
						<!-- 내용 -->
						<div class="form-group">
							<div class="content-group">
								<textarea id="message" name="msg_content" rows="5" required></textarea>
							</div>
						</div>
					</form>
				</div>
			</section>
		</div>
	</main>
	<!-- ======= Footer ======= -->
	<%@ include file="../footer.jsp"%>
	<!-- End Footer -->

	<!-- 스크롤하면 우측 아래 생기는 버튼 : 클릭하면 페이지의 맨 위로 이동 -->
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center">
		<i class="bi bi-arrow-up-short"></i>
	</a>

	<!-- Vendor JS Files -->
	<script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/vendor/chart.js/chart.umd.js"></script>
	<script src="assets/vendor/echarts/echarts.min.js"></script>
	<script src="assets/vendor/quill/quill.min.js"></script>
	<script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
	<script src="assets/vendor/tinymce/tinymce.min.js"></script>
	<script src="assets/vendor/php-email-form/validate.js"></script>

	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>
</body>
</html>