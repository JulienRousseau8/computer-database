<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page isELIgnored="false"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"
	media="screen">
<link href="css/font-awesome.css" rel="stylesheet" type="text/css"
	media="screen">
<link href="css/main.css" rel="stylesheet" type="text/css"
	media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="Dashboard"> Application - Computer
				Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>Add Computer</h1>
					<form action="AddComputer" method="POST">
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" id="computerName"
									name="computerName" placeholder="Computer name" required>
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date (yyyy-MM-dd)</label> <input
									type="date" class="form-control" id="introduced"
									name="introduced" placeholder="Introduced date" required pattern="\d{1,2}/\d{1,2}/\d{4}">
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date (yyyy-MM-dd)</label> <input
									type="date" class="form-control" id="discontinued"
									name="discontinued" placeholder="Discontinued date" required pattern="\d{1,2}/\d{1,2}/\d{4}">
							</div>
							
							<div class="form-group">
								<label for="companyId">Company</label> 
								<select	class="form-control" id="companyId" name="companyId">
									<c:forEach items="${listCompanyDTO}" var="company">
										<option value="${company.id}">
											<c:out	value="${company.name}"></c:out>
										</option>
									</c:forEach>
								</select>
							</div>

						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Add" class="btn btn-primary">
							or <a href="Dashboard" class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/dashboard.js"></script>
	<script type="text/javascript" src="js/FormulaireValidation.js"></script>
</html>