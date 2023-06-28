/**
 * 
 */
var employeeObj;
//var queryUserName = document.insert.queryUserName.value.trim ()
//用户管理页面上点击删除按钮弹出删除框(employeelist.jsp)
function deleteEmployee(obj){
	$.ajax({
		type:"GET",
		url:path+"/jsp/employee.do",
		data:{method:"delemployee",empid:obj.attr("empid")},
		dataType:"json",
		success:function(data){
			console.log(data)
			if(data.delResult == "true"){//删除成功：移除删除行
				cancleBtn();
				obj.parents("tr").remove();
			}else if(data.delResult == "false"){//删除失败
				//alert("对不起，删除用户【"+obj.attr("username")+"】失败");
				changeDLGContent("ユーザ【"+obj.attr("username")+"】を削除失敗しました。");
			}else if(data.delResult == "notexist"){
				//alert("对不起，用户【"+obj.attr("username")+"】不存在");
				changeDLGContent("【"+obj.attr("username")+"】は存在しない");
			}
		},
		error:function(data){
			//alert("对不起，删除失败");
			changeDLGContent("削除失敗しました。");
		}
	});
}

function openYesOrNoDLG(){
	$('.zhezhao').css('display', 'block');
	$('#removeUse').fadeIn();
}

function cancleBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeUse').fadeOut();
}
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}

$(function(){
	//通过jquery的class选择器（数组）
	//对每个class为viewEmployee的元素进行动作绑定（click）
	/**
	 * bind、live、delegate
	 * on
	 */
	$(".viewEmployee").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		window.location.href=path+"/jsp/employee.do?method=view&empid="+ obj.attr("empid");
	});
	
	$(".modifyEmployee").on("click",function(){
		var obj = $(this);
		window.location.href=path+"/jsp/employee.do?method=modify&empid="+ obj.attr("empid");
	});

	$('#no').click(function () {
		cancleBtn();
	});
	
	$('#yes').click(function () {
		deleteEmployee(employeeObj);
	});

	$(".deleteEmployee").on("click",function(){
		employeeObj = $(this);
		changeDLGContent("ユーザ【"+employeeObj.attr("username")+"】を削除しますか？");
		openYesOrNoDLG();
	});
	

});