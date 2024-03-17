// 대댓글 더보기 버튼
$('.reply-list').on('click', '.reply-reply-btn', function () {
	const reply_list = $(this).closest('.reply-reply-box').find('.reply-reply-list');
	if ($(reply_list).css('display') == 'none') {
		$(reply_list).css('display', 'block');
	} else {
		$(reply_list).css('display', 'none');
	}
});

// 대댓글/ 대댓글 추가용 변수
let preply_group;

// 대댓글 달기 버튼
$('.reply-list').on('click', '.reply-reply-write', function () {
	const write_box = $(this).closest('.reply-detail').find('.reply-reply-write-box')
	console.log(write_box)
	preply_group = $(this).closest('.reply-detail').children('input[name=preply_group]').val();
	console.log(preply_group);
	if ($(write_box).css('display') == 'none') {
		$(write_box).css('display', 'flex');
	} else if ($(write_box).css('display') == 'flex' && $(write_box).children('textarea').val() != '') {
		$(write_box).children('textarea').val('');
	} else {
		$(write_box).css('display', 'none');
	}
});

// 대대댓글 달기 버튼
$('.reply-list').on('click', '.reply-reply-reply-write', function () {
	const write_box = $(this).closest('.reply-detail').find('.reply-reply-write-box');
	preply_group = $(this).closest('.reply-reply-detail').find('input[name=preply_group]').val();
	const user_name = $(this).closest('.reply-reply-detail').find('.reply-reply-user-name').text();
	if ($(write_box).css('display') == 'none') {
		$(write_box).children('textarea').val('@' + user_name + ' ');
		$(write_box).css('display', 'flex');
	} else if ($(write_box).css('display') == 'flex' && $(write_box).val() != '') {
		$(write_box).css('display', 'none');
	} else {
		$(write_box).children('textarea').val('@' + user_name + ' ');
	}
});


// reply-write-btn = 댓글 쓰기
// reply-reply-write = 대댓글 쓰기 / 대대댓글 쓰기

// 댓글 쓰기
$('.reply-write-btn').on('click', function () {
	const preply_content = $(this).siblings().val();
	const pboard_no = $('#pboard_no').val();
	$(this).siblings().val('');
	$.ajax({
		type: "post",
		url: "boardProjectReplyAdd",
		data: {
			preply_content: preply_content,
			pboard_no: pboard_no
		},
		dataType: "json",
		success: function (reply) {
			
			const viewCnt = Number($('.reply-count span').text());  
			$('.reply-count span').text(viewCnt + 1);

			const userImg = reply.user_profile == null ?
				`<img src="/upload/userImg/987654321487321564defaultImg.jpg" alt="Profile" class="rounded-circle">` :
				`<img src="/upload/userImg/${reply.user_profile}" alt="Profile" class="rounded-circle">`;

			const writeDay = reply.preply_update_date == null ?
				`<p class="reply-write-day">${reply.preply_date}</p>` :
				`<p class="reply-write-day">${reply.preply_update_date}</p>`;

			let preply_content = reply.preply_delete_chk == 0 ?
				`<div class="reply-content">${reply.preply_content}</div>` :
				`<div class="reply-content">삭제된 댓글입니다.</div>`;

			$('.reply-list').prepend(`
				<div class="reply-detail">
					<input type="hidden" name="preply_no" value="${reply.preply_no}" />
					<input type="hidden" name="preply_group" value="${reply.preply_group }">
					<input type="hidden" name="preply_indent" value="${reply.preply_indent}" />
					<input type="hidden" name="preply_level" value="${reply.preply_level}" />
					<div class="reply-detail-top">
						<div class="reply-writer">
							<input type="hidden" name="user_no" value="${reply.user_no}" />
							${userImg}
							<div class="reply-writer-detail">
								<p class="reply-user-name">${reply.user_name}</p>
								${writeDay}
								<i class="bi bi-trash-fill reply-delete-btn"></i>
							</div>
						</div>
						<div class="reply-reply-write">
							<i class="bi bi-reply-fill"></i> 답글
						</div>
					</div>
					${preply_content}
					<div class="reply-reply-write-box">
						<textarea class="form-control reply-reply-write-area" placeholder="댓글을 입력하세요"></textarea>
						<button type="button"
							class="btn btn-primary reply-reply-write-btn">
							등록</button>
					</div>
				</div>
			`);


		}
	});
});

