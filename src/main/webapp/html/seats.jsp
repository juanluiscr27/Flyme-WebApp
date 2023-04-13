<!DOCTYPE html>
<html>

<head>
	<title>FlyMe - Seats</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" type="image/png" href="images/favicon.png">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
		integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
		crossorigin="anonymous"></script>
	<link href="css/styles.css" rel="stylesheet" type="text/css">
	<script defer src="js/seats.js"></script>
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
						<div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
							<div class="card-body p-4 p-md-5">
								<h3 class="mb-4 pb-2 pb-md-0 mb-md-5 text-center">Seats selection</h3>
								<h4 class="mb-4 pb-2 pb-md-0 mb-md-5 text-center">From Pearson International to New York
									International</h4>
								<div class="row justify-content-center d-flex align-items-center">
									<div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

										<form id="sign-up" action="SeatsServlet" method="POST">
											<div class="row d-flex align-items-center">
												<div class="col-md-1 mb-4"></div>
												<div class="col-md-8 mb-4">
													<label for="p1-seat" class="form-label select-label">Jeniffer
														Lawrence</label>
												</div>
												<div class="col-md-1 mb-4">
													<select class="select form-control-lg" id="p1-seat" name="p1-seat">
														<option value="1">1</option>
														<option value="2">2</option>
														<option value="3">3</option>
													</select>
												</div>
											</div>
											<div class="row d-flex align-items-center">
												<div class="col-md-1 mb-4"></div>
												<div class="col-md-8 mb-4">
													<label for="p2-seat" class="form-label select-label">Mike
														Tyson</label>
												</div>
												<div class="col-md-1 mb-4">
													<select class="select form-control-lg" id="p2-seat" name="p2-seat">
														<option value="1">1</option>
														<option value="2">2</option>
														<option value="3">3</option>
													</select>
												</div>
											</div>
											<div class="row d-flex align-items-center">
												<div class="col-md-1 mb-4"></div>
												<div class="col-md-8 mb-4">
													<label for="p3-seat" class="form-label select-label">Steven Tyler</label>
												</div>
												<div class="col-md-1 mb-4">
													<select class="select form-control-lg" id="p3-seat" name="p3-seat">
														<option value="1">1</option>
														<option value="2">2</option>
														<option value="3">3</option>
													</select>
												</div>
											</div>
											<br>
											<div class="row">
												<div class="col-md-12 mb-4 text-center">
													<input class="btn btn-primary btn-lg" type="submit"
														value="Summary" />
													<input class="btn btn-secondary btn-lg" type="button" onclick="window.location.href='passengers';"
														value="Back" />
												</div>
											</div>
										</form>
									</div>
									<div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">

										<img src="../images/a380_economy.jpg" class="img-fluid mx-auto d-block" alt="Economy">

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>

</html>