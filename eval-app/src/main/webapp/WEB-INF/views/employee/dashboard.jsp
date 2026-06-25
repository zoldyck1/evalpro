<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Tableau de bord Employé"/>
<jsp:include page="../common/header.jsp"/>

<h1>Bienvenue, <c:out value="${employee.fullName}"/></h1>

<div class="grid">
  <div class="stat"><div class="num">${employee.department}</div><div class="label">Département</div></div>
  <div class="stat"><div class="num">${employee.position}</div><div class="label">Poste</div></div>
  <div class="stat"><div class="num">${empty employee.managerName ? '—' : employee.managerName}</div><div class="label">Manager</div></div>
</div>

<div class="card">
  <h2>Évaluations récentes</h2>
  <c:choose>
    <c:when test="${empty evaluations}"><p>Aucune évaluation pour le moment.</p></c:when>
    <c:otherwise>
      <table>
        <thead><tr><th>Manager</th><th>Note</th><th>Statut</th></tr></thead>
        <tbody>
          <c:forEach var="e" items="${evaluations}">
            <tr>
              <td><c:out value="${e.managerName}"/></td>
              <td>${e.score == null ? '—' : e.score}</td>
              <td><span class="badge badge-${e.status == 'VALIDATED' ? 'validated' : 'draft'}">${e.status}</span></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:otherwise>
  </c:choose>
</div>

<jsp:include page="../common/footer.jsp"/>
