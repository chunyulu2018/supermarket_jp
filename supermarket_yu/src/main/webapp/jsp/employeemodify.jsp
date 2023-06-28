<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>
    <div class="right">
        <div class="location">
                <strong>現在の場所:</strong>
                <span>社員情報管理画面</span>
        </div>
        <div class="providerAdd">
        <form id="employeeForm" name="employeeForm" method="post" action="${pageContext.request.contextPath }/jsp/employee.do">
			<input type="hidden" name="method" value="modifyexe">
			<input type="hidden" name="empid" value="${employee.id }"/>
			<div>
                <label for="userName">社員名：</label>
                <input type="text   " name="userName" id="userName" value="${employee.userName }">
				<font color="red"></font>
            </div>
            <div>
                <label >ランク：</label>
                <!-- 列出所有的会员级别 -->
             
                <input type="hidden" value="${employee.rank }" id="rid" />
                <select name="rank" id="rank"></select>
                <font color="red"></font> 	
            </div>
            <div>
                <label for="age">年龄：</label>
                <input type="text" id="age" name="age" value="${employee.age }">
                <font color="red"></font>
            </div>
			 <div>
                    <label >性別：</label>
                    <select name="gender" id="gender">
						<c:choose>
							<c:when test="${employee.gender == 1 }">
								<option value="1" selected="selected">男</option>
					    		<option value="2">女</option>
							</c:when>
							<c:otherwise>
								<option value="1">男</option>
					    		<option value="2" selected="selected">女</option>
							</c:otherwise>
						</c:choose>
					 </select>
             </div>
			
			 <div>
                    <label for="phone">電話番号：</label>
                    <input type="text" name="phone" id="phone" value="${employee.phone }"> 
                    <font color="red"></font>
             </div>
             <div>
                    <label for="mailAddress">メールアドレス：</label>
                    <input type="text" name="mailAddress" id="mailAddress" value="${employee.mailAddress }"> 
                    
             </div>
             <div>
                    <label for="address">住所：</label>
                    <input type="text" name="address" id="address" value="${employee.address }">
             </div>
             <div>
                    <label for="userId">マイナンバーカード：</label>
                    <input type="text" name="userId" id="userId" value="${employee.userId }">
             </div>
			
			 <div class="providerAddBtn">
                    <input type="button" name="save" id="save" value="変更" />
                    <input type="button" id="back" name="back" value="取消"/>
                </div>
            </form>
        </div>
    </div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/employeemodify.js"></script>
