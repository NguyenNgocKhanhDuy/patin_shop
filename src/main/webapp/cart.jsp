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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/cart.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/fontawesome/css/all.min.css">
    <title>Giỏ hàng</title>
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
            <p class="breadcrumb">
                <a href="#">Giỏ hàng</a>
            </p>
        </div>
    </div>

    <div id="content">
        <div class="container">
            <c:if test="${data.size() == 0}">
                <p>Bạn chưa thêm sản phẩm vào giỏ hàng</p>
            </c:if>
            <c:if test="${data.size() > 0}">
                <div class="title">
                    <h3>Sản phẩm</h3>
                    <h3>Kích cỡ</h3>
                    <h3>Màu sắc</h3>
                    <h3>Giá</h3>
                    <h3>Số lượng</h3>
                    <h3>Tổng cộng</h3>
                </div>
                <div class="product-list">
                    <c:set var="totalPrice" value="${totalPrice != null ? totalPrice : 0}"/>
                    <c:if test="${sessionScope.cart.getData().size() > 0 && sessionScope.cart != null}">
                        <c:forEach begin="0" end="${data.size()-1}" varStatus="index">
                            <c:set var="product" value="${data.get(keys.get(index.index)).getProduct()}"/>
                            <c:set var="totalPrice" value="${totalPrice + (product.getProductDetail().getPrice() * data.get(keys.get(index.index)).getQuantity())}"/>
                            <a href="productDetail?productID=${keys.get(index.index).getId()}" class="product-item">
                                <input type="hidden" class="id" value="${keys.get(index.index).getId()}"/>
                                <input type="hidden" class="size" value="${keys.get(index.index).getSize()}"/>
                                <input type="hidden" class="color" value="${keys.get(index.index).getColor()}"/>
                                <div class="img-name">
                                    <img src="${product.getImg()}" alt="">
                                    <p class="name">${product.getProductDetail().getProduct().getName()}</p>
                                </div>
                                <p class="size">${product.getProductDetail().getSize().getName()}</p>
                                <p class="color">${product.getProductDetail().getColor().getName()}</p>
                                <p class="price">
                                    <fmt:formatNumber type="currency" value="${product.getProductDetail().getPrice()}"/>
                                </p>
                                <div class="boxQuantity">
                                    <div class="quantity">
                                        <i class="fa-solid fa-minus minus"></i>
                                        <input type="number" value="${data.get(keys.get(index.index)).getQuantity()}">
                                        <i class="fa-solid fa-plus plus"></i>
                                    </div>
                                </div>
                                <p class="total">
                                    <fmt:formatNumber type="currency" value="${product.getProductDetail().getPrice() * data.get(keys.get(index.index)).getQuantity()}"/>
                                </p>
                                <i class="fa-solid fa-xmark del"></i>
                            </a>
                        </c:forEach>

                    </c:if>

                </div>
                <c:if test="${sessionScope.cart != null && sessionScope.cart.getData().size() > 0}">
                    <div class="pay_total">
                        <h3>Giỏ hàng</h3>
                        <div class="price-total">
                            <p>Tổng tiền</p>
                            <p class="totalValue">
                                <fmt:formatNumber type="currency" value="${totalPrice}"/>
                            </p>
                        </div>
                        <a href="payment" class="checkout">Thanh toán</a>
                    </div>
                </c:if>
            </c:if>
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
    <script src="${pageContext.request.contextPath}/assets/js/cart.js"></script>
</body>
</html>