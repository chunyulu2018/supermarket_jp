var empCode = null;
var empName = null;
var phone = null;
var addBtn = null;
var backBtn = null;


$(function(){
	empCode = $("#empCode");
	empName = $("#empName");
	phone = $("#phone");
	addBtn = $("#add");
	backBtn = $("#back");
	//初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
	empCode.next().html("*");
	empName.next().html("*");
	phone.next().html("*");
	
	/*
	$.ajax({
		type:"GET",//请求类型
		url:path+"/jsp/emp.do",//请求的url
		data:{method:"getRoleList"},//请求参数
		dataType:"json",//ajax接口（请求url）返回的数据类型
		success:function(data){//data：返回数据（json对象）
			if(data != null){
				empRole.html("");
				var options = "<option value=\"0\">-選択してください-</option>";
				for(var i = 0; i < data.length; i++){
					//alert(data[i].id);
					//alert(data[i].roleName);
					options += "<option value=\""+data[i].id+"\">"+data[i].roleName+"</option>";
				}
				empRole.html(options);
			}
		},
		error:function(data){//当访问时候，404，500 等非200的错误状态码
			validateTip(empRole.next(),{"color":"red"},imgNo+" ユーザーロールリスト取得エラー",false);
		}
	});
	*/
	
	
	/*
	 * 验证
	 * 失焦\获焦
	 * jquery的方法传递
	 */
	empCode.bind("blur",function(){
		//ajax后台验证--empCode是否已存在
		//emp.do?method=ucexist&empCode=**
		$.ajax({
			type:"GET",//请求类型
			url:path+"/jsp/emp.do",//请求的url
			data:{method:"ifExist",empCode:empCode.val()},//请求参数
			dataType:"json",//ajax接口（请求url）返回的数据类型
			success:function(data){//data：返回数据（json对象）
				if(data.empCode == "NoWrite"){//填写的empcode为空
					console.log("NoWrite");
					validateTip(empCode.next(),{color:"red"},imgNo+" 社員アカウントが入力されていません ",false);
				}else{
					if(data.empCode == "exist"){//账号已存在，错误提示
						validateTip(empCode.next(),{"color":"red"},imgNo+ " この社員 アカウントはすでに存在します",false);
					}else{//账号可用，正确提示
						validateTip(empCode.next(),{"color":"green"},imgYes+" このアカウントは使用できます",true);
					}
				}
			},
			error:function(data){//当访问时候，404，500 等非200的错误状态码
				validateTip(empCode.next(),{"color":"red"},imgNo+" アクセスしているページは存在しません",false);
			}
		});
		
		
	}).bind("focus",function(){
		//显示友情提示
		validateTip(empCode.next(),{"color":"#666666"},"* 社員コードはシステムにログインするためのアカウントです",false);
	}).focus();
	
	empName.bind("focus",function(){
		validateTip(empName.next(),{"color":"#666666"},"* 社員名の長さは 1 文字以上 10 文字未満である必要があります",false);
	}).bind("blur",function(){
		if(empName.val() != null && empName.val().length > 1
				&& empName.val().length < 10){
			validateTip(empName.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(empName.next(),{"color":"red"},imgNo+" 入力した社員名が仕様を満たしていません。再入力してください。",false);
		}
		
	});
		
	phone.bind("focus",function(){
		validateTip(phone.next(),{"color":"#666666"},"* 電話番号を入力してください",false);
	}).bind("blur",function(){
		var patrn=/^0\d{10}$/;
		if(phone.val().match(patrn)){
			validateTip(phone.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(phone.next(),{"color":"red"},imgNo + " 入力した携帯電話番号の形式が正しくありません",false);
		}
	});
	
	addBtn.bind("click",function(){
		if(empCode.attr("validateStatus") != "true"){
			empCode.blur();
			// confirm("1");
		}else if(empName.attr("validateStatus") != "true"){
			empName.blur();
			// confirm("2");
		}else if(phone.attr("validateStatus") != "true"){
			phone.blur();
			// confirm("3");
		}else{
			if(confirm("保存しますか")){
				$("#empForm").submit();
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