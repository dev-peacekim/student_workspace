<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>휴지통 : 블루베리</title>
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
<link href="assets/css/kdw/msgTrashbox.css" rel="stylesheet">

<!-- 검색바&드롭박스 JS -->
<script type="text/javascript">
document.addEventListener('DOMContentLoaded', function() {
	function changeDropdownItem(value) {
		var dropdown = document.getElementById('dropdownSelect');
		dropdown.value = value;
	}

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

	// 체크박스
	var selectAllCheckbox = document.getElementById("select-all-checkbox");

	selectAllCheckbox.addEventListener("click", function() {
		var messageCheckboxes = document
				.querySelectorAll(".message-checkbox");

		for (var i = 0; i < messageCheckboxes.length; i++) {
			messageCheckboxes[i].checked = selectAllCheckbox.checked;
		}
	});

	// '영구 삭제' 버튼 클릭 시 선택된 쪽지들의 번호를 가져옴
	var btnMsgPermanentDelete = document.querySelector(".btn-msg-permanent-delete");
	btnMsgPermanentDelete.addEventListener("click", function() {
	    // selectedMessageNos를 정의
	    selectedMessageNos = Array.from(document.querySelectorAll(".message-checkbox:checked")).map(function(checkbox) {
	        return checkbox.getAttribute("data-msg-no");
	    });

	    // 이후에 선택된 쪽지들의 번호를 활용하여 영구 삭제 수행
	    console.log("Selected Message Nos for Permanent Delete:", selectedMessageNos);

	    // 선택된 메시지들의 번호를 서버로 보내어 영구 삭제하는 함수 호출
	    permanentDeleteMessages(selectedMessageNos);
	});

	function permanentDeleteMessages(selectedMessages) {
	    // AJAX를 사용하여 서버에 영구 삭제 요청을 보냅니다.
	    var xhr = new XMLHttpRequest();
	    xhr.open('POST', '/permanentDeleteMessages', true);
	    xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');

	    xhr.onreadystatechange = function() {
	        if (xhr.readyState === 4) {
	            console.log("Server Response:", xhr.status, xhr.responseText);  // 응답 상태 콘솔에 출력

	            if (xhr.status === 200) {
	                // 성공적으로 영구 삭제된 경우의 처리를 여기에 추가합니다.
	                alert('쪽지가 성공적으로 영구 삭제되었습니다.');
	                // 알림창 확인 시 화면을 새로고침 또는 업데이트
	                location.reload();
	            } else {
	                alert('쪽지 영구 삭제에 실패했습니다.');
	            }
	        }
	    };
	    
	    var data = {
	        msgNos: selectedMessages.map(Number)
	    };

	    xhr.send(JSON.stringify(data));
	}
});
</script>

</head>
<body>
	<!-- ======= Header ======= -->
	<%@ include file="../header.jsp"%>

	<!-- ======= Sidebar ======= -->
	<%@ include file="../asidebar.jsp"%>
	
	
	<!-- ======= 받은 쪽지함 Main ======= -->
	<main id="main" class="main">

		<!-- 받은 쪽지함 pageTitle -->
		<div class="pagetitle">
			<h1>휴지통</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="main">Home</a></li>
					<li class="breadcrumb-item active">휴지통</li>
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
		                       <th>
		                            <input type="checkbox" id="select-all-checkbox">
		                        </th>
								<th scope="col" class="readStatus">읽음</th>
								<th scope="col" class="attachment">첨부</th>
								<th scope="col" class="mailType">유형</th>
								<th scope="col" class="author">아이디</th>
								<th scope="col" class="subject">제목</th>
								<th scope="col" class="date">일시</th>
							</tr>
						</thead>
						<!-- 나중에 구현할때 읽은건 폰트에 bold 빼야함 -->
						<tbody id="mailList">
						    <c:forEach var="message" items="${trashMessages}">
						    	<!-- msg_readdate(읽음여부)가 'null'이라면 굵기 800 아니면 500 -->
						        <tr class="list-item" style="font-weight: ${empty message.msg_readdate ? '800' : '500'};">
						        	<!-- 체크박스 -->
						            <td><input type="checkbox" class="message-checkbox"
										data-msg-no="${message.msg_no}"></td>
								    <td class="readStatus">
								        <c:choose>
								            <c:when test="${message.msg_readdate ne null}">
								                <img src="assets/img/kdw/msg-read-icon.png" width="15" height="15">
								            </c:when>
								            <c:otherwise>
								                <img src="assets/img/kdw/msg-unread-icon.png" width="15" height="16">
								            </c:otherwise>
								        </c:choose>
								    </td>
						            <td class="attachment">
						                <img src="" alt="첨부 여부">
						            </td>
									<td class="mailType">
										<!-- 로그인유저와 보낸사람이(user_no = msg_sender) 같다면 -->
									    <c:choose>
									        <c:when test="${message.msg_sender == trashboxUserNo}">
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
									        <c:when test="${message.msg_sender == trashboxUserNo}">
									            ${message.msg_receiver}
									        </c:when>
									        <c:otherwise>
									            ${message.msg_sender}
									        </c:otherwise>
									    </c:choose>
									</td>
								    <!-- 제목 -->
								    <!-- 보낸사람(로그인) == trashboxUserNo(로그인) 참일시 보낸쪽지 읽기페이지 -->
									<td class="subject" 
									    <c:choose>
									        <c:when test="${message.msg_sender == trashboxUserNo}">
									            onclick="location.href='/msgReadSent?msg_no=${message.msg_no}'"
									        </c:when>
									        <c:otherwise>
									            onclick="location.href='/msgReadReceived?msg_no=${message.msg_no}'"
									        </c:otherwise>
									    </c:choose>>
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
				<button type="button" class="btn-msg-permanent-delete">영구 삭제</button>
			</div>
			<!-- 리스트 번호 -->
			<nav aria-label="Page navigation"
				class="msgTrashbox-pagination-container">
				<ul class="pagination">
					<li class="page-item"><a class="page-link"
						href="?currentPage=${page.startPage - 1}" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span>
					</a></li>

					<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
						<li class="page-item ${i eq page.currentPage ? 'active' : ''}">
							<a class="page-link" href="?currentPage=${i}">${i}</a>
						</li>
					</c:forEach>

					<li class="page-item"><a class="page-link"
						href="?currentPage=${page.endPage + 1}" aria-label="Next"> <span
							aria-hidden="true">&raquo;</span></a></li>
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
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>


</body>
</html>
