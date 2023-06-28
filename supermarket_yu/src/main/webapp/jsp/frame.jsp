<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ page import="com.chen.util.Constants" %>
<%@ page import="com.chen.pojo.User" %>
<%@include file="/jsp/common/head.jsp"%>

<div class="right">
    <img class="wColck" src="../images/clock.jpg" alt=""/>
    <div class="wFont">
        <h2>${ userSession.userName }</h2>
        <p>スーパーマーケット管理システムへようこそ！</p>
    </div>
</div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
