<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Mon Profil"/>
<jsp:include page="../common/header.jsp"/>

<h1>Mon Profil</h1>
<div class="card">
  <table>
    <tr><th style="width:200px;">Nom complet</th><td><c:out value="${employee.fullName}"/></td></tr>
    <tr><th>Email</th><td><c:out value="${employee.email}"/></td></tr>
    <tr><th>Poste</th><td><c:out value="${employee.position}"/></td></tr>
    <tr><th>Département</th><td><c:out value="${employee.department}"/></td></tr>
    <tr><th>Date d'embauche</th><td>${employee.hireDate}</td></tr>
    <tr><th>Manager</th><td><c:out value="${empty employee.managerName ? '—' : employee.managerName}"/></td></tr>
  </table>
</div>

<jsp:include page="../common/footer.jsp"/>
