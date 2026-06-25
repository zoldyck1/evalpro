<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Historique des Évaluations"/>
<jsp:include page="../common/header.jsp"/>

<h1>Historique des Évaluations</h1>
<div class="card">
  <c:choose>
    <c:when test="${empty evaluations}"><p>Aucune évaluation pour le moment.</p></c:when>
    <c:otherwise>
      <table>
        <thead>
          <tr><th>Date</th><th>Employé</th><th>Note</th><th>Statut</th><th></th></tr>
        </thead>
        <tbody>
          <c:forEach var="e" items="${evaluations}">
            <tr>
              <td>${e.createdAt}</td>
              <td><c:out value="${e.employeeName}"/></td>
              <td>${e.score == null ? '—' : e.score}</td>
              <td><span class="badge badge-${e.status == 'VALIDATED' ? 'validated' : 'draft'}">${e.status}</span></td>
              <td>
                <c:if test="${e.status != 'VALIDATED'}">
                  <form method="post" action="${pageContext.request.contextPath}/manager/validate" style="display:inline;">
                    <input type="hidden" name="id" value="${e.id}">
                    <button class="btn btn-sm btn-success" type="submit">Valider</button>
                  </form>
                </c:if>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:otherwise>
  </c:choose>
</div>

<jsp:include page="../common/footer.jsp"/>
