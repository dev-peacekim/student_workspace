/* 댓글 REST API */

// 로그인 유저 정보
let userInfo;

function getUserNo() {
	fetch("/userNo")
		.then(response => response.json())
		.then(data => {
			console.log('getUserNo data :' + data);
			userInfo = data
			console.log('getUserNo : '+ userInfo );
		})
		.catch(error => {
			console.log('사용자 정보를 가져오는 중 오류 발생:', error);
		})
}


// 댓글리스트 API 호출
let cboardNo = null;

function getCommentList(cboard_no) {
	if(cboardNo === null){
		cboardNo = cboard_no;
	}
	console.log('cboard_no 1 -> '+cboard_no);
	fetch("/comment?cboard_no=" + cboard_no)
		.then(response => response.json())
		.then(data => {
			updateReplyView(data);
		})
		.catch(error => {
			console.log('리스트 오류 발생!', error);
		});
}

// 댓글 작성자와 로그인한 유저 정보가 같을 경우에만 수정, 삭제 버튼을 활성화 시킨다

// 댓글리스트 갱신
function updateReplyView(data) {
	let replyList = '';
	data.forEach(function(comment) {
		
		console.log(comment);
		
		const originalDate = new Date(comment.comm_update_date);
		
		let hour = originalDate.getHours();
        const ampm = hour >= 12 ? '오후' : '오전';
        hour = hour % 12 || 12; // 0시일 때 12시로 변경
 		const formatted = `${originalDate.getFullYear()}.${(originalDate.getMonth() + 1).toString().padStart(2, '0')}.${originalDate.getDate().toString().padStart(2, '0')} ${ampm} ${hour.toString().padStart(2, '0')}:${originalDate.getMinutes().toString().padStart(2, '0')}`;		
 
 		// 댓글 작성자
 		const commentWriter = comment.user_no // commentWriter 댓글 작성자, userInfo 로그인 한 유저정보
  		
 		console.log('updateReplyView userInfo : ' + userInfo);
 		console.log('updateReplyView comment.user_no : ' + comment.user_no);
 		console.log('updateReplyView commentWriter : ' + commentWriter);
 		
 		
 		// 로그인 유저 정보 댓글 작성자 
 		                       
		replyList += `<div class="comment-card">
		<div class="comment-header">
			<i class="bi bi-person-circle comment-user-profile" alt="유저 프로필"></i>
			<div class="comment-user-container">
				<p class="card-title comment-user-name"><a href="#">${comment.user_id}</a></p>
				<p class="card-subtitle comment-updated-at">작성일 ${formatted}</p>
			</div>
			<div class="btnContainer">
				<button type="button" class="modifyComment_${comment.creply_no} badge bg-light text-dark" value="${comment.creply_no}" onclick="buttonStatus('${comment.creply_no}')" style="display: ${userInfo && commentWriter && userInfo === commentWriter ? 'inline' : 'none'}">
					<i class="bi bi-pencil-fill"></i> 수정
				</button>
				<button type="button" class="deleteComment badge bg-light text-dark" onclick="deleteComment('${comment.cboard_no}','${comment.creply_no}')" style="display: ${userInfo && commentWriter && userInfo === commentWriter ? 'inline' : 'none'}">
					<i class="bi bi-trash"></i> 삭제
				</button>
				<div class="replyBtn badge bg-light text-dark">
					<i class="bi bi-reply-fill"></i>
				</div>
			</div>
		</div>
		<div class="comment-body">
			<input type="text" id="inputField_${comment.creply_no}" value ="${comment.creply_content}" class="form-control" required="required" disabled >
 			<button type="button" id="checkButton_${comment.creply_no}" onclick="updateComment('${comment.creply_no}', document.querySelector('#inputField_${comment.creply_no}').value)" style="display : none"><i class="bi bi-check-circle"></i> 확인</button>
		</div>
		</div>

	`;
	});
	
	document.querySelector('#replyContainer').innerHTML = replyList;

}

// 댓글 등록 이벤트

const commentSubmitBtn = document.getElementById("commentSubmitBtn");

commentSubmitBtn.addEventListener("click", function() {
	const creply_content = document.getElementById('creply_content').value;
    const cboardNo = document.querySelector('input[name="cboard_no"]').value;
    const userNo = document.querySelector('input[name="user_no"]').value;
              
   var commentData = {
        cboard_no: cboardNo,
        user_no: userNo,
        creply_content: creply_content
    };
    
    writeComment(commentData);
});


// 댓글 등록
function writeComment(commentData) {
	const cboard_no = commentData.cboard_no;
	console.log(cboard_no);
	fetch("/comment", {
		method: "POST",
		headers: {
		  'Accept': 'application/json',
		  'Content-Type': 'application/json'
		},
		body: JSON.stringify(commentData)
	})
	.then(response => response.json())
	.then(data => {
		console.log(data);
				getCommentList(cboard_no); 
	})
	.catch(error => {
			console.log('댓글 등록 오류 발생!', error);
		});
}


// 수정을 누르면 확인 버튼이 생긴다
// 확인을 누른 수정 inputfield는 disable된다
// 버튼 이벤트
function buttonStatus(creply_no) {
	console.log('buttonStatus creply_no : ' + creply_no);

	let inputField = document.querySelector(`#inputField_` + creply_no);
	let checkButton = document.querySelector(`#checkButton_` + creply_no);
	
	inputField.removeAttribute('disabled');
	checkButton.style.display ='inline';
}


// 댓글 수정
function updateComment(creply_no, creply_content) {
	
	var commentData = {
		creply_no: creply_no,			// 댓글 번호
		creply_content: creply_content 	// 수정 된 댓글 내용
	};
	
	console.log("commentData.creply_content ===> " + commentData.creply_content);

	fetch("/comment", {
		method: "PUT",
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(commentData)
	})
	.then(response => response.json())
	.then(data => {
		console.log(data);
		document.querySelector(`#inputField_${creply_no}`).value = commentData.creply_content;
		// getUpdateView(data); 하는중 ...
	})
	.catch(error => {
		console.log('댓글 수정 오류!', error);
	});
	
	// 확인 누르면 화면에서 갱신됨
	document.querySelector(`#inputField_${creply_no}`).disabled = true;
	
}


// 댓글 삭제
// 댓글 삭제 버튼 클릭하면 삭제상태 값을 0 > 1 변경 (관리자 페이지에서 최종 삭제)
function deleteComment(cboard_no, creply_no) {
	fetch("/comment?creply_no="+creply_no, {
		method: "PATCH"
	})
	.then(() => {
		console.log('cboard_no 2 -> '+cboard_no);
		updateReplyView(cboardNo, creply_no);
	})
	.catch(error => {
		console.log('댓글 삭제 오류!', error);
	});
}

/*
// 삭제되면 화면에서 없애라
function getUpdateView() {
	getUpdateView(data); 하는중 ...
}
*/


// 목록(리스트)으로 돌아가기
// 모집중, 모집완료
// 페이징 
// 검색