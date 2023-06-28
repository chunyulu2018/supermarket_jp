<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>
<div class="right">
     <div class="location">
         <strong>現在の場所:</strong>
         <span>注文管理ページ>>情報表示</span>
     </div>
     <div class="providerView">
         <p><strong>注文番号：</strong><span>${bill.billCode }</span></p>
         <p><strong>商品名称：</strong><span>${bill.productName }</span></p>
         <p><strong>商品单位：</strong><span>${bill.productUnit }</span></p>
         <p><strong>商品数量：</strong><span>${bill.productCount }</span></p>
         <p><strong>合計金額：</strong><span>${bill.totalPrice }</span></p>
         <p><strong>サプライヤ：</strong><span>${bill.providerName }</span></p>
         <p><strong>支払の有無：</strong>
         	<span>
         		<c:if test="${bill.isPayment == 1}">未払い</c:if>
				<c:if test="${bill.isPayment == 2}">支払済</c:if>
			</span>
		</p>
		<div class="providerAddBtn">
         	<input type="button" id="back" name="back" value="戻る" >
        </div>
     </div>
 </div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/billview.js"></script>