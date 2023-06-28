/**
 * 
 */
var userCode = null;                                    
var userName = null;
var rank = null;
var age = null;
var phone = null;
var addBtn = null;
var backBtn = null;


$(function(){
	userCode = $("#userCode");
	userName = $("#userName");
	rank = $("#rank");
	age = $("#age");
	phone = $("#phone");
	addBtn = $("#add");
	backBtn = $("#back");
	//初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
	userCode.next().html("*");
	userName.next().html("*");
	rank.next().html("*");
	age.next().html("*");
    phone.next().html("*");
	
	$.ajax({
		type:"GET",//请求类型
		url:path+"/jsp/employee.do",//请求的url
		data:{method:"getRankList"},//请求参数
		dataType:"json",//ajax接口（请求url）返回的数据类型
		success:function(data){//data：返回数据（json对象）
			if(data != null){
				rank.html("");
				var options = "<option value=\"0\">-選択してください-</option>";
				for(var i = 0; i < data.length; i++){
					//alert(data[i].id);
					//alert(data[i].rankName);
					options += "<option value=\""+data[i].id+"\">"+data[i].rankName+"</option>";
				}
				rank.html(options);
			}
		},
		error:function(data){//当访问时候，404，500 等非200的错误状态码
			validateTip(rank.next(),{"color":"red"},imgNo+" ランクリスト取得エラー",false);
		}
	});
	
	
	
	/*
	 * 验证
	 * 失焦\获焦
	 * jquery的方法传递
	 */
	userCode.bind("blur",function(){
		//ajax后台验证--userCode是否已存在
		//user.do?method=ucexist&userCode=**
		$.ajax({
			type:"GET",//请求类型
			url:path+"/jsp/employee.do",//请求的url
			data:{method:"ifExist",userCode:userCode.val()},//请求参数
			dataType:"json",//ajax接口（请求url）返回的数据类型
			success:function(data){//data：返回数据（json对象）
				if(data.userCode == "NoWrite"){//填写的usercode为空
					console.log("NoWrite");
					validateTip(userCode.next(),{color:"red"},imgNo+" ユーザーアカウントが入力されていません ",false);
				}else{
					if(data.userCode == "exist"){//账号已存在，错误提示
						validateTip(userCode.next(),{"color":"red"},imgNo+ " このユーザー アカウントはすでに存在します",false);
					}else{//账号可用，正确提示
						validateTip(userCode.next(),{"color":"green"},imgYes+" このアカウントは使用できます",true);
					}
				}
			},
			error:function(data){//当访问时候，404，500 等非200的错误状态码
				validateTip(userCode.next(),{"color":"red"},imgNo+" アクセスしているページは存在しません",false);
			}
		});
		
		
	}).bind("focus",function(){
		//显示友情提示
		validateTip(userCode.next(),{"color":"#666666"},"* ユーザーコードはシステムにログインするためのアカウントです",false);
	}).focus();
	
	userName.bind("focus",function(){
		validateTip(userName.next(),{"color":"#666666"},"* ユーザー名の長さは 1 文字以上 10 文字未満である必要があります",false);
	}).bind("blur",function(){
		if(userName.val() != null && userName.val().length > 1
				&& userName.val().length < 10){
			validateTip(userName.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(userName.next(),{"color":"red"},imgNo+" 入力したユーザー名が仕様を満たしていません。再入力してください。",false);
		}
		
	});

	rank.bind("focus",function(){
		validateTip(rank.next(),{"color":"#666666"},"* ランクを選択してください",false);
	}).bind("blur",function(){
		if(rank.val() != null && rank.val() > 0){
			validateTip(rank.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(rank.next(),{"color":"red"},imgNo + " ランクを再度選択してください",false);
		}
	});
	
	age.bind("focus",function(){
		validateTip(age.next(),{"color":"#666666"},("* 年齢を入力してください"),false);
	}).bind("blur",function(){
		if(age.val() != null && age.val() != "" && age.val() < 120 && age.val() > 14){
			validateTip(age.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(age.next(),{"color":"red"},imgNo + " 適合な年齢ではない、再入力してください",false);
		}
	});
	
	phone.bind("focus",function(){
		validateTip(phone.next(),{"color":"#666666"},"* 電話番号を入力してください",false);
	}).bind("blur",function(){
		var patrn=/^(13[0-9]|15[0-9]|18[0-9])\d{8}$/;
		if(phone.val().match(patrn)){
			validateTip(phone.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(phone.next(),{"color":"red"},imgNo + " 入力した携帯電話番号の形式が正しくありません",false);
		}
	});
	
	
	
	addBtn.bind("click",function(){
		if(userCode.attr("validateStatus") != "true"){
			userCode.blur();
			// confirm("1");
		}else if(userName.attr("validateStatus") != "true"){
			userName.blur();
			// confirm("2");
		}else if(rank.attr("validateStatus") != "true"){
			rank.blur();
			// confirm("3");
		}else if(age.attr("validateStatus") != "true"){
			age.blur();
			// confirm("4");
		}else if(phone.attr("validateStatus") != "true"){
			phone.blur();
			// confirm("5");
		}else{
			if(confirm("保存しますか")){
				$("#employeeForm").submit();
			}
		}
	});
	
	backBtn.on("click",function(){
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
