<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>

<div class="right">
       <div class="location">
           <strong>現在の場所:</strong>
           <span>「注文管理」ページ</span>
       </div>
       <div class="search">
       <form method="get" action="${pageContext.request.contextPath }/jsp/bill.do">
			<input name="method" value="query" class="input-text" type="hidden">
			<span>商品名称：</span>
			<input name="queryProductName" type="text" style="width:150px;" value="${queryProductName }">
			 
			<span>サプライヤ：</span>
			<select name="queryProviderId">
				<c:if test="${providerList != null }">
				   <option value="0">--選択--</option>
				   <c:forEach var="provider" items="${providerList}">
				   		<option <c:if test="${provider.id == queryProviderId }">selected="selected"</c:if>
				   		value="${provider.id}">${provider.proName}</option>
				   </c:forEach>
				</c:if>
       		</select>
			 
			<span>支払の有無：</span>
			<select name="queryIsPayment">
				<option value="0">--選択--</option>
				<option value="1" ${queryIsPayment == 1 ? "selected=\"selected\"":"" }>未払い</option>
				<option value="2" ${queryIsPayment == 2 ? "selected=\"selected\"":"" }>支払済</option>
       		</select>
			 <input type="hidden" name="pageIndex" value="1"/>
			 <input	value="検索" type="submit" id="searchbutton">
			 <a href="${pageContext.request.contextPath }/jsp/billadd.jsp">注文追加</a>
		</form>
       </div>
       <!--账单表格 样式和サプライヤ公用-->
      <table class="providerTable" cellpadding="0" cellspacing="0">
          <tr class="firstTr">
              <th width="10%">注文番号</th>
              <th width="20%">商品名称</th>
              <th width="10%">サプライヤ</th>
              <th width="10%">注文金額</th>
              <th width="10%">支払の有無</th>
              <th width="10%">作成時間</th>
              <th width="30%">オペレーション</th>
          </tr>
          <c:forEach var="bill" items="${billList }" varStatus="status">
				<tr>
					<td>
					<span>${bill.billCode }</span>
					</td>
					<td>
					<span>${bill.productName }</span>
					</td>
					<td>
					<span>${bill.providerName}</span>
					</td>
					<td>
					<span>${bill.totalPrice}</span>
					</td>
					<td>
					<span>
						<c:if test="${bill.isPayment == 1}">未払い</c:if>
						<c:if test="${bill.isPayment == 2}">支払済</c:if>
					</span>
					</td>
					<td>
					<span>
					<fmt:formatDate value="${bill.creationDate}" pattern="yyyy-MM-dd"/>
					</span>
					</td>
					<td>
					<span><a class="viewBill" href="javascript:;" billid=${bill.id } billcc=${bill.billCode }><img src="${pageContext.request.contextPath }/images/read.png" alt="表示" title="表示"/></a></span>
					<span><a class="modifyBill" href="javascript:;" billid=${bill.id } billcc=${bill.billCode }><img src="${pageContext.request.contextPath }/images/xiugai.png" alt="変更" title="変更"/></a></span>
					<span><a class="deleteBill" href="javascript:;" billid=${bill.id } billcc=${bill.billCode }><img src="${pageContext.request.contextPath }/images/schu.png" alt="削除" title="削除"/></a></span>
					</td>
				</tr>
			</c:forEach>
      </table>
	<input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
	<c:import url="rollpage.jsp">
		<c:param name="totalCount" value="${totalCount}"/>
		<c:param name="currentPageNo" value="${currentPageNo}"/>
		<c:param name="totalPageCount" value="${totalPageCount}"/>
	</c:import>
  </div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeBi">
    <div class="removerChid">
        <h2>ヒント</h2>
        <div class="removeMain">
            <p>この注文を削除しますか。</p>
            <a href="#" id="yes">はい</a>
            <a href="#" id="no">いいえ</a>
        </div>
    </div>
</div>

<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/billlist.js"></script>