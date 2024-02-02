<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Restaurant Information</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="res" value="${sessionScope.user}" />
<div class="page-header">
    <h1>Need something to eat?</h1>
    <br>
    <ul class="nav nav-pills">
        <li><a href="${contextPath}/admin/menu.htm">Menu</a></li>
        <li class="active"><a href="${contextPath}/admin/res-info.htm">Restaurant Information</a></li>
        <li><a href="${contextPath}/admin/received-orders.htm">Received Orders</a></li>
        <li><a href="${contextPath}/user/login.htm">Logout</a></li>
    </ul>
</div>


<div class="panel panel-default">
    <div class="panel-heading">
        Enter Restaurant Information
    </div>
    <div class="panel-body">
        <c:set var="contextPath" value="${pageContext.request.contextPath}" />
        <form action="${contextPath}/admin/res-info.htm" method="POST" class="form-horizontal">
            <div class="form-group">
                <label class="col-lg-3 control-label">Name:</label>
                <div class="col-lg-5">
                    <input type="text" class="form-control" name="name" value="${res.name}"
                           data-bv-message="The name is not valid"
                           required
                           data-bv-notempty-message="The name is required and cannot be empty"
                           pattern="[a-zA-Z][a-zA-Z\s\']+"
                           data-bv-regexp-message="The username can only consist of alphabetical" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-lg-3 control-label">Phone Number:</label>
                <div class="col-lg-5">
                    <input type="text" class="form-control" name="phoneNumber" value="${res.phoneNumber}"
                           data-bv-message="The phoneNumber is not valid"
                           required
                           data-bv-notempty-message="The phoneNumber is required and cannot be empty"
                           pattern="[1-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]"
                           data-bv-regexp-message="The phoneNumber should be a 10 digits" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-lg-3 control-label">Address:</label>
                <div class="col-lg-5">
                    <input type="text" class="form-control" name="address" value="${res.address}"
                           data-bv-message="The address is not valid"
                           required
                           data-bv-notempty-message="The address is required and cannot be empty"
                           pattern="[a-zA-Z0-9][a-zA-Z0-9\s\,]+"
                           data-bv-regexp-message="The address can only consist of alphabetical, number" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-lg-3 control-label">City:</label>
                <div class="col-lg-5">
                    <input type="text" class="form-control" name="city" value="${res.city}"
                           data-bv-message="The city is not valid"
                           required
                           data-bv-notempty-message="The city is required and cannot be empty"
                           pattern="[a-zA-Z][a-zA-Z\s\']+"
                           data-bv-regexp-message="The city can only consist of alphabetical" />
                </div>
            </div>

            <div style="text-align:center">
                <input type="submit" value="Update Information" class="btn btn-primary" />
            </div>
        </form>
    </div>
</div>
</body>
</html>