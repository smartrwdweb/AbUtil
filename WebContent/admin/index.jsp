<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" />
<meta http-equiv="Content-Type" content="UTF-8">
<title>T</title>
</head>
<body>

	<%@include file="common/header.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<%@include file="common/left.jsp"%>

			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
			<div
				class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
				<h1 class="h2">Dashboard</h1>
				<div class="btn-toolbar mb-2 mb-md-0">
					<div class="btn-group me-2">
						<button type="button" class="btn btn-sm btn-outline-secondary">Share</button>
						<button type="button" class="btn btn-sm btn-outline-secondary">Export</button>
					</div>
					<button type="button"
						class="btn btn-sm btn-outline-secondary dropdown-toggle">
						<span data-feather="calendar"></span> This week
					</button>
				</div>
			</div>


			<div class="card">
				<div class="card-header">Compare List</div>
				<div class="card-body">
					<h5 class="card-title">
					<div class="row">
					<div class="col-md-6">List1</div>
					<div class="col-md-6">List2</div>
					</div>
					</h5>
					<p class="card-text">
					<div class="row">
					<div class="col-md-6"><textarea rows="" cols="" class="form-control"></textarea></div>
					<div class="col-md-6"><textarea rows="" cols="" class="form-control"></textarea></div>
					</div>
						
					</p>
					<a href="#" class="btn btn-primary">Compare</a>
				</div>
			</div>
			</main>
		</div>
	</div>

</body>
</html>