<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Rapports"/>
<jsp:include page="../common/header.jsp"/>

<h1>Rapports & Statistiques</h1>

<div class="grid">
  <div class="stat"><div class="num">${draftCount}</div><div class="label">Brouillons</div></div>
  <div class="stat"><div class="num">${validatedCount}</div><div class="label">Validées</div></div>
</div>

<div class="card">
  <h2>Score moyen par département</h2>
  <c:choose>
    <c:when test="${empty avgByDept}"><p>Aucune donnée d'évaluation pour l'instant.</p></c:when>
    <c:otherwise>
      <table>
        <thead><tr><th>Département</th><th>Score moyen</th><th>Nb évaluations</th></tr></thead>
        <tbody>
          <c:forEach var="r" items="${avgByDept}">
            <tr>
              <td><c:out value="${r[0]}"/></td>
              <td><c:out value="${r[1]}"/></td>
              <td><c:out value="${r[2]}"/></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:otherwise>
  </c:choose>
</div>

<jsp:include page="../common/footer.jsp"/>
