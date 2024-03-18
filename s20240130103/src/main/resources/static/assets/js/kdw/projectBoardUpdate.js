// ================ 드래그 앤 드롭 업로드================
$(document).ready(function() {
    var customFileBtn = $('#customFileBtn');
    var filesInput = $('#files');
    var fileList = $('#fileList');
    var dragAndDropFiles = []; // 드래그 앤 드롭으로 선택된 파일들을 저장하는 배열
    var fileSelectionMethod = null; // 파일 선택 방법 ('input' 또는 'dragAndDrop')
    var uploadFileListElement = $('#uploadFileList'); // 업로드된 파일 목록을 담을 요소	
	
    // 업로드된 파일 목록 표시
    displayUploadedFiles(uploadedFiles);

    function displayUploadedFiles(uploadedFiles) {
        uploadedFiles.forEach(function(file) {
            var listItem = $('<li class="file-list-item"></li>').data('fileId', file.pboard_file_no);
            var deleteButton = $('<span class="delete-file">X</span>');
            var fileNameSpan = $('<span>').text(file.pboard_file_name);
            var fileSizeSpan = $('<span>').text(file.pboard_file_user_name); // 이 예제에서는 파일의 실제 크기 정보가 없으므로, user_name을 대신 사용합니다.

            // 삭제 버튼 클릭 이벤트 핸들러
            deleteButton.on('click', function() {
                deleteUploadedFile(file.pboard_no, file.pboard_file_no); // 파일 삭제
            });

            listItem.append(deleteButton).append(fileNameSpan).append(fileSizeSpan);
            uploadFileListElement.append(listItem);
        });
    }

    // 업로드된 파일 삭제
    function deleteUploadedFile(pboardNo, fileId) {
        $.ajax({
            url: '/delete-files', // 파일 삭제 엔드포인트
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ pboardNo: [pboardNo] }), // 서버에 전달할 데이터. 실제 필드명과 데이터 구조는 서버 구현에 따라 달라질 수 있습니다.
            success: function(response) {
                console.log(response);
                // 성공 시 페이지에서 해당 파일 목록 아이템 삭제
                $('li').filter(function() {
                    return $(this).data('fileId') === fileId;
                }).remove();
            },
            error: function(xhr, status, error) {
                console.error('파일 삭제 실패', status, error);
            }
        });
    }
	
	
	// ================= 업데이트 END =================
	
	
	// 사용자 정의 파일 선택 버튼 클릭 이벤트
	customFileBtn.on('click', function() {
		fileSelectionMethod = 'input'; // 파일 선택 방법을 'input'으로 설정
		filesInput.click();
	});

	// 파일 입력 필드 변경 이벤트
	filesInput.on('change', function() {
		updateFileListDisplay(); // 파일 목록 업데이트 함수 호출
	});

	// 드래그 앤 드롭 이벤트 핸들링
	$('#drop_zone').on('dragover', function(e) {
		e.preventDefault();
	}).on('drop', function(e) {
		e.preventDefault();
		e.stopPropagation(); // 이벤트 버블링 중지

		fileSelectionMethod = 'dragAndDrop'; // 파일 선택 방법을 'dragAndDrop'으로 설정
		dragAndDropFiles = Array.from(e.originalEvent.dataTransfer.files); // 드롭된 파일들을 배열에 저장
		updateFileListDisplay(); // 파일 목록 업데이트 함수 호출
	});

	// 파일 목록 업데이트 및 초기 문구, 파일 상단바 처리 함수
	function updateFileListDisplay() {
		fileList.empty(); // 파일 목록 초기화
		var filesToShow = [];

		// 파일 선택 방법에 따라 표시할 파일 목록 결정
		if (fileSelectionMethod === 'input') {
			filesToShow = Array.from(filesInput[0].files);
		} else if (fileSelectionMethod === 'dragAndDrop') {
			filesToShow = dragAndDropFiles;
		}

		// 파일 목록 표시 및 초기 문구, 파일 상단바 처리
		if (filesToShow.length > 0) {
			$('#initial_message').hide();
			$('#file_list_bar').show(); // 파일 상단바 표시
			filesToShow.forEach(function(file, index) {
				var fileSize = formatBytes(file.size);
				var listItem = $('<li class="file-list-item"></li>');
				var deleteButton = $('<span class="delete-file">X</span>');

				// 삭제 버튼 클릭 이벤트 핸들러
				deleteButton.on('click', function() {
					if (fileSelectionMethod === 'dragAndDrop') {
						dragAndDropFiles.splice(index, 1); // 드래그 앤 드롭 파일 배열에서 파일 삭제
					} else {
						filesInput.val(''); // input 선택 파일 클리어
					}
					updateFileListDisplay(); // 파일 목록 업데이트
				});

				listItem.append(deleteButton)
					.append($('<span>').text(file.name))
					.append($('<span>').text('(' + fileSize + ')'));
				fileList.append(listItem);
			});
		} else {
			$('#initial_message').show();
			$('#file_list_bar').hide(); // 파일 상단바 숨김
		}

		toggleFileListVisibility();
		$('#fileCount').text(filesToShow.length + '개의 파일 선택됨');
	}

	// 전체 삭제 버튼 클릭 이벤트
	$('#delete_all').on('click', function() {
		if (fileSelectionMethod === 'dragAndDrop') {
			dragAndDropFiles = [];
		} else {
			filesInput.val('');
		}
		updateFileListDisplay(); // 파일 목록 업데이트
	});

	// 파일 목록의 가시성 토글 함수 구현
	function toggleFileListVisibility() {
		if ($('#fileList li').length > 0) {
			$('#file_list_bar').show();
			$('#initial_message').hide();
		} else {
			$('#file_list_bar').hide();
			$('#initial_message').show();
		}
	}

	// 파일 용량 형식 변환 함수 구현
	function formatBytes(bytes, decimals = 2) {
		if (bytes === 0) return '0 Bytes';
		const k = 1024;
		const dm = decimals < 0 ? 0 : decimals;
		const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];
		const i = Math.floor(Math.log(bytes) / Math.log(k));
		return parseFloat((bytes / Math.pow(k, i)).toFixed(dm)) + ' ' + sizes[i];
	}

	// 폼 제출 이벤트
	$('#update-form').on('submit', function(e) {
		e.preventDefault();

		var formData = new FormData(this);

		if (fileSelectionMethod === 'dragAndDrop') {
			// 드래그 앤 드롭으로 선택된 파일들을 FormData에 추가
			dragAndDropFiles.forEach(function(file) {
				formData.append('files', file);
			});
		}
		// 파일 선택 방법이 'input'일 경우, <input type="file">의 파일들은 자동으로 formData에 포함됩니다.

		// 폼 데이터를 이용하여 서버에 폼 제출
		$.ajax({
			url: $(this).attr('action'), //
			type: 'POST',
			data: formData,
			processData: false,
			contentType: false,
			success: function(response) {
				console.log('메시지가 성공적으로 전송되었습니다.');
				window.history.back(); // 성공 시 이전 페이지로 돌아감
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.error('메시지 전송 실패: ', textStatus, errorThrown);
			}
		});
	});
});