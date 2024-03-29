<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 12/1/2023
  Time: 4:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/product_detail.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/fontawesome/css/all.min.css">
    <title>Chi tiết sản phẩm</title>
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
            <input type="text" placeholder="Nhập vào sản phẩm" id="search">
            <button id="searchBtn">Tìm Kiếm</button>
            <ul>

            </ul>
        </div>
    </div>
</div>

<div id="content">
    <div class="container">
        <div class="product">
            <div class="left">
                <img id="img-main" src="${products.get(0).getImg()}" alt="">

                <div class="carousel" id="carousel">
                    <c:forEach var="img" items="${listImg}">
                        <c:if test="${img.getId() != 1}">
                            <img class="sub-img" src="${img.getUrl()}" alt="">
                        </c:if>
                    </c:forEach>
                </div>

            </div>
            <div class="right">
                <h2 id="name">${products.get(0).getProductDetail().getProduct().getName()}</h2>
                <div class="rating-star">
                    ${totalRate}
                    <i class="fa-solid fa-star"></i>
                    <span>(${ratings.size()} lượt đánh giá)</span>
                </div>
                <div class="price-section">
                    <p id="priceDetail">
                        <c:if test="${products.get(0).getProductDetail().getPrice() != products.get(products.size()-1).getProductDetail().getPrice()}">
                            <fmt:formatNumber value="${products.get(0).getProductDetail().getPrice()}" type="currency"/>
                            -
                            <fmt:formatNumber value="${products.get(products.size()-1).getProductDetail().getPrice()}"
                                              type="currency"/>
                        </c:if>

                        <c:if test="${products.get(0).getProductDetail().getPrice() == products.get(products.size()-1).getProductDetail().getPrice()}">
                            <fmt:formatNumber value="${products.get(0).getProductDetail().getPrice()}" type="currency"/>
                        </c:if>
                    </p>
                </div>
                <form action="cartController">
                    <input type="hidden" id="idProduct" name="productID" value="${productID}">
                    <div class="colors">
                        <span>Màu sắc</span>
                        <div class="container-checkColor">
                            <c:forEach items="${colors}" var="color">
                                <%--                                <c:forEach items="${products}" var="product">--%>
                                <c:if test="${color.getId() == 1}">
                                    <%--                                    <c:if test="${product.getProductDetail().getColor().getId() == 1}">--%>
                                    <div class="check-color">
                                        <input type="radio" value="white">
                                        <input type="hidden" value="${color.getId()}">
                                            <%--                                            <input type="hidden" value="${product.getProductDetail().getColor().getId()}">--%>
                                    </div>
                                </c:if>
                                <c:if test="${color.getId() == 2}">
                                    <%--                                    <c:if test="${product.getProductDetail().getColor().getId() == 2}">--%>
                                    <div class="check-color">
                                        <input type="radio" value="black">
                                        <input type="hidden" value="${color.getId()}">
                                            <%--                                            <input type="hidden" value="${product.getProductDetail().getColor().getId()}">--%>
                                    </div>
                                </c:if>
                                <c:if test="${color.getId() == 3}">
                                    <%--                                    <c:if test="${product.getProductDetail().getColor().getId() == 3}">--%>
                                    <div class="check-color">
                                        <input type="radio" value="pink">
                                        <input type="hidden" value="${color.getId()}">
                                            <%--                                            <input type="hidden" value="${product.getProductDetail().getColor().getId()}">--%>
                                    </div>
                                </c:if>
                                <%--                                    <c:if test="${product.getProductDetail().getColor().getId() == 4}">--%>
                                <c:if test="${color.getId() == 4}">
                                    <div class="check-color">
                                        <input type="radio" value="blue">
                                            <%--                                            <input type="hidden" value="${product.getProductDetail().getColor().getId()}">--%>
                                        <input type="hidden" value="${color.getId()}">
                                    </div>
                                </c:if>
                                <c:if test="${color.getId() == 5}">
                                    <%--                                    <c:if test="${product.getProductDetail().getColor().getId() == 5}">--%>
                                    <div class="check-color">
                                        <input type="radio" value="green">
                                        <input type="hidden" value="${color.getId()}">
                                            <%--                                            <input type="hidden" value="${product.getProductDetail().getColor().getId()}">--%>
                                    </div>
                                </c:if>
                                <c:if test="${color.getId() == 6}">
                                    <%--                                    <c:if test="${product.getProductDetail().getColor().getId() == 6}">--%>
                                    <div class="check-color">
                                        <input type="radio" value="yellow">
                                        <input type="hidden" value="${color.getId()}">
                                            <%--                                            <input type="hidden" value="${product.getProductDetail().getColor().getId()}">--%>
                                    </div>
                                </c:if>
                                <%--                                    <c:if test="${product.getProductDetail().getColor().getId() == 7}">--%>
                                <c:if test="${color.getId() == 7}">
                                    <div class="check-color">
                                        <input type="radio" value="brown">
                                        <input type="hidden" value="${color.getId()}">
                                            <%--                                            <input type="hidden" value="${product.getProductDetail().getColor().getId()}">--%>
                                    </div>
                                </c:if>
                                <%--                                    <c:if test="${product.getProductDetail().getColor().getId() == 8}">--%>
                                <c:if test="${color.getId() == 8}">
                                    <div class="check-color">
                                        <input type="radio" value="red">
                                            <%--                                            <input type="hidden" value="${product.getProductDetail().getColor().getId()}">--%>
                                        <input type="hidden" value="${color.getId()}">
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="sizes">
                        <span>Kích cỡ</span>
                        <div class="container-checkSize">
                            <c:forEach var="size" items="${sizes}">
                                <%--                                <c:forEach var="product" items="${products}">--%>
                                <div class="check-size">
                                        ${size.getName()}
                                    <input type="radio" name="size" value="${size.getId()}">
                                        <%--                                        ${product.getProductDetail().getSize().getName()}--%>
                                        <%--                                        <input type="radio" name="size" value="${product.getProductDetail().getSize().getId()}">--%>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="quantityStore">
                        <p id="quantityDetail">
                            ${totalQuantity}
                        </p>
                        sản phẩm có sẵn
                    </div>
                    <div class="deselect">
                        <span>Bỏ Chọn</span>
                    </div>
                    <div class="buy">
                        <div class="quantity">
                            <i id="minus" class="fa-solid fa-minus"></i>
                            <input type="number" value="1" name="quantity">
                            <i id="plus" class="fa-solid fa-plus"></i>
                        </div>
                        <div class="addTocart">
                            <input class="btnAdd" type="submit" value="Thêm vào giỏ hàng">
                        </div>
                        <a href="addWishList?productID=${productID}">
                                <span id="addToLike">
                                    <i class="fa-regular fa-heart"></i>
                                </span>
                        </a>
                    </div>
                </form>
                <div class="moreInfo">
                    <c:if test="${products.get(0).getProductDetail().getProduct().getSalePercent() != 0}">
                        <p>
                            <span class="title">Khuyến mãi:</span>
                            <span class="sub-title khuyenMai">
                                    <fmt:formatNumber type="percent"
                                                      value="${products.get(0).getProductDetail().getProduct().getSalePercent()}"/>
                                </span>
                        </p>
                    </c:if>
                    <p>
                        <span class="title">Tình trạng:</span>
                        <c:if test="${totalQuantity > 0}">
                            <span class="sub-title">Còn hàng</span>
                        </c:if>
                        <c:if test="${totalQuantity <= 0}">
                            <span class="sub-title">Hết hàng</span>
                        </c:if>
                    </p>
                    <p>
                        <span class="title">Giao hàng:</span>
                        <span class="sub-title">2 - 5 ngày</span>
                    </p>
                </div>
            </div>
        </div>
        <div class="info">
            <div class="navigation">
                <ul>

                    <c:if test="${hasImg == null}">
                        <li id="info"><a href="productDetail?productID=${productID}" class="activeNav">Thông tin</a></li>
                        <li id="review"><a href="showRatingProduct?productID=${productID}">Đánh giá</a></li>
                    </c:if>
                    <c:if test="${hasImg != null}">
                        <li id="info"><a href="productDetail?productID=${productID}">Thông tin</a></li>
                        <li id="review"><a class="activeNav" href="showRatingProduct?productID=${productID}">Đánh giá</a></li>
                    </c:if>
                </ul>
            </div>
            <c:if test="${hasImg == null}">
                <div class="info-text">
                    <h3 class="title">Thông tin sản phẩm</h3>
                    <p class="product-info">-
                            ${products.get(0).getProductDetail().getProduct().getInformation()}
                    </p>
                </div>
            </c:if>
            <c:if test="${hasImg != null}">
                <div class="review">
                    <div class="left">
                        <input type="hidden" id="sortRating" value="${sort != null ? sort : ""}">
                        <select class="sort">
                            <c:if test="${sort.equals(\"date\")}">
                                <option selected value="date">Mới nhất</option>
                                <option value="like">Nổi bật</option>
                            </c:if>
                            <c:if test="${sort.equals(\"like\")}">
                                <option value="date">Mới nhất</option>
                                <option selected value="like">Nổi bật</option>
                            </c:if>
                        </select>
                        <c:if test="${ratings.size() == 0}">
                            <p>Chưa có đánh giá nào</p>
                        </c:if>
                        <c:if test="${ratings.size() != 0}">
                            <div class="rate-list">
                                    <c:forEach var="rating" items="${ratingPerPage}" varStatus="index">
                                        <c:set var="hasImg" value="0"/>

                                        <c:forEach items="${listImgRating}" var="image">
                                            <c:if test="${image.getRating().getId() == rating.getId()}">
                                                <c:set var="hasImg" value="1"/>
                                            </c:if>
                                        </c:forEach>
                                        
