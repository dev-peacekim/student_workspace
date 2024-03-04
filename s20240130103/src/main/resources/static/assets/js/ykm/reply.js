/* 댓글 */
// REST API
let cboardNo = null;

// 댓글리스트 API 호출
function getCommentList(cboard_no) {
	if(cboardNo === null){
		cboardNo = cboard_no;
	}
	console.log('cboard_no 1 -> '+cboard_no);
	fetch("board/post?cboard_no=" + cboard_no)
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
		
		// const dateFormat = require('dateformat');

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
				<button type="button" class="modifyComment badge bg-light text-dark" onclick="updateComment('${comment.creply_no}'), enableInput();">
					<i class="bi bi-pencil-fill"></i> 수정
				</button>
				<button type="button" class="deleteComment badge bg-light text-dark" onclick="deleteComment('${comment.cboard_no}', '${comment.creply_no}');">
					<i class="bi bi-trash"></i> 삭제
				</button>
				<div class="replyBtn badge bg-light text-dark">
					<i class="bi bi-reply-fill"></i>
				</div>	
			</div>
		</div>
		<div class="comment-body input-group">
			<input type="text" value ="${comment.creply_content}" class="form-control" required="required" disabled >
 			<span class="check" disabled><i class="bi bi-check-circle"></i> 확인</span>
		</div>
		</div>

	`;
	});
	document.querySelector('#replyContainer').innerHTML = replyList;
}
// 수정 이벤트
function enableInput() {
	var inputElement = document.querySelector('.comment-body input[type="text"]');
	var checkElement = document.querySelector('.comment-body .check');
	inputElement.removeAttribute('disabled');
}

// 댓글 등록 이벤트
const commentSubmitBtn = document.getElementById("commentSubmitBtn"); // 변수 선언

commentSubmitBtn.addEventListener("click", function() {
	const creply_content = document.getElementById('creply_content').value;
    const cboardNo = document.querySelector('input[name="cboard_no"]').value;
    const userNo = document.querySelector('input[name="user_no"]').value;

    const commentData = {
        cboard_no: cboardNo,
        user_no: userNo,
        creply_content: creply_content
    };
    
    addComment(commentData);
});

// 댓글 등록
function addComment(commentData) {
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


// 삭제 후 업데이트




// 댓글 수정
function updateComment(commentData) {
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
		getCommentList();
	})
	.catch(error => {
		console.log('댓글 수정 오류!', error);
	});
}


