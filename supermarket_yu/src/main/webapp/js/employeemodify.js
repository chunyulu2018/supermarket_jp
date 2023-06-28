/**
 * 
 */
var userName = null;
var rank = null;
var age = null;
var phone = null;
var mailAddress = null;
var address = null;
var userId = null;
var saveBtn = null;
var backBtn = null;

$(function(){
	userName = $("#userName");
    rank = $("#rank");
	age = $("#age");
	phone = $("#phone");
	mailAddress = $("#mailAddress");
    address = $("#address");
    userId = $("#userId");
	saveBtn = $("#save");
	backBtn = $("#back");
	
	userName.next().html("*");
    rank.next().html("*");
	age.next().html("*");
	phone.next().html("*");
//	mailAddress.next().html("*");
//    address.next().html("*");
//    userId.next().html("*");
	
	
	$.ajax({
		type:"GET",//请求类型
		url:path+"/jsp/employee.do",//请求的url
		data:{method:"getRankList"},//请求参数
		dataType:"json",//ajax接口（请求url）返回的数据类型
		success:function(data){//data：返回数据（json对象）
			// confirm("进入employeemodify.js");
			if(data != null){
				var rid = $("#rid").val();
				rank.html("");
				var options = "<option value=\"-1\">--選択してください。--</option>";
				for(var i = 0; i < data.length; i++){
					//alert(data[i].id);
					//alert(data[i].rankName);
					if(rid != null && rid != undefined && data[i].id == rid ){
						options += "<option selected=\"selected\" value=\""+data[i].id+"\" >"+data[i].rankName+"</option>";
					}else{
						options += "<option value=\""+data[i].id+"\" >"+data[i].rankName+"</option>";
					}
					
				}
				rank.html(options);
			}
		},
		error:function(data){//当访问时候，404，500 等非200的错误状态码
			validateTip(rank.next(),{"color":"red"},imgNo+" ランクリスト取得エラー",false);
		}
	});
	
	
	userName.on("focus",function(){
		validateTip(userName.next(),{"color":"#666666"},"* ユーザ名の長さは 1 文字以上 15 文字未満である必要があります",false);
	}).on("blur",function(){
		if(userName.val() != null && userName.val().length > 1 
				&& userName.val().length < 15){
			validateTip(userName.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(userName.next(),{"color":"red"},imgNo+" 入力したユーザ名が仕様を満たしていません。再入力してください。",false);
		}
		
	});
		
	rank.on("focus",function(){
		validateTip(rank.next(),{"color":"#666666"},"* 社員ランクを選択してください",false);
	}).on("blur",function(){
		if(rank.val() != null && rank.val() > -1){
			validateTip(rank.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(rank.next(),{"color":"red"},imgNo+" 社員ランクを再度選択してください",false);
		}
		
	});
	age.on("focus",function(){
		validateTip(age.next(),{"color":"#666666"},"* 年齢を入力してください",false);
	}).on("blur",function(){
		if(age.val() != null && age.val() != "" && age.val() < 120 && age.val() > 14){
			validateTip(age.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(age.next(),{"color":"red"},imgNo + " 適合な年齢ではない、再入力してください",false);
		}
	});
	
	phone.on("focus",function(){
		validateTip(phone.next(),{"color":"#666666"},"* 電話番号を入力してください",false);
	}).on("blur",function(){
		var patrn=/^(13[0-9]|15[0-9]|18[0-9])\d{8}$/;
		if(phone.val().match(patrn)){
			validateTip(phone.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(phone.next(),{"color":"red"},imgNo + " 入力した携帯電話番号の形式が正しくありません",false);
		}
	});

	
	saveBtn.on("click",function(){
		userName.blur();
		rank.blur();
		age.blur();
		phone.blur();
		if(userName.attr("validateStatus") == "true" 
			&& rank.attr("validateStatus") == "true"
			&& age.attr("validateStatus") == "true"
			&& phone.attr("validateStatus") == "true"){
			if(confirm("変更しますか？")){
				$("#employeeForm").submit();
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