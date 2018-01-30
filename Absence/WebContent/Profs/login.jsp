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
			              <a class="nav-link waves-light" href="../Admin/login.jsp" >Administrateur</a>
			          </li>
			          <li class="nav-item active">
			              <a class="nav-link waves-light"  href="login.jsp">Professeurs</a>
			          </li>
			          <li class="nav-item">
			              <a class="nav-link waves-light"  href="../Etudiants/login.jsp">Etudiants</a>
			          </li>
			      </ul>
			</nav>
			<div class="container">
		        <div class="row" style="margin-top:50px;">
		            <div class="col-lg-12">
		                <h1 class="page-header">Connexion
		                    <small>Professeurs</small>
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
		        		<form action="connexion" method="post">
		        			<legend>Vos Informations</legend>
					            <div class="control-group form-group">
					                 <div class="controls">
					                     <input type="text" class="form-control" id="cin" name="cin" placeholder="Votre CIN">
					                 	 <p style="color:red;">${controle.errors.cin}</p>
					                 </div>
					           </div>
					            <div class="control-group form-group">
					                 <div class="controls">
					                     <input type="password" class="form-control" id="passe" name="passe" placeholder="Votre mot de passe"  >
					                 	 <p style="color:red;">${controle.errors.passe}</p>
					                 </div>
					           </div>	
					           <div class="row">
									<div class="col-lg-offset-9">
						           	   <button type="submit" class="btn btn-info btn-rounded" >Connexion</button>
									</div>
					           </div>			        
		        		</form>	
						<c:if test="${!empty controle.errors.query}" >
							<div class="row col-lg-12">
								<div class="alert alert-danger">
									<p><strong>${controle.errors.query}</strong></p>
								</div>
							</div>
						</c:if>  		        		
		        	</div>
		        </div>			
			</div>
	</body>
</html>