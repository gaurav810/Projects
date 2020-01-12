<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns:th="http://thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Daily Report</title>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/jquery-ui.min.css">
<link rel="stylesheet" href="css/style.css">

<script src="js/jquery.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</head>
<body>
	<div class="container">
		<c:if test="${successMessage != null }">
			<div class="alert alert-success col-md-12" style="margin-top: 15px; margin-bottom:0px;">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<c:out value="${successMessage}"></c:out>
			</div>
		</c:if>
		<c:if test="${errorMessage != null }">
			<div class="alert alert-danger col-md-12" style="margin-top: 15px; margin-bottom:0px;">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<c:out value="${errorMessage}"></c:out>
			</div>
		</c:if>
	</div>
	
	<div class="container" style="margin-top:15px;">
		<c:choose>
			<c:when test="${userType eq 'user'}">
				<form:form method="post" modelAttribute="taskDetailsDTO"
					action="submit">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Task Details</h3>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-2">
									Name: <strong>${name}</strong>
								</div>
								<div class="col-md-2">
									Date: <strong>${date}</strong>
								</div>
							</div>
							<div class="row" style="margin-top:15px;">
								<div class="col-md-4">
									<div class="form-group">
										<label>Client Task</label> 
										<form:textarea path="clientTask" class="form-control" type="text" rows="3" required="true"/>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Learn New Things</label> 
										<form:textarea path="learnNewThings" class="form-control" type="text" rows="3" required="true"/>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Achievements</label> 
										<form:textarea path="achievement" class="form-control" type="text" rows="3" required="true"/>
									</div>
								</div>
							</div>
						</div>
						<div class="panel-footer text-right"><input type="submit" class="btn btn-success" value="Submit"></div>
					</div>
					
				</form:form>
				
				<form method="post" action="download-report" autocomplete="off">
					<div class="row">
						<div class="col-md-3">
							<h4>Task History</h4>
							<input type="hidden" value="${usreId}" name="userId">
						</div>						
						<div class="col-md-2 col-md-offset-3">
							<input type="text" class="form-control" name="fromDate" value="${dateFormmated}"
								id="fromDate" placeholder="From Date" />
						</div>
						<div class="col-md-2">
							<input type="text" class="form-control" name="toDate" id="toDate" value="${dateFormmated}"
								placeholder="To Date" />
						</div>
						<div class="col-md-2">
							<button class="btn btn-primary btn-block" type="submit">Generate
								Report</button>
						</div>
					</div>
				</form>
				
				<h4></h4>
				<table class="table table-bordered cutomTable">
					<tr class="tableHead">
						<th>Date</th>
						<th>Client Task</th>
						<th>Learn New Things</th>
						<th>Achievement</th>
					</tr>
					<c:forEach items="${tasks}" var="task">
						<tr>
							<td>${task.getTaskDateString()}</td>
							<td>${task.clientTask}</td>
							<td>${task.learnNewThings}</td>
							<td>${task.achievement}</td>
						</tr>
					</c:forEach>
				</table>
			</c:when>

			<c:when test="${userType eq 'admin'}">
				<h3>Filter for Report</h3>
				<form method="post" action="download-report" class="reportForm"
					autocomplete="off">
					<div class="row">
						<div class="col-md-3">
							<select class="form-control" name="userId" required="true">
								<option value="">Select User</option>
								<c:forEach items="${users}" var="user">
									<option value="${user.id}">${user.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-3">
							<input type="text" class="form-control" name="fromDate" value="${dateFormmated}"
								id="fromDate" placeholder="From Date" />
						</div>
						<div class="col-md-3">
							<input type="text" class="form-control" name="toDate" id="toDate" value="${dateFormmated}
								placeholder="To Date" />
						</div>
						<div class="col-md-3">
							<button class="btn btn-primary btn-block" type="submit">Generate
								Report</button>
						</div>
					</div>
				</form>

				<div class="row" style="margin-top: 30px;">
					<div class="col-md-4">
						<div class="input-group">
							<span class="input-group-addon"><strong>Task Date:</strong></span> 
							<input type="text" class="form-control"
								value="${dateFormmated}" id="dateFormmated" name="dateFormmated" />
							<span class="input-group-btn">
								<button class="btn btn-success" type="button"
									onclick="getDetailsByTask()">Show</button>
							</span>
						</div>

					</div>
				</div>
				<div class="row" style="margin-top: 10px;">
					<div class="col-md-12">
						<table class="table table-bordered">
							<tr class="tableHead">
								<th>Name</th>
								<th>Client Task</th>
								<th>Learn New Things</th>
								<th>Achievement</th>
							</tr>
							<c:forEach items="${userTasks}" var="task">
								<tr>
									<td>${task.userName}</td>
									<td>${task.clientTask}</td>
									<td>${task.learnNewThings}</td>
									<td>${task.achievement}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<!-- TODO for other condition -->
			</c:otherwise>
		</c:choose>
	</div>
</body>
<script>
	function getDetailsByTask() {
		var date = document.getElementById("dateFormmated").value;
		window.location.href = "?date=" + date;
	}
	$(function() {
		$('#fromDate').datepicker({
			dateFormat : 'dd-mm-yy'
		});
		$('#toDate').datepicker({
			dateFormat : 'dd-mm-yy'
		});
		$('#dateFormmated').datepicker({
			dateFormat : 'dd-mm-yy'
		});
	});
</script>
</html>
