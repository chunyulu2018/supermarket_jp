var empObj;

//用户管理页面上点击删除按钮弹出删除框(emplist.jsp)
function deleteEmp(obj){
	$.ajax({
		type:"GET",
		url:path+"/jsp/emp.do",
		data:{method:"delemp",eid:obj.attr("empid")},
		dataType:"json",
		success:function(data){
			console.log(data)
			if(data.delResult == "true"){//删除成功：移除删除行
				cancleBtn();
				obj.parents("tr").remove();
			}else if(data.delResult == "false"){//删除失败
				//alert("对不起，删除用户【"+obj.attr("empname")+"】失败");
				changeDLGContent("ユーザ【"+obj.attr("empname")+"】を削除失敗しました。");
			}else if(data.delResult == "notexist"){
				//alert("对不起，用户【"+obj.attr("empname")+"】不存在");
				changeDLGContent("【"+obj.attr("empname")+"】は存在しない");
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
	$('#removeEmp').fadeIn();
}

function cancleBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeEmp').fadeOut();
}
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}

$(function(){
	//通过jquery的class选择器（数组）
	//对每个class为viewEmp的元素进行动作绑定（click）
	/**
	 * bind、live、delegate
	 * on
	 */
	$(".viewEmp").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		window.location.href=path+"/jsp/emp.do?method=view&eid="+ obj.attr("empid");
	});
	
	$(".modifyEmp").on("click",function(){
		var obj = $(this);
		window.location.href=path+"/jsp/emp.do?method=modify&eid="+ obj.attr("empid");
	});

	$('#no').click(function () {
		cancleBtn();
	});
	
	$('#yes').click(function () {
		deleteEmp(empObj);
	});

	$(".deleteEmp").on("click",function(){
		empObj = $(this);
		changeDLGContent("ユーザ【"+empObj.attr("empname")+"】を削除しますか？");
		openYesOrNoDLG();
	});
	
	/*$(".deleteEmp").on("click",function(){
		var obj = $(this);
		if(confirm("你确定要删除用户【"+obj.attr("empname")+"】吗？")){
			$.ajax({
				type:"GET",
				url:path+"/jsp/emp.do",
				data:{method:"delemp",uid:obj.attr("empid")},
				dataType:"json",
				success:function(data){
					if(data.delResult == "true"){//删除成功：移除删除行
						alert("删除成功");
						obj.parents("tr").remove();
					}else if(data.delResult == "false"){//删除失败
						alert("对不起，删除用户【"+obj.attr("empname")+"】失败");
					}else if(data.delResult == "notexist"){
						alert("对不起，用户【"+obj.attr("empname")+"】不存在");
					}
				},
				error:function(data){
					alert("对不起，删除失败");
				}
			});
		}
	});*/
});