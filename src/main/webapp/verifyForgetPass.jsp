<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 12/9/2023
  Time: 7:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ page isELIgnored="false" %>
<head>
    <meta charset="UTF-8">
    <title>Xác thực email</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/verifyEmail.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/fontawesome/css/all.min.css">
</head>
<body>
    <div class="container">
        <form action="verifyForgetPass" class="wrapper">
            <h3>Xác thực email</h3>
            <p>Chúng tôi vừa gửi đến email ${sessionScope.email} một mã xác thực
                <br>Hãy kiểm tra email và điền vào bên dưới để xác thực email của bạn</p>
            <div class="code-container">
                <input class="code" type="number" min="0" max="9" required name="code">
                <input class="code" type="number" min="0" max="9" required name="code">
                <input class="code" type="number" min="0" max="9" required name="code">
                <input class="code" type="number" min="0" max="9" required name="code">
                <input class="code" type="number" min="0" max="9" required name="code">
                <input class="code" type="number" min="0" max="9" required name="code">
            </div>
            <input type="hidden" value="${timeStart}" name="timeStart">
            <button class="btn">Xác thực</button>
            <div class="resend">
                <p>Nếu bạn không nhận được mã.
                    <a href="doResend">Gửi lại</a>
                </p>
            </div>
        </form>
        <div class="popup ${type != null ? type : "none"}">
            <c:if test="${type.equals(\"success\")}">
                <i class="fa-solid fa-check icon"></i>
            </c:if>
            <c:if test="${type.equals(\"error\")}">
                <i class="fa-solid fa-ban fa-flip-horizontal icon"></i>
            </c:if>
            <c:if test="${type.equals(\"alert\")}">
                <i class="fa-solid fa-triangle-exclamation icon"></i>
            </c:if>
            <p>${information}</p>
            <i class="fa-solid fa-xmark del"></i>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/assets/js/verifyEmail.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/popupNotice.js"></script>
</body>
</html>
