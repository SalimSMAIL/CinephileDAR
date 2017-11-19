<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <link rel="stylesheet" href="js/jPlayer/jplayer.flat.css" type="text/css" />
  <link rel="stylesheet" href="css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="css/animate.css" type="text/css" />
  <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="css/simple-line-icons.css" type="text/css" />
  <link rel="stylesheet" href="css/font.css" type="text/css" />
  <link rel="stylesheet" href="css/app.css" type="text/css" />  
</head>
<body class="bg-info dker">
  <section id="content" class="m-t-lg wrapper-md animated fadeInDown">
    <div class="container aside-xl">
      <a class="navbar-brand block" href="index.html"><span class="h1 font-bold"
      style="font-family: 'CollegeMovie';  color: white;  font-weight: normal;font-size: 60px;">DCinephilia</span></a>
      <section class="m-b-lg">
        <header class="wrapper text-center">
          <strong style="font-style: italic; font-family: Candara; font-size: 16px;" >Rejoinez la communauté cinéphile</strong>
        </header>
        <form id="register_form" >
          <div class="form-group">
            <input placeholder="Prénom" id="firstname" name="firstname" data-required="true" class="form-control rounded input-lg text-center no-border">
          	<p style="display:none" id="firstname_error"></p>
          </div>
          <div class="form-group">
            <input placeholder="Nom" id="lastname" name="lastname" class="form-control rounded input-lg text-center no-border">
         	 <p style="display:none" id="lastname_error"></p>
          </div>
          
          <div class="form-group">
            <input placeholder="Nom d'utilisateur" id="username" name="username" class="form-control rounded input-lg text-center no-border">
          	<p style="display:none" id="username_error"></p>
          </div>
          
          <div class="form-group">
            <input data-type="email" placeholder="Email" id="email" name="email" class="form-control rounded input-lg text-center no-border">
          	<p style="display:none" id="email_error"></p>
          </div>
          <div class="form-group">
             <input type="password" placeholder="Mot de passe" id="password" name="password" class="form-control rounded input-lg text-center no-border">
        	 <p style="display:none" id="password_error"></p>
		  </div>
          <div class="form-group">
             <input type="password" placeholder="Confirmer le mot de passe" id="confirmed_password" name="confirmed_password" class="form-control rounded input-lg text-center no-border">
        	  <p style="display:none" id="confirmed_password_error"></p>
          </div>
          <div class="form-group" style="text-align-last:center;" >
          <select data-required="true" id="gender" name="gender" class="form-control rounded input-lg text-center no-border">
                                <option value="femme">Femme</option>
                                <option value="homme">Homme</option>
         </select>
          </div>
        
          <div class="form-group">
            <textarea placeholder="Description" id="description" name="description" class="form-control rounded input-lg text-center no-border"></textarea>
          	<p style="display:none" id="description_error"></p>
          </div>
          
          <div class="form-group">
            <input placeholder="Adresse" id="address" name="address" class="form-control rounded input-lg text-center no-border">
          	<p style="display:none" id="address_error"></p>
          </div>
          
          
          <button type="submit" id="submit_inscription" onclick="CreateCinephileAccount();return false;" class="btn btn-lg btn-warning lt b-white b-2x btn-block btn-rounded"><i class="icon-arrow-right pull-right"></i><span class="m-r-n-lg">S'inscrire</span></button>
          <div class="line line-dashed"></div>
          <p class="text-muted text-center"><small>Vous avez déjà un compte ?</small></p>
          <a href="Login.jsp" class="btn btn-lg btn-info btn-block btn-rounded">Se connecter</a>
        </form>
      </section>
    </div>
  </section>
  <!-- footer -->
  <footer id="footer">
    <div class="text-center padder clearfix">
      <p>
        <small>DCinephila - Réseau social pour les cinéphiles<br>&copy; 2017</small>
      </p>
    </div>
  </footer>
  <!-- / footer -->
  <script src="js/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="js/bootstrap.js"></script>
  <!-- App -->
  <script src="js/app.js"></script>  
  <script src="js/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="js/app.plugin.js"></script>
  <script type="text/javascript" src="js/jPlayer/jquery.jplayer.min.js"></script>
  <script type="text/javascript" src="js/jPlayer/add-on/jplayer.playlist.min.js"></script>
  <script type="text/javascript" src="js/jPlayer/demo.js"></script>
  <script type="text/javascript" src="js/cinephile.js"></script>
 
  
</body>
</html>