<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Managers"/>
<jsp:include page="../common/header.jsp"/>

<h1>Managers</h1>
<p><a class="btn" href="${pageContext.request.contextPath}/admin/managers?action=new">+ Ajouter un manager</a></p>

<div class="card">
  <table>
    <thead><tr><th>Nom</th><th>Email</th><th>Département</th><th></th></tr></thead>
    <tbody>
      <c:forEach var="m" items="${managers}">
        <tr>
          <td><c:out value="${m.fullName}"/></td>
          <td><c:out value="${m.email}"/></td>
          <td><c:out value="${m.department}"/></td>
          <td>
            <a class="btn btn-sm" href="${pageContext.request.contextPath}/admin/managers?action=edit&id=${m.id}">Modifier</a>
            <form method="post" action="${pageContext.request.contextPath}/admin/managers" style="display:inline;"
                  onsubmit="return confirm('Supprimer ce manager et son compte ?');">
              <input type="hidden" name="action" value="delete">
              <input type="hidden" name="id" value="${m.id}">
              <button class="btn btn-sm btn-danger" type="submit">Supprimer</button>
            </form>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

<jsp:include page="../common/footer.jsp"/>
