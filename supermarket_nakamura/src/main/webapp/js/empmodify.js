var empName = null;
var phone = null;
var saveBtn = null;
var backBtn = null;

$(function(){
	empName = $("#empName");
	phone = $("#phone");
	saveBtn = $("#save");
	backBtn = $("#back");
	
	empName.next().html("*");
	phone.next().html("*");
	
	empName.on("focus",function(){
		validateTip(empName.next(),{"color":"#666666"},"* 社員名の長さは 1 文字以上 10 文字未満である必要があります",false);
	}).on("blur",function(){
		if(empName.val() != null && empName.val().length > 1 
				&& empName.val().length < 10){
			validateTip(empName.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(empName.next(),{"color":"red"},imgNo+" 入力したユーザ名が仕様を満たしていません。再入力してください。",false);
		}
		
	});
	
	phone.on("focus",function(){
		validateTip(phone.next(),{"color":"#666666"},"* 電話番号を入力してください",false);
	}).on("blur",function(){
		var patrn=/^0\d{10}$/;
		if(phone.val().match(patrn)){
			validateTip(phone.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(phone.next(),{"color":"red"},imgNo + " 入力した携帯電話番号の形式が正しくありません",false);
		}
	});
	
	saveBtn.on("click",function(){
		empName.blur();
		phone.blur();
		if(empName.attr("validateStatus") == "true" 
			&& phone.attr("validateStatus") == "true"){
			if(confirm("変更しますか？")){
				$("#empForm").submit();
			}
		}
	});
	
	backBtn.on("click",function(){
		//alert("modify: "+referer);
		if(referer != undefined 
			&& null != referer 
			&& "" != referer
			&& "null" != referer
			&& referer.length > 4){
		 window.location.href = referer;
		}else{
			history.back(-1);
		}
	});
});