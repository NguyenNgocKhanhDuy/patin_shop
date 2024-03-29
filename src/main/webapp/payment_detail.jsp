<%@ page import="java.text.NumberFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/payment_detail.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/fontawesome/css/all.min.css">
    <title>Thanh Toán</title>
    <fmt:setLocale value="vi_VN"/>
</head>
<body>
    <header>
        <div class="container">
            <img src="${pageContext.request.contextPath}/assets/images/logo.PNG" alt="" class="logo">
            <nav>
                <ul class="menu">
                    <li><a href="home">TRANG CHỦ</a></li>
                    <li>
                        <a href="listProduct">SẢN PHẨM</a>
                        <ul class="sub_menu list-category">

                        </ul>
                    </li>
                    <li><a href=contact.jsp>LIÊN HỆ</a></li>
                </ul>
            </nav>
            <div class="user">
                <ul>
                    <c:if test="${sessionScope.auth != null}">
                        <li>
                            <a href="account.jsp">
                                    ${sessionScope.auth.getFullName()}
                            </a>
                            <ul class="sub_menu user_sub">
                                <li>
                                    <a href="account.jsp">Tài khoản</a>
                                </li>
                                <c:if test="${sessionScope.auth.getRole() > 0}">
                                    <li>
                                        <a href="showUserAdmin">Quản lý</a>
                                    </li>
                                </c:if>
                                <li>
                                    <a href="logout">Đăng xuất</a>
                                </li>
                            </ul>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.auth == null}">
                        <li>
                            <a href="login.jsp">
                                ĐĂNG NHẬP
                            </a>
                        </li>
                        <li>
                            <a href="register.jsp">
                                ĐĂNG KÝ
                            </a>
                        </li>
                    </c:if>

                    <li><a href="showWishList"><i class="fa-solid fa-heart"></i></a></li>
                    <li class="cartLink">
                        <a href="showCart"><i class="fa-solid fa-cart-shopping"></i></a>
                        <c:if test="${sessionScope.cart != null && sessionScope.cart.getData().size() > 0}">
                            <span class="amount">${sessionScope.cart.getData().size()}</span>
                        </c:if>
                    </li>
                </ul>
            </div>
        </div>
    </header>

    <div id="category-search">
        <div class="container">

            <div class="category-title" id="categoryAll">
                <div class="category">
                    <span>Danh Mục</span>
                </div>
                <ul id="list-cate" class="list list-category hideCategory">

                </ul>
            </div>
            <div class="search">
                <input type="text" placeholder="Nhập vào sản phẩm" id="search" name="search">
                <button id="searchBtn">Tìm Kiếm</button>
                <ul>

                </ul>
            </div>
        </div>
    </div>

    <div id="image">
        <div class="container-img">
            <img src="${pageContext.request.contextPath}/assets/images/patin.jpg" alt="">
        </div>
    </div>

    <div id="content">
        <div class="container">
            <div class="pay">
                <c:set var="totalPrice" value="${totalPrice != null ? totalPrice : 0}"/>

                <c:forEach var="b" items="${bills}">
                        <c:set var="totalPrice" value="${totalPrice + (b.getPrice() * b.getQuantity())}"/>
                </c:forEach>

                <c:if test="${!bill.getPayment().equals(\"Trả tiền khi nhận\")}">
                    <div class="pay-show">
                        <h3 class="titleMethod">Quét mã để thanh toán</h3>
                        <c:if test="${bill.getPayment().equals(\"Bank\")}">
                            <img class="app large" src="assets/images/${bill.getPayment()}.png" alt="">
                        </c:if>

                        <c:if test="${!bill.getPayment().equals(\"Bank\")}">
                            <img class="app" src="assets/images/${bill.getPayment()}.png" alt="">
                        </c:if>
                        <div class="payment-info">
                            <p>Người nhận:
                                <span class="info">${user.getFullName()}</span>
                            </p>
                            <p class="titleChange">Số điện thoại:
                                <span class="info number">${user.getPhone()}</span>
                            </p>
                            <p>Số tiền:
                                <span class="price">
                                    <fmt:formatNumber type="currency" value="${totalPrice}"/>
                                </span>
                            </p>
                            <p>Ghi chú chuyển tiền bạn ghi mã đơn hàng:
                                <span class="orderId">${bill.getName()}</span>
                            </p>
                        </div>
                        <img class="qrcode" src="assets/images/qr.jpg" alt="">
                        <div class="sub">
                            <img src="assets/images/scan.png" alt="">
                            <p>Sử dụng app
                                <span class="appName">${bill.getPayment()}</span>
                                để quét mã
                            </p>
                        </div>
                        <p class="sub-note">Đơn hàng sẽ xử lý tự động trong 5 - 10 phút</p>
                    </div>
                </c:if>

                <h3 class="title">Chi tiết đơn hàng</h3>
                <div class="order-product">
                    <div class="title seperate">
                        <h4>Sản phẩm</h4>
                        <h4 class="right">Giá</h4>
                    </div>
                    <div class="product">
                        <ul>
                            <li class="item">
                                <ul>
                                    <c:forEach var="b" items="${bills}" varStatus="index">
                                        <c:if test="${bills.size() > 1 && index.index > 0}">
                                            <li class="seperate line"></li>
                                        </c:if>
                                        <li class="seperate">
                                            <c:set var="product" value="${b.getProduct()}"/>
                                            <p class="infoItem">
                                                    ${index.index + 1}.
                                                    ${product.getProductDetail().getProduct().getName()}
                                                <br>- Màu sắc:
                                                <span class="color">${b.getColor().getName()}</span>
                                                <br>- Size:
                                                <span class="size">${b.getSize().getName()}</span>
                                                <br>- Số lượng:
                                                <span class="quantity">${b.getQuantity()}</span>
                                            </p>
                                            <p class="price right">
                                                <fmt:formatNumber type="currency" value="${b.getPrice() * b.getQuantity()}"/>
                                            </p>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <div class="sub-total seperate">
                        <p>Tạm tính</p>
                        <p class="sub-total-price right">
                            <fmt:formatNumber type="currency" value="${totalPrice}"/>
                        </p>
                    </div>
                    <div class="ship seperate">
                        <p>Giao hàng</p>
                        <p class="ship-price right">Miễn phí</p>
                    </div>
                    <div class="total seperate">
                        <p>Tổng cộng</p>
                        <p class="total-price right">
                            <fmt:formatNumber type="currency" value="${totalPrice}"/>
                        </p>
                    </div>
                </div>
                <h3 class="title">Địa chỉ giao hàng</h3>
                <p class="address">${user.getAddress()}</p>
            </div>
            <div class="bill">
                <div class="bill-detail">
                    <p>Cảm ơn bạn. Đơn hàng của bạn đã được nhận.</p>
                    <ul>
                        <li>Mã đơn hàng:
                            <span class="orderId">${bill.getName()}</span>
                        </li>
                        <li>Ngày:
                            <span class="date">
                                <fmt:parseDate value="${bill.getDate()}" pattern="y-M-dd'T'H:m" var="myParseDate"/>
                                <fmt:formatDate value="${myParseDate}"  pattern="yyyy-MM-dd HH:mm"/>
                            </span>
                        </li>
                        <li>Số điện thoại:
                            <span class="phone">${user.getPhone()}</span>
                        </li>
                        <li>Tổng cộng:
                            <span class="price">
                                <fmt:formatNumber type="currency" value="${totalPrice}"/>
                            </span>
                        </li>
                        <c:if test="${!bill.getPayment().equals(\"Trả tiền khi nhận\")}">
                            <li>Phương thức thanh toán:
                                <span class="paymentMethod">${bill.getPayment()}</span>
                            </li>
                        </c:if>
                        <c:if test="${bill.getPayment().equals(\"Trả tiền khi nhận\")}">
                            <li>Phương thức thanh toán:
                                <span class="paymentMethod">Trả tiền khi nhận hàng</span>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
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

    <footer>
        <div class="container">
            <div class="info">
                <img src="${pageContext.request.contextPath}/assets/images/logo.PNG" alt="">
                <p>
                    Địa chỉ: Trường Đại học Nông Lâm Thành Phố Hồ Chí Minh
                </p>
                <p>
                    Số điện thoại:
                    <a href="tel:+">0839151003</a>
                </p>

                <p>
                    Email:
                    <a href="mailto:">21130035@st.hcmuaf.edu.vn</a>
                </p>
            </div>
            <div class="subscribe">
                <div class="social-media">
                    <ul>
                        <li><a href="https://www.facebook.com/"><i class="fa-brands fa-facebook-f"></i></a></li>
                        <li><a href="https://www.instagram.com/"><i class="fa-brands fa-instagram"></i></a></li>
                        <li><a href="https://twitter.com/"><i class="fa-brands fa-twitter"></i></a></li>
                        <li><a href="https://www.pinterest.com/"><i class="fa-brands fa-pinterest"></i></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </footer>

    <script src="${pageContext.request.contextPath}/assets/js/showDanhMuc.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/search.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/category.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/popupNotice.js"></script>
</body>
</html>