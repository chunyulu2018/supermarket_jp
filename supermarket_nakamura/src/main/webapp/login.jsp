<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>ログイン - スーパーマーケット管理システム</title>
    <link type="text/css" rel="stylesheet" href="css/style.css" />
    <script type="text/javascript">
	/* if(top.location!=self.location){
	      top.location=self.location;
	 } */
    </script>
</head>
<body class="login_bg">

<%--    <%--%>
<%--        Object attribute = request.getAttribute("error");--%>
<%--    %>--%>

    <section class="loginBox">
        <header class="loginHeader">
            <h1>スーパーマーケット管理システム</h1>
        </header>
        <section class="loginCont">
	        <form class="loginForm" action="${pageContext.request.contextPath}/login.do"  name="actionForm" id="actionForm"  method="post" >
<%--				<div class="info">${error}</div>--%>
				<div class="inputbox">
                    <label for="userCode">User</label>
					<input type="text" class="input-text" id="userCode" name="userCode" placeholder="ユーザ名を入力してください。" required/>
				</div>
				<div class="inputbox">
                    <label for="userPassword">Password</label>
                    <input type="password" id="userPassword" name="userPassword" placeholder="パスワードを入力してください。" required/>
                </div>
                <div class="info" style="text-align: center;">${error}</div>
				<div class="subBtn">
					
                    <input type="submit" value="login"/>
                    <input type="reset" value="reset"/>
                </div>	
			</form>
        </section>
    </section>
</body>
</html>
