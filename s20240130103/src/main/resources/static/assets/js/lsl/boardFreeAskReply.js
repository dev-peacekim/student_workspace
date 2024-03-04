
let cboard_no = null;


//  게시판 댓글 리스트 
function replyBoardFreeList(cboard_no) {
	if(cboardNo === null) {
		cboardNo = cboard_no;
	}
	
	console.log('cboard_no  ->' + cboard_no);
	fetch("/board/boardFreeContents?cboard_no=" + cboard_no)
		.then(response => response.json())
		.then(data => {
			updateReplyList(data);
		})
		.catch(error => {
			console.log('리스트 업데이트 오류', error);
		});
}

// 게시판 댓글 리스트 갱신 
function  updateReplyList(data) {
	let boardReplyList = '';
	data.forEach(function(boardReply){
		console.log(boardReply);
		
		const originalDate = new Date(boardReply.comm_update_date);
		
		let hour = originalDate.getHours();
        const ampm = hour >= 12 ? '오후' : '오전';
        hour = hour % 12 || 12; // 0시일 때 12시로 변경
 		const formatDate = `${originalDate.getFullYear()}.${(originalDate.getMonth() + 1).toString().padStart(2, '0')}.${originalDate.getDate().toString().padStart(2, '0')} ${ampm} ${hour.toString().padStart(2, '0')}:${originalDate.getMinutes().toString().padStart(2, '0')}`;	 
	
		boardReplyList +=`<div class="re-comment-body">
                <div id="replyBoardFreeList" class="comment-card">
                    
                    <div class="comment-header">
                        <i class="bi bi-person-circle comment-user-profile" alt="유저 프로필"></i>
                        <div class="comment-user-container">
                            <p class="card-title comment-user-name">
                                <a href="#">${boardReply.USER_NIC}</a>
                            </p>
                            <p class="card-subtitle comment-updated-at">작성일 ${formatDate}</p>
                        </div>
                        <div class="re-btn-container">
                            <form action="/boardFreeCommentRepl" method="GET">
                                <button type="submit" class="btn btn-outline-primary">
                                    <i class="bi bi-reply-fill">Replay</i>
                                </button>
                            </form>
                        </div>
                    </div>
                    <div class="card-body comment-body">
                        <p class="markdown-body">${boardReply.creply_content}</p>
                    </div>
            </div>
        </div>`;
	});
		document.querySelector('#boardReplyForm').innerHTML = boardReplyList;
	}
	
	
// 댓글 등록 이벤트 처리 
const replySubmitBtn = document.getElementById("replySubmitBtn");
	replySubmitBtn.addEventListener("click", function() {
	const creply_content = document.getElementById('creply_content').value;
    const cboardNo = document.querySelector('input[name="cboard_no"]').value;
    const userNo = document.querySelector('input[name="user_no"]').value;
    
    const replyData = {
		cboard_no: cboardNo,
        user_no: userNo,
        creply_content: creply_content
	};
	addComment(replyData);
});

// 댓글 등록
function addComment(replyData) {
	const cboard_no = commentData.cboard_no;
	console.log(cboard_no);
	fetch("/boardreply", {
		method: "POST",
		headers: {
		  'Accept': 'application/json',
		  'Content-Type': 'application/json'
		},
		body: JSON.stringify(replyData)
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


