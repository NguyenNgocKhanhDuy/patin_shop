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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/payment.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/fontawesome/css/all.min.css">
    <title>Thanh toán</title>
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
                <a href="#">Thanh toán</a>
            </p>
        </div>
    </div>

    <div id="content">
        <div class="container">
            <h3 class="big-title">Thông tin</h3>
            <form action="addBill" method="post" class="wrapper">
                <div class="infomation">
                    <div>
                        <div class="holder">
                            <label>Họ tên</label>
                            <input type="text" name="fullName" value="${user.getFullName()}">
                        </div>
                        <div class="holder">
                            <label>Địa chỉ</label>
                            <input type="text" name="address" value="${user.getAddress()}">
                        </div>
                        <div class="holder-phone-email">
                            <div class="holder">
                                <label>Email</label>
                                <input type="email" name="email" value="${user.getEmail()}">
                            </div>
                            <div class="holder">
                                <label>Số điện thoại</label>
                                <input type="tel" name="phone" value="${user.getPhone()}">
                            </div>
                        </div>
                        <div class="holder">
                            <label>Ghi chú đơn hàng</label>
                            <textarea name="note"></textarea>
                        </div>
                    </div>
                </div>
                <div class="order">
                    <h3>Đơn hàng</h3>
                    <div class="order-product">
                        <div class="title seperate">
                            <h4>Sản phẩm</h4>
                            <h4 class="right">Giá</h4>
                        </div>
                        <div class="product">
                            <ul>
                                <li class="item">
                                    <ul>
                                        <c:set var="totalPrice" value="${totalPrice != null ? totalPrice : 0}"/>
                                        <c:forEach begin="0" end="${data.size()-1}" varStatus="index">
                                            <c:if test="${data.size() > 1 && index.index > 0}">
                                                <li class="seperate line"></li>
                                            </c:if>
                                            <li class="seperate">
                                                <c:set var="product" value="${data.get(keys.get(index.index)).getProduct()}"/>
                                                <c:set var="totalPrice" value="${totalPrice + (product.getProductDetail().getPrice() * data.get(keys.get(index.index)).getQuantity())}"/>
                                                <p class="infoItem">
                                                    ${index.index + 1}.
                                                    ${product.getProductDetail().getProduct().getName()}
                                                    <br>- Màu sắc:
                                                    <span class="color">${product.getProductDetail().getColor().getName()}</span>
                                                    <br>- Size:
                                                    <span class="size">${product.getProductDetail().getSize().getName()}</span>
                                                    <br>- Số lượng:
                                                    <span class="quantity">${data.get(keys.get(index.index)).getQuantity()}</span>
                                                </p>
                                                <p class="price right">
                                                    <fmt:formatNumber type="currency" value="${product.getProductDetail().getPrice() * data.get(keys.get(index.index)).getQuantity()}"/>
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
                    <div class="payment">
                        <h4>Phương thức thanh toán</h4>
                        <div class="payment-item">
                            <input type="radio" class="show" name="payment" id="cash" checked="true" value="cash">
                            <p>Trả tiền mặt khi nhận hàng</p>
                            <input type="radio" class="hide" id="cashHide">
                        </div>
                        <div class="payment-item">
                            <input type="radio" class="show" name="payment" id="momo" value="MoMo">
                            <p class="hasImg">Quét mã MoMo</p>
                            <img src="assets/images/MoMo.png" alt="">
                            <input type="radio" class="hide" id="momoHide">
                        </div>
                        <div class="payment-item">
                            <input type="radio" class="show" name="payment" id="zalopay" value="ZaloPay">
                            <p class="hasImg">Quét mã ZaloPay</p>
                            <img src="assets/images/ZaloPay.png" alt="">
                            <input type="radio" class="hide" id="zalopayHide">
                        </div>
                        <div class="payment-item">
                            <input type="radio" class="show" name="payment" id="bank" value="Bank">
                            <p>Chuyển khoản ngân hàng</p>
                            <input type="radio" class="hide" id="bankHide">
                        </div>
                        <div class="checkout">
                            <input type="submit" class="checkoutBtn" value="Xác nhận">
                        </div>
                    </div>
                </div>
            </form>
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
    <script src="${pageContext.request.contextPath}/assets/js/payment.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/popupNotice.js"></script>
</body>
</html>