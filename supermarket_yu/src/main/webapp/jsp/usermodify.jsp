<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>
    <div class="right">
        <div class="location">
            <strong>現在の場所:</strong>
            <span>ユーザ管理ページ>>ユーザ変更ページ</span>
        </div>
        <div class="providerAdd">
        <form id="userForm" name="userForm" method="post" action="${pageContext.request.contextPath }/jsp/user.do">
			<input type="hidden" name="method" value="modifyexe">
			<input type="hidden" name="uid" value="${user.id }"/>
			 <div>
                    <label for="userName">ユーザ名：</label>
                    <input type="text   " name="userName" id="userName" value="${user.userName }">
					<font color="red"></font>
             </div>
			 <div>
                    <label >性別：</label>
                    <select name="gender" id="gender">
						<c:choose>
							<c:when test="${user.gender == 1 }">
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
                    <label for="data">生年月日：</label>
                    <input type="text" Class="Wdate" id="birthday" name="birthday" value="${user.birthday }"
					readonly="readonly" onclick="WdatePicker();">
                    <font color="red"></font>
              </div>
			
		       <div>
                    <label for="userphone">電話番号：</label>
                    <input type="text" name="phone" id="phone" value="${user.phone }"> 
                    <font color="red"></font>
               </div>
                <div>
                    <label for="userAddress">住所：</label>
                    <input type="text" name="address" id="address" value="${user.address }">
                </div>
				<div>
                    <label>ロール：</label>
                    <!-- 列出所有的角色分类 -->
					<input type="hidden" value="${user.userRole }" id="rid" />
					<select name="userRole" id="userRole"></select>
        			<font color="red"></font>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/usermodify.js"></script>
