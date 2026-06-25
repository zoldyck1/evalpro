<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="p" value="${pageContext.request.requestURI}"/>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>${pageTitle != null ? pageTitle : 'Évaluations'}</title>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="${ctx}/css/style.css">
</head>
<body>
<nav class="navbar">
  <a href="${ctx}/${sessionScope.user.roleName == 'RH_ADMIN' ? 'admin' : (sessionScope.user.roleName == 'MANAGER' ? 'manager' : 'employee')}/dashboard" class="brand">
    <svg class="brand-icon" viewBox="0 0 24 24"><path d="M12 20V10"/><path d="M18 20V4"/><path d="M6 20v-4"/></svg>
    ÉvalPro
  </a>

  <div class="nav-links" id="navLinks">
    <c:if test="${sessionScope.user.roleName == 'EMPLOYEE'}">
      <a href="${ctx}/employee/dashboard" class="nav-link${p.endsWith('/employee/dashboard') ? ' active' : ''}">
        <svg class="nav-icon" viewBox="0 0 24 24"><rect x="3" y="3" width="7" height="7" rx="1"/><rect x="14" y="3" width="7" height="7" rx="1"/><rect x="3" y="14" width="7" height="7" rx="1"/><rect x="14" y="14" width="7" height="7" rx="1"/></svg>
        Tableau de bord
      </a>
      <a href="${ctx}/employee/profile" class="nav-link${p.endsWith('/employee/profile') ? ' active' : ''}">
        <svg class="nav-icon" viewBox="0 0 24 24"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/><circle cx="12" cy="7" r="4"/></svg>
        Profil
      </a>
      <a href="${ctx}/employee/results" class="nav-link${p.endsWith('/employee/results') ? ' active' : ''}">
        <svg class="nav-icon" viewBox="0 0 24 24"><path d="M22 12h-4l-3 9L9 3l-3 9H2"/></svg>
        Mes résultats
      </a>
    </c:if>

    <c:if test="${sessionScope.user.roleName == 'MANAGER'}">
      <a href="${ctx}/manager/dashboard" class="nav-link${p.endsWith('/manager/dashboard') ? ' active' : ''}">
        <svg class="nav-icon" viewBox="0 0 24 24"><rect x="3" y="3" width="7" height="7" rx="1"/><rect x="14" y="3" width="7" height="7" rx="1"/><rect x="3" y="14" width="7" height="7" rx="1"/><rect x="14" y="14" width="7" height="7" rx="1"/></svg>
        Tableau de bord
      </a>
      <a href="${ctx}/manager/team" class="nav-link${p.endsWith('/manager/team') ? ' active' : ''}">
        <svg class="nav-icon" viewBox="0 0 24 24"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"/><circle cx="9" cy="7" r="4"/><path d="M23 21v-2a4 4 0 0 0-3-3.87"/><path d="M16 3.13a4 4 0 0 1 0 7.75"/></svg>
        Équipe
      </a>
      <a href="${ctx}/manager/dashboard" class="nav-link${p.endsWith('/manager/dashboard') ? ' active' : ''}">
        <svg class="nav-icon" viewBox="0 0 24 24"><path d="M12 20h9"/><path d="M16.5 3.5a2.1 2.1 0 0 1 3 3L7 19l-4 1 1-4Z"/><path d="m15 5 4 4"/></svg>
        Évaluer
      </a>
      <a href="${ctx}/manager/history" class="nav-link${p.endsWith('/manager/history') ? ' active' : ''}">
        <svg class="nav-icon" viewBox="0 0 24 24"><circle cx="12" cy="12" r="10"/><polyline points="12 6 12 12 16 14"/></svg>
        Historique
      </a>
    </c:if>

    <c:if test="${sessionScope.user.roleName == 'RH_ADMIN'}">
      <a href="${ctx}/admin/dashboard" class="nav-link${p.endsWith('/admin/dashboard') ? ' active' : ''}">
        <svg class="nav-icon" viewBox="0 0 24 24"><rect x="3" y="3" width="7" height="7" rx="1"/><rect x="14" y="3" width="7" height="7" rx="1"/><rect x="3" y="14" width="7" height="7" rx="1"/><rect x="14" y="14" width="7" height="7" rx="1"/></svg>
        Tableau de bord
      </a>
      <a href="${ctx}/admin/employees" class="nav-link${p.endsWith('/admin/employees') ? ' active' : ''}">
        <svg class="nav-icon" viewBox="0 0 24 24"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/><circle cx="12" cy="7" r="4"/></svg>
        Employés
      </a>
      <a href="${ctx}/admin/managers" class="nav-link${p.endsWith('/admin/managers') ? ' active' : ''}">
        <svg class="nav-icon" viewBox="0 0 24 24"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"/><circle cx="9" cy="7" r="4"/><path d="M23 21v-2a4 4 0 0 0-3-3.87"/><path d="M16 3.13a4 4 0 0 1 0 7.75"/></svg>
        Managers
      </a>
      <a href="${ctx}/admin/evaluations" class="nav-link${p.endsWith('/admin/evaluations') ? ' active' : ''}">
        <svg class="nav-icon" viewBox="0 0 24 24"><path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"/><polyline points="14 2 14 8 20 8"/><line x1="16" y1="13" x2="8" y2="13"/><line x1="16" y1="17" x2="8" y2="17"/></svg>
        Évaluations
      </a>
      <a href="${ctx}/admin/reports" class="nav-link${p.endsWith('/admin/reports') ? ' active' : ''}">
        <svg class="nav-icon" viewBox="0 0 24 24"><line x1="18" y1="20" x2="18" y2="10"/><line x1="12" y1="20" x2="12" y2="4"/><line x1="6" y1="20" x2="6" y2="14"/></svg>
        Rapports
      </a>
    </c:if>
  </div>

  <div class="nav-user">
    <button class="user-toggle" id="userToggle" type="button" aria-label="Menu utilisateur">
      <span class="user-avatar">
        <svg viewBox="0 0 24 24"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/><circle cx="12" cy="7" r="4"/></svg>
      </span>
      <span class="user-email"><c:out value="${sessionScope.user.email}"/></span>
      <svg class="chevron-icon" viewBox="0 0 24 24"><polyline points="6 9 12 15 18 9"/></svg>
    </button>
    <div class="user-dropdown" id="userDropdown" role="menu">
      <div class="dropdown-header">
        <span class="dropdown-role">
          <c:choose>
            <c:when test="${sessionScope.user.roleName == 'RH_ADMIN'}">Administrateur RH</c:when>
            <c:when test="${sessionScope.user.roleName == 'MANAGER'}">Manager</c:when>
            <c:otherwise>Employé</c:otherwise>
          </c:choose>
        </span>
        <span class="dropdown-email"><c:out value="${sessionScope.user.email}"/></span>
      </div>
      <div class="dropdown-divider"></div>
      <a href="${ctx}/logout" class="dropdown-item dropdown-item-danger">
        <svg class="dropdown-icon" viewBox="0 0 24 24"><path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"/><polyline points="16 17 21 12 16 7"/><line x1="21" y1="12" x2="9" y2="12"/></svg>
        Déconnexion
      </a>
    </div>
  </div>

  <button class="mobile-toggle" id="mobileToggle" type="button" aria-label="Menu">
    <span></span><span></span><span></span>
  </button>
</nav>

<div class="container">

<script>
(function() {
  var t = document.getElementById('userToggle'),
      d = document.getElementById('userDropdown'),
      m = document.getElementById('mobileToggle'),
      n = document.getElementById('navLinks');
  function toggle(el, open) { el.classList.toggle('open', open) }
  if (t && d) t.addEventListener('click', function(e) {
    e.stopPropagation();
    var o = !d.classList.contains('open');
    toggle(d, o);
    t.setAttribute('aria-expanded', o);
  });
  document.addEventListener('click', function() {
    if (d && d.classList.contains('open')) toggle(d, false);
  });
  if (d) d.addEventListener('click', function(e) { e.stopPropagation() });
  if (m && n) m.addEventListener('click', function(e) {
    e.stopPropagation();
    var o = !n.classList.contains('open');
    toggle(n, o);
    m.classList.toggle('active', o);
    if (d && d.classList.contains('open')) toggle(d, false);
  });
  document.addEventListener('keydown', function(e) {
    if (e.key === 'Escape') { toggle(d, false); toggle(n, false); m.classList.remove('active') }
  });
  if (n) n.addEventListener('click', function(e) {
    if (e.target.closest('a')) { toggle(n, false); m.classList.remove('active') }
  });
})();
</script>
