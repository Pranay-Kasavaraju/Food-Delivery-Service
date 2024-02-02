<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>StudentWorkArea</title>
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
        <li><a href="${contextPath}/my-orders.htm">My Orders</a></li>
        <li><a href="${contextPath}/customer-info.htm">My Information</a></li>
        <li><a href="${contextPath}/viewCart.htm">My Cart</a></li>
        <li><a href="${contextPath}/user/login.htm">Logout</a></li>
    </ul>
</div>

<c:set var="result" value="${sessionScope.result}" />

<c:if test="${result eq 'Order placed Successfully' }">
    <p>Order placed successfully!</p>
</c:if>


<c:if test="${result eq 'Added Successfully' }">
    <p>Items added to cart successfully!</p>
</c:if>


<c:if test="${result eq 'Removed Successfully' }">
    <p>Items removed from cart successfully!</p>
</c:if>


<c:if test="${result eq 'Updated Successfully' }">
    <p>Order updated successfully!</p>
</c:if>


<c:if test="${result eq 'Update Customer Information Successfully' }">
    <p>Update Customer Information Successfully!</p>
</c:if>

</body>
</html>