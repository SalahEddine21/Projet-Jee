<%@ page pageEncoding = "utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta content="text/html; charset=utf-8">
		<title>Connexion</title>
		<link href="../css/mdb.css" rel="stylesheet">
		<link href="../css/bootstrap.css" rel="stylesheet">
	</head>
	<body>
	
			<nav Class="navbar navbar-expand-lg navbar-dark black-sell">
			    <a class="navbar-brand" href="../hello_world.jsp">Accueil</a>
			      <ul class="navbar-nav mr-auto">
			          <li class="nav-item">
			              <a class="nav-link waves-light" href="Admin/loginAdmin.jsp" >Administrateur</a>
			          </li>
			          <li class="nav-item">
			              <a class="nav-link waves-light"  href="Profs/login.jsp">Professeurs</a>
			          </li>
			      </ul>
			</nav>
			
			
			<div class="container">
		        <div class="row" style="margin-top:50px;">
		            <div class="col-lg-12">
		                <h1 class="page-header">Connexion
		                    <small>Administrateur</small>
		                </h1>
		                <ol class="breadcrumb">
		                    <li><a href="">Accueil</a>
		                    </li>
		                    <li class="active">Connexion</li>
		                </ol>
		            </div>
		        </div>	
		        
		        <div class="row">
		        	<div class="col-lg-offset-3 col-lg-5 col-lg-offset-3" style="margin-top:55px;">
		        		<form action="ConnexionAdmin" method="post">
		        			<legend>Vos Informations</legend>
					            <div class="control-group form-group">
					                 <div class="controls">
					                     <input type="text" class="form-control" id="cin" name="cin" placeholder="Votre CIN">
					                 	 <p style="color:red;">${error.cin}</p>
					                 </div>
					           </div>
					            <div class="control-group form-group">
					                 <div class="controls">
					                     <input type="password" class="form-control" id="passe" name="passe" placeholder="Votre mot de passe"  >
					                 	 <p style="color:red;">${error.pass}</p>
					                 </div>
					           </div>	
					           <div class="row">
									<div class="col-lg-offset-9">
						           	   <button type="submit" class="btn btn-info btn-rounded" >Connexion</button>
									</div>
					           </div>			        
		        		</form>	
								        		
		        	</div>
		        </div>			
			</div>
	</body>
</html>