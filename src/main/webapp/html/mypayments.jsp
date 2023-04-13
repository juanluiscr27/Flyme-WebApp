<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html>

<head>
<title>FlyMe - Payments</title>
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
<script defer src="js/mypayments.js"></script>
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

					<li class="nav-item"><a class="nav-link" href="user">Profile</a>
					</li>
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
								<div class="row text-center">
									<div class="col-md-3 mb-4"></div>
									<div class="col-md-2 mb-4">
										<div class="form-outline">
											<a href="user" class="btn btn-primary btn-sm" />My profile</a>
										</div>
									</div>
									<div class="col-md-2 mb-4">
										<div class="form-outline">
											<a class="btn btn-secondary btn-sm disabled" />My payments</a>
										</div>
									</div>
									<div class="col-md-2 mb-4">
										<div class="form-outline">
											<a href="myflights" class="btn btn-primary btn-sm" />My
											flights</a>
										</div>
									</div>
								</div>
								<br>
								<h3 class="mb-4 pb-2 pb-md-0 mb-md-5 text-center">My
									payment methods</h3>
								<form id="sign-up" action="payment" method="POST">
<input type="text" name="paymentId" id="paymentId" value="${payment.getId()}" hidden>
 <input type="text" name="deleteAction" id="deleteAction" value="" hidden>
									<section>
										<div class="row">
											<div class="col-md-12 mb-4">
												<h4>Payment method</h4>
											</div>
										</div>
										<div class="row d-flex align-items-center">
											<div class="col-md-1 mb-4"></div>
											<div class="col-md-2 mb-4">
												<div class="form-outline">
													<label class="form-label" for="card-number">Card
														number</label>
												</div>
											</div>
											<div class="col-md-8 mb-4">
												<div class="form-outline">
													<input type="text" id="card-number" name="card-number" aria-describedby="cardNumberHelpInline"
														class="form-control form-control-lg" pattern="^[0-9]{16}"
														value="${payment.getCardNumber()}" required disabled/>
												</div>
											<div class="col-auto">
												<span id="cardNumberHelpInline" class="form-text" hidden>
													16 digits</span>
											</div>
											</div>
										</div>
										<div class="row d-flex align-items-center">
											<div class="col-md-1 mb-4"></div>
											<div class="col-md-2 mb-4">
												<div class="form-outline">
													<label class="form-label" for="card-name">Name on
														card</label>
												</div>
											</div>
											<div class="col-md-8 mb-4">
												<div class="form-outline">
													<input type="text" id="card-name" name="card-name"
														class="form-control form-control-lg"
														value="${payment.getNameOnCard()}" required disabled />
												</div>
											</div>
										</div>
										<div class="row d-flex align-items-center">
											<div class="col-md-1 mb-4"></div>
											<div class="col-md-2 mb-4">
												<div class="form-outline">
													<label class="form-label" for="expiry-date">Expiry
														date</label>
												</div>
											</div>
											<div class="col-md-3 mb-4">
												<div class="form-outline">
													<input type="text" id="expiry-date" name="expiry-date"
														class="form-control form-control-lg"
														value="${payment.getExpiryDate().format(DateTimeFormatter.ofPattern("yy-MM"))}" pattern="^[2][2-9]-[0-1][0-9]" required disabled />
												</div>
											</div>
											<div class="col-md-2 mb-4">
												<div class="form-outline">
													<label class="form-label" for="cvc">CVC</label>
												</div>
											</div>
											<div class="col-md-3 mb-4">
												<div class="form-outline">
													<input type="password" id="cvc" name="cvc"
														pattern="^[0-9]{3}" class="form-control form-control-lg"
														value="${payment.getSecurityCode()}" required disabled />
												</div>
											</div>
										</div>
										<div class="row text-center d-flex align-items-center">
											<div class="col-md-12 mb-2">
												<div class="form-outline">
													<input id="editButton" class="btn btn-secondary btn-sm"
														type="button" value="Edit" /> <input id="saveButton"
														class="btn btn-primary btn-sm" type="submit" value="Save"
														disabled />
												 <input id="deleteButton" class="btn btn-danger btn-sm"
													type="submit" value="Delete" /> 
												</div>
											</div>
										</div>
									</section>
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