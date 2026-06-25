<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Évaluer"/>
<jsp:include page="../common/header.jsp"/>

<h1>Évaluer <c:out value="${employee.fullName}"/></h1>

<div class="card">
  <form method="post" action="${pageContext.request.contextPath}/manager/evaluate">
    <input type="hidden" name="employeeId" value="${employee.id}">

    <div class="field">
      <label for="score">Note (0–100)</label>
      <input type="number" min="0" max="100" id="score" name="score"
             value="${evaluation.score}" required>
    </div>

    <div class="field">
      <label for="comments">Commentaires</label>
      <textarea id="comments" name="comments"><c:out value="${evaluation.comments}"/></textarea>
    </div>

    <c:if test="${not empty evaluation}">
      <p>Statut actuel : <span class="badge badge-${evaluation.status == 'VALIDATED' ? 'validated' : 'draft'}">${evaluation.status}</span></p>
    </c:if>

    <button class="btn" type="submit">Enregistrer (Brouillon)</button>
    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/manager/dashboard">Annuler</a>
  </form>
</div>

<c:if test="${not empty evaluation and evaluation.status != 'VALIDATED'}">
  <div class="card">
    <h3>Valider l'évaluation</h3>
    <p>La validation verrouille l'évaluation et la rend visible à l'employé comme finale.</p>
    <form method="post" action="${pageContext.request.contextPath}/manager/validate" style="display:inline;">
      <input type="hidden" name="id" value="${evaluation.id}">
      <button class="btn btn-success" type="submit">Valider maintenant</button>
    </form>
  </div>
</c:if>

<jsp:include page="../common/footer.jsp"/>
