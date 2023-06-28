<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>
 <div class="right">
        <div class="location">
            <strong>現在の場所:</strong>
            <span>社員管理ページ>>社員情報表示ページ</span>
        </div>
        <div class="providerView">
            			<p><strong>社員コード：</strong><span>${emp.empCode }</span></p>
			<p><strong>社員名：</strong><span>${emp.empName }</span></p>
			<p><strong>ランク：</strong><span>${emp.rank }</span></p>
			<p><strong>年齢：</strong><span>${emp.age }</span></p>
			<p><strong>性別：</strong><span>${emp.gender }</span></p>
			<p><strong>電話番号：</strong><span>${emp.phone }</span></p>
			<p><strong>メールアドレス：</strong><span>${emp.mail }</span></p>
			<p><strong>IDカード：</strong><span>${emp.IDCard }</span></p>
			<p><strong>写真：</strong><span>${emp.picture }</span></p>
			<p><strong>住所：</strong><span>${emp.address }</span></p>
			<div class="providerAddBtn">
            	<input type="button" id="back" name="back" value="戻る" >
            </div>
        </div>
    </div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/empview.js"></script>