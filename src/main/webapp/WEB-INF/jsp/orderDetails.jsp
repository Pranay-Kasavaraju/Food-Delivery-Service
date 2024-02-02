<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div class="page-header">
    <h1>Need something to eat?</h1>
    <br>
    <ul class="nav nav-pills">
        <li><a href="${contextPath}/restaurants.htm">Restaurants</a></li>
        <li class="active"><a href="${contextPath}/my-orders.htm">My Orders</a></li>
        <li><a href="${contextPath}/customer-info.htm">My Information</a></li>
        <li><a href="${contextPath}/viewCart.htm">My Cart</a></li>
        <li><a href="${contextPath}/user/login.htm">Logout</a></li>
    </ul>
</div>


<%-- <c:set var="order" value="${sessionScope.currentOrder}" /> --%>
<h2>Order ID: ${sessionScope.currentOrder.orderId}</h2>
<c:forEach var="orderItem" items="${sessionScope.orderItems}">
    <div class="list-group">
        <div class="list-group-item">
            <h4 class="list-group-item-heading">
                Name : ${orderItem.item.name}&nbsp;&nbsp;&nbsp;
                Price : ${orderItem.item.price}&nbsp;&nbsp;&nbsp;
                Quantity: ${orderItem.quantity}
            </h4>
        </div>
    </div>
</c:forEach>
<div>
    Total Quantities: ${sessionScope.currentOrder.totalQuantity}&nbsp;&nbsp;&nbsp;
    Total Price: ${sessionScope.currentOrder.totalPrice}&nbsp;&nbsp;&nbsp;
    Order Status: ${sessionScope.currentOrder.orderStatus}
    <br/>
    <br/>
    <c:set var="status" value="Customer Received Food" />
    <c:set var="status1" value="Food under delivering" />
    <c:choose>
        <c:when test="${sessionScope.currentOrder.orderStatus eq status}">
            <h2>Order has been completed!</h2>
        </c:when>
        <c:when test="${sessionScope.currentOrder.orderStatus eq status1}">
            <a href="${contextPath}/updateOrder.htm?orderId=${sessionScope.currentOrder.orderId}">Received Food</a>
        </c:when>
        <c:otherwise>
            <h2>Order Processing!</h2>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>