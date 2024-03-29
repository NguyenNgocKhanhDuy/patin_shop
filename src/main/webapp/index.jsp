
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/home.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/fontawesome/css/all.min.css">
    <title>Trang Chủ</title>
    <fmt:setLocale value="vi_VN"/>
</head>
<body>
    <header>
        <div class="container">
            <img src="${pageContext.request.contextPath}/assets/images/logo.PNG" alt="" class="logo">
            <nav>
                <ul class="menu">
                    <li><a href="#">TRANG CHỦ</a></li>
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
                <a href="#">Trang chủ</a>
            </p>
        </div>
    </div>


    <div id="content">
        <div class="container">
            <div class="top">
                <h3>Sản phẩm bán chạy</h3>
                <div class="wrapper">
                    <div class="carousel" id="carousel">
                        <c:forEach items="${hotProducts}" var="product">
                            <div class="product-item">
                                <a href="productDetail?productID=${product.getProductDetail().getProduct().getId()}">
                                    <img src= ${product.getImg()} >
                                    <h4 class="name"> ${product.getProductDetail().getProduct().getName()} </h4>
                                    <span class="price-section">
                                        <c:if test="${product.getMinPrice() == product.getMaxPrice()}">
                                            <c:if test="${product.getProductDetail().getProduct().getSalePercent() == 0}">
                                                <h5 class="price">
                                                    <fmt:formatNumber value="${product.getMinPrice()}" type="currency"/>
                                                </h5>
                                            </c:if>
                                            <c:if test="${product.getProductDetail().getProduct().getSalePercent() != 0}">
                                                 <h5 class="price">
                                                    <fmt:formatNumber value="${product.getMinPrice()}" type="currency"/>
                                                 </h5>
                                                <h5 class="origin">
                                                    <fmt:formatNumber value="${product.getMinPrice() / (1 - product.getProductDetail().getProduct().getSalePercent())}" type="currency"/>
                                                </h5>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${product.getMinPrice() != product.getMaxPrice()}">
                                        <h5 class="price">
                                            <fmt:formatNumber value="${product.getMinPrice()}" type="currency"/>
                                        </h5>
                                            -
                                            <h5 class="price">
                                            <fmt:formatNumber value="${product.getMaxPrice()}" type="currency"/>
                                        </h5>
                                        </c:if>
                                    </span>

                                    <ul>
                                        <li>
                                            <a href="showWishList">
                                                <i class="fa-solid fa-heart like"></i>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="showCart">
                                                <i class="fa-solid fa-cart-shopping cart"></i>
                                            </a>
                                        </li>
                                    </ul>
                                </a>
                                <c:if test="${product.getProductDetail().getProduct().getSalePercent() != 0}">
                                    <div class="sale">
                                        <p>
                                            <fmt:formatNumber value="${product.getProductDetail().getProduct().getSalePercent()}" type="percent"/>
                                        </p>
                                        <img class="tag" src="${pageContext.request.contextPath}/assets/images/tag.png" alt="">
                                    </div>
                                </c:if>
                            </div>
                        </c:forEach>
                    </div>
                    <i class="fa-regular fa-circle-right selection" id="next"></i>
                    <i class="fa-regular fa-circle-left selection" id="previous"></i>
                </div>
            </div>

            <h3>Sản phẩm khác</h3>
            <div class="product-list" id="product-list">
                <c:forEach items="${products}" var="product">
                    <div class="product-item">
                        <a href="productDetail?productID=${product.getProductDetail().getProduct().getId()}">
                            <img src= ${product.getImg()} >
                            <h4 class="name"> ${product.getProductDetail().getProduct().getName()} </h4>
                            <span class="price-section">
                                <c:if test="${product.getMinPrice() == product.getMaxPrice()}">
                                    <c:if test="${product.getProductDetail().getProduct().getSalePercent() == 0}">
                                        <h5 class="price">
                                            <fmt:formatNumber value="${product.getMinPrice()}" type="currency"/>
                                        </h5>
                                    </c:if>
                                    <c:if test="${product.getProductDetail().getProduct().getSalePercent() != 0}">
                                        <h5 class="price">
                                            <fmt:formatNumber value="${product.getMinPrice()}" type="currency"/>
                                        </h5>
                                        <h5 class="origin">
                                            <fmt:formatNumber value="${product.getMinPrice() / (1 - product.getProductDetail().getProduct().getSalePercent())}" type="currency"/>
                                        </h5>
                                    </c:if>
                                </c:if>
                                <c:if test="${product.getMinPrice() != product.getMaxPrice()}">
                                    <h5 class="price">
                                        <fmt:formatNumber value="${product.getMinPrice()}" type="currency"/>
                                    </h5>
                                    -
                                    <h5 class="price">
                                        <fmt:formatNumber value="${product.getMaxPrice()}" type="currency"/>
                                    </h5>
                                </c:if>
                            </span>
                            <ul>
                                <li>
                                    <a href="showWishList">
                                        <i class="fa-solid fa-heart like"></i>
                                    </a>
                                </li>
                                <li>
                                    <a href="showCart">
                                        <i class="fa-solid fa-cart-shopping cart"></i>
                                    </a>
                                </li>
                            </ul>
                        </a>
                        <c:if test="${product.getProductDetail().getProduct().getSalePercent() != 0}">
                            <div class="sale">
                                <p>
                                    <fmt:formatNumber value="${product.getProductDetail().getProduct().getSalePercent()}" type="percent"/>
                                </p>
                                <img class="tag" src="${pageContext.request.contextPath}/assets/images/tag.png" alt="">
                            </div>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="intro">
            <div class="box">
                <div class="text">
                    <h4>Lợi ích trượt patin</h4>
                    <p>Patin – Roller Sports là môn thể thao mang lại nhiều lợi ích sức khỏe thể chất và tinh thần dành cho tất cả mọi người ở mọi lứa tuổi. Giúp cho bạn có 1 thể lực bền bỉ hơn & 1 tinh thần luôn minh mẫn, sảng khoái cùng những mối quan hệ bạn bè mới.</p>
                    <a href="listProduct" class="shop">Xem sản phẩm</a>
                </div>
                <img src="https://patin.cdn.vccloud.vn/wp-content/uploads/2022/12/hinh-anh-giay-flying-eagle-drift-carbon1-min-e1669867679773-1024x688.jpg" alt="">
            </div>
            <ul>
                <li>
                    <span>1.</span>
                    <p><span>Cơ bắp.</span><br>
                        Độ bền & sức mạnh sẽ được cải thiện tốt hơn.</p>
                </li>
                <li>
                    <span>2.</span>
                    <p><span>Phản xạ & thăng bằng.</span><br>
                        Ngày càng nhanh nhẹn & gia tăng khả năng giữ thăng bằng.</p>
                </li>
                <li>
                    <span>3.</span>
                    <p>
                        <span>Dáng thon - cường tráng.</span><br>
                        Tùy mục đích bài tập, mỗi giờ trượt có thể đốt 1060 calories.
                    </p>
                </li>
                <li>
                    <span>4.</span>
                    <p><span>Hệ tuần hoàn.</span><br>
                        Cải thiện và phát triển tốt hơn hệ tuần hoàn máu, hệ hô hấp.</p>
                </li>
            </ul>
        </div>
    </div>
    <div class="goTop">
        <a href="#">
            <i class="fa-solid fa-arrow-up"></i>
        </a>
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
    <script src="${pageContext.request.contextPath}/assets/js/home.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/preventClickLikeCart.js.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/goTop.js"></script>
</body>
</html>