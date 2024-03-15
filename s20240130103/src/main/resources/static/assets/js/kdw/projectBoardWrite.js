// ================ 드래그 앤 드롭 업로드================
$(document).ready(function() {
	var customFileBtn = $('#customFileBtn');
	var filesInput = $('#files');
	var fileList = $('#fileList');
	var selectedFiles = []; // 선택된 파일들을 저장하는 배열

	// 사용자 정의 파일 선택 버튼 클릭 이벤트
	customFileBtn.on('click', function() {
		filesInput.click();
	});

	// 파일 입력 필드 변경 이벤트
	filesInput.on('change', function(e) {
		// 선택된 파일들을 selectedFiles 배열에 추가
		for (let file of e.target.files) {
			selectedFiles.push(file);
		}
		updateFileList();
	});

	// 파일 목록 업데이트 함수
	function updateFileList() {
		fileList.empty(); // 파일 목록 초기화

		selectedFiles.forEach(function(file, index) {
			var fileSize = formatBytes(file.size);
			var listItem = $('<li class="file-list-item"></li>');
			var deleteButton = $('<span class="delete-file">X</span>');

			// 삭제 버튼 클릭 이벤트 핸들러
			deleteButton.on('click', function() {
				// 파일 배열에서 파일 삭제
				selectedFiles.splice(index, 1);
				updateFileList(); // 파일 목록 업데이트
			});

			listItem.append(deleteButton)
				.append($('<span>').text(file.name))
				.append($('<span>').text('(' + fileSize + ')'));
			fileList.append(listItem);
		});

		$('#fileCount').text(selectedFiles.length + '개의 파일 선택됨');
		toggleFileListVisibility();
	}

	// 파일 용량 형식 변환 함수
	function formatBytes(bytes, decimals = 2) {
		if (bytes === 0) return '0 Bytes';
		const k = 1024;
		const dm = decimals < 0 ? 0 : decimals;
		const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];
		const i = Math.floor(Math.log(bytes) / Math.log(k));
		return parseFloat((bytes / Math.pow(k, i)).toFixed(dm)) + ' ' + sizes[i];
	}

	// 파일 목록의 가시성 토글 함수
	function toggleFileListVisibility() {
		if (selectedFiles.length > 0) {
			$('#initial_message').hide();
			$('#file_list_bar').show();
		} else {
			$('#initial_message').show();
			$('#file_list_bar').hide();
		}
	}

	// 드래그 앤 드롭 이벤트 핸들링
	var dropZone = $('#drop_zone');
	dropZone.on('dragover', function(e) {
		e.preventDefault();
	}).on('drop', function(e) {
		e.preventDefault();
		e.stopPropagation(); // 이벤트 버블링 중지

		// 드롭된 파일들을 selectedFiles 배열에 추가
		for (let file of e.originalEvent.dataTransfer.files) {
			selectedFiles.push(file);
		}
		updateFileList(); // 파일 목록 업데이트
	});

	// 폼 제출 이벤트
	$('#write-form').on('submit', function(e) {
		e.preventDefault();

		var formData = new FormData(this);
		selectedFiles.forEach(function(file) {
			formData.append('files', file); // selectedFiles 배열의 파일들을 formData에 추가
		});

		// 폼 데이터를 이용하여 서버에 폼 제출
		$.ajax({
			url: $(this).attr('action'), // '/msgSent' 또는 서버 측 엔드포인트
			type: 'POST',
			data: formData,
			processData: false, // processData와 contentType을 false로 설정
			contentType: false,
			success: function(response) {
				// 성공 처리 로직
				console.log('메시지가 성공적으로 전송되었습니다.');
				window.history.back(); // 성공 시 이전 페이지로 돌아감
			},
			error: function(jqXHR, textStatus, errorThrown) {
				// 에러 처리 로직
				console.error('메시지 전송 실패: ', textStatus, errorThrown);
			}
		});
	});

	// 전체 삭제 버튼 클릭 이벤트
	$('#delete_all').on('click', function() {
		// 선택된 파일 배열 비우기
		selectedFiles = [];
		// 파일 목록 업데이트
		updateFileList();
	});
});