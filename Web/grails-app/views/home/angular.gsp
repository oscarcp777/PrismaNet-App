<!doctype html>
<html lang="es-ES">
<head>
  <!-- bower:css -->
  <!-- endbower -->
  <!-- inject:css -->
  <link rel="stylesheet" href="/css/app.css">
  <!-- endinject -->
</head>
<body ng-app="blog">
<div class="container-fluid">
  <div class="row">
    <aside class="col-sm-3">
      <a ng-href="/new">Crear Entrada</a>
    </aside>
    <section class="col-sm-9" ng-view></section>
  </div>
</div>

  <!-- bower:js -->
  <script src="lib/jquery/dist/jquery.js"></script>
  <script src="lib/angular/angular.js"></script>
  <script src="lib/bootstrap/dist/js/bootstrap.js"></script>
  <!-- endbower -->
<!-- inject:js -->
<script src="/js/app.js"></script>
<!-- endinject -->
<script src="/js/app.js"></script>
<!-- endinject -->
</body>
</html>