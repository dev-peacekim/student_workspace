/* 삭제 확인 팝업 */

// 변수 선언
var postDeleteBtn = document.querySelector('#postDeleteBtn');
var confirmPopup = document.querySelector('.confirmPopup');
var cancelButton = confirmPopup.querySelector('#cancelButton');

if (postDeleteBtn!==null) {
	postDeleteBtn.addEventListener("click", function(e) {
		e.preventDefault();
		showPopup();
	});
}

postDeleteBtn.addEventListener("click", function() {
	showPopup();
});

function showPopup() {
	confirmPopup.style.display = 'flex';
}


// 취소 버튼 클릭 시 팝업을 숨김
cancelButton.addEventListener('click', function() {
	hidePopup();
});

function hidePopup() {
	confirmPopup.style.display = 'none';
}


