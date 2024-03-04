$("#search-text").on("keyup", (event) => {
  if (event.keyCode === 13) {
    const keyword = $("#search-text").val();
    const searchFilter = $(".search-filter").val();

    $(".table-nav .table tbody").empty();
    $(".table-nav .page-navigation .pagination").empty();

    $.ajax({
      type: "post",
      url: "projectAddressSearch",
      data: JSON.stringify({ 
        keyword: keyword,
        searchFilter: searchFilter
      }),
      contentType: "application/json",
      dataType: 'json',
      success: function (response) {

        let projectTaskList = response.projectTaskList;
        let kphPaging = response.kphPaging;
        let num = kphPaging.start; 

        projectTaskList.forEach((projectTask) => {
            $(".table-nav .table tbody").append(`
                <tr>
                    <th>${num}</th>
                    <td><a>${projectTask.project_title}><a></td>
                    <td>${projectTask.task_title}</td>
                    <td>${projectTask.task_start.substring(0, projectTask.task_start.indexOf(" "))}</td>
                    <td>${projectTask.task_end.substring(0, projectTask.task_end.indexOf(" "))}</td>
                </tr>
            `);
            num = num + 1;
        });

        if(kphPaging.startPage > kphPaging.pageBlock) {
            $(".table-nav .page-navigation .pagination").append(`
                <li class="page-item"><a class="page-link" href="totalTaskList?currentPage=${response.kphPaging.startPage-response.kphPaging.pageBlock}"><span>&laquo;</span></a></li>
            `);
        }

        for(let i=kphPaging.startPage; i<=kphPaging.endPage; i++) {
            $(".table-nav .page-navigation").append(`
                <li class="page-item"><a class="page-link" href="totalTaskList?currentPage=${i}">${i}</a></li>
            `);
        }

        if(kphPaging.endPage < kphPaging.totalPage) {
            $(".table-nav .page-navigation").append(`
                <li class="page-item"><a class="page-link" href="totalTaskList?currentPage=${kphPaging.startPage+kphPaging.pageBlock }"><span>&raquo;</span></a></li>
            `);
        }
      }
    });
  }
});
