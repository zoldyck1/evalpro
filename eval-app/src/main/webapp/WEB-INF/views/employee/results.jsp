<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Mes Résultats"/>
<jsp:include page="../common/header.jsp"/>

<h1>Mes Résultats d'Évaluation</h1>

<div class="card">
  <c:choose>
    <c:when test="${empty evaluations}"><p>Aucune évaluation pour le moment.</p></c:when>
    <c:otherwise>
      <table>
        <thead>
          <tr><th>Manager</th><th>Note</th><th>Commentaires</th><th>Statut</th><th>Date</th></tr>
        </thead>
        <tbody>
          <c:forEach var="e" items="${evaluations}">
            <tr>
              <td><c:out value="${e.managerName}"/></td>
              <td>${e.score == null ? '—' : e.score}</td>
              <td><c:out value="${e.comments}"/></td>
              <td><span class="badge badge-${e.status == 'VALIDATED' ? 'validated' : 'draft'}">${e.status}</span></td>
              <td>${e.validatedAt != null ? e.validatedAt : e.createdAt}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:otherwise>
  </c:choose>
</div>

<jsp:include page="../common/footer.jsp"/>
