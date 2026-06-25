<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Tableau de bord RH"/>
<jsp:include page="../common/header.jsp"/>

<h1>Tableau de bord RH</h1>

<div class="grid">
  <div class="stat"><div class="num">${nbEmployees}</div><div class="label">Employés</div></div>
  <div class="stat"><div class="num">${nbManagers}</div><div class="label">Managers</div></div>
  <div class="stat"><div class="num">${nbEvaluations}</div><div class="label">Évaluations</div></div>
</div>

<div class="card">
  <h2>Actions rapides</h2>
  <a class="btn" href="${pageContext.request.contextPath}/admin/employees?action=new">+ Ajouter un employé</a>
  <a class="btn" href="${pageContext.request.contextPath}/admin/managers?action=new">+ Ajouter un manager</a>
  <a class="btn btn-secondary" href="${pageContext.request.contextPath}/admin/reports">Voir les rapports</a>
</div>

<jsp:include page="../common/footer.jsp"/>
