<%@ page import="model.Reservation"%>
<!DOCTYPE html>
<html>

<head>
<title>FlyMe - Summary</title>
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
<script defer src="js/summary.js"></script>
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
								<h3 class="mb-4 pb-2 pb-md-0 mb-md-5 text-center">Summary</h3>
								<form id="sign-up" action="pay" method="POST">

                  <% Reservation reservation = (Reservation) session.getAttribute("reservation"); %>

									<div class="row">
										<h4>Flight 1234</h4>
										<p>
											From Pearson International to New York International<br>
											February 28th, 2023 - 10:00<br> Boeing 737-400 - Economy
										</p>
									</div>
									<div class="row">
										<h4>Flight 5678</h4>
										<p>
											From New York International to Pearson International<br>
											March 15th, 2023 - 17:00<br> Airbus A380 - Economy
										</p>
									</div>
									<div class="row">
										<h4>Passengers</h4>
										<div class="col-md-12 mb-4">
											<table class="table">
												<tr>
													<td>Mike Tyson</td>
													<td>Male</td>
													<td>2 bags</td>
													<td>Seat 3</td>
													<td>$660</td>
												</tr>
												<tr>
													<td>Jeniffer Lawrence</td>
													<td>Female</td>
													<td>1 bag</td>
													<td>Seat 2</td>
													<td>$480</td>
												</tr>
												<tr>
													<td>Steven Tyler</td>
													<td>Male</td>
													<td>1 bag</td>
													<td>Seat 1</td>
													<td>$480</td>
												</tr>
											</table>
										</div>
									</div>
									<br>
									<div class="row">
										<div class="col-md-12 mb-4 text-center">
											<input class="btn btn-primary btn-lg" type="submit"
												value="Payment" /> <input class="btn btn-secondary btn-lg"
												type="button" onclick="window.location.href='seats';"
												value="Back" />
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