<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Tableau de bord Manager"/>
<jsp:include page="../common/header.jsp"/>

<h1>Bienvenue, <c:out value="${manager.fullName}"/></h1>

<div class="grid">
  <div class="stat"><div class="num">${empty team ? 0 : team.size()}</div><div class="label">Membres de l'équipe</div></div>
  <div class="stat"><div class="num">${empty recentEvals ? 0 : recentEvals.size()}</div><div class="label">Total des évaluations</div></div>
</div>

<div class="card">
  <h2>Évaluer votre équipe</h2>
  <c:choose>
    <c:when test="${empty team}">
      <p>Aucun membre dans votre équipe.</p>
    </c:when>
    <c:otherwise>
      <table>
        <thead><tr><th>Employé</th><th>Poste</th><th></th></tr></thead>
        <tbody>
          <c:forEach var="emp" items="${team}">
              <tr>
                <td><c:out value="${emp.fullName}"/></td>
                <td><c:out value="${emp.position}"/></td>
                <td>
                  <a class="btn btn-sm" href="${pageContext.request.contextPath}/manager/evaluate?employeeId=${emp.id}">Évaluer</a>
                </td>
              </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:otherwise>
  </c:choose>
</div>

<jsp:include page="../common/footer.jsp"/>
