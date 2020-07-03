<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page isELIgnored="false"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>

<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
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
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${nbComputers} Computers found</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="dashboard" method="GET"
						class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="dashboard/deleteComputer" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">


			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th><a
							href="dashboard?pageNum=0&pageTaille=${pageTaille}&orderBy=name&direction=${direction + 1}">Computer
								name</a></th>
						<th><a
							href="dashboard?pageNum=0&pageTaille=${pageTaille}&orderBy=introduced&direction=${direction + 1}">Introduced
								date</a></th>
						<!-- Table header for Discontinued Date -->
						<th><a
							href="dashboard?pageNum=0&pageTaille=${pageTaille}&orderBy=discontinued&direction=${direction + 1}">Discontinued
								date</a></th>
						<!-- Table header for Company -->
						<th><a
							href="dashboard?pageNum=0&pageTaille=${pageTaille}&orderBy=company&direction=${direction + 1}">Company
								Name</a></th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach var="computer" items="${computerList}">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id }"></td>
							<td><a href="EditComputer?computerId=${computer.id }"> <c:out
										value="${computer.name}" />
							</a></td>
							<td>${computer.introduced}</td>
							<td>${computer.discontinued}</td>
							<td>${computer.companyName}</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">

				<c:if test="${pageNum > 0}">
					<li><a
						href="dashboard?pageNum=${pageNum-1}&search=${search}&orderBy=${orderBy}&pageTaille=${pageTaille}&direction=${direction}"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a></li>
				</c:if>

				<c:forEach var="i" begin="1" end="5">
					<c:if test="${pageNum+i <= pageMax}">
						<li><a
							href="dashboard?pageNum=${pageNum+i}&search=${search}&orderBy=${orderBy}&pageTaille=${pageTaille}&direction=${direction}">
								<c:out value="${pageNum+i}"></c:out>
						</a></li>
					</c:if>
				</c:forEach>

				<c:if test="${pageNum < pageMax}">
					<li><a
						href="dashboard?pageNum=${pageNum+1}&search=${search}&orderBy=${orderBy}&pageTaille=${pageTaille}&direction=${direction}"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>

			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<a
					href="dashboard?pageNum=0&pageTaille=10&search=${search}&orderBy=${orderBy}&direction=${direction}"><button
						type="button" class="btn btn-default">10</button> </a> <a
					href="dashboard?pageNum=0&pageTaille=50&search=${search}&orderBy=${orderBy}&direction=${direction}"><button
						type="button" class="btn btn-default">50</button></a> <a
					href="dashboard?pageNum=0&pageTaille=100&search=${search}&orderBy=${orderBy}&direction=${direction}">
					<button type="button" class="btn btn-default">100</button>
				</a>
			</div>
		</div>
	</footer>

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