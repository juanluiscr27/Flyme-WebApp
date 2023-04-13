<!DOCTYPE html>
<html>

<head>
<title>FlyMe - Passengers information</title>
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
<script defer src="js/passengers.js"></script>
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
								<h3 class="mb-4 pb-2 pb-md-0 mb-md-5 text-center">Passengers
									information</h3>
								<form id="sign-up" action="PassengersServlet" method="POST">
									<div class="row">
										<h4>Passenger 1</h4>
										<div class="col-md-6 mb-4">
											<div class="form-outline">
												<label class="form-label" for="p1-name">Name</label> <br>
												<input type="text" id="p1-name" name="p1-name"
													class="form-control form-control-lg"
													aria-describedby="fullNameHelpInline"
													pattern="^[a-zA-Z.]+( [a-zA-Z.]+)*$"
													title="Alphabets and no special characters" required />
											</div>
											<div class="col-auto">
												<span id="fullNameHelpInline" class="form-text" hidden>
													Alphabets and no special characters. </span>
											</div>
										</div>
										<div class="col-md-2 mb-4">
											<label for="p1-gender" class="form-label select-label">Gender</label>
											<br> <select class="select form-control-lg"
												id="p1-gender" name="p1-gender">
												<option value="F">Female</option>
												<option value="M">Male</option>
												<option value="O">Other</option>
											</select>
										</div>
										<div class="col-md-3 mb-4">
											<div class="form-outline datepicker w-100">
												<label for="p1-birth" class="form-label">Date of
													birth</label> <br> <input type="date"
													class="form-control form-control-lg" id="p1-birth"
													name="p1-birth" />
											</div>
										</div>
										<div class="col-md-1 mb-4">
											<label for="p1-bags" class="form-label select-label">Bags</label>
											<br> <select class="select form-control-lg" id="p1-bags"
												name="p1-bags">
												<option value="0">0</option>
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
											</select>
										</div>
									</div>
									<div class="row">
										<h4>Passenger 2</h4>
										<div class="col-md-6 mb-4">
											<div class="form-outline">
												<label class="form-label" for="p2-name">Name</label> <br>
												<input type="text" id="p2-name" name="p2-name"
													class="form-control form-control-lg"
													aria-describedby="fullNameHelpInline"
													pattern="^[a-zA-Z.]+( [a-zA-Z.]+)*$"
													title="Alphabets and no special characters" required />
											</div>
											<div class="col-auto">
												<span id="fullNameHelpInline" class="form-text" hidden>
													Alphabets and no special characters. </span>
											</div>
										</div>
										<div class="col-md-2 mb-4">
											<label for="p2-gender" class="form-label select-label">Gender</label>
											<br> <select class="select form-control-lg"
												id="p2-gender" name="p2-gender">
												<option value="F">Female</option>
												<option value="M">Male</option>
												<option value="O">Other</option>
											</select>
										</div>
										<div class="col-md-3 mb-4">
											<div class="form-outline datepicker w-100">
												<label for="p2-birth" class="form-label">Date of
													birth</label> <br> <input type="date"
													class="form-control form-control-lg" id="p2-birth"
													name="p2-birth" />
											</div>
										</div>
										<div class="col-md-1 mb-4">
											<label for="p2-bags" class="form-label select-label">Bags</label>
											<br> <select class="select form-control-lg" id="p2-bags"
												name="p2-bags">
												<option value="0">0</option>
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
											</select>
										</div>
									</div>
									<br>
									<div class="row">
										<div class="col-md-12 mb-4 text-center">
											<input class="btn btn-primary btn-lg" type="submit"
												value="Seats" /> <input class="btn btn-secondary btn-lg"
												type="button" onclick="window.location.href='flight';"
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