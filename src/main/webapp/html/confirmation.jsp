<!DOCTYPE html>
<html>

<head>
<title>FlyMe - Oder Confirmation</title>
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
				<% } %>
				</ul>
			</div>
		</div>
	</nav>

  <main class="container">
		<section class="gradient-custom">
			<div class="container py-3 h-100">
				<div class="row justify-content-center align-items-center h-100">
					<div class="col-12 col-lg-9 col-xl-10">
						<div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
							<div class="card-body p-4 p-md-5">

                <section class="py-5 text-center container">
                  <div class="row py-lg-5">
                    <div class="col-lg-6 col-md-8 mx-auto">
                      <h1 class="fw-light">Thank you for choosing Flyme</h1>
                      <p class="lead text-body-secondary">Your reservation has been processed. Your confirmation number is <b>${confirmation.confirmationNumber()}</b>.</p>
                      <p>In your profile page is your flight itinerary where you can see receipts, calendar; also check in, cancel, or upgrade your flights.<p>
                        <a href="profile" class="btn btn-primary my-2">See your Profile</a>
                        <a href="home" class="btn btn-secondary my-2">Go to Home Page</a>
                      </p>
                    </div>
                  </div>
                </section>

							</div>
						</div>
					</div>
				</div>
		</section>

	</main>
  
</body>

</html>