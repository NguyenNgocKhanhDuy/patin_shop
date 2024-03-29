<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 12/8/2023
  Time: 3:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ page isELIgnored="false" %>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/newPass.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/fontawesome/css/all.min.css">
    <title>Mật khẩu</title>
</head>
<body>
    <div class="container">
        <form action="newPass" class="wrapper">
            <h3>Mật khẩu</h3>
            <div class="code-container">
                <div class="holder">
                    <label>Mật khẩu mới</label>
                    <input class="code" type="password" name="pass" value="${pass != null ? pass : ""}" placeholder="Mật khẩu">
                </div>
                <div class="holder">
                    <label>Nhập lại mật khẩu mới</label>
                    <input class="code" type="password" name="confirmPass" value="${confirmPass != null ? confirmPass : ""}" placeholder="Mật khẩu">
                </div>
            </div>
            <button type="submit" class="btn">Thay đổi</button>
        </form>
    </div>
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
    <script src="${pageContext.request.contextPath}/assets/js/popupNotice.js"></script>
</body>

</html>
