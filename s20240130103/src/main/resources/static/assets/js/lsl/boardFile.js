const fileArray = []; //파일 저장하는 배열
const dataTransfer = new DataTransfer();
const dataList = dataTransfer.items;
var fileListDiv = document.getElementById('fileList');
var formFileInput = document.getElementById('formFile');
$(document).ready(function() {
    formFileInput.addEventListener('change', function() {
        fileListDiv.innerHTML = '';
        var files = this.files;
        if (files.length === 0) {
            fileListDiv.textContent = '파일이 없습니다.';
            return;
        }
        for (var i = 0; i < files.length; i++) {
            var file = files[i];
            dataList.add(file);
            var fileName = file.name;
            var listItem = document.createElement('div');
            listItem.textContent = fileName;
            fileListDiv.appendChild(listItem);
        }
    });
});

// -----------------드래그 앤 드롭 테스트중-------------------------

document.querySelector('.upload-title').addEventListener('dragover',function(e){
	e.preventDefault();
	e.stopPropagation();
});
document.querySelector('.upload-title').addEventListener('drop',function(e){
	e.preventDefault();
	e.stopPropagation();
    var file = e.dataTransfer?.files[0];
    var fileName = file.name;
    var listItem = document.createElement('div');
    listItem.textContent = fileName;
    fileListDiv.appendChild(listItem);
    dataList.add(file);
    console.log(dataList);
    formFileInput.files = dataTransfer.files;
})



