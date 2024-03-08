/* 댓글 REST API */

// 로그인 유저 정보
let userInfo;

function getUserNo() {
	$.ajax({
		url: "/userNo",
		type: "GET",
		success: function(data) {
			console.log('getUserNo data :' + data);
			userInfo = data
			console.log('getUserNo : '+ userInfo );
		},
		error: function(error) {
			console.log('사용자 정보를 가져오는 중 오류 발생:', error);
		}
		
	});
}


// 댓글리스트 API 호출
let cboardNo = null;

function getCommentList(cboard_no) {
	if(cboardNo === null){
		cboardNo = cboard_no;
	}
	console.log('cboard_no 1 -> '+cboard_no);
	
	$.ajax({
		url: "/comment?cboard_no="+ cboard_no,
		type: "GET",
		success: function(data) {
			let listFilter = data.filter(comment => comment.creply_delete_chk === 0);
			updateReplyView(listFilter);
		},
		error: function(error) {
			console.log('리스트 오류 발생!', error);
		}
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
 
 		// 댓글 작성자 비교
 		const commentWriter = comment.user_no // commentWriter 댓글 작성자, userInfo 로그인 한 유저정보
 		// 댓글 작성자와 로그인한 유저 정보가 같을 경우에만 수정, 삭제 버튼을 활성화 시킨다 		                       
		
		replyList += `<div class="comment-card" data-creply-no="${comment.creply_no}">
		<div class="comment-header">
			<i class="bi bi-person-circle comment-user-profile" alt="유저 프로필"></i>
			<div class="comment-user-container">
				<p class="card-title comment-user-name"><a href="#">${comment.user_nic}</a></p>
				<p class="card-subtitle comment-updated-at">작성일 ${formatted}</p>
			</div>
			<div class="btnContainer">
				<button type="button" class="modifyComment_${comment.creply_no} badge bg-light text-dark" value="${comment.creply_no}" onclick="buttonStatus('${comment.creply_no}')" style="display: ${userInfo && commentWriter && userInfo === commentWriter ? 'inline' : 'none'}">
					<i class="bi bi-pencil-fill"></i> 수정
				</button>
				<button type="button" class="deleteComment badge bg-light text-dark" onclick="deleteComment('${comment.creply_no}')" style="display: ${userInfo && commentWriter && userInfo === commentWriter ? 'inline' : 'none'}">
					<i class="bi bi-trash"></i> 삭제
				</button>
				<div class="replyBtn badge bg-light text-dark">
					<i class="bi bi-reply-fill"></i>
				</div>
			</div>
		</div>
		<div class="comment-body">
			<input type="text" id="inputField_${comment.creply_no}" value ="${comment.creply_content}" class="form-control" required="required" disabled >
 			<button type="button" id="checkButton_${comment.creply_no}" onclick="updateComment('${comment.creply_no}', document.querySelector('#inputField_${comment.creply_no}').value)" style="display : none"><i class="bi bi-check2-circle"></i> 확인</button>
		</div>
		</div>

	`;
	});
	
	document.querySelector('#replyContainer').innerHTML = replyList;

}


// 댓글 등록 버튼 이벤트
const commentEditor = $('.commentEditor');
const commentSubmitBtn = $('#commentSubmitBtn');

commentEditor.on("keypress", function(e) {
	if (e.keyCode === 13) {
		submitComment();
	}
})

commentSubmitBtn.on("click", function() {
	submitComment();
})


// 댓글 등록 버튼 정보 담기
function submitComment() {
	const creply_content = $('#creply_content').val();
	const cboardNo = $('input[name="cboard_no"]').val();
	const userNo = $('input[name="user_no"]').val();
              
	const commentData = {
		cboard_no: cboardNo,
		user_no: userNo,
		creply_content: creply_content
	};
    
    writeComment(commentData);
    
    document.getElementById('creply_content').value = ''; // 입력 필드 비우기
}



// 댓글 등록
function writeComment(commentData) {
	const cboard_no = commentData.cboard_no;
	console.log(cboard_no);
	
	$.ajax({
		url: "/comment",
		type: "POST",
		contentType: "application/json",
		data: JSON.stringify(commentData),
		success: function(data) {
			console.log(data);
			getCommentList(cboard_no); 
		},
		error: function(error) {
			console.log('댓글 등록 오류 발생!', error);
		}
	});
}


// 댓글 수정 버튼 이벤트
function buttonStatus(creply_no) {
	
	console.log('buttonStatus creply_no : ' + creply_no);

	let inputField = $(`#inputField_` + creply_no);
	let checkButton = $(`#checkButton_` + creply_no);
	let modifyButton = $(`.modifyComment_` + creply_no);
	
	if (modifyButton.html() === '<i class="bi bi-x-lg"></i> 취소') {
        // 취소 버튼을 클릭한 경우
        inputField.prop('disabled', 'disabled');
        checkButton.hide();
        modifyButton.html('<i class="bi bi-pencil-fill"></i> 수정');
    } else {
        // 수정 버튼을 클릭한 경우
        inputField.prop('disabled', false);
        checkButton.show();
        modifyButton.html('<i class="bi bi-x-lg"></i> 취소');
    }
		
}


// 댓글 수정
function updateComment(creply_no, creply_content) {
	
	var commentData = {
		creply_no: creply_no,			// 댓글 번호
		creply_content: creply_content 	// 수정 된 댓글 내용
	};
	
	console.log("commentData.creply_content ===> " + commentData.creply_content);

	$.ajax({
		url: "/comment",
		type: "PUT",
		contentType: "application/json",
		data: JSON.stringify(commentData),
		success: function(data) {
			console.log(data);
			document.querySelector(`#inputField_${creply_no}`).value = commentData.creply_content;
		},
		error: function(error) {
			console.log('댓글 수정 오류!', error);
		}
	});
	// 확인 누르면 화면에서 갱신됨
	$(`#inputField_${creply_no}`).disabled = true;
}


// 댓글 삭제
// 댓글 삭제 버튼 클릭하면 삭제상태 값을 0 > 1 변경 (관리자 페이지에서 최종 삭제)
function deleteComment(creply_no) {
	$.ajax({
		url: "/comment/" + creply_no,
		type: "DELETE",
		success: function(response) {
			// 삭제된 댓글을 화면에서 제거
			const deleteComment = $(`.comment-card[data-creply-no="${creply_no}"]`);
			deleteComment.remove();
		},
		error: function(xhr, status, error) {
			console.log('댓글 삭제 오류!', error);
		}
	});
}



// 대댓글

// 댓글 등록이 완료되었습니다 아님 댓글 달린 쪽으로 화면 포인트주기...?
// 목록(리스트)으로 돌아가기
// 페이징 
// 검색


/* 모집중 모집완료 카테고리 분류*/

// 버튼 이벤트 처리
// 작성자랑 로그인한유저랑 같다면 모집중 버튼을 활성화 시킨다 userInfo 
const recruitBtn = $('.recruitBtn');
recruitBtn.on("click", function() {
		
});




// 모집 완료 버튼을 누르면 상태를 변경한다 (값이 바뀐다)



// 모집중 카테고리로 분류시킨다





