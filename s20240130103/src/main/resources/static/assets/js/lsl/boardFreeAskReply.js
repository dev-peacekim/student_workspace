
let currentuser_no;

// 유저 정보 가져오기 
function getUserNo() {
	fetch("/userno")
	.then(response => response.json())
	.then(data => {
		currentuser_no = data;
		console.log('currentuser_no : ' + currentuser_no);
	})
	.catch(error => {
		console.log('사용자 정보를 가져오는 중 오류 발생:', error);
	})
}

// 게시판 댓글 리스트
let cboardNo = null;

function replyBoardFreeAskList(cboard_no) {
	if(cboardNo === null){
		cboardNo = cboard_no;
	}
    fetch("/reply?cboard_no=" + cboard_no)
        .then(response => response.json())
        .then(data => {
			 data.forEach(comment => {
                comment.creply_no = comment.creply_no; // 이 부분은 서버 응답에 따라 수정해야 함
            });
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

	let ReplyUser = lslCommReply.user_no;

        console.log(currentuser_no);
        console.log(ReplyUser);

     // 현재 세션 사용자와 댓글 작성자가 동일한 경우에만 수정 및 삭제 버튼 표시
     const showButtons = currentuser_no === ReplyUser;
			
     boardReplyList += `<div class="re-comment-body">
    <div id="replyBoardFreeList" class="comment-card">
        <div class="comment-header">
            <img class="comment-user-profile" src="${lslCommReply.user_profile}">
            <div class="comment-user-container">
                <p id="replyuser_name" class="card-title comment-user-name">${lslCommReply.user_name}</p>
                <p class="card-subtitle comment-updated-at">작성일 ${formatDate}</p>
            </div>
            <div class="re-btn-container">
                <button type="button" class="btn brModify" onclick="toggleEdit('${lslCommReply.creply_no}');" style="display: ${showButtons ? 'block' : 'none'};">수정</button>
                        <button type="button" class="btn brDelete" onclick="deleteComment('${lslCommReply.cboard_no}', '${lslCommReply.creply_no}');" style="display: ${showButtons ? 'block' : 'none'};">삭제</button>
                <div class="btn brBtn"><i class="bi bi-reply-fill"></i></div>
            </div>
        </div>
        <div id="editComment_${lslCommReply.creply_no}" class="card-body comment-body" style="display: none;">
            <textarea id="editCommentText_${lslCommReply.creply_no}" class="form-control">${lslCommReply.creply_content}</textarea>
            <button type="button" class="btn brSave" onclick="modifyComment('${lslCommReply.creply_no}');">저장</button>
        </div>
        <div id="viewComment_${lslCommReply.creply_no}" class="card-body comment-body">
            <p class="markdown-body" id="creply_content${lslCommReply.creply_no}">${lslCommReply.creply_content}</p>
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
    const cboard_no = document.querySelector('input[name="cboard_no"]').value;
    const user_No = document.querySelector('input[name="user_no"]').value;
	const creply_no = document.querySelector('input[name="creply_no"]').value;
	 
    const replyData = {
		cboard_no: cboard_no,
        user_no: user_No,
        creply_content: creply_content,
     	creply_no : creply_no
       
	};
	 console.log(replyData);
	addComment(replyData);
});



// 댓글 등록 
function addComment(replyData) {
    fetch("/replys/insert", {
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
            document.getElementById('creply_content').value = '';
            replyBoardFreeAskList(replyData.cboard_no);
        } else {
            console.log('댓글 등록에 실패했습니다!');
           
        }
    })
    .catch(error => {
        console.log('댓글 등록 오류 발생!', error);
    });
}


// 댓글 삭제
function deleteComment(cboard_no, creply_no) {
    // AJAX 요청을 통해 댓글 삭제 API 호출
    fetch(`/replys/delete?cboard_no=${cboard_no}&creply_no=${creply_no}`, {
        method: "DELETE"
    })
    .then(response => response.json())
    .then(data => {
        // 댓글 삭제 성공 여부에 따라 처리
        if (data > 0) {
            // 삭제 성공 시, 다시 댓글 리스트를 업데이트
            replyBoardFreeAskList(cboard_no);
        } else {
            console.log('댓글 삭제에 실패했습니다!');
        }
    })
    .catch(error => {
        console.log('댓글 삭제 오류 발생!', error);
    });
}


// 수정 창을 토글하는 함수
function toggleEdit(creply_no) {
    // 수정 창의 display 속성을 토글
    const editComment = document.getElementById(`editComment_${creply_no}`);
    const brModify = document.querySelector('.brModify');
    const brDelete = document.querySelector('.brDelete');
    
    if (editComment.style.display === "none") {
        editComment.style.display = "block";
        brModify.style.display = "none";
        brDelete.style.display = "none";
    } else {
        editComment.style.display = "none";
          brModify.style.display = "block";
        brDelete.style.display = "block";
    }
    
    // 뷰 창의 display 속성도 토글
    const viewComment = document.getElementById(`viewComment_${creply_no}`);
    if (viewComment.style.display === "none") {
        viewComment.style.display = "block";
    } else {
        viewComment.style.display = "none";
    }
}

// 댓글 수정
function modifyComment(creply_no) {
	const creply_content = document.getElementById('editCommentText_'+creply_no);
	const modifyData = {
		creply_no : creply_no,
		creply_content : creply_content.value
	}
	console.log('modifyData -> '+ JSON.stringify(modifyData));
	
	fetch("/replys/modify", {
    method: "PUT",
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(modifyData)
})
.then(response => response.json())
.then(data => {
    if (data > 0) {
        // 수정 성공 시, 다시 댓글 리스트를 업데이트
        document.querySelector('#creply_content'+creply_no).innerHTML = modifyData.creply_content;
        toggleEdit(modifyData.creply_no);
    } else {
        console.log('댓글 수정에 실패했습니다!');
    }
})
.catch(error => {
    console.log('댓글 수정 오류 발생!', error);
});

}



