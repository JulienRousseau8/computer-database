<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page isELIgnored="false"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
	<title>Computer Database</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- Bootstrap -->
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapStyle" />
<spring:url value="/resources/css/font-awesome.css" var="fontAweSomeStyle" />
<spring:url value="/resources/css/main.css" var="mainCss" />

<link href="${bootstrapStyle}" rel="stylesheet" media="screen">
<link href="${fontAweSomeStyle}" rel="stylesheet" media="screen">
<link href="${mainCss}" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="ListComputers"> Application - Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">	
			<div class="alert alert-danger">
				Error 500: An error has occured!
				<br/>
				<!-- stacktrace -->
			</div>
		</div>
	</section>
<spring:url value="/resources/js/jquery.min.js" var="jqueryMinJs" />
<spring:url value="/resources/js/bootstrap.min.js" var="bootsrapJs" />
<spring:url value="/resources/js/dashboard.js" var="dashboardJs" />
<spring:url value="/resources/js/FormulaireValidation.js" var="Form" />

<script src="${jqueryMinJs }"></script>
<script src="${bootsrapJs }"></script>
<script src="${dashboardJs }"></script>
<script src="${Form}"></script>

</body>
</html>