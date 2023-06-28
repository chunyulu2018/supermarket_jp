var oldpassword = null;
var newpassword = null;
var rnewpassword = null;
var saveBtn = null;

$(function(){
	oldpassword = $("#oldpassword");
	newpassword = $("#newpassword");
	rnewpassword = $("#rnewpassword");
	saveBtn = $("#save");
	
	oldpassword.next().html("*");
	newpassword.next().html("*");
	rnewpassword.next().html("*");
	
	oldpassword.on("blur",function(){
		$.ajax({
			type:"GET",
			url:path+"/jsp/user.do",
			data:{method:"pwdmodify",oldpassword:oldpassword.val()},	//Ajax传递的参数
			//path+/jsp/user.do?method=pwdmodify&oldpassword=oldpassword.val();
			dataType:"json",	//主流开发都是用JSON实现前后端交互
			success:function(data){
				if(data.result == "true"){//旧密码正确
					validateTip(oldpassword.next(),{"color":"green"},imgYes,true);
				}else if(data.result == "false"){//旧密码输入不正确
					validateTip(oldpassword.next(),{"color":"red"},imgNo + " 元のパスワード入力が間違っています",false);
				}else if(data.result == "sessionerror"){//当前用户session过期，请重新登录
					validateTip(oldpassword.next(),{"color":"red"},imgNo + " 現在のユーザーセッションの有効期限が切れました。再度ログインしてください",false);
				}else if(data.result == "error"){//旧密码输入为空
					validateTip(oldpassword.next(),{"color":"red"},imgNo + " 古いパスワードを入力してください",false);
				}
			},
			error:function(data){
				//请求出错
				validateTip(oldpassword.next(),{"color":"red"},imgNo + " 間違った要求",false);
			}
		});


	}).on("focus",function(){
		validateTip(oldpassword.next(),{"color":"#666666"},"* 元のパスワードを入力してください",false);
	});

	newpassword.on("focus",function(){
		validateTip(newpassword.next(),{"color":"#666666"},"* パスワードの長さは 6 より大きく 20 未満である必要があります",false);
	}).on("blur",function(){
		if(newpassword.val() != null && newpassword.val().length > 6
				&& newpassword.val().length < 20 ){
			validateTip(newpassword.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(newpassword.next(),{"color":"red"},imgNo + " 入力したパスワードが仕様を満たしていません。再入力してください",false);
		}
	});


	rnewpassword.on("focus",function(){
		validateTip(rnewpassword.next(),{"color":"#666666"},"* 上と同じパスワードを入力してください",false);
	}).on("blur",function(){
		if(rnewpassword.val() != null && rnewpassword.val().length > 5
				&& rnewpassword.val().length < 20 && newpassword.val() == rnewpassword.val()){
			validateTip(rnewpassword.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(rnewpassword.next(),{"color":"red"},imgNo + " 入力した 2 つのパスワードは不一致です。再入力してください。",false);
		}
	});

	saveBtn.on("click",function(){
		oldpassword.blur();
		newpassword.blur();
		rnewpassword.blur();
		if(
			oldpassword.attr("validateStatus") == "true"
			&&
			newpassword.attr("validateStatus") == "true"
			&&
			rnewpassword.attr("validateStatus") == "true"){
			if(confirm("パスワードを修正しますか？")){
				$("#userForm").submit();
			}
		}
	});
}
);