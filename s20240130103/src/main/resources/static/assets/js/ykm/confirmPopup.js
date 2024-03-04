/* 삭제 확인 팝업 */

// 변수 선언
var postDeleteBtn = document.querySelector('#postDeleteBtn');
var confirmPopup = document.querySelector('.confirmPopup');
var cancelButton = confirmPopup.querySelector('#cancelButton');
// var confirmButton = confirmPopup.querySelector('#confirmButton');


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

/*
// 확인 버튼 클릭 시 팝업을 숨김
confirmButton.addEventListener('click', function() {
	hidePopup();
	// 여기에 확인 버튼을 클릭했을 때의 동작 추가
});
*/


if (postDeleteBtn!==null) {
	postDeleteBtn.addEventListener("click", function(e) {
		e.preventDefault();
		showPopup();
	});
}
