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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/account.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/fontawesome/css/all.min.css">
    <title>Tài khoản</title>
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
                            <a href="#">
                                    ${sessionScope.auth.getFullName()}
                            </a>
                            <ul class="sub_menu user_sub">
                                <li>
                                    <a href="#">Tài khoản</a>
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

                    <li><a href="wishlist.jsp"><i class="fa-solid fa-heart"></i></a></li>
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
                <a href="#">Tài khoản</a>
            </p>
        </div>
    </div>

    <div id="content">
        <div class="container">
            <div class="left">
                <div class="account">
                    <ul class="menu">
                        <c:if test="${bills == null && pass == null}">
                            <li class="activeAccountNav">
                                <i class="fa-regular fa-user"></i>
                                <a class="activeAccountNav" href="account.jsp">Tài khoản của tôi</a>
                            </li>
                            <li>
                                <i class="fa-regular fa-clipboard"></i>
                                <a href="orderAccount">Đơn hàng</a>
                            </li>
                            <li>
                                <i class="fa-regular fa-bell"></i>
                                <a href="showChangePassAccount">Đổi mật khẩu</a>
                            </li>
                        </c:if>
                        <c:if test="${bills != null}">
                            <li>
                                <i class="fa-regular fa-user"></i>
                                <a href="account.jsp">Tài khoản của tôi</a>
                            </li>
                            <li class="activeAccountNav">
                                <i class="fa-regular fa-clipboard"></i>
                                <a class="activeAccountNav" href="orderAccount">Đơn hàng</a>
                            </li>
                            <li>
                                <i class="fa-regular fa-bell"></i>
                                <a href="showChangePassAccount">Đổi mật khẩu</a>
                            </li>
                        </c:if>
                        <c:if test="${pass != null}">
                            <li>
                                <i class="fa-regular fa-user"></i>
                                <a href="account.jsp">Tài khoản của tôi</a>
                            </li>
                            <li>
                                <i class="fa-regular fa-clipboard"></i>
                                <a href="orderAccount">Đơn hàng</a>
                            </li>
                            <li class="activeAccountNav">
                                <i class="fa-regular fa-bell"></i>
                                <a class="activeAccountNav" href="showChangePassAccount">Đổi mật khẩu</a>
                            </li>
                        </c:if>

                    </ul>
                </div>
            </div>

            <div class="right">
                <c:if test="${pass == null && bills == null && billDetail == null}">
                    <div class="infomation">
                        <h3>Thông tin</h3>
                        <form action="userInformation" method="post" enctype="multipart/form-data" class="info">
                            <div class="wrapper">
                                <div class="avatar">
                                    <img src="${sessionScope.auth.getAvatar()}" alt="">
                                    <div class="selectImg">
                                        <input type="file" accept="image/*" name="file">
                                        <input type="submit" value="Lựa chọn ảnh">
                                    </div>
                                </div>

                                <div class="part name">
                                    <label>Tên:</label>
                                    <input type="text" name="fullname" value="${sessionScope.auth.getFullName()}">
                                </div>
                                <div class="part email">
                                    <label>Email: </label>
                                    <input type="email" value="${sessionScope.auth.getEmail()}" readonly="readonly">
                                </div>
                                <div class="part phone">
                                    <label>Số điện thoại: </label>
                                    <input type="tel" name="phone" value="${sessionScope.auth.getPhone()}">
                                </div>
                                <div class="part">
                                    <label>Địa chỉ: </label>
                                    <input type="tel" name="text" value="${sessionScope.auth.getAddress()}">
                                </div>
                                <div class="part gender">
                                    <label>Giới tính:</label>
                                    <div class="selectGender">
                                        <input type="radio" value="Nam" name="gender" checked = "true">
                                        <label>Nam</label>
                                        <input type="radio" value="Nữ" name="gender">
                                        <label>Nữ</label>
                                    </div>
                                </div>
                                <div class="part dob">
                                    <label>Ngày sinh:</label>
                                    <input type="hidden" id="DOB" value="${sessionScope.auth.getDob() != null ? sessionScope.auth.getDob() : 0}">
                                    <div class="dob-input">
                                        <select class="day" name="day">
                                        </select>
                                        <select class="month" name="month">
                                        </select>
                                        <select class="year" name="year">
                                        </select>
                                    </div>
                                </div>
                                <input type="submit" class="save" value="Lưu Thông Tin">
                            </div>
                        </form>
                    </div>
                </c:if>


                <c:if test="${bills != null}">
                    <div class="order">
                        <div class="nav">
                            <ul>
                                <c:if test="${billStatus == 0}">
                                    <li class="activeOrderNav">
                                        <a href="orderAccount?billStatus=0">Tất cả</a>
                                    </li>
                                    <li>
                                        <a href="orderAccount?billStatus=1">Vận chuyển</a>
                                    </li>
                                    <li>
                                        <a href="orderAccount?billStatus=2">Hoàn thành</a>
                                    </li>
                                </c:if>
                                <c:if test="${billStatus == 1}">
                                    <li>
                                        <a href="orderAccount?billStatus=0">Tất cả</a>
                                    </li>
                                    <li class="activeOrderNav">
                                        <a href="orderAccount?billStatus=1">Vận chuyển</a>
                                    </li>
                                    <li>
                                        <a href="orderAccount?billStatus=2">Hoàn thành</a>
                                    </li>
                                </c:if>
                                <c:if test="${billStatus == 2}">
                                    <li>
                                        <a href="orderAccount?billStatus=0">Tất cả</a>
                                    </li>
                                    <li>
                                        <a href="orderAccount?billStatus=1">Vận chuyển</a>
                                    </li>
                                    <li class="activeOrderNav">
                                        <a href="orderAccount?billStatus=2">Hoàn thành</a>
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                        <div class="all-order orderShow">
                            <c:if test="${bills.size() == 0}">
                                <p>Bạn chưa có đơn hàng</p>
                            </c:if>

                            <c:if test="${bills.size() > 0}">
                                <div class="bill section">
                                    <div class="bill-list">
                                        <div class="title">
                                            <h4>STT</h4>
                                            <h4>Mã đơn hàng</h4>
                                            <h4>Ngày đặt</h4>
                                            <h4>Tình trạng</h4>
                                            <h4>Phương thức</h4>
                                        </div>
                                        <c:forEach var="bill" items="${bills}" varStatus="index">
                                            <div class="bill-item">
                                                <p class="index">${(currentPage - 1) * productPerPage + index.index + 1}</p>
                                                <p class="id">${bill.getName()}</p>
                                                <p class="date">
                                                    <fmt:parseDate value="${bill.getDate()}" pattern="y-M-dd'T'H:m" var="myParseDate"/>
                                                    <fmt:formatDate value="${myParseDate}"  pattern="yyyy-MM-dd HH:mm"/>
                                                </p>

                                                <p class="state">${bill.getStatus()}</p>
                                                <p class="payment">${bill.getPayment()}</p>
                                                <a href="showOrderDetail?id=${bill.getId()}">
                                                    <i class="fa-solid fa-clipboard detail"></i>
                                                </a>
                                                    <%--                                            <i class="fa-solid fa-xmark del"></i>--%>
                                            </div>
                                        </c:forEach>

                                    </div>
                                </div>
                            </c:if>


                        </div>
                    </div>
                </c:if>

                <c:if test="${billDetail != null}">
                    <div class="bill_detail section">
                        <div class="general">
                            <p class="name">Người mua hàng: ${bill.getUser().getFullName()} </p>
                            <p class="phone">Số điện thoại: ${bill.getUser().getPhone()} </p>
                            <p class="email">Email: ${bill.getUser().getEmail()} </p>
                            <p class="address">Địa chỉ: ${bill.getUser().getAddress()} </p>
                            <p class="price">Giá: </p>
                            <p class="bill_info">
                                <span class="name">
                                    Đơn hàng: ${bill.getName()}
                                </span>
                                <span class="status">
                                    Tình trạng: ${bill.getStatus()}
                                </span>
                                <span class="payment">
                                    Phương thức thanh toán: ${bill.getPayment()}
                                </span>
                            </p>
                            <p class="date">Ngày đặt hàng:
                                <fmt:parseDate value="${bill.getDate()}" pattern="y-M-dd'T'H:m" var="myParseDate"/>
                                <fmt:formatDate value="${myParseDate}"  pattern="yyyy-MM-dd HH:mm"/>
                            </p>
                            <p class="more">
                                <span>Ghi chú: ${bill.getNote()}
                                </span>
