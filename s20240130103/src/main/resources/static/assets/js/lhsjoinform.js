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
})