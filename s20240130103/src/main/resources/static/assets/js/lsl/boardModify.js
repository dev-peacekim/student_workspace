$(document).ready(function() {
    var formFileInput = document.getElementById('formFile');
    var fileListDiv = document.getElementById('fileList');

    formFileInput.addEventListener('change', function() {
        fileListDiv.innerHTML = '';

        var files = this.files;
        if (files.length === 0) {
            fileListDiv.textContent = '파일이 없습니다.';
            return;
        }

        for (var i = 0; i < files.length; i++) {
            (function(index) {
                var file = files[index];
                var fileName = file.name;
                var listItem = document.createElement('div');
                var deleteBtn = document.createElement('button');

                deleteBtn.textContent = 'X';
                deleteBtn.dataset.index = index;

                deleteBtn.addEventListener('click', function() {
                    var index = parseInt(this.dataset.index);

                    // 파일 목록에서 해당 인덱스의 파일 정보 제거
                    $(this).closest('div').remove();

                    // 파일 인풋 박스에서 해당 파일 제거
                    var newFiles = Array.from(formFileInput.files);
                    newFiles.splice(index, 1);
                    formFileInput.files = newFiles;

                    // fileListDiv가 비었을 때 메시지 표시
                    if (fileListDiv.children.length === 0) {
                        fileListDiv.textContent = '파일이 없습니다.';
                    }
                });

                listItem.textContent = fileName;
                listItem.appendChild(deleteBtn);
                fileListDiv.appendChild(listItem);
            })(i);
        }
    });
});

$(document).on("click", ".deleteFileBtn", function() {
    var index = $(this).index(); // 삭제 버튼의 인덱스 가져오기
    $(this).prev().remove(); // 삭제 버튼 앞의 파일명 제거
    $(this).remove(); // 삭제 버튼 제거

    // fileListDiv가 비었을 때 메시지 표시
    var fileListDiv = document.getElementById('fileList');
    if (fileListDiv.children.length === 0) {
        fileListDiv.textContent = '파일이 없습니다.';
    }
});