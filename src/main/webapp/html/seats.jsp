<%@ page import="model.PassengerRequest"%>
<%@ page import="model.Reservation"%>
<%@ page import="model.SeatDTO"%>
<%@ page import="model.SeatDTO"%>
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
  <script type="module" src="js/passenger.js"></script>
  <script type="module" src="js/seats.js"></script>
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

                  
                  <div class="col-md-6">
										<form id="seats" action="summary" method="POST">
                      <% String passengersJSON = (String) request.getAttribute("passengers-seats"); %>
                      <input type="hidden" id="passengers" name="passengers" value='<%= passengersJSON %>'>
                      <% PassengerRequest[] passengers = (PassengerRequest[]) session.getAttribute("passengers");
                      Reservation reservation = (Reservation) session.getAttribute("reservation");
                      for(int i = 0; i < passengers.length; i++)  { 
                        request.setAttribute("i", i);
                      %>
                        <div class="">
                          <div class="col-md-10 mb-4">
                            <label class="form-label" for="full-name">Name</label>
                            <input type="text" id="full-name" name="full-name"
                                class="form-control form-control-lg" value="${passengers[i].firstName} ${passengers[i].lastName}"
                                disabled />
                          </div>
                          <div class="col-md-10 mb-4">
                            <label for="p${i+1}-seat" class="form-label select-label">Seats</label>
                            <select class="select form-control-lg col-md-12" id="p${i+1}-seat" name="p${i+1}-seat" required>
                              <option selected>select a seat</option>
                              <% for(int j = 0; j < reservation.getFlightSeats().length; j++)  { 
                                request.setAttribute("j", j);
                              %>
                              <option value="${reservation.flightSeats[j].seatId()}" ${ reservation.getFlightSeats()[j].seatClass().isReserved() ? disabled : "" }>${reservation.getFlightSeats()[j].seatClass().className()} - ${reservation.getFlightSeats()[j].row()} ${reservation.getFlightSeats()[j].column()} </option>
                              <% } %>
                            </select>
                          </div>
                        </div>
                        <% } %>
                        <br>
                        <div class="row">
                          <div class="col-md-12 mb-4 text-center">
                            <input class="btn btn-primary btn-lg" type="submit"
                              value="Summary" /> <input class="btn btn-secondary btn-lg"
                              type="button" onclick="window.location.href='passengers';"
                              value="Back" />
                          </div>
                        </div>
										</form>                    
                  </div>

									<div class="col-md-4">
										<img src="images/boeing-747-seatmap.png" width="100%" alt="Airplane Seat Map">
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