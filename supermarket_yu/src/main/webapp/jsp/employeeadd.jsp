<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>
  <div class="right">
            <div class="location">
                <strong>現在の場所:</strong>
                <span>社員情報管理画面</span>
            <div class="providerAdd">
            <form id="employeeForm" name="employeeForm" method="post" action="${pageContext.request.contextPath }/jsp/employee.do">
				<input type="hidden" name="method" value="add">
                <!--div的class 为error是验证错误，ok是验证成功-->
            <div>
                <label for="userCode">社員コード：</label>
                <input type="text" name="userCode" id="userCode" value="">
                <!-- 放置提示信息 -->
                <font color="red"></font>
            </div>
            <div>
                <label for="userName">社員名：</label>
                <input type="text" name="userName" id="userName" value=""> 
                <font color="red"></font>
            </div>
            <div>
                <label >ランク：</label>
                <!-- 列出所有的会员级别 -->
                <select name="rank" id="rank"></select>
                <font color="red"></font>
            </div>
            <div>
                <label for="age">年龄：</label>
                <input type="text" id="age" name="age">
                <font color="red"></font>
            </div>
            <div>
                <label >性別：</label>
                <select name="gender" id="gender">
                    <option value="1" selected="selected">男</option>
                    <option value="2">女</option>
                 </select>
            </div>
                
            <div>
                <label for="phone">電話番号：</label>
                <input type="text" name="phone" id="phone" value=""> 
                <font color="red"></font>
            </div>
            <div>
                <label for="address">住所：</label>
                <input name="address" id="address"  value="">
            </div>
            <div>
                <label for="mailAddress">メールアドレス：</label>
                <input name="mailAddress" id="mailAddress"  value="">
            </div>
            <div>
                <label for="userId">マイナンバーカード：</label>
                <input name="userId" id="userId"  value="">
            </div>
                
            <div class="providerAddBtn">
                <input type="button" name="add" id="add" value="登録" >
                <input type="button" id="back" name="back" value="取消" >
            </div>
            </form>
        </div>
        
</div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript"  charset="UTF-8" src="${pageContext.request.contextPath }/js/employeeadd.js" ></script>