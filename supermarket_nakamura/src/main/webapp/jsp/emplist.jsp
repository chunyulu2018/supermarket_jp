<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>
        <div class="right">
            <div class="location">
                <strong>現在の場所:</strong>
                <span>社員管理画面</span>
            </div>
            <div class="search">
           		<form method="get" action="${pageContext.request.contextPath }/jsp/emp.do">
					<input name="method" value="query" class="input-text" type="hidden">
					 <span>社員名：</span>
					 <input name="queryName" class="input-text"	type="text" value="${queryEmpName }">
					 
					 <input type="hidden" name="pageIndex" value="1"/>
					 <input	value="検索" type="submit" id="searchbutton">
					 <a href="${pageContext.request.contextPath}/jsp/empadd.jsp" >社員追加</a>
				</form>
            </div>
            <!--用户-->
        <table class="empTable" cellpadding="0" cellspacing="0">
            <tr class="firstTr">
                <th width="5%">ID</th>
                <th width="5%">社員コード</th>
                <th width="5%">社員名</th>
                <th width="5%">会員ランク</th>
                <th width="5%">年齢</th>
                <th width="5%">性別</th>
                <th width="9%">電話番号</th>
                <th width="9%">メールアドレス</th>
                <th width="9%">IDカード</th>
                <th width="9%">写真</th>
                <th width="9%">住所</th>
                <th width="15%">オペレーション</th>
            </tr>
            <c:forEach var="emp" items="${empList }" varStatus="status">
				<tr>
					<td>
					<span>${emp.id }</span>
					</td>
					<td>
					<span>${emp.empCode }</span>
					</td>
					<td>
					<span>${emp.empName }</span>
					</td>
					<td>
					<span>${emp.rank }</span>
					</td>
					<td>
					<span>${emp.age }</span>
					</td>
					<td>
					<span>${emp.gender }</span>
					</td>
					<td>
					<span>${emp.phone }</span>
					</td>
					<td>
					<span>${emp.mail }</span>
					</td>
					<td>
					<span>${emp.IDCard }</span>
					</td>
					<td>
					<span>${emp.picture }</span>
					</td>
					<td>
					<span>${emp.address }</span>
					</td>
					<!--  			
					<td>
					<span>
					<fmt:formatDate value="${emp.creationDate}" pattern="yyyy-MM-dd"/>
					</span>
					</td>
					-->	
						<td>
						<span>
							<a class="viewEmp" href="javascript:;" empid=${emp.id } empname=${emp.empName }>
								<img src="${pageContext.request.contextPath }/images/read.png" alt="查看" title="查看"/>
							</a>
						</span>
						<span>
							<a class="modifyEmp" href="javascript:;" empid=${emp.id } empname=${emp.empName }>
								<img src="${pageContext.request.contextPath }/images/xiugai.png" alt="修改" title="修改"/>
							</a>
						</span>
						<span>
							<a class="deleteEmp" href="javascript:;"  empid=${emp.id } empname=${emp.empName }>
								<img src="${pageContext.request.contextPath }/images/schu.png" alt="删除" title="删除"/>
							</a>
						</span>
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
<div class="remove" id="removeEmp">
    <div class="removerChid">
        <h2>ヒント</h2>
        <div class="removeMain">
            <p>社員を削除しますか？</p>
			<a href="#" id="yes">はい</a>
			<a href="#" id="no">いいえ</a>
		</div>
    </div>
</div>

<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/emplist.js"></script>
