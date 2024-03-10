$(".btn-secondary").on("click", function () {
    let form = document.createElement('form');
	form.setAttribute('action', 'detailProject');
	form.setAttribute('method', 'post');

	let input = document.createElement('input');
	input.setAttribute('type', 'hidden'); 
	input.setAttribute('name', 'project_no');
	input.setAttribute('value', $('#project_no').val());
	form.appendChild(input);
	document.body.appendChild(form);
	form.submit();
});

$(".btn-primary").on("click", function (event) {
	const task_title = $(".form-control[name='task_title']").val();
	const task_member = $(".form-check-input[name='user_no']").is(":checked");
	const task_start = $(".form-control[name='task_start']").val();
	const task_end_day = $(".form-control[name='task_end_day']").val();
	const task_end_time = $(".form-control[name='task_end_time']").val();

	if (task_title == '') {
		$(".task-title-alert").css("display", "block");
		event.preventDefault();
	} 

	if (!task_member) {
		$(".task-member-alert").css("display", "block");
		event.preventDefault();
	}

	if(task_start == '') {
		$(".task-start-alert").css("display", "block");
		event.preventDefault();
	} 
	
	if(task_end_day == '' || task_end_time == '') {
		$(".task-end-alert").css("display", "block");
		event.preventDefault();
	}
});

$(".form-control, .form-check-input").on("input", function (event) {
	const task_title = $(".form-control[name='task_title']").val();
	const task_member = $(".form-check-input[name='user_no']").is(":checked");
	const task_start = $(".form-control[name='task_start']").val();
	const task_end_day = $(".form-control[name='task_end_day']").val();
	const task_end_time = $(".form-control[name='task_end_time']").val();

	if (task_title != '') {
		$(".task-title-alert").css("display", "none");
	} 
	
	if(task_member) {
		$(".task-member-alert").css("display", "none");
	} 
	
	if(task_start != '') {
		$(".task-start-alert").css("display", "none");
	} 
	
	if(task_end_day != '' && task_end_time != '') {
		$(".task-end-alert").css("display", "none");
	}
});

$('#all').on('click', function () {  
	let checked = $('#all').is(':checked');

	if(checked) {
		$('input:checkbox').prop('checked', true);
	} else {
		$('input:checkbox').prop('checked', false);
	}
});