<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>
    <div class="right">
        <div class="location">
            <strong>現在の場所:</strong>
            <span>社員管理ページ>>社員変更ページ</span>
        </div>
        <div class="providerAdd">
        <form id="empForm" name="empForm" method="post" action="${pageContext.request.contextPath }/jsp/emp.do">
			<input type="hidden" name="method" value="modifyexe">
			<input type="hidden" name="empid" value="${emp.id }"/>
			<div>
				<label for="empCode">社員コード：</label>
				<input type="text" name="empCode" id="empCode" value="${emp.empCode }">
			</div>
			<div>
				<label for="empName">社員名：</label>
				<input type="text" name="empName" id="empName" value="${emp.empName }">
				<font color="red"></font>
			</div>
			<div>
				<label for="rank">ランク：</label>
				<input type="text" name="rank" id="rank" value="${emp.rank }">
			</div>
			<div>
				<label for="age">年齢：</label>
				<input type="text" name="age" id="age" value="${emp.age }">
			</div>
			<div>
				<label for="gender">性別：</label>
				<input type="text" name="gender" id="gender" value="${emp.gender }">
			</div>

			<div>
				<label for="phone">電話番号：</label>
				<input type="text" name="phone" id="phone" value="${emp.phone }"> 
				<font color="red"></font>
			</div>
			<div>
				<label for="mail">メール：</label>
				<input type="text" name="mail" id="mail" value="${emp.mail }">
			</div>
			<div>
				<label for="mail">IDカード：</label>
				<input type="text" name="IDCard" id="IDCard" value="${emp.IDCard }">
			</div>
			<div>
				<label for="picture">写真：</label>
				<input type="text" name="picture" id="picture" value="${emp.picture }">
			</div>
			<div>
				<label for="address">住所：</label>
				<input type="text" name="address" id="address" value="${emp.address }">
			</div>
			 <div class="providerAddBtn">
                    <input type="button" name="save" id="save" value="変更" />
                    <input type="button" id="back" name="back" value="取消"/>
                </div>
            </form>
        </div>
    </div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/empmodify.js"></script>
