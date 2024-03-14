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