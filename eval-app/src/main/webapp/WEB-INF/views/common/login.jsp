<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Login | Évaluations</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="login-wrap">
  <div class="login-card">
    <h1>Application de Gestion<br>des Évaluations</h1>
    <c:if test="${not empty error}">
      <div class="alert alert-error"><c:out value="${error}"/></div>
    </c:if>
    <form method="post" action="${pageContext.request.contextPath}/login">
      <div class="field">
        <label for="email">Email</label>
        <input type="email" id="email" name="email" required autofocus>
      </div>
      <div class="field">
        <label for="password">Mot de passe</label>
        <input type="password" id="password" name="password" required>
      </div>
      <button class="btn" type="submit" style="width:100%;">Se connecter</button>
    </form>
    <div class="demo">
      <strong>Comptes démo</strong> (mot de passe : <code>demo123</code>):<br>
      admin@demo.com · manager@demo.com · employee@demo.com
    </div>
  </div>
</div>
</body>
</html>
