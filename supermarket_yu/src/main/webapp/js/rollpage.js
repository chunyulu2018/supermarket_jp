function page_nav(frm,num){
		frm.pageIndex.value = num;
		frm.submit();
}

function jump_to(frm,num){
    //alert(num);
	//验证用户的输入
	var regexp=/^[1-9]\d*$/;
	var totalPageCount = document.getElementById("totalPageCount").value;
	//alert(totalPageCount);
	if(!regexp.test(num)){
		alert("0 より大きい正の整数を入力してください。");
		return false;
	}else if((num-totalPageCount) > 0){
		alert("総ページ数よりも小さいページ番号を入力してください");
		return false;
	}else{
		page_nav(frm,num);
	}
}