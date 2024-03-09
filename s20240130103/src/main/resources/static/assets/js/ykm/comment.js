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
			console.log('getUserNo : '+ userInfo);
		},
		error: function(error) {
			console.log('사용자 정보를 가져오는 중 오류 발생:', error);
		}
		
	});
}



// 글 작성자 정보
let postWriter;

// 글 작성자 정보 요청
function getPostWriter(cboard_no) {
	$.ajax({
		url: "/postWriter/"+cboard_no,
		type: "GET",
		success: function(data) {
			postWriter = data;
			console.log('postWriter : ' + postWriter)
			recruteButtonState();
		},
		error: function(error) {
			console.log('getPostWriter 정보를 가져오는 중 오류 발생!',error);
		}
	});
	
}



// 댓글 리스트
/*let cboardNo = null;*/

function getCommentList(cboard_no) {
	/*if(cboardNo === null){
		cboardNo = cboard_no;
	}*/
	console.log('cboard_no -> '+cboard_no);
	
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
    
    $('#creply_content').val() = ''; // 입력 필드 비우기
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



/* (모집중 & 모집완료) 카테고리 분류*/

// 로그인 유저(userInfo), 글 작성자(postWriter) 비교
// 글 작성자만 (모집중 버튼) 이용 가능
function recruteButtonState() {
	if (userInfo === postWriter) { 
		$(this).text('모집중');
		$('.recruitBtn').prop('disabled', false);
	} else {
        $('.recruitBtn').prop('disabled', true);
	}
}

// 모집중 버튼을 누르면 모집 완료로 변경
$('.recruitBtn').on("click", function() {
	if (userInfo === postWriter) {
	    $(this).text('모집완료');
    	$(this).prop('disabled', true);
	}
});


// 모집완료가 되면 값 변경
function updateRecruitment() {
	$.ajax({
		url: "/recruitment",
		type: "PUT",
		contentType: "application/json",
		data: JSON.stringify({ comm_mid_2: 20 }),
		success: function(data) {
			console.log('모집완료 상태로 변경되었습니다. : '+data);
		},
		error: function(error) {
			console.log('모집완료 변경 오류!', error);
		}
	});
}

// 글을 클릭해서 들어오면 모집중 모집완료의 상태를 확인해서 화면에 표시한다
// 모집중 모집완료 상태 가져오기
function getRecruitment(cboard_no) {
	$.ajax({
		url: "/recruitment/"+cboard_no,
		type: "GET",
		success: function(data) {
			console.log('getRecruitment : ' + typeof data);
			if(data === 20) {
				 $('.recruitBtn').prop('disabled', true);
                $('.tooltip').text('모집완료 상태입니다.');
            } else {
                $('.recruitBtn').prop('disabled', false);
                $('.tooltip').text('모집완료 상태로 바꾸려면 클릭');
            }
		},
		error: function(error) {
			console.log('모집 상태 확인 중 오류 발생!', error);
		} 		
	});
}


























// 게시글 리스트 불러오면서 게시글 번호들 가져오기
/*
function getBoardNo() {
	$.ajax({
		url: "/boardNo",
		type: "GET",
		success: function(data) {
			const postNo = $("#recruitment_") + data.cboard_no;
			if (data.recruitment == 20) {
				$(postNo).removeClass('recruiting').addClass('recruited').text('모집 완료'); // 클래스 변경, 텍스트 변경
			}
		},
		error: function(error) {
			console.log('모집여부 카테고리 변경 오류!', error);
		}
	});
}

*/







// 모집중 카테고리로 분류시킨다


// 대댓글

// 댓글 등록이 완료되었습니다 아님 댓글 달린 쪽으로 화면 포인트주기...?
// 목록(리스트)으로 돌아가기
// 페이징 
// 검색



/*

// 현재 글 상세페이지 번호
let currentPostNo;

function getPostNo() {
	$.ajax({
		url: "/postNo",
		type: "GET",
		success: function(data) {
			currentPostNo === data;
			console.log("currentPostNo : " + currentPostNo);
		},
		error: function(error) {
			console.log('글 번호 가져오는 중 오류 발생!', error);
		}	
	}); 
}
*/