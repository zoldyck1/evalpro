<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Mon Équipe"/>
<jsp:include page="../common/header.jsp"/>

<h1>Mon Équipe</h1>
<div class="card">
  <c:choose>
    <c:when test="${empty team}"><p>Aucun membre de l'équipe assigné.</p></c:when>
    <c:otherwise>
      <table>
        <thead><tr><th>Nom</th><th>Email</th><th>Poste</th><th>Département</th><th>Embauché</th></tr></thead>
        <tbody>
          <c:forEach var="e" items="${team}">
            <tr>
              <td><c:out value="${e.fullName}"/></td>
              <td><c:out value="${e.email}"/></td>
              <td><c:out value="${e.position}"/></td>
              <td><c:out value="${e.department}"/></td>
              <td>${e.hireDate}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:otherwise>
  </c:choose>
</div>

<jsp:include page="../common/footer.jsp"/>