<%--                                        <c:if test="${hasImg == 1 || (rating.getContent() != null && !rating.getContent().equals(\"\"))}">--%>
<%--                                        </c:if>--%>
                                        <c:if test="${index.index != 0 && index.index != 2}">
                                            <div class="rate-item boxBorder">
                                                <div class="user">
                                                    <img src="${rating.getUser().getAvatar()}" alt="">
                                                    <div class="side">
                                                        <h4 class="name">${rating.getUser().getFullName()}</h4>
                                                        <div class="rating-star rating-star-cmt">
                                                            <c:if test="${rating.getScore() < 5}">
                                                                <c:forEach begin="1" end="${rating.getScore()}">
                                                                    <i class="fa-solid fa-star starActive"></i>
                                                                </c:forEach>

                                                                <c:forEach begin="${rating.getScore()}" end="5">
                                                                    <i class="fa-solid fa-star"></i>
                                                                </c:forEach>
                                                            </c:if>
                                                            <c:if test="${rating.getScore() == 5}">
                                                                <c:forEach begin="1" end="${rating.getScore()}">
                                                                    <i class="fa-solid fa-star starActive"></i>
                                                                </c:forEach>
                                                            </c:if>
                                                        </div>
                                                        <div class="date">
                                                            <fmt:parseDate value="${rating.getDate()}" pattern="y-M-dd'T'H:m" var="myParseDate"/>
                                                            <fmt:formatDate value="${myParseDate}"  pattern="yyyy-MM-dd HH:mm"/>
                                                        </div>
                                                    </div>
                                                </div>

                                                <c:if test="${hasImg == 1}">
                                                    <div class="image">
                                                        <c:forEach items="${listImgRating}" var="image">
                                                            <c:if test="${image.getRating().getId() == rating.getId()}">
                                                                <img src="${image.getUrl()}" alt="">
                                                            </c:if>
                                                        </c:forEach>
                                                    </div>
                                                </c:if>
                                                <c:if test="${rating.getContent() != null && !rating.getContent().equals(\"\")}">
                                                    <div class="nameContent">
                                                        <p>${rating.getContent()}</p>
                                                    </div>
                                                </c:if>
                                                <span class="like">
                                                    <c:set var="sumLike" value="0"/>
                                                    <c:set var="isLike" value="0"/>
                                                    <c:forEach var="like" items="${likes}">
                                                        <c:if test="${like.getRating().getId() == rating.getId()}">
                                                            <c:if test="${like.getUser().getId() == sessionScope.auth.getId()}">
                                                                <c:set var="isLike" value="1"/>
                                                            </c:if>
                                                            <c:set var="sumLike" value="${sumLike + 1}"/>
                                                        </c:if>
                                                    </c:forEach>
                                                    <c:if test="${isLike == 1}">
                                                        <a href="likeRating?id=${rating.getId()}&productID=${productID}"></a>
                                                        <i class="fa-solid fa-thumbs-up isLike"></i>
                                                    </c:if>
                                                    <c:if test="${isLike != 1}">
                                                        <i class="fa-solid fa-thumbs-up"></i>
                                                    </c:if>
                                                    ${sumLike}
                                                </span>
                                            </div>
                                        </c:if>
                                        <c:if test="${index.index == 0 || index.index == 2}">
                                            <div class="rate-item">
                                                <div class="user">
                                                    <img src="${rating.getUser().getAvatar()}" alt="">
                                                    <div class="side">
                                                        <h4 class="name">${rating.getUser().getFullName()}</h4>
                                                        <div class="rating-star rating-star-cmt">
                                                            <c:if test="${rating.getScore() < 5}">
                                                                <c:forEach begin="1" end="${rating.getScore()}">
                                                                    <i class="fa-solid fa-star starActive"></i>
                                                                </c:forEach>

                                                                <c:forEach begin="${rating.getScore()}" end="5">
                                                                    <i class="fa-solid fa-star"></i>
                                                                </c:forEach>
                                                            </c:if>
                                                            <c:if test="${rating.getScore() == 5}">
                                                                <c:forEach begin="1" end="${rating.getScore()}">
                                                                    <i class="fa-solid fa-star starActive"></i>
                                                                </c:forEach>
                                                            </c:if>
                                                        </div>
                                                        <div class="date">
                                                            <fmt:parseDate value="${bill.getDate()}" pattern="y-M-dd'T'H:m" var="myParseDate"/>
                                                            <fmt:formatDate value="${myParseDate}"  pattern="yyyy-MM-dd HH:mm"/>
                                                        </div>
                                                    </div>
                                                </div>

                                                    <%--                                                <c:forEach items="${listImgRating}" var="image">--%>
                                                    <%--                                                    <c:if test="${image.getRating().getId() == rating.getId()}">--%>
                                                    <%--                                                        <c:set var="hasImg" value="1"/>--%>
                                                    <%--                                                    </c:if>--%>
                                                    <%--                                                </c:forEach>--%>

                                                <c:if test="${hasImg == 1}">
                                                    <div class="image">
                                                        <c:forEach items="${listImgRating}" var="image">
                                                            <c:if test="${image.getRating().getId() == rating.getId()}">
                                                                <img src="${image.getUrl()}" alt="">
                                                            </c:if>
                                                        </c:forEach>
                                                    </div>
                                                </c:if>
                                                <c:if test="${rating.getContent() != null && !rating.getContent().equals(\"\")}">
                                                    <div class="nameContent">
                                                        <p>${rating.getContent()}</p>
                                                    </div>
                                                </c:if>
                                                <span class="like">
                                                    <c:set var="sumLike" value="0"/>
                                                    <c:set var="isLike" value="0"/>
                                                    <c:forEach var="like" items="${likes}">
                                                        <c:if test="${like.getRating().getId() == rating.getId()}">
                                                            <c:if test="${like.getUser().getId() == sessionScope.auth.getId()}">
                                                                <c:set var="isLike" value="1"/>
                                                            </c:if>
                                                            <c:set var="sumLike" value="${sumLike + 1}"/>
                                                        </c:if>
                                                    </c:forEach>
                                                    <c:if test="${isLike == 1}">
                                                        <i class="fa-solid fa-thumbs-up isLike"></i>
                                                    </c:if>
                                                    <c:if test="${isLike != 1}">
                                                        <i class="fa-solid fa-thumbs-up"></i>
                                                    </c:if>
                                                    ${sumLike}
                                                </span>
                                            </div>
                                        </c:if>

                                    </c:forEach>
                                </div>
                            <div class="pagination">
                                <ul>
                                    <c:if test="${currentPage == 1}">
                                        <li id="previousPage" class="arrowPageLi"><a class="arrowPagea"><i
                                                class="fa-solid fa-angles-left"></i></a></li>
                                    </c:if>
                                    <c:if test="${currentPage != 1}">
                                        <li id="previousPage"><a class="arrowActive" href="${href}&currentPage=1"><i
                                                class="fa-solid fa-angles-left"></i></a></li>
                                    </c:if>

                                    <c:if test="${currentPage == 1}">
                                        <li id="previousPage" class="arrowPageLi"><a class="arrowPagea"><i
                                                class="fa-solid fa-chevron-left"></i></a></li>
                                    </c:if>
                                    <c:if test="${currentPage != 1}">
                                        <li id="previousPage"><a class="arrowActive" href="${href}&currentPage=${currentPage-1}"><i
                                                class="fa-solid fa-chevron-left"></i></a></li>
                                    </c:if>
                                    <ul id="number-page">
                                        <c:choose>
                                            <c:when test="${totalPage >= 3}">
                                                <c:if test="${currentPage == 1}">
                                                    <li class="numb"><a class="pageNumberActive">
                                                            ${currentPage}
                                                    </a></li>
                                                    <li class="numb"><a href="${href}&currentPage=${currentPage+1}">
                                                            ${currentPage + 1}
                                                    </a></li>
                                                    <li class="numb"><a href="${href}&currentPage=${currentPage+2}">
                                                            ${currentPage + 2}
                                                    </a></li>
                                                </c:if>
                                                <c:if test="${currentPage > 1 && currentPage < totalPage}">
                                                    <li class="numb"><a href="${href}&currentPage=${currentPage-1}">
                                                            ${currentPage - 1}
                                                    </a></li>
                                                    <li class="numb"><a class="pageNumberActive">
                                                            ${currentPage}
                                                    </a></li>
                                                    <li class="numb"><a href="${href}&currentPage=${currentPage+1}">
                                                            ${currentPage + 1}
                                                    </a></li>
                                                </c:if>
                                                <c:if test="${currentPage == totalPage}">
                                                    <li class="numb"><a href="${href}&currentPage=${currentPage-2}">
                                                            ${currentPage - 2}
                                                    </a></li>
                                                    <li class="numb"><a href="${href}&currentPage=${currentPage-1}">
                                                            ${currentPage - 1}
                                                    </a></li>
                                                    <li class="numb"><a class="pageNumberActive">
                                                            ${currentPage}
                                                    </a></li>
                                                </c:if>

                                            </c:when>
                                            <c:when test="${totalPage < 3}">
                                                <c:forEach begin="1" end="${totalPage}" varStatus="index">
                                                    <c:if test="${index.count == currentPage}">
                                                        <li class="numb"><a class="pageNumberActive">
                                                                ${index.count}
                                                        </a></li>
                                                    </c:if>
                                                    <c:if test="${index.count != currentPage}">
                                                        <li class="numb"><a href="${href}&currentPage=${index.count}">
                                                                ${index.count}
                                                        </a></li>
                                                    </c:if>
                                                </c:forEach>
                                            </c:when>
                                        </c:choose>

                                    </ul>

                                    <c:if test="${currentPage == totalPage}">
                                        <li id="nextPage" class="arrowPageLi"><a class="arrowPagea"><i
                                                class="fa-solid fa-chevron-right"></i></a></li>
                                    </c:if>
                                    <c:if test="${currentPage != totalPage}">
                                        <li id="nextPage"><a class="arrowActive" href="${href}&currentPage=${currentPage+1}"><i
                                                class="fa-solid fa-chevron-right"></i></a></li>
                                    </c:if>

                                    <c:if test="${currentPage == totalPage}">
                                        <li id="nextPage" class="arrowPageLi"><a class="arrowPagea"><i
                                                class="fa-solid fa-angles-right"></i></a></li>
                                    </c:if>
                                    <c:if test="${currentPage != totalPage}">
                                        <li id="nextPage"><a class="arrowActive" href="${href}&currentPage=${totalPage}"><i
                                                class="fa-solid fa-angles-right"></i></a></li>
                                    </c:if>
                                </ul>
                                <div  class="selectPage">
                                    <select>
                                        <c:forEach varStatus="index" begin="1" end="${totalPage}">
                                            <c:if test="${currentPage == index.index}">
                                                <option selected>
                                                        ${index.index}
                                                </option>
                                            </c:if>
                                            <c:if test="${currentPage != index.index}">
                                                <option>
                                                        ${index.index}
                                                </option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    trong ${totalPage} trang
                                </div>
                            </div>
                        </c:if>
                    </div>
                    <div class="right">
                        <form action="postRating" method="post" enctype="multipart/form-data">
                            <h3>Thêm đánh giá</h3>
                            <p>Đánh giá của bạn</p>
                            <div class="rating-star rate">
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-solid fa-star"></i>
                                <i class="fa-solid fa-star"></i>
                            </div>
                            <input type="hidden" id="ratePoint" name="rate" value="">
                            <input type="hidden" name="productID" value="${productID}">
                            <p>Nhận xét của bạn</p>
                            <textarea name="content">
                                ${content}
                            </textarea>
                            <div class="file">
                                <input type="file" name="file">
                            </div>
                            <input type="submit" class="send" value="Gửi đi">
                        </form>
                    </div>
                </div>
            </c:if>

        </div>
        <div class="other-product">
            <h3>Sản phẩm khác</h3>
            <div class="product-list" id="product-list">
                <c:forEach items="${orderProducts}" var="product">
                    <div class="product-item">
                        <a href="productDetail?productID=${product.getProductDetail().getProduct().getId()}">
                            <img src=${product.getImg()}>
                            <h4 class="name"> ${product.getProductDetail().getProduct().getName()} </h4>
                            <span class="price-section">
                                                    <c:if test="${product.getMinPrice() == product.getMaxPrice()}">
                                                        <c:if test="${product.getProductDetail().getProduct().getSalePercent() == 0}">
                                                            <h5 class="price">
                                                                <fmt:formatNumber value="${product.getMinPrice()}" type="currency"/>
                                                            </h5>
                                                        </c:if>
                                                        <c:if test="${product.getProductDetail().getProduct().getSalePercent() != 0}">
                                                            <h5 class="origin">
                                                                <fmt:formatNumber
                                                                        value="${product.getMinPrice() / (1 - product.getProductDetail().getProduct().getSalePercent())}"
                                                                        type="currency"/>
                                                            </h5>
                                                            <h5 class="price">
                                                                <fmt:formatNumber value="${product.getMinPrice()}" type="currency"/>
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
                                <li><i class="fa-solid fa-heart like"></i></li>
                                <li><i class="fa-solid fa-cart-shopping cart"></i></li>
                            </ul>
                        </a>
                        <c:if test="${product.getProductDetail().getProduct().getSalePercent() != 0}">
                            <div class="sale">
                                <p>
                                    <fmt:formatNumber value="${product.getProductDetail().getProduct().getSalePercent()}" type="percent"/>
                                </p>
                                <img class="tag" src="${pageContext.request.contextPath}/assets/images/tag.png"
                                     alt="">
                            </div>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="modal-img">
        <div class="modal-img-container">
            <i id="closeModalImg" class="fa-solid fa-x"></i>
            <i id="previousImg" class="fa-solid fa-left-long"></i>
            <i id="nextImg" class="fa-solid fa-right-long"></i>
            <img id="imageInModal" src="" alt="">
        </div>
    </div>
    <c:if test="${hasImg != null}">
        <div class="modal-img modalRateImg">
            <div class="modal-img-container">
                <i class="fa-solid fa-x del"></i>
                <img src="" alt="">
            </div>
        </div>
    </c:if>
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
                <a href="tel:+">+65 11.188.888</a>
            </p>

            <p>
                Email:
                <a href="mailto:">patin@gmail.com</a>
            </p>
        </div>
        <div class="subscribe">
            <p>Đăng ký để nhận tin tức về sản phẩm mới nhất</p>
            <div class="holder">
                <input type="email" id="email" placeholder="Nhập vào email của bạn ">
                <input type="submit" id="btn" value="Đăng Ký">
            </div>
            <div class="social-media">
                <ul>
                    <li><a href="#"><i class="fa-brands fa-facebook-f"></i></a></li>
                    <li><a href="#"><i class="fa-brands fa-instagram"></i></a></li>
                    <li><a href="#"><i class="fa-brands fa-twitter"></i></a></li>
                    <li><a href="#"><i class="fa-brands fa-pinterest"></i></a></li>
                </ul>
            </div>
        </div>
    </div>
</footer>

<script src="${pageContext.request.contextPath}/assets/js/showDanhMuc.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/category.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/search.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/product_detail.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/popupNotice.js"></script>
<c:if test="${hasImg != null}">
    <script src="${pageContext.request.contextPath}/assets/js/product_detailRating.js"></script>
</c:if>
</body>
</html>
