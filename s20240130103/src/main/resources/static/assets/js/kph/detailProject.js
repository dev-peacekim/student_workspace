$("#team-list-title").on("click", () => {
  if ($("#team-list-content").css("display") === "none") {
    $("#team-list-content").css("display", "block");
  } else {
    $("#team-list-content").css("display", "none");
  }
});

$("#setting-btn").on("click", () => {
  if ($("#setting-content").css("display") === "none") {
    $("#setting-content").css("display", "block");
  } else {
    $("#setting-content").css("display", "none");
  }
});

$(".task-list").on("click", ".task-member-title", function () {
  if ($(this).next().css("display") === "none") {
    $(this).next().css("display", "block");
    $(".task-member-title").not(this).next().css("display", "none");
  } else {
    $(this).next().css("display", "none");
  }
});

$(".task-list").on("click", ".bi-three-dots-vertical", function () {
  if ($(this).next().css("display") === "none") {
    $(this).next().css("display", "block");
    $(".bi-three-dots-vertical").not(this).next().css("display", "none");
  } else {
    $(this).next().css("display", "none");
  }
});

$(document).click(function (event) {
  if (
    !$(event.target).closest("#team-list-title").length &&
    !$(event.target).closest("#team-list-content").length
  ) {
    $("#team-list-content").css("display", "none");
  }

  if (
    !$(event.target).closest("#setting-btn").length &&
    !$(event.target).closest("#setting-content").length
  ) {
    $("#setting-content").css("display", "none");
  }

  if (
    !$(event.target).closest(".task-member-title").length &&
    !$(event.target).closest(".task-member-list").length
  ) {
    $(".task-member-list").css("display", "none");
  }

  if (
    !$(event.target).closest(".bi-three-dots-vertical").length &&
    !$(event.target).closest(".task-setting").length
  ) {
    $(".task-setting").css("display", "none");
  }
});

$(".task-list").on("click", '.comp-chk-btn', function () {
  if ($(this).attr("class").includes("uncomp-btn")) {
    $(this).removeClass("uncomp-btn");
    $(this).addClass("comp-btn");
    $(this).text("완료");
  } else {
    $(this).removeClass("comp-btn");
    $(this).addClass("uncomp-btn");
    $(this).text("미완료");
  }
});

$(".taskFilter").on("click", function () {
  $(".task-list").empty();
  const project_no = $(".project_no").val();
  const keyword = $(this).prev().text();

  $.ajax({
    type: "post",
    url: "taskFilter",
    data: JSON.stringify({
      project_no: project_no,
      keyword: keyword,
    }),
    contentType: "application/json",
    dataType: "json",
    success: function (taskList) {
      console.log(taskList);
      taskList.forEach((task) => {

		const users = task.users.map(user => `
          <li><img src="/upload/userImg/${user.user_profile}" alt="Profile" class="rounded-circle">${user.user_name}</li>
        `).join('');

		const day = ['일', '월', '화', '수', '목', '금', '토']
		const endDate = new Date(task.task_end);
		const endDay = day[endDate.getDay()]

        $(".task-list").append(`
			<div class="task-list-detail">
				<div class="task-head">
					<div class="task-title">${task.task_title}</div>
					<i class="bi bi-three-dots-vertical"></i>
					<ul class="task-setting">
						<li>과업 수정</li>
						<li>과업 삭제</li>
					</ul>
				</div>
				<div class="task-detail">
					<div class="task-comp-chk">
						<i class="bi bi-check-circle"></i>
						${task.task_comp_chk == 0 ? '<button class="comp-chk-btn uncomp-btn">미완료</button>' : '<button class="comp-chk-btn comp-btn">완료</button>'}
					</div>
					<div class="member">
						<i class="bi bi-people"></i>
						<div class="task-member-toggle">
							<div class="task-member-title dropdown-toggle">참여자</div>
							<ul class="task-member-list">
								${users}
							</ul>
						</div>
					</div>
					<div class="due-date">
						<i class="bi bi-calendar3"></i>
						<div>${task.task_end.substring(0, task.task_end.indexOf(" "))}(${endDay}) ${task.task_end.substring(task.task_end.indexOf(" "), 16)}까지</div>
					</div>
				</div>
			</div>
        `);
      });
    },
  });
});