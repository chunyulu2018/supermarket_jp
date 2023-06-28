<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>

<div class="right">
        <div class="location">
            <strong>現在の場所:</strong>
            <span>サプライヤ管理页面</span>
        </div>
        <div class="search">
        	<form method="get" action="${pageContext.request.contextPath }/jsp/provider.do">
				<input name="method" value="query" type="hidden">
				<span>サプライヤコード：</span>
				<input name="queryProCode" type="text" value="${queryProCode }">
				
				<span>サプライヤ名称：</span>
				<input name="queryProName" type="text" value="${queryProName }">

				<input type="hidden" name="pageIndex" value="1"/>
				<input value="検索" type="submit" id="searchbutton">
				<a href="${pageContext.request.contextPath }/jsp/provideradd.jsp">会社追加</a>
			</form>
        </div>
        <!--サプライヤオペレーション表格-->
        <table class="providerTable" cellpadding="0" cellspacing="0">
            <tr class="firstTr">
                <th width="10%">サプライヤコード</th>
                <th width="20%">会社名</th>
                <th width="10%">担当者</th>
                <th width="10%">電話番号</th>
                <th width="10%">FAX</th>
                <th width="10%">作成時間</th>
                <th width="30%">オペレーション</th>
            </tr>
            <c:forEach var="provider" items="${providerList }" varStatus="status">
				<tr>
					<td>
					<span>${provider.proCode }</span>
					</td>
					<td>
					<span>${provider.proName }</span>
					</td>
					<td>
					<span>${provider.proContact}</span>
					</td>
					<td>
					<span>${provider.proPhone}</span>
					</td>
					<td>
					<span>${provider.proFax}</span>
					</td>
					<td>
					<span>
					<fmt:formatDate value="${provider.creationDate}" pattern="yyyy-MM-dd"/>
					</span>
					</td>
					<td>
					<span><a class="viewProvider" href="javascript:;" proid=${provider.id } proname=${provider.proName }><img src="${pageContext.request.contextPath }/images/read.png" alt="查看" title="查看"/></a></span>
					<span><a class="modifyProvider" href="javascript:;" proid=${provider.id } proname=${provider.proName }><img src="${pageContext.request.contextPath }/images/xiugai.png" alt="修改" title="修改"/></a></span>
					<span><a class="deleteProvider" href="javascript:;" proid=${provider.id } proname=${provider.proName }><img src="${pageContext.request.contextPath }/images/schu.png" alt="删除" title="删除"/></a></span>
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
<div class="remove" id="removeProv">
   <div class="removerChid">
       <h2>ヒント</h2>
       <div class="removeMain" >
           <p>このサプライヤを削除しますか。</p>
           <a href="#" id="yes">はい</a>
           <a href="#" id="no">いいえ</a>
       </div>
   </div>
</div>

<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/providerlist.js"></script>
