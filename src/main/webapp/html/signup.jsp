<!DOCTYPE html>
<html>

<head>
<title>FlyMe - Sign up</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<link href="css/styles.css" rel="stylesheet" type="text/css">
<script defer src="js/signup.js"></script>
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
								<h3 class="mb-4 pb-2 pb-md-0 mb-md-5 text-center">Sign up
									form</h3>
								<form id="sign-up" action="user" method="POST">
									<div class="row">
										<div class="col-md-6 mb-4">
											<div class="form-outline">
												<label class="form-label" for="first-name">First
													Name</label> <br> <input type="text" id="first-name"
													name="first-name" class="form-control form-control-lg"
													aria-describedby="firstNameHelpInline"
													pattern="^[a-zA-Z.]+( [a-zA-Z.]+)*$"
													title="Alphabets and no special characters" required />
											</div>
											<div class="col-auto">
												<span id="firstNameHelpInline" class="form-text" hidden>
													Alphabets and no special characters. </span>
											</div>
										</div>
										<div class="col-md-6 mb-4">
											<div class="form-outline">
												<label class="form-label" for="last-name">Last Name</label>
												<br> <input type="text" id="last-name" name="last-name"
													class="form-control form-control-lg"
													aria-describedby="lastNameHelpInline"
													pattern="^[a-zA-Z.]+( [a-zA-Z.]+)*$"
													title="Alphabets and no special characters" required />
											</div>
											<div class="col-auto">
												<span id="lastNameHelpInline" class="form-text" hidden>
													Alphabets and no special characters. </span>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6 mb-4">
											<label for="nationality" class="form-label select-label">Nationality</label>
											<br> <select class="select form-control-lg"
												id="nationality" name="nationality">
												<option value="CA">Canada</option>
											</select>
										</div>
										<div class="col-md-3 mb-4">
											<div class="form-outline datepicker w-100">
												<label for="date-of-birth" class="form-label">Date
													of birth</label> <br> <input type="date"
													class="form-control form-control-lg" id="date-of-birth"
													name="date-of-birth" min="1900-01-01" max="2022-12-12" value="1999-01-01"/>
											</div>
										</div>
										<div class="col-md-3 mb-4">
											<label for="gender" class="form-label select-label">Gender</label>
											<br> <select class="select form-control-lg" id="gender"
												name="gender">
												<option value="F">Female</option>
												<option value="M">Male</option>
												<option value="O">Other</option>
											</select>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6 mb-4">
											<div class="form-outline">
												<label class="form-label" for="email">Email</label> <br>
												<input type="email" id="email" name="email"
													class="form-control form-control-lg"
													aria-describedby="emailHelpInline"
													pattern="^[a-zA-Z0-9]+(?:\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\.[a-zA-Z0-9]+)*$"
													title="Email address format" required />
											</div>
											<div class="col-auto">
												<span id="emailHelpInline" class="form-text" hidden>
													Email address format. </span>
											</div>
										</div>
										<div class="col-md-6 mb-4">
											<div class="form-outline">
												<label class="form-label" for="phone-number">Phone
													Number</label> <br>
												<div class="input-group">
													<span class="input-group-text" id="inputGroupPrepend">+1</span>
													<input type="tel" class="form-control form-control-lg"
														id="phone-number" name="phone-number"
														aria-describedby="phoneHelpInline"
														pattern="^[2-9][0-9]{9}"
														title="10 digit phone number format" required>
												</div>
												<div class="col-auto">
													<span id="phoneHelpInline" class="form-text" hidden>
														10 digit phone number format. </span>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6 mb-4">
											<div class="form-outline">
												<label class="form-label" for="password">Password</label> <br>
												<input type="password" id="password" name="password"
													class="form-control form-control-lg"
													aria-describedby="passwordHelpInline"
													pattern="[A-Za-z0-9!#$%&*+-]{8,}"
													title="At least 8 characters long" required />
											</div>
											<div class="col-auto">
												<span id="passwordHelpInline" class="form-text" hidden>
													At least 8 characters long. </span>
											</div>
										</div>
										<div class="col-md-6 mb-4">
											<div class="form-outline">
												<label class="form-label" for="confirm-password">Confirm
													Password</label> <br> <input type="password"
													id="confirm-password" class="form-control form-control-lg"
													aria-describedby="passwordHelpInline"
													pattern="[A-Za-z0-9!#$%&*+-]{8,}"
													title="At least 8 characters long" required />
											</div>
											<div class="col-auto">
												<span id="passwordHelpInline" class="form-text" hidden>
													At least 8 characters long. </span>
											</div>
										</div>
									</div>
									<br>
									<div class="row">
										<div class="col-md-12 mb-4 text-center">
											<input class="btn btn-primary btn-lg" type="submit"
												value="Create account" /> <input
												class="btn btn-secondary btn-lg" type="button"
												onclick="window.location.href='home';" value="Cancel" />
											<!--<input class="btn btn-secondary btn-lg" type="button" onclick="test()" value="Cancel" />-->
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