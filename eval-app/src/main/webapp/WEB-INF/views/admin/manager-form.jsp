<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Formulaire manager"/>
<jsp:include page="../common/header.jsp"/>

<h1><c:out value="${empty manager ? 'Nouveau manager' : 'Modifier manager'}"/></h1>

<div class="card">
  <form method="post" action="${pageContext.request.contextPath}/admin/managers">
    <input type="hidden" name="action" value="save">
    <c:if test="${not empty manager}">
      <input type="hidden" name="id" value="${manager.id}">
    </c:if>
    <c:if test="${empty manager}">
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
      <input type="text" name="fullName" value="<c:out value='${manager.fullName}'/>" required>
    </div>
    <div class="field">
      <label>Département</label>
      <input type="text" name="department" value="<c:out value='${manager.department}'/>">
    </div>
    <button class="btn" type="submit">Enregistrer</button>
    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/admin/managers">Annuler</a>
  </form>
</div>

<jsp:include page="../common/footer.jsp"/>
