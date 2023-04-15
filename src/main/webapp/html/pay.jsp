<%@ page import="model.Reservation"%>
<%@ page import="model.Receipt"%>
<%@ page import="model.Payment"%>
<!DOCTYPE html>
<html>

<head>
<title>FlyMe - Pay</title>
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
<script defer src="js/pay.js"></script>
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
								<h3 class="mb-4 pb-2 pb-md-0 mb-md-5 text-center">Pay</h3>
								<form id="sign-up" action="order" method="POST">
                  <% String passengersJSON = (String) request.getAttribute("passengers-seats"); %>
									<div class="row">
										<div class="col-md-3 mb-4"></div>
										<div class="col-md-6 mb-4">
											<h4>Fees</h4>
											<table class="table">
												<tr>
													<td>Tickets + Bags/td>
													<td>&dollar; ${reservation.receipt.subTotal }</td>
												</tr>
												<tr>
													<td>Bags fee</td>
													<td>&dollar; ${reservation.receipt.bagFees }</td>
												</tr>
												<tr>
													<td>Taxes</td>
													<td>&dollar; ${reservation.receipt.taxes }</td>
												</tr>
												<tr>
													<td>
														<h5>TOTAL</h5>
													</td>
													<td>
														<h5>&dollar; ${reservation.receipt.totalPrice }</h5>
													</td>
												</tr>
											</table>
										</div>
									</div>
									<div class="row">
										<div class="col-md-3 mb-4"></div>

                    <%
                      if(request.getAttribute("message") != null) {
                    %>

                      
                    <div class="col-md-6 mb-4">
											<h4>${message}</h4>
											<a class="btn btn-primary" href="user" role="button">Add payment method</a>
										</div>

                    <% } else {
                    %>                    

										<div class="col-md-6 mb-4">
											<h4>Payment method</h4>
											<ul>
												<li>Card number: ${payment.cardNumber}</li>
											</ul>
										</div>

                    <%
                      }
                    %>
									</div>
									<br>
									<div class="row">
										<div class="col-md-12 mb-4 text-center">
											<input class="btn btn-primary btn-lg" type="submit"
												value="Pay with card" ${ message != null ? "disabled" : "" }/> <input
												class="btn btn-primary btn-lg" type="button"
												value="Pay with points" /> <input
												class="btn btn-secondary btn-lg" type="button"
												onclick="window.location.href='search';" value="Cancel" />
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