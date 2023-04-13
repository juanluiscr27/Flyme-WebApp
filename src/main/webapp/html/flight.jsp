<%@ page import="model.Flight"%>
<!DOCTYPE html>
<html>

<head>
<title>FlyMe - Flight options</title>
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
<script defer src="js/flight.js"></script>
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
					if (session.getAttribute("username") != null) {
					%>
					<li class="nav-item"><a class="nav-link" href="user">Profile</a></li>
					<li class="nav-item"><a class="nav-link" href="logout">Log
							out</a></li>
					<%
					} else {
					%>
					<li class="nav-item"><a class="nav-link" href="login">Login</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="signup">Sign
							up</a></li>
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
								<h3 class="mb-4 pb-2 pb-md-0 mb-md-5 text-center">Search
									flights</h3>
								<%
								Flight[] allFlights = (Flight[]) session.getAttribute("allFlights");
								if (allFlights.length > 0) {
								%>

								<h4 class="mb-4 pb-2 pb-md-0 mb-md-5 text-center">From
									${allFlights[0].origin.city()} (${allFlights[0].origin.airportId()}) to
									${allFlights[0].destination.city()} (${allFlights[0].destination.airportId()})</h4>
								<form id="sign-up" action="passengers" method="POST">
									<div class="row text-center">
										<div class="col-md-5 mb-4"></div>
										<div class="col-md-2 mb-4">
											<div class="form-outline">
												<input class="btn btn-primary btn-sm" type="button"
													value="${allFlights[0].departure.toLocalDate()}" />
											</div>
										</div>
									</div>
									<br>
									
									<div class="row d-flex align-items-center">
										<div class="col-md-3 mb-4">
											<div class="form-label">
												<h5>Flight number</h5>
											</div>
										</div>
										<div class="col-md-3 mb-4">
											<div class="form-outline">
												<h5 class="form-label">Airplane</h5>
											</div>
										</div>
										<div class="col-md-3 mb-4">
											<div class="form-outline">
												<h5 class="form-label">Departure time</h5>
											</div>
										</div>
										<div class="col-md-3 mb-4">
											<div class="form-outline">
												<h5 class="form-label">Price</h5>
											</div>
										</div>
									</div>
									<hr class="hr hr-blurry">
									<%
									for (int i = 0; i < allFlights.length; i++) {
										request.setAttribute("i", i);
									%>

									<div class="row d-flex align-items-center">
										<div class="col-md-3 mb-4 px-3">
											<div class="form-check">
												<input type="radio" class="form-check-input"
													name="flightGroup" id="flight${i+1}"
													value="${allFlights[i].flightNumber}"
													${ i == 0 ? "checked" : "" }> <label
													class="form-check-label" for="flight${i+1}">${allFlights[i].flightNumber}</label>
											</div>
										</div>
										<div class="col-md-3 mb-4 px-3">
											<div class="form-outline">
												<p class="form-label">
													
													${allFlights[i].airPlane.manufacturer()}
													${allFlights[i].airPlane.model()}
												</p>
											</div>
										</div>
										<div class="col-md-3 mb-4 px-3">
											<div class="form-outline">
												<p class="form-label">
													${allFlights[i].departure.toLocalTime()}
												</p>
											</div>
										</div>
										<div class="col-md-3 mb-4 px-3">
											<div class="form-outline">
												<p class="form-label">
													
													Starting at &dollar;${allFlights[i].airPlane.basePrice()}
												</p>
											</div>
										</div>
									</div>
									<hr class="hr hr-blurry">
									<%
									}
									%>

									<br>
									<div class="row">
										<div class="col-md-12 mb-4 text-center">
											<input class="btn btn-primary btn-lg" type="submit"
												value="Passengers" /> <input
												class="btn btn-secondary btn-lg" type="button"
												onclick="window.location.href='search';" value="Back" />
										</div>
									</div>
								</form>
								<%
								} else {
								%>

								<h5 class="mb-4 pb-2 pb-md-0 mb-md-5 text-center">No
									flights found !</h5>

								<br>
								<div class="row">
									<div class="col-md-12 mb-4 text-center">
										<input class="btn btn-secondary btn-lg" type="button"
											onclick="window.location.href='search';" value="Back" />
									</div>
								</div>
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