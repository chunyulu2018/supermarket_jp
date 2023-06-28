<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>
 <div class="right">
        <div class="location">
            <strong>現在の場所:</strong>
            <span>サプライヤ管理页面 >> 信息查看</span>
        </div>
        <div class="providerView">
            <p><strong>サプライヤコード：</strong><span>${provider.proCode }</span></p>
            <p><strong>会社名：</strong><span>${provider.proName }</span></p>
            <p><strong>担当者：</strong><span>${provider.proContact }</span></p>
            <p><strong>電話番号：</strong><span>${provider.proPhone }</span></p>
            <p><strong>FAX：</strong><span>${provider.proFax }</span></p>
            <p><strong>コメント：</strong><span>${provider.proDesc}</span></p>
			<div class="providerAddBtn">
            	<input type="button" id="back" name="back" value="戻る" >
            </div>
        </div>
    </div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/providerview.js"></script>
