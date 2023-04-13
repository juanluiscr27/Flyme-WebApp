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
								<h3 class="mb-4 pb-2 pb-md-0 mb-md-5 text-center">Search
									flights</h3>
								<h4 class="mb-4 pb-2 pb-md-0 mb-md-5 text-center">From
									Pearson International to New York International</h4>
								<form id="sign-up" action="FlightServlet" method="POST">
									<div class="row text-center">
										<div class="col-md-1 mb-4"></div>
										<div class="col-md-2 mb-4">
											<div class="form-outline">
												<input class="btn btn-secondary btn-sm" type="button"
													value="2023-02-28" />
											</div>
										</div>
										<div class="col-md-2 mb-4">
											<div class="form-outline">
												<input class="btn btn-secondary btn-sm" type="button"
													value="2023-03-01" />
											</div>
										</div>
										<div class="col-md-2 mb-4">
											<div class="form-outline">
												<input class="btn btn-primary btn-sm" type="button"
													value="2023-03-02" />
											</div>
										</div>
										<div class="col-md-2 mb-4">
											<div class="form-outline">
												<input class="btn btn-secondary btn-sm" type="button"
													value="2023-03-03" />
											</div>
										</div>
										<div class="col-md-2 mb-4">
											<div class="form-outline">
												<input class="btn btn-secondary btn-sm" type="button"
													value="2023-03-04" />
											</div>
										</div>
									</div>
									<br>
									<div class="row">
										<div class="col-md-3 mb-4"></div>
										<div class="col-md-3 mb-4">
											<h5>Economy</h5>
										</div>
										<div class="col-md-3 mb-4">
											<h5>Business</h5>
										</div>
										<div class="col-md-3 mb-4">
											<h5>First class</h5>
										</div>
									</div>
									<hr>
									<div class="row d-flex align-items-center">
										<div class="col-md-3 mb-4">
											<div class="form-outline">
												<p class="form-label">
													Flight 1234<br>10:00<br>Boeing 737
												</p>
											</div>
										</div>
										<div class="col-md-3 mb-4">
											<div class="form-check">
												<input type="radio" class="form-check-input" id="economy1"
													name="flight" value="economy1" checked>$200 <label
													class="form-check-label" for="radio1"></label>
											</div>
										</div>
										<div class="col-md-3 mb-4">
											<div class="form-check">
												<input type="radio" class="form-check-input" id="business1"
													name="flight" value="business1">$300 <label
													class="form-check-label" for="radio1"></label>
											</div>
										</div>
										<div class="col-md-3 mb-4">
											<div class="form-check">
												<input type="radio" class="form-check-input" id="first1"
													name="flight" value="first1">$500 <label
													class="form-check-label" for="radio1"></label>
											</div>
										</div>
									</div>
									<hr class="hr hr-blurry">
									<div class="row d-flex align-items-center">
										<div class="col-md-3 mb-4">
											<div class="form-outline">
												<p class="form-label">
													Flight 5678<br>15:00<br>Airbus A380
												</p>
											</div>
										</div>
										<div class="col-md-3 mb-4">
											<div class="form-check">
												<input type="radio" class="form-check-input" id="economy2"
													name="flight" value="economy2">$250 <label
													class="form-check-label" for="radio1"></label>
											</div>
										</div>
										<div class="col-md-3 mb-4">
											<div class="form-check">
												<input type="radio" class="form-check-input" id="business2"
													name="flight" value="business2">$400 <label
													class="form-check-label" for="radio1"></label>
											</div>
										</div>
										<div class="col-md-3 mb-4">
											<div class="form-check">
												<input type="radio" class="form-check-input" id="first2"
													name="flight" value="first2">$600 <label
													class="form-check-label" for="radio1"></label>
											</div>
										</div>
									</div>
									<hr class="hr hr-blurry">
									<div class="row d-flex align-items-center">
										<div class="col-md-3 mb-4">
											<div class="form-outline">
												<p class="form-label">
													Flight 5697<br>19:00<br>Boeing 777
												</p>
											</div>
										</div>
										<div class="col-md-3 mb-4">
											<div class="form-check">
												<input type="radio" class="form-check-input" id="economy3"
													name="flight" value="economy3">$250 <label
													class="form-check-label" for="radio1"></label>
											</div>
										</div>
										<div class="col-md-3 mb-4">
											<div class="form-check">
												<input type="radio" class="form-check-input" id="business3"
													name="flight" value="business3">$400 <label
													class="form-check-label" for="radio1"></label>
											</div>
										</div>
										<div class="col-md-3 mb-4">
											<div class="form-check">
												<input type="radio" class="form-check-input" id="first3"
													name="flight" value="first3">$550 <label
													class="form-check-label" for="radio1"></label>
											</div>
										</div>
									</div>
									<hr class="hr hr-blurry">
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
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>

</html>