<%--                                <span>--%>
<%--                                    <i class="fa-solid fa-clipboard detail"></i>--%>
<%--                                </span>--%>
                            </p>
                        </div>

                        <div class="bill-list">
                            <div class="title">
                                <h4>STT</h4>
                                <h4>Tên sản phẩm</h4>
                                <h4>Màu sắc</h4>
                                <h4>Size</h4>
                                <h4>Giá</h4>
                                <h4>Số lượng</h4>
                            </div>
                            <c:forEach var="b" items="${billDetail}" varStatus="index">
                                <div class="bill-item">
                                    <p class="index">${(currentPage - 1) * productPerPage + index.index + 1}</p>
                                    <p class="name">${b.getProduct().getProductDetail().getProduct().getName()}</p>
                                    <p class="color">${b.getColor().getName()}</p>
                                    <p class="size">${b.getSize().getName()}</p>
                                    <p class="price">
                                        <fmt:formatNumber value="${b.getPrice()}" type="currency"/>
                                    </p>
                                    <p class="quantỉty">${b.getQuantity()}</p>
<%--                                    <i class="fa-solid fa-clipboard detail"></i>--%>
<%--                                    <i class="fa-solid fa-xmark del"></i>--%>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:if>

                <c:if test="${pass != null}">
                    <div class="notice">
                        <h3>Thay đổi mật khẩu</h3>
                        <form action="changePass">
                            <div class="holder">
                                <label>Mật khẩu cũ</label>
                                <input type="password" name="oldPass">
                            </div>
                            <div class="holder">
                                <label>Mật khẩu mới</label>
                                <input type="password" name="newPass">
                            </div>
                            <div class="holder">
                                <label>Nhập lại mật khẩu mới</label>
                                <input type="password" name="confirmPass">
                            </div>
                            <input type="submit" value="Xác nhận">
                        </form>
                    </div>
                </c:if>

            </div>
        </div>
        <div class="modal">
            <div class="modal-container">
                <i class="fa-solid fa-x closeModal"></i>
                <h3 class="title">Thêm địa chỉ</h3>
                <form>
                    <input type="text" placeholder="Nhập địa chỉ của bạn">
                    <button class="modal-save">Thêm</button>
                </form>
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
    <script src="${pageContext.request.contextPath}/assets/js/account.js"></script>
</body>
</html>