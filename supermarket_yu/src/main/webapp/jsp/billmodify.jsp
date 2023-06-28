<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>

<div class="right">
        <div class="location">
            <strong>現在の場所:</strong>
            <span>注文管理の決定ページ>>注文変更ページ</span>
        </div>
        <div class="providerAdd">
          <form id="billForm" name="billForm" method="post" action="${pageContext.request.contextPath }/jsp/bill.do">
				<input type="hidden" name="method" value="modifysave">
				<input type="hidden" name="billid" value="${bill.id }">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div class="">
                    <label for="billCode">注文番号：</label>
                    <input type="text" name="billCode" id="billCode" value="${bill.billCode }" readonly="readonly"> 
                </div>
                <div>
                    <label for="productName">商品名称：</label>
                    <input type="text" name="productName" id="productName" value="${bill.productName }"> 
					<font color="red"></font>
                </div>
                <div>
                    <label for="productUnit">商品単位：</label>
                    <input type="text" name="productUnit" id="productUnit" value="${bill.productUnit }"> 
					<font color="red"></font>
                </div>
                <div>
                    <label for="productCount">商品数量：</label>
                    <input type="text" name="productCount" id="productCount" value="${bill.productCount }"> 
					<font color="red"></font>
                </div>
                <div>
                    <label for="totalPrice">合計金額：</label>
                    <input type="text" name="totalPrice" id="totalPrice" value="${bill.totalPrice }"> 
					<font color="red"></font>
                </div>
                <div>
                    <label for="providerId">サプライヤ：</label>
                    <input type="hidden" value="${bill.providerId }" id="pid" />
					<select name="providerId" id="providerId">
		        	</select>
					<font color="red"></font>
                </div>
                <div>
                    <label >支払の有無：</label>
                    <c:if test="${bill.isPayment == 1 }">
						<input type="radio" name="isPayment" value="1" checked="checked">未払い
						<input type="radio" name="isPayment" value="2" >支払済
					</c:if>
					<c:if test="${bill.isPayment == 2 }">
						<input type="radio" name="isPayment" value="1">未払い
						<input type="radio" name="isPayment" value="2" checked="checked">支払済
					</c:if>
                </div>
                <div class="providerAddBtn">
                  <input type="button" name="save" id="save" value="変更">
				  <input type="button" id="back" name="back" value="取消" >
              	</div>
            </form>
        </div>

    </div>
</section>

<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/billmodify.js"></script>