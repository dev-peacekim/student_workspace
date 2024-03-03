document.getElementById("searchbtn").addEventListener("click",function(){
	let inputresult = document.getElementById("inputid").value;
	$.ajax({
		type : 'post',
		url : 'addressIdSearch',
		data: {'user_id': inputresult},
		dataType : 'json',
		success : function(result){
			console.log(result);
			$(".address-main").append(`
				<div class="address-main-inner">
		            <div class="address-list-card-detail">
		              <div class="profile-img-user-name">
		                <img src="upload/userImg/${result.user_profile}" alt="Profile" class="rounded-circle address-list-profile-img">
		                <div>
		                  <p class="user-name">${result.user_name}</p>
		                  <p class="user-id">#${result.user_id}</p>
		                </div>
		              </div>
		              <div class="score-message">
		              	<div class="user-score rounded-circle">${result.eval_score}</div> 
		              	<a href="/addressAdd?re_user_no=${result.user_no}" style="color:white;">
			                <div class="user-score rounded-circle">
			                  <i class="bi bi-plus"></i>
			                </div> 
			            </a>
		              </div>
		            </div>
		          </div>
			`);
		},
		error : function(){
			alert("에러발생");
		}
	})
	
	
	
	
})