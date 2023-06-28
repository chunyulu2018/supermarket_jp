<%@ page import="com.chen.util.Constants" %>
<%@ page import="com.chen.pojo.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>スーパーマーケット管理システム</title>
    <link type="text/css" rel="stylesheet" href="../css/style.css" />
    <link type="text/css" rel="stylesheet" href="../css/public.css" />
</head>
<body>


<!--头部-->
    <header class="publicHeader">
        <h1>スーパーマーケット管理システム</h1>
        <div class="publicHeaderR">
            <p><span id="hello">こんにちは！</span><span style="color: #fff21b"> </span></p>
            <a href="../jsp/logout.do">logout</a>
        </div>
    </header>
<!--时间-->
    <section class="publicTime">
        <span id="time" ></span>
        <a href="#">ヒント：正常に閲覧できるように、高バージョンのブラウザを使用してください！（Googleブラウザ推奨）</a>
    </section>
 <!--主体内容-->
 <section class="publicMian ">
     <div class="left">
         <h2 class="leftH2"><span class="span1"></span>メニュー<span></span></h2>
         <nav>
             <ul class="list">
              <li ><a href="${pageContext.request.contextPath}/jsp/bill.do?method=query">注文管理</a></li>
              <li><a href="${pageContext.request.contextPath}/jsp/provider.do?method=query">サプライヤ管理</a></li>
              <li><a href="${pageContext.request.contextPath}/jsp/user.do?method=query">ユーザ管理</a></li>
              <li><a href="${pageContext.request.contextPath}/jsp/emp.do?method=query">社員管理</a></li>
              <li><a href="${pageContext.request.contextPath}/jsp/pwdmodify.jsp">パスワード変更</a></li>
              <li><a href="${pageContext.request.contextPath}/jsp/logout.do">システム終了</a></li>
             </ul>
         </nav>
     </div>
<%-- </section>这是主体的一部分 另外的section结束标签在 其他主体的结束位置--%>
<input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
     <input type="hidden" id="referer" name="referer" value="<%=request.getHeader("Referer")%>"/>
</body>
</html>