<!DOCTYPE html>
<html>

<head>
<title>FlyMe - Search flights</title>
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
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<link href="css/styles.css" rel="stylesheet" type="text/css">
<script defer src="js/search.js"></script>
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
					</li>
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
								<form id="sign-up" action="flights" method="GET">
									<div class="row">
										<div class="col-md-6 mb-4">
											<div class="form-outline">
												<label for="from" class="form-label">From</label>
												<input class="form-control" list="fromOptions" name="from" id="from" placeholder="Type to search..." aria-describedby="fromHelpInline" title="Please, select a departure airport" required>
												<datalist id="fromOptions" >
												  </datalist>
											</div>
										</div>
										<div class="col-md-6 mb-4">
											<div class="form-outline">
												<label for="to" class="form-label">To</label>
												<input class="form-control" list="toOptions" name="to" id="to" placeholder="Type to search..." title="Please, select an arrival airport"  required>
												<datalist id="toOptions" >
												  </datalist>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-1 mb-4"></div>
										<div class="col-md-2 mb-4">
											<div class="form-check form-switch">
												<input class="form-check-input" type="checkbox"
													role="switch" id="SwitchCheck"> <label
													class="form-check-label" for="SwitchCheck"
													id="SwitchCheckLbl">One way</label>
											</div>
										</div>
										<div class="col-md-4 mb-4">
                      <input type="hidden" id="departure" name="departure">
                      <input type="hidden" id="return" name="return">
											<div class="form-outline">
												<label class="form-label" for="daterange" id="dateLabel">Travel
													date</label> <br> <input id="daterange"	class="form-control form-control-lg" placeholder="YYYY-MM-DD" value="" title="Must select a travel date"  required/>
											</div>
										</div>
										<div class="col-md-2 mb-4"></div>
										<div class="col-md-1 mb-4">
											<label for="passengers" class="form-label select-label">Passengers</label>
											<select class="select form-control-lg" id="passengers"
												name="passengers">
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
												value="Search" /> <input class="btn btn-secondary btn-lg"
												type="button" onclick="window.location.href='home';"
												value="Cancel" />
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