// 대댓글 / 대대댓글 쓰기
$('.reply-list').on('click', '.reply-reply-write-btn', function () {
	const preply_content = $(this).siblings().val();
	const pboard_no = $('#pboard_no').val();
	$(this).siblings().val('');
	const reply_reply_box = $(this).closest('.reply-detail').find('.reply-reply-box');
	if ($(reply_reply_box).length == 0) {
		$(this).closest('.reply-detail').append(`
			<div class="reply-reply-box">
				<p class="reply-reply-btn">답글 더보기</p>
				<div class="reply-reply-list">
				</div>
			</div>
		`);
	}
	const reply_reply_list = $(this).closest('.reply-detail').find('.reply-reply-list');

	$.ajax({
		type: "post",
		url: "boardProjectReplyReplyAdd",
		data: {
			preply_content: preply_content,
			preply_group: preply_group,
			pboard_no: pboard_no
		},
		dataType: "json",
		success: function (reply) {
			
			const viewCnt = Number($('.reply-count span').text());  
			$('.reply-count span').text(viewCnt + 1);

			const userImg = reply.user_profile == null ?
							`<img src="/upload/userImg/987654321487321564defaultImg.jpg" alt="Profile" class="rounded-circle">` :
							`<img src="/upload/userImg/${reply.user_profile}" alt="Profile" class="rounded-circle">`;

			const writeDay = reply.preply_update_date == null ?
							`<p class="reply-reply-write-day">${reply.preply_date}</p>` :
							`<p class="reply-reply-write-day">${reply.preply_update_date}</p>`;


			let preply_indent = reply.preply_indent == 0 ?
								`<div>${reply.preply_content}</div>` :
								`<span class="tag">${reply.preply_content.substring(0, reply.preply_content.indexOf(" "))}</span>
								<div>${reply.preply_content.substring(reply.preply_content.indexOf(" "), reply.preply_content.length)}</div>`;

			let preply_content = reply.preply_delete_chk == 0 ?
								preply_indent :
								`<div>삭제된 댓글입니다.</div>`;

			$(reply_reply_list).append(`
				<div class="reply-reply-detail">
					<div class="reply-reply-detail-top">
						<input type="hidden" name="preply_no" value="${reply.preply_no}" />
						<input type="hidden" name="preply_group" value="${reply.preply_group}">
						<input type="hidden" name="preply_indent" value="${reply.preply_indent}" />
						<input type="hidden" name="preply_level" value="${reply.preply_level}" />
						<div class="reply-reply-writer">
							${userImg}
							<div class="reply-reply-writer-detail">
								<p class="reply-reply-user-name">${reply.user_name}</p>
								${writeDay}
								<i class="bi bi-trash-fill reply-reply-delete-btn"></i>
							</div>
						</div>
						<div class="reply-reply-reply-write">
							<i class="bi bi-reply-fill"></i> 답글
						</div>
					</div >
					<div class="reply-reply-content">
					${preply_content}
				</div>
			</div >
			`);
		}
	});
});

$('.reply-list').on('click', '.reply-delete-btn, .reply-reply-delete-btn', function() {
	let preply_no;
	let reply_detail;
	let reply_reply_detail;
	let preply_level;
	
	if($(this).hasClass('reply-delete-btn')) {
		reply_detail = $(this).closest('.reply-detail');
		preply_no = $(reply_detail).find('input[name=preply_no]').val();
		preply_level = $(reply_detail).find('input[name=preply_level]').val();
	} else {
		reply_detail = $(this).closest('.reply-detail');
		reply_reply_detail = $(this).closest('.reply-reply-detail');
		preply_no = $(reply_reply_detail).find('input[name=preply_no]').val();
		preply_level = $(reply_reply_detail).find('input[name=preply_level]').val();
	}
	
	$.ajax({
		type: "post",
		url: "boardProjectReplyDelete",
		data: {
			preply_no : preply_no,
			preply_level : preply_level
		},
		dataType: "text",
		success: function (response) {
			console.log(response);
			if(reply_reply_detail === undefined) {
				// 댓글 삭제인 경우
				if($(reply_detail).find('.reply-reply-box').length == 0) {
					$(reply_detail).remove();
				} else {
					$(reply_detail).find('.reply-content').text('삭제된 댓글입니다.');
				}
			} else {
				// 대댓글 삭제인 경우
				$(reply_reply_detail).remove();
				if($(reply_detail).find('.reply-reply-detail').length == 0 && $(reply_detail).find('.reply-content').text() == '삭제된 댓글입니다.'){
					$(reply_detail).remove();
				} else if ($(reply_detail).find('.reply-reply-detail').length == 0) {
					$(reply_detail).find('.reply-reply-box').remove();
				}
			}
			
			const viewCnt = Number($('.reply-count span').text());  
			$('.reply-count span').text(viewCnt - 1);
		}
	});
});

$('#board-project-delete, #board-project-update').on('click', function () {  
	$(this).closest('form').submit();
})
