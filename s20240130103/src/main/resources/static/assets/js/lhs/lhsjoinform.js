let idChkval = 0;
let passChkval = 0;
const extenReg = /.+\.(jpeg|jpg|png)/;
document.getElementById('file').addEventListener('change',function(){
	if(this.value.match(extenReg)){
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
	}else{
		alert('이미지파일을 선택해주세요');
		this.value='';
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
					document.getElementById('id').readOnly=true;
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

document.getElementById('pw2').addEventListener('keyup',function(){
	if(document.getElementById('pw1').value === this.value){
		document.querySelector('.disablePass').style.display='none';
		document.querySelector('.enablePass').style.display='block';
		passChkval=1;
	}else{
		document.querySelector('.enablePass').style.display='none';
		document.querySelector('.disablePass').style.display='block';
		passChkval=0;
	}
});

function chk(){
	if(idChkval===1){
		if(passChkval===1){
			return true;
		}else{
			alert("pass가 맞는지 확인해주세요")
			return false;
		}
	}else{
		alert("id중복확인 해주세요");
		return false;
	}
}
