<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Employés"/>
<jsp:include page="../common/header.jsp"/>

<h1>Employés</h1>
<p><a class="btn" href="${pageContext.request.contextPath}/admin/employees?action=new">+ Ajouter un employé</a></p>

<div class="card">
  <table>
    <thead><tr><th>Nom</th><th>Email</th><th>Poste</th><th>Département</th><th>Manager</th><th></th></tr></thead>
    <tbody>
      <c:forEach var="e" items="${employees}">
        <tr>
          <td><c:out value="${e.fullName}"/></td>
          <td><c:out value="${e.email}"/></td>
          <td><c:out value="${e.position}"/></td>
          <td><c:out value="${e.department}"/></td>
          <td><c:out value="${empty e.managerName ? '—' : e.managerName}"/></td>
          <td>
            <a class="btn btn-sm" href="${pageContext.request.contextPath}/admin/employees?action=edit&id=${e.id}">Modifier</a>
            <form method="post" action="${pageContext.request.contextPath}/admin/employees" style="display:inline;"
                  onsubmit="return confirm('Supprimer cet employé et son compte ?');">
              <input type="hidden" name="action" value="delete">
              <input type="hidden" name="id" value="${e.id}">
              <button class="btn btn-sm btn-danger" type="submit">Supprimer</button>
            </form>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

<jsp:include page="../common/footer.jsp"/>
