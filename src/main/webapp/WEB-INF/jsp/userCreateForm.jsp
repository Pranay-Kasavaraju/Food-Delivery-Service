<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="../vendor/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="../dist/css/bootstrapValidator.css"/>

    <script type="text/javascript" src="../vendor/jquery/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="../vendor/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../dist/js/bootstrapValidator.js"></script>
<title>User Registration</title>
</head>
<body>
	<font color="red">${errorMessage}</font>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<div class="container">
		<form action="${contextPath}/user/create.htm" method="POST" class="form-horizontal">
			<div class="form-group">
                <label class="col-lg-3 control-label">User Email:</label>
                <div class="col-lg-5">
                    <input type="email" class="form-control" name="username" required />
                </div>
            </div>
            
            <div class="form-group">
			    <label class="col-lg-3 control-label">Password:</label>
			    <div class="col-lg-5">
			        <input type="password" class="form-control" name="password"
			            data-bv-message="The password is not valid"
			            required
			            data-bv-notempty-message="The password is required and cannot be empty"			
			            pattern="[a-zA-Z0-9]+"
			            data-bv-regexp-message="The username can only consist of alphabetical, number" />
			    </div>
			</div>
			
			<div class="form-group">
			    <label class="col-lg-3 control-label">Role:</label>
			    <div class="col-lg-5">
			        <select name="role">
			    		<option value="Customer">Customer</option>
						<option value="Restaurant">Restaurant</option>
			    	</select>
			    </div>
			</div>
			
			<div class="form-group">
			    <label class="col-lg-3 control-label">Name:</label>
			    <div class="col-lg-5">
			        <input type="text" class="form-control" name="name"
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
			        <input type="text" class="form-control" name="phoneNumber"
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
			        <input type="text" class="form-control" name="address"
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
					<input type="text" class="form-control" name="city"
						   data-bv-message="The city is not valid"
						   required
						   data-bv-notempty-message="The city is required and cannot be empty"
						   pattern="[a-zA-Z][a-zA-Z\s\']+"
						   data-bv-regexp-message="The city can only consist of alphabetical" />
				</div>
			</div>
			
			<div style="text-align:center">
				<div class="col-lg-3 control-label"><a href="${contextPath}/" class="btn btn-primary" >Cancel</a></div>
			    <input type="submit" value="Create Account" class="btn btn-primary" />
		    </div>
			
			
		</form>
	</div>
</body>
</html>