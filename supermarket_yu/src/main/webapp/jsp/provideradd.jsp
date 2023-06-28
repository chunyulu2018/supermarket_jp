<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>

<div class="right">
        <div class="location">
            <strong>現在の場所:</strong>
            <span>サプライヤ管理页面 >> サプライヤ添加页面</span>
        </div>
        <div class="providerAdd">
           <form id="providerForm" name="providerForm" method="post" action="${pageContext.request.contextPath }/jsp/provider.do">
			<input type="hidden" name="method" value="add">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div class="">
                    <label for="proCode">サプライヤコード：</label>
                    <input type="text" name="proCode" id="proCode" value=""> 
					<!-- 放置提示信息 -->
					<font color="red"></font>
                </div>
                <div>
                    <label for="proName">会社名：</label>
                   <input type="text" name="proName" id="proName" value=""> 
					<font color="red"></font>
                </div>
                <div>
                    <label for="proContact">担当者：</label>
                    <input type="text" name="proContact" id="proContact" value=""> 
					<font color="red"></font>

                </div>
                <div>
                    <label for="proPhone">電話番号：</label>
                    <input type="text" name="proPhone" id="proPhone" value=""> 
					<font color="red"></font>
                </div>
                <div>
                    <label for="proAddress">住所：</label>
                    <input type="text" name="proAddress" id="proAddress" value=""> 
                </div>
                <div>
                    <label for="proFax">FAX：</label>
                    <input type="text" name="proFax" id="proFax" value=""> 
                </div>
                <div>
                    <label for="proDesc">コメント：</label>
                    <input type="text" name="proDesc" id="proDesc" value=""> 
                </div>
                <div class="providerAddBtn">
                    <input type="button" name="add" id="add" value="登録">
					<input type="button" id="back" name="back" value="取消" >
                </div>
            </form>
     </div>
</div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/provideradd.js"></script>
