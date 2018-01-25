
 <%@ page pageEncoding = "utf-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Admin profil</title>
<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700" rel="stylesheet">
<!-- Bootstrap  -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="style_profilAdmin.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
$(function () {
  	$('.navbar-toggle-sidebar').click(function () {
  		$('.navbar-nav').toggleClass('slide-in');
  		$('.side-body').toggleClass('body-slide-in');
  		$('#search').removeClass('in').addClass('collapse').slideUp(200);
  	});

  	$('#search-trigger').click(function () {
  		$('.navbar-nav').removeClass('slide-in');
  		$('.side-body').removeClass('body-slide-in');
  		$('.search-input').focus();
  	});
  });

</script>
	<!--<link rel="stylesheet" href="bootstrap.css"> -->	
</head>
<body>

	<nav class="navbar navbar-default navbar-static-top">
    <div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle navbar-toggle-sidebar collapsed">
			MENU
			</button>
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">
				${sessionScope.nom}
			</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">      
			<form class="navbar-form navbar-left" method="GET" role="search">
				<div class="form-group">
					<input type="text" name="q" class="form-control" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default"><i class="glyphicon glyphicon-search"></i></button>
			</form>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="http://www.pingpong-labs.com" target="_blank">Visit Site</a></li>
				<li class="dropdown ">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
						Mon compte
						<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li class="dropdown-header">Paramétres</li>
							<li class=""><a href="#">Modifier mot de passe</a></li>
							
							<li class="divider"></li>
							<li><a href="deconnexionAdmin">Déconnexion</a></li>
						</ul>
					</li>
				</ul>
			</div><!-- /.navbar-collapse -->
		</div><!-- /.container-fluid -->
	</nav>  
		<div class="container-fluid main-container">
  		<div class="col-md-2 sidebar">
  			<div class="row">
	<!-- uncomment code for absolute positioning tweek see top comment in css -->
	<div class="absolute-wrapper"> </div>
	<!-- Menu -->
	<div class="side-menu">
		<nav class="navbar navbar-default" role="navigation">
			<!-- Main Menu -->
			<div class="side-menu-container">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#"><span class="glyphicon glyphicon-dashboard"></span> Dashboard</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-plane"></span> Parametre prof</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-cloud"></span> Parametre etudiant</a></li>

					<!-- Dropdown-->
					<li class="panel panel-default" id="dropdown">
						<a data-toggle="collapse" href="#dropdown-lvl1">
							<span class="glyphicon glyphicon-user"></span> Sub Level <span class="caret"></span>
						</a>

						<!-- Dropdown level 1 -->
						<div id="dropdown-lvl1" class="panel-collapse collapse">
							<div class="panel-body">
								<ul class="nav navbar-nav">
									<li><a href="#">Link</a></li>
									<!-- Dropdown level 2 -->
									<li class="panel panel-default" id="dropdown">
										<a data-toggle="collapse" href="#dropdown-lvl2">
											<span class="glyphicon glyphicon-off"></span> Sub Level <span class="caret"></span>
										</a>
										<div id="dropdown-lvl2" class="panel-collapse collapse">
											<div class="panel-body">
												<ul class="nav navbar-nav">
													<li><a href="#">Link</a></li>
												</ul>
											</div>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</li>

					<li><a href="#"><span class="glyphicon glyphicon-signal"></span> Link</a></li>

				</ul>
			</div><!-- /.navbar-collapse -->
		</nav>

	</div>
</div>  		</div>

  	<div class="col-md-10 content">
  	<div class="panel panel-default">
	<div class="panel-heading">
		 <div class="container">
		<div class="col-md-2 " id="dash" >
			Dashboard
		</div>
			
			<div class="col-md-3">    
      
      <form>
	  <div class="form-row align-items-center">
	    <div class="col-auto my-1">
	      <p>Sélectionner
	      <select class="custom-select mr-sm-2" id="inlineFormCustomSelect">
	        <option selected>date</option>
	        <c:forEach var="date" items="${ dates }" varStatus="status">
			            <option  value="${status.count}" id="dateClique" ><c:out value="${date }" /></option>
			        </c:forEach>
		      </select>
		      </p>
		    </div>
		    </div>
			</form>
		
			</div>
			<!--  ajourd'hui : date -->
			<div " class="col-md-4 "  >
				<c:choose>
				    <c:when test="${!empty currentdate }">
				       <p>Ajourd'hui:  <b ><c:out value=" ${currentdate}" /> </b> </p>
				        <br />
				    </c:when>    
				    <c:otherwise>
				        Pas de séance aujourd'hui
				        <br />
				    </c:otherwise>
				</c:choose>
				
				</div>
				
					<div " class="col-md-2 " id="test" ><c:out value=" ${currentdate}" /> </div>
				
				<!--  affichage de la date selectionne au menu -->
				<script>

				$(document).ready(function(){
				    $("#inlineFormCustomSelect").click(function(){
						var texte = jQuery("#inlineFormCustomSelect option:selected").text() ;
				        $("#test").text(texte);
				    });
				});
				
				</script>
				
				
		</div>
	</div>
	<div class="panel-body">
		 
		  <table class="table">
		    <thead>
		      <tr>
		        <th>Id séance</th>
		        <th>Groupe</th>
		        <th>Heure</th>
		        <th>Module</th>
		        <th>Professeur</th>
		        <th>Appel effectué</th>
		        <th></th>
		      </tr>
		    </thead>
		    
		    <script>
				     $(document).ready(function() {
				         $("#inlineFormCustomSelect").change(function() {
				             servletCall();
				         });
				
				     });
				     function servletCall() {
							var text = jQuery("#inlineFormCustomSelect option:selected").text() ;
							<!--var text1=$('#inlineFormCustomSelect').val() ; -->
				         $.post(
				             "profilAdmin", 
				             {name : text }, 
				             function(result) {
					             $('#fill').empty() ;
				             $('#fill').append(result) ;
				        
				         });
				     };
   				</script>
		     <tbody id="fill"></tbody>
		    	
		      </table>		
		       
		          
	</div>
</div>
  		</div>
  		<!-- Footer -->
  		<footer class="pull-left footer">
  			<p class="col-md-12">
  				<hr class="divider">
  				Copyright &COPY; 2015 <a href="http://www.pingpong-labs.com">Gravitano</a>
  			</p>
  		</footer>
  	</div>
</body>
</html>
