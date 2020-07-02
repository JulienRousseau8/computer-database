<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page isELIgnored="false"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<spring:url value="/resources/css/bootstrap.min.css"
	var="bootstrapStyle" />
<spring:url value="/resources/css/font-awesome.css"
	var="fontAweSomeStyle" />
<spring:url value="/resources/css/main.css" var="mainCss" />

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<link href="${bootstrapStyle}" rel="stylesheet" media="screen">
<link href="${fontAweSomeStyle}" rel="stylesheet" media="screen">
<link href="${mainCss}" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
				<script type="text/javascript">
			function googleTranslateElementInit() {
				new google.translate.TranslateElement({
					pageLanguage : 'en'
				}, 'google_translate_element');
			}
		</script>

		<script type="text/javascript"
			src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
		<script type="text/javascript"
			src="https://translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
			
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
					<form action="AddComputer/add" method="POST">
						<fieldset>
							<div class="form-group">
								<label for="name">Computer name</label> <input
									type="text" class="form-control" id="name"
									name="name" placeholder="Computer name" required>
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
<spring:url value="/resources/js/jquery.min.js" var="jqueryMinJs" />
<spring:url value="/resources/js/bootstrap.min.js" var="bootsrapJs" />
<spring:url value="/resources/js/dashboard.js" var="dashboardJs" />
<spring:url value="/resources/js/FormulaireValidation.js" var="Form" />

<script src="${jqueryMinJs }"></script>
<script src="${bootsrapJs }"></script>
<script src="${dashboardJs }"></script>
<script src="${Form}"></script>
</html>