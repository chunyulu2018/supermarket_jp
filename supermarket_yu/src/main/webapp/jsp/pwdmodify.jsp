<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>
<div class="right">
            <div class="location">
                <strong>現在の場所:</strong>
                <span>パスワード変更ページ</span>
            </div>
            <div class="providerAdd">
                <form id="userForm" name="userForm" method="post" action="${pageContext.request.contextPath}/jsp/user.do">
                    <input type="hidden" name="method" value="savePwd">

                    <div class="">
                        <label for="oldpassword">古いパスワード：</label>
                        <input type="password" name="oldpassword" id="oldpassword" value="">
						<font color="red"></font>
                    </div>
                    <div>
                        <label for="newpassword">新しいパスワード：</label>
                        <input type="password" name="newpassword" id="newpassword" value="">
						<font color="red"></font>
                    </div>
                    <div>
                        <label for="rnewpassword">新しいパスワードの確認：</label>
                        <input type="password" name="rnewpassword" id="rnewpassword" value="">
						<font color="red"></font>
                    </div>
                    <div class="info">${message}</div>
                    <div class="providerAddBtn">
                        <!--<a href="#">保存</a>-->
                        <input type="button" name="save" id="save" value="変更" class="input-button">
                    </div>
                </form>
            </div>
        </div>
    </section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="../js/pwdmodify.js"></script>