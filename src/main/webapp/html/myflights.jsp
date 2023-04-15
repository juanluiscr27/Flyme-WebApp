<%@ page import="model.Order"%>
<%@ page import="model.Flight"%>
<%@ page import="model.AirportDTO"%>
<%@ page import="model.PassengerDTO"%>
<%@ page import="model.FlightClassDTO"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>

<head>
<title>FlyMe - Flights</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/png" href="images/favicon.png">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
	crossorigin="anonymous"></script>
<link href="css/styles.css" rel="stylesheet" type="text/css">
<script defer src="js/myflights.js"></script>
</head>

<body>
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="home"><img src="images/logo.jpg"></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#mynavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="mynavbar">
				<ul class="navbar-nav me-auto">
					<li class="nav-item"><a class="nav-link" href="search">Search
							flights</a></li>
				</ul>
				<ul class="navbar-nav d-flex">
				<%
					if(session.getAttribute("username") != null) {
				%>
				
					<li class="nav-item"><a class="nav-link" href="user">Profile</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="logout">Log out</a></li>
				<% } else {
				%>
					<li class="nav-item"><a class="nav-link" href="login">Login</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="signup">Sign	up</a></li>
				<%
					}
				%>	
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<section class="gradient-custom">
			<div class="container py-3 h-100">
				<div class="row justify-content-center align-items-center h-100">
					<div class="col-12 col-lg-9 col-xl-10">
						<div class="card shadow-2-strong card-registration"
							style="border-radius: 15px;">
							<div class="card-body p-4 p-md-5">
									<div class="row text-center">
										<div class="col-md-3 mb-4"></div>
										<div class="col-md-2 mb-4">
											<div class="form-outline">
												<a href="user" class="btn btn-primary"/>My profile</a>
											</div>
										</div>
										<div class="col-md-2 mb-4">
											<div class="form-outline">
												<a href="payment" class="btn btn-primary"/>My payments</a>
											</div>
										</div>
										<div class="col-md-2 mb-4">
											<div class="form-outline">
												<a class="btn btn-secondary disabled"/>My flights</a>
											</div>
										</div>
									</div>
									<br>
								<h3 class="mb-4 pb-2 pb-md-0 mb-md-5 text-center">My
									flights</h3>
									<%
									List<Order> orders = (List<Order>) request.getAttribute("orders");
									if (orders.size() > 0){
									%>
								<form id="sign-up" action="user" method="POST">
									
									<section>
										<div class="row d-flex align-items-center">
											<div class="col-md-1 mb-4"></div>
											<div class="col-md-2 mb-4">
												<h5>Flight number</h5>
											</div>
											<div class="col-md-3 mb-4">
												<h5>From - To</h5>
											</div>
											<div class="col-md-2 mb-4">
												<h5>Departure date</h5>
											</div>
										</div>
								<%
								for (int i = 0 ; i < orders.size() ; i++ ){
									request.setAttribute("i", i);
								%>
										<div class="row d-flex align-items-center">
											<div class="col-md-1 mb-4"></div>
											<div class="col-md-2 mb-4">
												<p>${orders.get(i).flight().flightNumber }</p>
											</div>
											<div class="col-md-3 mb-4">
												<p>${orders.get(i).flight().origin.city()} - ${orders.get(i).flight().destination.city()}</p>
											</div>
											<div class="col-md-2 mb-4">
												<p>${orders.get(i).flight().departure.toLocalDate()}</p>
											</div>
											<div class="col-md-1 mb-4"></div>
											<div class="col-md-2 mb-4">
												<div class="form-outline">
													<input class="btn btn-secondary btn-sm" type="button"
														data-bs-toggle="collapse" data-bs-target="#flight${i}"
														value="Details" /> <input class="btn btn-primary btn-sm"
														type="button" value="Cancel" />
												</div>
											</div>
										</div>
										<div class="row d-flex align-items-center">
											<div class="collapse" id="flight${i}">
												<div class="card card-body">
													<div class="row font-small">
														<p>
															<b>From: </b>${orders.get(i).flight().origin.city()} - ${orders.get(i).flight().origin.name()} (${orders.get(i).flight().origin.airportId()}) <b>To: </b>${orders.get(i).flight().destination.city()} - ${orders.get(i).flight().destination.name()} (${orders.get(i).flight().destination.airportId()})<br>
															<b>Departure date/time: </b>${orders.get(i).flight().departure}<b> Arrival date/time: </b>${orders.get(i).flight().arrival}<br>
															<b>Aircraft: </b>${orders.get(i).flight().airPlane.manufacturer()} ${orders.get(i).flight().airPlane.model()} 
														</p>
													</div>
													<div class="row font-small">
														<b>Passengers:</b>
														<div class="col-md-12 mb-4">
															<table class="table">
															<%
															for (int j = 0 ; j < orders.get(i).passengers().size() ; j++ ){
																request.setAttribute("j", j);
															%>
																<tr>
																	<td>${orders.get(i).passengers().get(j).firstName()} ${orders.get(i).passengers().get(j).lastName()}</td>
																	<td>${orders.get(i).passengers().get(j).gender()}</td>
																	<td>${orders.get(i).passengers().get(j).bags()} bag${orders.get(i).passengers().get(j).bags() == "1" ? "" : "s" }</td>
																	<td>Seat ${orders.get(i).passengers().get(j).seat().row()} ${orders.get(i).passengers().get(j).seat().column()}</td>
																	<td>${orders.get(i).passengers().get(j).seat().seatClass().className()} class</td>
																</tr>
															<%
															}
															%>
															</table>
														</div>
													</div>
												</div>
											</div>
										</div>
										<br>
										<% } %> 
									</section>
								</form>
										<%
									}
										else
										{
											%>
											<h5 class="mb-4 pb-2 pb-md-0 mb-md-5 text-center">No orders found !</h5>
											<%
										}
										%>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>

</html>