// 파일 입력란
const fileInput = document.getElementById('formFile');
// 파일 체크박스
const fileCheckBox = document.getElementById('formFile-box');

// 파일 입력란에 변화가 있을 때 실행될 함수
fileInput.addEventListener('click', function() {
    // 파일 입력란에서 선택한 파일들을 가져와서 파일 체크박스에 표시
    if (this.files.length > 0) {
        const fileName = this.files[0].name; // 첫 번째 파일의 이름을 가져옴
        fileCheckBox.value = fileName; // 파일 체크박스의 값을 파일 이름으로 설정
    }
});