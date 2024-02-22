let idChkval = 0;

document.getElementById('file').addEventListener('change',function(){
	if(this.files[0]){
		let reader = new FileReader();
		reader.onload = function(event){
			document.getElementById('joinPicture').src = event.target.result;
		};
		reader.readAsDataURL(this.files[0]);
	}else{
		alert("파일선택해주세요");
		document.getElementById('joinPicture').src = "";
	}
});

document.getElementById("idChkBtn").addEventListener('click',function(){
	document.querySelector('.disableId').style.display='none';
	document.querySelector('.enableId').style.display='none';
	let idval = document.getElementById('id').value;
	if(idval && idval !==""){
		$.ajax({
			type:'post',
			url:'/joinIdChk',
			data:{'id':idval},
			dataType:'json',
			success:function(result){
				if(result=='0'){
					document.querySelector('.enableId').style.display='block';
					idChkval=1;
				}else{
					document.querySelector('.disableId').style.display='block';
				}
			}
		})
	}else{
		alert("id 입력하세요");
	}
});

function chk(){
	if(idChkval=1){
		return true;
	}else{
		alert("가입신청다시");
		return false;
	}
}

