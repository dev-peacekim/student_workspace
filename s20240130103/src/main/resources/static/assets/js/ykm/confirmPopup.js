var confirmPopup = document.querySelector('.confirmPopup');
var delBtn = document.getElementById('delBtn');
var cancelButton = confirmPopup.querySelector('#cancelButton');
var confirmButton = confirmPopup.querySelector('#confirmButton');

function showPopup() {
	confirmPopup.style.display = 'flex';
}

function hidePopup() {
	confirmPopup.style.display = 'none';
}

// 취소 버튼 클릭 시 팝업을 숨김
cancelButton.addEventListener('click', function() {
	hidePopup();
});

// 확인 버튼 클릭 시 팝업을 숨김
confirmButton.addEventListener('click', function() {
	hidePopup();
	// 여기에 확인 버튼을 클릭했을 때의 동작 추가
});

delBtn.addEventListener("click", function(e) {
	e.preventDefault();
	showPopup();
});