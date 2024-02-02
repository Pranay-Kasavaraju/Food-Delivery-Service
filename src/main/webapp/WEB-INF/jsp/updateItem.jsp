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
		  <li class="active"><a href="${contextPath}/admin/menu.htm">Menu</a></li>
		  <li><a href="${contextPath}/admin/res-info.htm">Restaurant Information</a></li>
		  <li><a href="${contextPath}/admin/received-orders.htm">Received Orders</a></li>
		  <li><a href="${contextPath}/user/login.htm">Logout</a></li>
		</ul>
	</div>


	<div class="panel panel-default">
		<div class="panel-heading">
			Enter Item Information
		</div>
		<div class="panel-body">
			<c:set var="contextPath" value="${pageContext.request.contextPath}" />
			<div class="container">
				<form action="${contextPath}/admin/updateItem.htm" method="POST" class="form-horizontal">
					<div class="form-group">
		                <label class="col-lg-3 control-label">Item Name:</label>
		                <div class="col-lg-5">
		                    <input type="text" class="form-control" name="name" value="${sessionScope.item.name}"
		                        data-bv-message="The name is not valid"
					            required
					            data-bv-notempty-message="The name is required and cannot be empty"	
		                        pattern="[a-zA-Z0-9][a-zA-Z0-9\s\']+"
		                        data-bv-regexp-message="The name should follow the valid type" />
		                </div>	                
		            </div>
		            
		            <div class="form-group">
		                <label class="col-lg-3 control-label">Price:</label>
		                <div class="col-lg-5">
		                    <input type="text" class="form-control" name="price" value="${sessionScope.item.price}"
		                        data-bv-message="The price is not valid"
					            required
					            data-bv-notempty-message="The price is required and cannot be empty"	
		                        pattern="[0-9]*\.?[0-9]*"
		                        data-bv-regexp-message="The price should follow the valid type" />
		                </div>	                
		            </div>

					<div class="form-group">
						<label class="col-lg-3 control-label">Item Cuisine:</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="cuisine" value="${sessionScope.item.cuisine}"
								   data-bv-message="The cuisine is not valid"
								   required
								   data-bv-notempty-message="The cuisine is required and cannot be empty"
								   pattern="[a-zA-Z][a-zA-Z\s\']+"
								   data-bv-regexp-message="The cuisine should follow the valid type" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">Item Type:</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="type" value="${sessionScope.item.type}"
								   data-bv-message="The type is not valid"
								   required
								   data-bv-notempty-message="The type is required and cannot be empty"
								   pattern="[a-zA-Z][a-zA-Z\s\']+"
								   data-bv-regexp-message="The type should follow the valid type" />
						</div>
					</div>
		            
		            <div style="text-align:center">
					    <input type="submit" value="Update Item" class="btn btn-primary" />
				    </div>
		            
		        </form>
		    </div>
		</div>
	</div>
</body>
</html>