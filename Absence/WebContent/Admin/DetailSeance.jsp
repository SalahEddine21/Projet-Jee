<%@ page pageEncoding = "utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta content="text/html; charset=utf-8">
		<title>${sessionScope.admin.prenom} - ${sessionScope.admin.nom}</title>
		<link href="../css/bootstrap.css" rel="stylesheet">
		<link href="../css/mdb.css" rel="stylesheet">
	</head>
	<body>
		<jsp:include page = "Admin_navbar.jsp" />

		<div class="container">
		
	      <div class="row" style="margin-top:50px;">
	      	 <div class="col-lg-12">
	            <h1 class="page-header">Profil
	               <small>${sessionScope.admin.prenom} - ${sessionScope.admin.nom}</small>
	            </h1>
	            <ol class="breadcrumb">
		           <li><a href="">Accueil</a>
	               </li>
		           <li class="active">Administrateur/DASHBOARD</li>
	            </ol>
	          </div>
	      </div>
	      
	      <div class="row" style="margin-top:30px;">
	      	<div class="col-lg-offset-3 col-lg-6">
				<c:choose>
					<c:when test="${!empty requestScope.not_founded}">
						<p>${requestScope.not_founded}</p>
					</c:when>
					<c:otherwise>
						<table class="table table-bordered table-sm">
							<thead class="mdb-color darken-3">
								<tr class="text-white">
									<th>Numèro</th>
									<th>Nom</th>
									<th>Prenom</th>
									<th>Present?</th>
									<th>Jusitifé</th>
								</tr>
								</thead>
								<tbody>
								<c:forEach items="${requestScope.etudiants}" var="etudiants">
									<tr>
										<td><a href="detailEtd?id_etd=${etudiants.id}" style="color:#f50057" target="_blank" >${etudiants.num}</a></td>
										<td>${etudiants.nom}</td>
										<td>${etudiants.prenom}</td>
										<c:choose>
										    <c:when test="${etudiants.present == 1}">
										      	<td style="color:#9933CC" >Oui</td>
										      	<td>---</td>
										    </c:when>
										    <c:otherwise>
										       <td style="color:#f44336">Non</td>
												  <c:choose>
												     <c:when test="${etudiants.justifier == 1}">
												      	<td style="color:#9933CC" >Oui</td>
												     </c:when>
												     <c:otherwise>
												      	<td style="color:#f44336">Non</td>
												     </c:otherwise>
												  </c:choose>
										    </c:otherwise>
									   </c:choose>								      
									</tr>
								</c:forEach>
							 </tbody>
							</table>
					</c:otherwise>
				</c:choose>	
	
					<div class="row">
						<div class="col-lg-12">
							<hr>
						</div>
					</div>
	      		
	      	</div>
	     </div> 		      
	   </div>   	
	</body>
</html>