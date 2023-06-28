<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>
 <div class="right">
        <div class="location">
            <strong>現在の場所:</strong>
            <span>ユーザ管理ページ>>ユーザ情報表示ページ</span>
        </div>
        <div class="providerView">
            <p><strong>ユーザコード：</strong><span>${user.userCode }</span></p>
            <p><strong>ユーザ名：</strong><span>${user.userName }</span></p>
            <p><strong>性別：</strong>
            	<span>
            		<c:if test="${user.gender == 1 }">男</c:if>
					<c:if test="${user.gender == 2 }">女</c:if>
				</span>
			</p>
            <p><strong>生年月日：</strong><span>${user.birthday }</span></p>
            <p><strong>電話番号：</strong><span>${user.phone }</span></p>
            <p><strong>住所：</strong><span>${user.address }</span></p>
            <p><strong>ロール：</strong><span>${user.userRoleName}</span></p>
			<div class="providerAddBtn">
            	<input type="button" id="back" name="back" value="戻る" >
            </div>
        </div>
    </div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/userview.js"></script>