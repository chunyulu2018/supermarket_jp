<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>
        <div class="right">
            <div class="location">
                <strong>現在の場所:</strong>
                <span>社員情報管理画面</span>
                
          <!-- 检索 用rankList-->                
            </div>
            <div class="search">
           		<form method="get" action="${pageContext.request.contextPath }/jsp/employee.do">
					<input name="method" value="query" class="input-text" type="hidden">
					 <span>社員名：</span>
					 <input name="queryUserName" class="input-text"	type="text" value="${queryUserName}">
					 
					 <span>ランク：</span>
					 <select name="queryRank">
						<c:if test="${rankList != null }">
						   <option value="0">--選択--</option>
						   <c:forEach var="rank" items="${rankList}">
						   		<option <c:if test="${rank.id == queryRank }">selected="selected"</c:if>
						   		value="${rank.id}">${rank.rankName}</option>
						   </c:forEach>
						</c:if>
	        		</select>
					 
					 <input type="hidden" name="pageIndex" value="1"/>
					 <input	value="検索" type="submit" id="searchbutton">
					 <a href="${pageContext.request.contextPath}/jsp/employeeadd.jsp" >社員追加</a>
				</form>
            </div>
            
            <!--用户 用employeeList-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="10%">社員コード</th>
                    <th width="10%">社員名</th>
                    <th width="5%">年齢</th>
                    <th width="5%">性別</th>
                    <th width="10%">携帯電話</th>
                    <th width="10%">メールアドレス</th>
                    <th width="20%">住所</th>
                    <th width="15%">IDカード番号</th>
                    <th width="15%">オペレーション</th>
                </tr>
                   <c:forEach var="employee" items="${employeeList }" varStatus="status">
					<tr>
						<td>
						<span>${employee.userCode }</span>
						</td>
						<td>
						<span>${employee.userName }				
							<c:if test="${employee.rank >0 }"><% { %> <img src="${pageContext.request.contextPath }/images/Dia.jpg" width=19.2 height=20.25 /> <% } %></c:if>
							<c:if test="${employee.rank <=0}"></c:if>
						
						</span>
						</td>
						<td>
						<span>${employee.age }</span>
						</td>
						<td>
							<span>
								<c:if test="${employee.gender==1}">男</c:if>
								<c:if test="${employee.gender==2}">女</c:if>
							</span>
						</td>
						<td>
						<span>${employee.phone}</span>
						</td>
						<td>
							<span>${employee.mailAddress}</span>
						</td>						
						<td>
							<span>${employee.address}</span>
						</td>						
						<td>
							<span>${employee.userId}</span>
						</td>
						<td>
						<span>
							<a class="viewEmployee" href="javascript:;" empid="${employee.id }" username="${employee.userName }">
								<img src="${pageContext.request.contextPath }/images/read.png" alt="查看" title="查看"/>
							</a>
						</span>
						<span>
							<a class="modifyEmployee" href="javascript:;" empid="${employee.id  }" username="${employee.userName }">
								<img src="${pageContext.request.contextPath }/images/xiugai.png" alt="修改" title="修改"/>
							</a>
						</span>
						<span>
							<a class="deleteEmployee" href="javascript:;"  empid="${employee.id  }" username="${employee.userName }">
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
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>ヒント</h2>
        <div class="removeMain">
            <p>ユーザを削除しますか？</p>
			<a href="#" id="yes">はい</a>
			<a href="#" id="no">いいえ</a>
		</div>
    </div>
</div>

<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/employeelist.js"></script>