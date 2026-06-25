<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Toutes les évaluations"/>
<jsp:include page="../common/header.jsp"/>

<h1>Toutes les évaluations</h1>
<div class="card">
  <table>
    <thead>
      <tr><th>Date</th><th>Employé</th><th>Manager</th><th>Score</th><th>Statut</th></tr>
    </thead>
    <tbody>
      <c:forEach var="e" items="${evaluations}">
        <tr>
          <td>${e.createdAt}</td>
          <td><c:out value="${e.employeeName}"/></td>
          <td><c:out value="${e.managerName}"/></td>
          <td>${e.score == null ? '—' : e.score}</td>
          <td><span class="badge badge-${e.status == 'VALIDATED' ? 'validated' : 'draft'}">${e.status}</span></td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

<jsp:include page="../common/footer.jsp"/>
