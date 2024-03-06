/* 댓글 REST API */

// 로그인 한 유저 정보
let currentUserNo = null;

function getUserNo() {
	fetch("/userNo")
	.then(response => response.json())
	.then(data => {
		currentUserNo = data.user_no;
		console.log('currentUserNo : ' + currentUserNo);
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
                        
		replyList += `<div class="comment-card">
		<div class="comment-header">
			<i class="bi bi-person-circle comment-user-profile" alt="유저 프로필"></i>
			<div class="comment-user-container">
				<p class="card-title comment-user-name"><a href="#">${comment.user_id}</a></p>
				<p class="card-subtitle comment-updated-at">작성일 ${formatted}</p>
			</div>
			<div class="btnContainer">
				<button type="button" class="modifyComment badge bg-light text-dark" value="${comment.creply_no}" onclick="enableInput('${comment.creply_no}')">
					<i class="bi bi-pencil-fill"></i> 수정
				</button>
				<button type="button" class="deleteComment badge bg-light text-dark" onclick="deleteComment('${comment.cboard_no}','${comment.creply_no}')">
					<i class="bi bi-trash"></i> 삭제
				</button>
				<div class="replyBtn badge bg-light text-dark">
					<i class="bi bi-reply-fill"></i>
				</div>
			</div>
		</div>
		<div class="comment-body">
			<input type="text" id="inputField_${comment.creply_no}" value ="${comment.creply_content}" class="form-control" required="required" disabled >
 			<button type="button" id="checkButton_${comment.creply_no}" onclick="updateComment('${comment.creply_content}', '${comment.creply_no}','${comment.cboard_no}')" disabled><i class="bi bi-check-circle"></i> 확인</button>
		</div>
		</div>

	`;
	});
	
	document.querySelector('#replyContainer').innerHTML = replyList;
	
	// 댓글 리스트 갱신하면서 버튼 상태 값 변경
	data.forEach(function(comment) {
		buttonStatus(comment);
	})
	
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


// 수정 및 삭제 버튼 상태 변경
function buttonStatus(comment) {
    const modifyButton = document.querySelector('.modifyComment');
    const deleteButton = document.querySelector('.deleteComment');

    if (comment.user_no === currentUserNo) { // 댓글 작성자와 로그인한 사용자가 일치하는 경우
        modifyButton.style.display = 'inline'; // 수정 버튼 표시
        deleteButton.style.display = 'inline'; // 삭제 버튼 표시
    } else {
        modifyButton.style.display = 'none'; // 수정 버튼 숨김
        deleteButton.style.display = 'none'; // 삭제 버튼 숨김
    }
    
}

// 수정 이벤트
function enableInput(creply_no) {
	console.log('enableInput creply_no : ' + creply_no);
	
	var inputElement = document.querySelector(`#inputField_`+creply_no);
	var checkElement = document.querySelector(`#checkButton_`+creply_no);
	
	inputElement.removeAttribute('disabled');
	checkElement.removeAttribute('disabled');
}


// 댓글 수정
function updateComment(creply_content, creply_no, cboard_no) {
	
	var commentData = {
		creply_content: creply_content,
		creply_no: creply_no,
		cboard_no: cboard_no
	}

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
		getCommentList(cboard_no);
	})
	.catch(error => {
		console.log('댓글 수정 오류!', error);
	});
}


// 댓글 삭제
function deleteComment(cboard_no, creply_no) {
	fetch("/comment?creply_no="+creply_no, {
		method: "DELETE"
	})
	.then(() => {
		console.log('cboard_no 2 -> '+cboard_no);
		getCommentList(cboardNo);
	})
	.catch(error => {
		console.log('댓글 삭제 오류!', error);
	});
}

// 댓글 삭제하면 데이터베이스에는 남기고 delete_chk() 0 > 1 업데이트
// 화면에선 안보이게 

