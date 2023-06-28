<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>
 <div class="right">
        <div class="location">
            <strong>現在の場所:</strong>
            <span>社員管理ページ>>社員情報表示ページ</span>
        </div>
        <div class="providerView">
            <p><strong>社員コード：</strong><span>${employee.userCode }</span></p>
            <p><strong>社員名：</strong><span>${employee.userName }</span></p>
            <p><strong>会員ランク：</strong>
            	<span>
            	<c:if test="${employee.rank == 0 }">非会員</c:if>
				<c:if test="${employee.rank == 1 }">シルバー</c:if>
				<c:if test="${employee.rank == 2 }">ゴールデン</c:if>
				<c:if test="${employee.rank == 3 }">プラチナ</c:if>
            	</span>
            </p>
            <p><strong>年齢：</strong><span>${employee.age }</span></p>
            <p><strong>性別：</strong>
            	<span>
            		<c:if test="${employee.gender == 1 }">男</c:if>
					<c:if test="${employee.gender == 2 }">女</c:if>
				</span>
			</p>         
            <p><strong>携帯電話：</strong><span>${employee.phone }</span></p>
            <p><strong>メールアドレス：</strong><span>${employee.mailAddress }</span></p>
            <p><strong>住所：</strong><span>${employee.address }</span></p>
            <p><strong>IDカード番号：</strong><span>${employee.userId}</span></p>
			<div class="providerAddBtn">
            	<input type="button" id="back" name="back" value="戻る" >
            </div>
        </div>
    </div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/employeeview.js"></script>