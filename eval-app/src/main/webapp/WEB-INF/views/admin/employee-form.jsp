<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Formulaire employé"/>
<jsp:include page="../common/header.jsp"/>

<h1><c:out value="${empty employee ? 'Nouvel employé' : 'Modifier employé'}"/></h1>

<div class="card">
  <form method="post" action="${pageContext.request.contextPath}/admin/employees">
    <input type="hidden" name="action" value="save">
    <c:if test="${not empty employee}">
      <input type="hidden" name="id" value="${employee.id}">
    </c:if>

    <c:if test="${empty employee}">
      <div class="field">
        <label>Email (connexion)</label>
        <input type="email" name="email" required>
      </div>
      <div class="field">
        <label>Mot de passe initial</label>
        <input type="text" name="password" required>
      </div>
    </c:if>

    <div class="field">
      <label>Nom complet</label>
      <input type="text" name="fullName" value="<c:out value='${employee.fullName}'/>" required>
    </div>
    <div class="field">
      <label>Poste</label>
      <input type="text" name="position" value="<c:out value='${employee.position}'/>">
    </div>
    <div class="field">
      <label>Département</label>
      <input type="text" name="department" value="<c:out value='${employee.department}'/>">
    </div>
    <div class="field">
      <label>Date d'embauche</label>
      <input type="date" name="hireDate" value="${employee.hireDate}">
    </div>
    <div class="field">
      <label>Manager</label>
      <select name="managerId">
        <option value="">— aucun —</option>
        <c:forEach var="m" items="${managers}">
          <option value="${m.id}" <c:if test="${employee.managerId == m.id}">selected</c:if>>
            <c:out value="${m.fullName}"/>
          </option>
        </c:forEach>
      </select>
    </div>

    <button class="btn" type="submit">Enregistrer</button>
    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/admin/employees">Annuler</a>
  </form>
</div>

<jsp:include page="../common/footer.jsp"/>
