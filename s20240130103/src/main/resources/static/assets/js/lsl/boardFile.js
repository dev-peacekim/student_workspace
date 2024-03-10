// Dropzone 객체 초기화
Dropzone.autoDiscover = false; // Dropzone 자동 초기화 비활성화

const dropzone = new Dropzone(".dropzone", {
    url: "/boardAskWrite", // 파일 업로드할 서버 주소
    method: 'post', // POST 방식으로 업로드
    paramName: 'files', // 서버에서 사용할 formdata 이름 설정
    maxFiles: 5, // 최대 업로드 파일 수
    maxFilesize: 500, // 최대 업로드 용량 (MB)
    acceptedFiles: '.jpeg,.jpg,.png,.gif,.JPEG,.JPG,.PNG,.GIF', // 허용된 파일 형식
    addRemoveLinks: true, // 업로드 후 파일 삭제 버튼 표시 여부
    dictRemoveFile: '삭제', // 파일 삭제 버튼 텍스트
    init: function() {
        this.on("success", function(response) {
            console.log('파일 업로드 성공:', response);
        });
        this.on("error", function(errorMessage) {
            console.error('파일 업로드 실패:', errorMessage);
        });
    }
});
