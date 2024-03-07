$("#team-list-title").on("click", () => {
	if($("#team-list-content").css('display') === 'none') {
		$("#team-list-content").css('display', 'block')
	} else {
		$("#team-list-content").css('display', 'none')
	}
});

$("#setting-btn").on("click", () => {
	if($("#setting-content").css('display') === 'none') {
		$("#setting-content").css('display', 'block')
	} else {
		$("#setting-content").css('display', 'none')
	}
});

$(".task-member-title").on("click", function () {
	if($(this).next().css('display') === 'none') {
		$(this).next().css('display', 'block')
		$(".task-member-title").not(this).next().css('display', 'none');
	} else {
		$(this).next().css('display', 'none')
	}
});

$(".bi-three-dots-vertical").on("click", function () {
	if($(this).next().css('display') === 'none') {
		$(this).next().css('display', 'block')
		$(".bi-three-dots-vertical").not(this).next().css('display', 'none');
	} else {
		$(this).next().css('display', 'none')
	}
});

$(document).click(function(event) {

    if (!$(event.target).closest('#team-list-title').length && !$(event.target).closest('#team-list-content').length) {
        $("#team-list-content").css('display', 'none');
    }
    
    if (!$(event.target).closest('#setting-btn').length && !$(event.target).closest('#setting-content').length) {
        $("#setting-content").css('display', 'none');
    }

	if (!$(event.target).closest('.task-member-title').length && !$(event.target).closest('.task-member-list').length) {
		$('.task-member-list').css('display', 'none');
	}

	if (!$(event.target).closest('.bi-three-dots-vertical').length && !$(event.target).closest('.task-setting').length) {
		$('.task-setting').css('display', 'none');
	}

});

$('.comp-chk-btn').on("click", function () {  
	if($(this).attr('class').includes('uncomp-btn')) {
		$(this).removeClass('uncomp-btn');
		$(this).addClass('comp-btn');
		$(this).text('완료');
	} else {
		$(this).removeClass('comp-btn');
		$(this).addClass('uncomp-btn');
		$(this).text('미완료');
	}
});