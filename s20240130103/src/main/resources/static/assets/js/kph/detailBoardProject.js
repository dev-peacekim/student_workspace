$('.reply-reply-btn').on('click', function () {  
    const reply_list = $(this).siblings('.reply-reply-list');
    if($(reply_list).css('display') == 'none') {
        $(reply_list).css('display', 'block');
    } else {
        $(reply_list).css('display', 'none');
    }
});

$('.reply-reply-write').on('click', function () {  
    const write_box = $(this).parent().siblings('.reply-reply-reply-write-box')
    if($(write_box).css('display') == 'none') {
        $(write_box).css('display', 'flex');
    } else {
        $(write_box).css('display', 'none');
    }
});

$('.reply-reply-reply-write').on('click', function () {  
    const write_box = $(this).closest('.reply-detail').find('.reply-reply-reply-write-box');
    const user_no = $(this).siblings('input').val();
    const user_name = $(this).siblings('.reply-reply-writer').find('.reply-reply-user-name').text();
    if($(write_box).css('display') == 'none') {
        $(write_box).children('textarea').val('@' + user_name+ ' ');
        $(write_box).css('display', 'flex');
    } else {
        $(write_box).css('display', 'none');
    }
});

$(document).click(function (event) {
    const isReply_reply_list = !$(event.target).closest(".reply-reply-list").length
    const isReply_reply_btn = !$(event.target).closest(".reply-reply-btn").length;
    const isReply_reply_reply_write_box = !$(event.target).closest(".reply-reply-reply-write-box").length;
    const isReply_reply_write = !$(event.target).closest(".reply-reply-write").length;
    const isReply_reply_reply_write = !$(event.target).closest(".reply-reply-reply-write").length;

    if (isReply_reply_list && isReply_reply_btn && isReply_reply_reply_write_box && isReply_reply_write && isReply_reply_reply_write) {
        $(".reply-reply-list").css("display", "none");
        $(".reply-reply-reply-write-box").css("display", "none");
    }
});