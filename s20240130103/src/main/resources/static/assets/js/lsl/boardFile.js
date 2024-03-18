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
            var file = files[i];
            var fileName = file.name;

            var listItem = document.createElement('div');
            listItem.textContent = fileName;
            fileListDiv.appendChild(listItem);

            // 파일 이름 다음에 줄 바꿈 추가
            fileListDiv.appendChild(document.createElement('br'));
        }
    });
});
