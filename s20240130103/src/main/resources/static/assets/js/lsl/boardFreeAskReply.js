
let cboard_no = null;


// 게시판 댓글 리스트
function replyBoardFreeList(cboard_no) {
    fetch("/reply/listAll?cboard_no=" + cboard_no)
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
	data.forEach(function(lslCommReply){
		console.log(lslCommReply);
		
		const originalDate = new Date(lslCommReply.creply_date);

		let hour = originalDate.getHours();
        const ampm = hour >= 12 ? '오후' : '오전';
        hour = hour % 12 || 12; // 0시일 때 12시로 변경
 		const formatDate = `${originalDate.getFullYear()}.${(originalDate.getMonth() + 1).toString().padStart(2, '0')}.${originalDate.getDate().toString().padStart(2, '0')} ${ampm} ${hour.toString().padStart(2, '0')}:${originalDate.getMinutes().toString().padStart(2, '0')}`;	 

       
       
       
       boardReplyList += `<div class="re-comment-body">
    <div id="replyBoardFreeList" class="comment-card">
        <div class="comment-header">
            <img class="comment-user-profile" src="${lslCommReply.user_profile}">
            <div class="comment-user-container">
                <p id="replyuser_name" class="card-title comment-user-name">${lslCommReply.user_name}</p>
                <p id ="replyuser_id" class="card-title comment-user-id">#${lslCommReply.user_id}</p>
                <p class="card-subtitle comment-updated-at">작성일 ${formatDate}</p>
            </div>
            <div class="re-btn-container">
                <button type="button" class="btn brModify" onclick="toggleEdit('${lslCommReply.creply_no}');">수정</button>
                <button type="button" class="btn brDelete" onclick="deleteComment('${lslCommReply.cboard_no}', '${lslCommReply.creply_no}');">삭제</button>
                <div class="replyBtn badge bg-light text-dark"><i class="bi bi-reply-fill"></i></div>
            </div>
        </div>
        <div id="editComment_${lslCommReply.creply_no}" class="card-body comment-body" style="display: none;">
            <form action="/boardFreeCommentRepl" method="POST">
                <textarea id="editCommentText_${lslCommReply.creply_no}" class="form-control">${lslCommReply.creply_content}</textarea>
                <button type="button" class="btn btn-primary" onclick="updateComment('${lslCommReply.creply_no}', 'editCommentText_${lslCommReply.creply_no}');">저장</button>
            </form>
        </div>
        <div id="viewComment_${lslCommReply.creply_no}" class="card-body comment-body">
            <p class="markdown-body">${lslCommReply.creply_content}</p>
        </div>
    </div>
</div>`;
    });
    document.querySelector('#boardReplyForm').innerHTML = boardReplyList;
}
	
	
// 댓글 등록 이벤트 처리 
const submitBtn = document.getElementById("submitBtn");
submitBtn.addEventListener("click", function() {
	const creply_content = document.getElementById('creply_content').value;
    const cboardNo = document.querySelector('input[name="cboard_no"]').value;
    const userNo = document.querySelector('input[name="user_no"]').value;
    
    const replyData = {
		cboard_no: cboardNo,
        user_no: userNo,
        creply_content: creply_content
       
	};
	 console.log(replyData);
	addComment(replyData);
});

function addComment(replyData) {
    fetch("/replys", {
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
        if (data > 0) {
            replyBoardFreeList(replyData.cboard_no);
        } else {
            console.log('댓글 등록에 실패했습니다!');
           
        }
    })
    .catch(error => {
        console.log('댓글 등록 오류 발생!', error);
    });
}
