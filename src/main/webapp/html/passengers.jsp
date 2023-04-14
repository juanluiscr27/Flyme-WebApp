<%@ page import="model.FlightSearchDTO"%>
<%@ page import="model.PassengerRequest"%>
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
<script type="module" src="js/passenger.js"></script>
<script type="module" src="js/passengers.js"></script>
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

								<form id="passengers-form" action="seats" method="POST">
                  <input type="hidden" id="passengers" name="passengers">
                  <% FlightSearchDTO flightSearch = (FlightSearchDTO) session.getAttribute("flightSearch");
                  for(int i = 0; i < flightSearch.passengers(); i++)  { 
                    request.setAttribute("i", i);
                  %>
									<div class="row passenger">
										<h4>Passenger ${i + 1}</h4>
										
											<div class="col-md-3 mb-4 form-outline">
												<label class="form-label" for="p${i + 1}-fname">First name</label> <br>
												<input type="text" id="p${i + 1}-name" name="p${i + 1}-fname"
													class="first-name form-control form-control-lg"
													aria-describedby="firstNameHelpInline${i + 1}"
													pattern="^[a-zA-Z.]+( [a-zA-Z.]+)*$"
													title="Alphabets and no special characters" required />
												<span id="firstameHelpInline${i + 1}" class="form-text" hidden>
													Alphabets and no special characters. </span>
											</div>
											
                     						<div class="col-md-3 mb-4 form-outline">
												<label class="form-label" for="p${i + 1}-lname">Last name</label> <br>
												<input type="text" id="p${i + 1}-name" name="p${i + 1}-lname"
													class="last-name form-control form-control-lg"
													aria-describedby="lastNameHelpInline${i + 1}"
													pattern="^[a-zA-Z.]+( [a-zA-Z.]+)*$"
													title="Alphabets and no special characters" required />
												<span id="lastNameHelpInline${i + 1}" class="form-text" hidden>
													Alphabets and no special characters. </span>
											</div>
											
										<div class="col-md-2 mb-4">
											<label for="p${i + 1}-gender" class="form-label select-label">Gender</label>
											<br> <select class="gender select form-control-lg"
												id="p${i + 1}-gender" name="p${i + 1}-gender">
												<option value="F">Female</option>
												<option value="M">Male</option>
												<option value="X">Other</option>
											</select>
										</div>
										<div class="col-md-3 mb-4">
											<div class="form-outline datepicker w-100">
												<label for="p${i + 1}-birth" class="form-label">Date of
													birth</label> <br> <input type="date"
													class="date-of-birth form-control form-control-lg" id="p${i + 1}-birth"
													name="p${i + 1}-birth" required />
											</div>
										</div>
										<div class="col-md-1 mb-4">
											<label for="p${i + 1}-bags" class="form-label select-label">Bags</label>
											<br> <select class="bags select form-control-lg" id="p1-bags"
												name="p${i + 1}-bags">
												<option value="0">0</option>
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
											</select>
										</div>
									</div>
                  <% } %>
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