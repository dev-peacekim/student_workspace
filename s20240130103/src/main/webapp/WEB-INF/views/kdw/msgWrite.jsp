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
	//================ 주소록 ================
	$(document).ready(function() {
    // 토글 버튼과 사용자 리스트 드롭다운 요소를 찾아 변수에 저장
    var toggleButton = $('#toggleButton');
    var userListDropdown = $('#userListDropdown');
    var isToggleButtonClicked = false; // 토글 버튼 클릭 상태를 추적하는 변수 초기화

    // 토글 버튼 클릭 이벤트 핸들러 정의
    toggleButton.click(function() {
        // 클릭 상태를 토글
        isToggleButtonClicked = !isToggleButtonClicked;
        // 클릭 상태에 따라 버튼의 클래스 토글
        toggleButton.toggleClass('clicked', isToggleButtonClicked);
        // 드롭다운의 표시 여부를 클릭 상태에 따라 조절
        userListDropdown.css('display', isToggleButtonClicked ? 'block' : 'none');
        // 화살표 클래스도 클릭 상태에 따라 토글하여 방향을 변경
        toggleButton.find('.arrow').toggleClass('down', isToggleButtonClicked);
    });

    // "받는 사람" 인풋 필드 클릭 이벤트 핸들러 정의
    $('#receiverInput').on('click', function(event) {
        // 위와 동일한 로직을 사용하여 드롭다운 표시 여부 토글
        isToggleButtonClicked = !isToggleButtonClicked;
        toggleButton.toggleClass('clicked', isToggleButtonClicked);
        userListDropdown.css('display', isToggleButtonClicked ? 'block' : 'none');
        toggleButton.find('.arrow').toggleClass('down', isToggleButtonClicked);
        event.stopPropagation(); // 이벤트 버블링 방지
        loadAddressBookList(); // 주소록 데이터 로드 및 드롭다운 업데이트 함수 호출
    });

    // 토글 버튼 내 화살표 클릭 시 "받는 사람" 인풋 필드 클릭 이벤트 트리거
    toggleButton.find('.arrow').on('click', function(event) {
        $('#receiverInput').trigger('click');
        event.stopPropagation(); // 이벤트 버블링 방지
    });

    // 주소록 버튼 클릭 이벤트 핸들러 정의
    $('.receiver-addressBtn button').on('click', function() {
        // AJAX를 사용하여 서버에서 주소록 데이터 가져오기
        $.ajax({
            url: '/getAddressBookList', // 데이터를 가져올 URL
            type: 'GET', // HTTP 메소드 지정
            success: function(data) {
                // 데이터 로딩 성공 시 UI 업데이트 함수 호출
                updateAddressBookUI(data);
            },
            error: function(error) {
                // 데이터 로딩 실패 시 오류 메시지 출력
                console.log("Error loading address book: ", error);
            }
        });
    });

    // 주소록 UI 업데이트 함수 정의
    function updateAddressBookUI(addresses) {
        var addressList = $('#addressList'); // 주소록 리스트 요소 찾기
        addressList.empty(); // 기존 내용을 비움
        // 주소록 데이터를 반복하여 리스트 아이템 생성
        $.each(addresses, function(index, user) {
            var listItem = $('<li>').addClass('list-group-item');
            var checkbox = $('<input>').attr('type', 'checkbox').addClass('address-checkbox').val(user.user_no);
            var label = $('<label>').text(user.user_nic + " (" + user.user_name + ")").prepend(checkbox);

            // 체크박스 변경 이벤트 핸들러 정의: 체크박스 상태에 따라 선택된 주소 추가 또는 제거
            listItem.append(label).on('change', '.address-checkbox', function() {
                var checked = $(this).is(':checked'); // 체크 상태 확인
                var label = $(this).next().text(); // 체크박스 옆의 라벨 텍스트 가져오기
                var value = $(this).val(); // 체크박스의 값을 가져오기
                if(checked) {
                    // 체크된 경우, 선택된 주소 목록에 추가
                    var newItem = $('<li>').addClass('list-group-item selected-address').text(label).data('value', value);
                    $('#selectedAddresses').append(newItem);
                } else {
                    // 체크 해제된 경우, 선택된 주소 목록에서 제거
                    $('#selectedAddresses').find(`li[data-value='${value}']`).remove();
                }
            });
            addressList.append(listItem); // 생성된 리스트 아이템을 주소록 리스트에 추가
        });
        // "모두 선택" 체크박스 동작 로직: 모든 주소록 항목의 체크박스를 선택 또는 해제
        $('#selectAllAddresses').on('change', function() {
            var isChecked = $(this).is(':checked'); // "모두 선택" 체크박스의 상태 확인
            $('.address-checkbox').prop('checked', isChecked); // 모든 체크박스 상태를 일치시킴
            // 선택 상태에 따라 받는 사람 탭에 추가/제거하는 로직 필요
        });

        // 저장 버튼 클릭 이벤트 핸들러: 선택된 주소들을 "받는 사람" 입력 필드에 추가
        $('#saveSelectedAddresses').on('click', function() {
            var selectedAddresses = $('.address-checkbox:checked').map(function() {
                return $(this).val(); // 선택된 체크박스의 값들을 배열로 수집
            }).get().join(', '); // 배열을 문자열로 변환하여 쉼표로 구분
            $('#receiverInput').val(selectedAddresses); // "받는 사람" 입력 필드에 값 설정
            $('#addressBookModal').modal('hide'); // 모달 창 숨김
        });
    }

    // 주소록 데이터 로드 및 드롭다운 리스트 업데이트 함수 정의
    function loadAddressBookList() {
        // AJAX를 사용하여 서버에서 주소록 데이터 가져오기
        $.ajax({
            url: '/getAddressBookList', // 데이터를 가져올 URL
            type: 'GET', // HTTP 메소드 지정
            success: function(data) {
                // 데이터 로딩 성공 시 드롭다운 메뉴 업데이트
                var dropdownMenu = $('#userListDropdown');
                dropdownMenu.empty(); // 기존 내용 비우기
                // 주소록 데이터를 반복하여 드롭다운 메뉴 항목 생성
                $.each(data, function(index, user) {
                    var listItem = $('<li>');
                    var linkItem = $('<a>').addClass('dropdown-item')
                                           .attr('href', '#')
                                           .text(user.user_no + ' ' + user.user_nic + ' (' + user.user_name + ')')
                                           .click(function() {
                                               // 항목 클릭 시 "받는 사람" 입력 필드에 값 추가
                                               var receiverInput = $('#receiverInput');
                                               var currentValue = receiverInput.val();
                                               var newValue = currentValue ? currentValue + ', ' + user.user_no : user.user_no;
                                               receiverInput.val(newValue);
                                               dropdownMenu.hide(); // 드롭다운 메뉴 숨김
                                           });
                    listItem.append(linkItem);
                    dropdownMenu.append(listItem);
                });
            },
            error: function(error) {
                // 데이터 로딩 실패 시 오류 메시지 출력
                console.log("Error loading address book: ", error);
            }
        });
    }
	    // 사용자 리스트 드롭다운 내부 항목 클릭 이벤트 핸들러
	    $('#userListDropdown').on('click', 'li', function() {
	        // 드롭다운을 닫고, 토글 버튼 상태 및 화살표 방향을 초기화
	        isToggleButtonClicked = false; // 드롭다운 닫힘 상태로 변경
	        toggleButton.removeClass('clicked');
	        userListDropdown.css('display', 'none');
	        toggleButton.find('.arrow').removeClass('down'); // 화살표 방향을 위로 변경
	    });
	});
	
	// ================ 드래그 앤 드롭 업로드================
	$(document).ready(function() {
	    var dropZone = $('#drop_zone');
	    var fileList = $('#fileList');
	    var filesInput = $('#files');
	    var initialMessage = $('#initial_message');
	    var fileListBar = $('#file_list_bar');
	    var deleteAllBtn = $('#delete_all');

	    var filesArray = [];

	    function updateUIForFiles() {
	        fileList.html(''); // 기존 목록 초기화
	        if (filesArray.length > 0) {
	            initialMessage.css('display', 'none');
	            fileListBar.css('display', 'flex');
	            // 파일 목록 표시
	            $.each(filesArray, function(index, file) {
	                fileList.append(createFileListItem(file));
	            });
	        } else {
	            initialMessage.css('display', 'block');
	            fileListBar.css('display', 'none');
	        }
	    }

	    function createFileListItem(file) {
	        var li = $('<li>').addClass('file-list-item');
	        var fileNameSpan = $('<span>').text(file.name);
	        var fileSizeSpan = $('<span>').text((file.size / 1024).toFixed(2) + ' KB');
	        var deleteBtnSpan = $('<span>').text('X').css('cursor', 'pointer');
	        deleteBtnSpan.on('click', function() {
	            filesArray = $.grep(filesArray, function(value) {
	                return value !== file;
	            });
	            updateUIForFiles();
	        });

	        li.append(fileNameSpan).append(fileSizeSpan).append(deleteBtnSpan);
	        return li;
	    }

	    function handleFiles(files) {
	        $.each(files, function(index, file) {
	            filesArray.push(file);
	        });
	        updateUIForFiles();
	    }

	    dropZone.on('dragover', function(e) {
	        e.stopPropagation();
	        e.preventDefault();
	        e.originalEvent.dataTransfer.dropEffect = 'copy';
	    });

	    dropZone.on('drop', function(e) {
	        e.stopPropagation();
	        e.preventDefault();
	        handleFiles(e.originalEvent.dataTransfer.files);
	    });

	    filesInput.on('change', function(e) {
	        handleFiles(e.target.files);
	    });

	    deleteAllBtn.on('click', function() {
	        filesArray = [];
	        updateUIForFiles();
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
								<c:choose>
									<c:when test="${receiverId != null}">
										<input id="receiverInput" type="text" class="form-control"
											aria-label="Text input with segmented dropdown button"
											name="msg_receiver" value="${receiverId}">
									</c:when>
									<c:otherwise>
										<input id="receiverInput" type="text" class="form-control"
									aria-label="Text input with segmented dropdown button"
									name="msg_receiver">
									</c:otherwise>
								</c:choose>
								
								<div class="receiver-dropdown">
									<!-- 드롭다운 토글 버튼 --><!-- 토글버튼을 JS로 따로 주고있어서그런거같음 -->
									<button type="button" id="toggleButton">
									    <span class="visually-hidden">주소록 드롭다운</span>
									    <!-- 토글버튼 -->
									    <span class="arrow">&gt;</span>
									</button>

									<!-- 드롭다운 주소록 리스트 -->
									<ul class="dropdown-menu dropdown-menu-end"
										id="userListDropdown">
									</ul>
								</div>
								<!-- 주소록 버튼 -->
								<div class="receiver-addressBtn">
									<button type="button" class="btn btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#addressBookModal">주소록</button>
								</div>
								<!-- 주소록 버튼 클릭시 OPEN : 주소록 모달 -->
								<div class="modal fade" id="addressBookModal" tabindex="-1" aria-labelledby="addressBookModalLabel" aria-hidden="true">
								  <div class="modal-dialog modal-lg">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h5 class="modal-title" id="addressBookModalLabel" style="font-weight: 800;">쪽지 주소록</h5>
								        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								      </div>
								      <div class="modal-body">
								        <div class="row">
								          <div class="col-6">
								          	<!-- 주소록 리스트 전체 체크박스 -->					        	
								            <h6><input type="checkbox" id="selectAllAddresses" />등록된 주소록</h6>
								            <ul id="addressList" class="list-group">
								              <!-- 주소록 리스트가 여기에 동적으로 로드됩니다 -->
								            </ul>
								          </div>
								          <div class="col-6">
								            <h6>받는사람</h6>
								            <ul id="selectedAddresses" class="list-group">
								              <!-- 선택된 주소록이 여기에 표시됩니다 -->
								            </ul>
								          </div>
								        </div>
								      </div>
								      <div class="modal-footer">
								        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
								        <button type="button" class="btn btn-primary" id="saveSelectedAddresses">저장</button>
								      </div>
								    </div>
								  </div>
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