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
<meta charset="utf-8">
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
			<h1 id="homeTitle">${nbComputers} Computers found</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="AddComputer">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>
		<form id="deleteForm" action="#" method="POST">
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
						<th>Computer name</th>
						<th>Introduced date</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date</th>
						<!-- Table header for Company -->
						<th>Company Id</th>
						<th>Company Name</th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach var="computer" items="${computerList}">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="0"></td>
							<td><a href="EditComputer?computerId=${computer.id }"><c:out
										value="${computer.name}" /></a></td>
							<td>${computer.introduced}</td>
							<td>${computer.discontinued}</td>
							<td>${computer.companyId}</td>
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
					<li><a href="Dashboard?pageNum=${pageNum-1}"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a></li>
				</c:if>


				<c:forEach var="i" begin="1" end="5">
					<c:if test="${pageNum + i <= pageMax}">
						<li><a href="Dashboard?pageNum=${pageNum+i}"
							aria-label="Next"> <c:out value="${pageNum+i}"></c:out>
						</a></li>
					</c:if>
				</c:forEach>


				<c:if test="${pageNum < pageMax}">
					<li><a href="Dashboard?pageNum=${pageNum+1}" aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>

			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<button type="button" class="btn btn-default">
					<a href="Dashboard?pageTaille=10">10</a>
				</button>
				<button type="button" class="btn btn-default">
					<a href="Dashboard?pageTaille=50">50</a>
				</button>
				<button type="button" class="btn btn-default">
					<a href="Dashboard?pageTaille=100">100</a>
				</button>
			</div>
		</div>
	</footer>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>

</body>

</html>