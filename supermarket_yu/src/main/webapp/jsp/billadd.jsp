<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>

<div class="right">
     <div class="location">
         <strong>現在の場所:</strong>
         <span>注文管理ページ>>注文追加ページ</span>
     </div>
     <div class="providerAdd">
         <form id="billForm" name="billForm" method="post" action="${pageContext.request.contextPath }/jsp/bill.do">
             <!--div的class 为error是验证错误，ok是验证成功-->
                 <input type="hidden" name="method" value="add">
             <div class="">
                 <label for="billCode">注文番号：</label>
                 <input type="text" name="billCode" class="text" id="billCode" value=""> 
				 <!-- 放置提示信息 -->
				 <font color="red"></font>
             </div>
             <div>
                 <label for="productName">商品名：</label>
                 <input type="text" name="productName" id="productName" value=""> 
				 <font color="red"></font>
             </div>
             <div>
                 <label for="productUnit">商品单位：</label>
                 <input type="text" name="productUnit" id="productUnit" value=""> 
				 <font color="red"></font>
             </div>
             <div>
                 <label for="productCount">商品数量：</label>
                 <input type="text" name="productCount" id="productCount" value=""> 
				 <font color="red"></font>
             </div>
             <div>
                 <label for="totalPrice">合計金額：</label>
                 <input type="text" name="totalPrice" id="totalPrice" value=""> 
				 <font color="red"></font>
             </div>
             <div>
                 <label >サプライヤ：</label>
                 <select name="providerId" id="providerId">
		         </select>
				 <font color="red"></font>
             </div>
             <div>
                 <label >支払の有無：</label>
                 <input type="radio" name="isPayment" value="1" checked="checked">未払い
				 <input type="radio" name="isPayment" value="2" >支払済
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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/billadd.js"></script>