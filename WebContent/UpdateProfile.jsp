<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import ="DAO.CinephileDAO" %> 
   <%@ page import ="Beans.Cinephile" %> 
<!DOCTYPE html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>DCinephilia | Réseau social des cinéphiles</title>
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <link rel="stylesheet" href="js/jPlayer/jplayer.flat.css" type="text/css" />
  <link rel="stylesheet" href="css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="css/animate.css" type="text/css" />
  <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="css/simple-line-icons.css" type="text/css" />
  <link rel="stylesheet" href="css/font.css" type="text/css" />
  <link rel="stylesheet" href="css/app.css" type="text/css" />
  <link rel="stylesheet" href="css/style_card.css" type="text/css" />  
</head>
<body class="">
  <section class="vbox">
    <header class="bg-white-only header header-md navbar navbar-fixed-top-xs">
      <div class="navbar-header aside bg-info">
        <a class="btn btn-link visible-xs" data-toggle="class:nav-off-screen,open" data-target="#nav,html">
          <i class="icon-list"></i>
        </a>
        <a href="Home.html" class="navbar-brand text-lt">
          <i class="icon-film"></i>
          <img src="images/logo.png" alt="." class="hide">
          <span class="hidden-nav-xs m-l-sm" style="font-family: 'CollegeMovie'; font-size: 30px; font-weight: normal;">DCinephilia</span>
        </a>
        <a class="btn btn-link visible-xs" data-toggle="dropdown" data-target=".user">
          <i class="icon-settings"></i>
        </a>
      </div>      <ul class="nav navbar-nav hidden-xs">
        <li>
          <a href="#nav,.navbar-header" data-toggle="class:nav-xs,nav-xs" class="text-muted">
            <i class="fa fa-indent text"></i>
            <i class="fa fa-dedent text-active"></i>
          </a>
        </li>
      </ul>
      <form class="navbar-form navbar-left input-s-lg m-t m-l-n-xs hidden-xs">
        <div class="form-group">
          <div class="input-group">
            <span class="input-group-btn">
              <button type="submit" onclick="SearchEvent();return false;" class="btn btn-sm bg-white btn-icon rounded"><i class="fa fa-search"></i></button>
            </span>
            <input type="text" id="search_keywords" class="form-control input-sm no-border rounded" placeholder="Chercher film, série...">
          </div>
        </div>
      </form>
      <div class="navbar-right ">
        <ul class="nav navbar-nav m-n hidden-xs nav-user user">
           <li class="dropdown">
          <% 
				CinephileDAO cinephile_dao = new CinephileDAO();
				Cinephile cinephile = cinephile_dao.GetCinephileById(Integer.valueOf(String.valueOf(session.getAttribute("id_cinephile")))); 
				pageContext.setAttribute("username", cinephile.getUsername());
				if (cinephile.getGender().equals("femme")) pageContext.setAttribute("picture", "images/female.png");
				else pageContext.setAttribute("picture", "images/male.png");
				pageContext.setAttribute("firstname", cinephile.getFirstname());
				pageContext.setAttribute("lastname", cinephile.getLastname());
				pageContext.setAttribute("description", cinephile.getDescription());
				pageContext.setAttribute("address", cinephile.getAdress());
				pageContext.setAttribute("email", cinephile.getEmail());
				%>
            <a style="cursor:pointer" class="dropdown-toggle bg clear" data-toggle="dropdown">
              <span class="thumb-sm avatar pull-right m-t-n-sm m-b-n-sm m-l-sm">
                <img src=${picture} alt="...">
              </span>
              	${username}<b class="caret"></b>
            </a>

            <ul class="dropdown-menu animated fadeInRight">            
               <li>
                <a style="cursor:pointer" href="CinephileProfile.jsp">Profile</a>
              </li>
              <li class="divider"></li>
              <li>
                <a onclick="Logout();return false;" style="cursor:pointer" data-toggle="ajaxModal" >Déconnexion</a>
              </li>
            </ul>
          </li>
         </ul>
      </div>      
    </header>
    <section>
      <section class="hbox stretch">
        <!-- .aside -->
        <aside class="bg-black dk aside hidden-print" id="nav">          
          <section class="vbox">
            <section class="w-f-md scrollable">
              <div class="slim-scroll" data-height="auto" data-disable-fade-out="true" data-distance="0" data-size="10px" data-railOpacity="0.2">
                

  <!-- nav -->                 
                <nav class="nav-primary hidden-xs">
                  <ul class="nav bg clearfix">
                    <li class="hidden-nav-xs padder m-t m-b-sm text-xs text-muted">
                    <div class="input-group">
		            <span class="input-group-btn">
		              <button type="submit" onclick="SearchCinephileEvent();return false;" class="btn btn-sm bg-empty no-border btn-icon"><i class="fa fa-search"></i></button>
		            </span>
           			 <input type="text" id="search_cinephile" class="form-control input-sm text-white bg-empty  b-b b-dark no-border" placeholder="Chercher un cinéphile"></div>  
                    </li>
                    <li>
                      <a href="MoviesByGenre.jsp?id_genre=28">
                        <i class="icon-social-youtube icon text-success"></i>
                        <span class="font-bold">Films par genre</span>
                      </a>
                    </li>
                    <li>
                      <a href="MessagesCinephile.jsp">
                        <i class="fa fa-comment text-primary-lter"></i>
                        <span class="font-bold">Mes messages</span>
                      </a>
                    </li>
                    
                    <li>
                      <a href="Seances.html">
                        <i class="icon-clock icon  text-info"></i>
                        <span class="font-bold">Séances films</span>
                      </a>
                    </li>
                    
                    <li>
                      <a href="MapCinemasIleDeFrance.html" >
                        <i class="fa fa-map-marker icon  text-warning"></i>
                        <span class="font-bold">Cinémas de l'IDF</span>
                      </a>
                    </li>
                    
                    <li>
                      <a href="Events.html">
                        <i class="icon-calendar icon text-success"></i>
                        <b class="badge bg-success pull-right">6</b>
                        <span class="font-bold">Evénements</span>
                      </a>
                    </li>
                    <li class="m-b hidden-nav-xs"></li>
                  </ul>
                 <ul class="nav text-sm" id="lists">
                    <li class="hidden-nav-xs padder m-t m-b-sm text-xs text-muted">
                      <span class="pull-right"><a href="CinephileProfile.jsp"><i class="icon-plus i-lg"></i></a></span>
                      Mes listes
                    </li>
                    
                    
                  </ul>
                </nav>
                <!-- / nav -->
              </div>
            </section>
            
            <footer class="footer hidden-xs no-padder text-center-nav-xs">
              <div class="bg hidden-xs ">
                  <div class="dropdown dropup wrapper-sm clearfix">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                      <span class="thumb-sm avatar pull-left m-l-xs">                        
                        <img src="${picture}" class="dker" alt="...">
                        <i class="on b-black"></i>
                      </span>
                      <span class="hidden-nav-xs clear">
                        <span class="block m-l">
                          <strong class="font-bold text-lt">${username}</strong> 
                          <b class="caret"></b>
                        </span>
                      </span>
                    </a>
                    <ul class="dropdown-menu animated fadeInRight aside text-left">                      

                      <li>
		                <a style="cursor:pointer" href="CinephileProfile.jsp">Profile</a>
		              </li>
		              <li class="divider"></li>
		              <li>
		                <a onclick="Logout();return false;" style="cursor:pointer" data-toggle="ajaxModal" >Déconnexion</a>
		              </li>
                    </ul>
                  </div>
                </div>            </footer>
          </section>
        </aside>
        <!-- /.aside -->
        <section id="content">
          <section class="vbox">
            <section class="scrollable">
              <section class="hbox stretch">
                <aside class="aside-lg bg-light lter b-r">
                  <section class="vbox">
                    <section class="scrollable">
                     
                          <div class="row">
					                <div class="col-xs-12">
					                  <form class="form-horizontal" data-validate="parsley">
					                    <section class="panel panel-default">
					                      <header class="panel-heading">
					                       <h2 class="font-thin m-b">Modifier profil</h2>
					                      </header>
					                      <div class="panel-body">                    
					                        
					                        <div class="line line-dashed b-b line-lg pull-in"></div>
					                        <div class="form-group">
					                          <label class="col-sm-3 control-label">Prenom</label>
					                          <div class="col-sm-9">
					                            <input id='firstnameup' type="text" data-maxlength="60" class="form-control" placeholder="${firstname}">
					                          </div>
					                        </div>
					                        <div class="line line-dashed b-b line-lg pull-in"></div>
					                        <div class="form-group">
					                          <label class="col-sm-3 control-label">Nom</label>
					                          <div class="col-sm-9">
					                            <input id='lastnameup' type="text" data-maxlength="60" class="form-control" placeholder="${lastname}">
					                          </div>
					                        </div>
					                        <div class="line line-dashed b-b line-lg pull-in"></div>
					                        <div class="form-group">
					                          <label class="col-sm-3 control-label">Pseudonyme</label>
					                          <div class="col-sm-9">
					                            <input id='usernameup' type="text" data-maxlength="60" class="form-control" placeholder="${username}">
					                          	<p style="display:none" id="username_errorup"></p>
					                          </div>
					                        </div>
					                        <div class="line line-dashed b-b line-lg pull-in"></div>
					                        <div class="form-group">
					                          <label class="col-sm-3 control-label">Description</label>
					                          <div class="col-sm-9">
					                            <textarea id='descriptionup' type="text" class="form-control" placeholder="${description}"></textarea>
					                          </div>
					                        </div>
					                        <div class="line line-dashed b-b line-lg pull-in"></div>
					                        <div class="form-group">
					                        <div class="col-sm-9" style="text-align-last:center;">
					                        
							                    
							                <div class="radio i-checks">
					                          <label>
					                            <input id='gender' type="radio" name="optionsRadios" value="femme" <% if (cinephile.getGender().equals("femme")) pageContext.setAttribute("isChecked", "checked"); %> ${isChecked}>
					                            <i></i>
					                            Femme
					                          </label>
					                        </div>
					                        <div class="radio i-checks">
					                          <label>
					                            <input id='gender' type="radio" name="optionsRadios" value="homme" <% if (cinephile.getGender().equals("homme")) pageContext.setAttribute("isCheckedH", "checked"); %> ${isCheckedH} >
					                            <i></i>
					                            Homme
					                          </label>
					                        </div>
							                    
							                        
					                        </div>
					                        	
					                        	
					                      </div>
					                        <div class="line line-dashed b-b line-lg pull-in"></div>
					                        <div class="form-group">
					                          <label class="col-sm-3 control-label">Adresse</label>
					                          <div class="col-sm-9">
					                            <input id ='addressup' type="text" class="form-control" placeholder="${address}">
					                          </div>
					                        </div>
					       
					       					<div class="line line-dashed b-b line-lg pull-in"></div>
					                        <div class="form-group">
					                          <label class="col-sm-3 control-label">Email</label>
					                          <div class="col-sm-9">
					                            <input id='emailup' type="text" data-maxlength="60" class="form-control" placeholder="${email}"/>
					                          	<p style="display:none" id="email_errorup"></p>
					                          </div>
					                        </div>
					                      <div class="line line-dashed b-b line-lg pull-in"></div>
					                        <div class="form-group">
					                          <label class="col-sm-3 control-label">Mot de passe</label>
					                          <div class="col-sm-9">
					                            <input id ='passwordup' type="password" data-maxlength="60" class="form-control" placeholder="">
					                            <p style="display:none" id="password_errorup"></p>
					                          </div>
					                        </div>
					                      
					                      <div class="line line-dashed b-b line-lg pull-in"></div>
					                        <div class="form-group">
					                          <label class="col-sm-3 control-label">Confirmation</label>
					                          <div class="col-sm-9">
					                            <input id ='confirmationup' type="password" data-maxlength="60" class="form-control" placeholder="">
					                             <p style="display:none" id="confirmed_password_errorup"></p>
					                          </div>
					                        </div>
					                      </div>
					                      <footer class="panel-footer text-right bg-light lter">
					                        <button onclick="updateUser();return false;" type="submit"  class="btn btn-success btn-s-xs">Enregistrer les modifications</button>
					                      </footer>
					                    </section>
					                  </form>
					                </div>
                      </div>
                    </section>
                  </section>
                </aside>
                
                
                
              </section>
            </section>
          </section>
          <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen,open" data-target="#nav,html"></a>
        </section>
      </section>
    </section>    
  </section>
  <script src="js/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="js/bootstrap.js"></script>
  <!-- App -->
  <script src="js/app.js"></script>  
  <script src="js/slimscroll/jquery.slimscroll.min.js"></script>
  <script src="js/charts/easypiechart/jquery.easy-pie-chart.js"></script>
  <script src="js/app.plugin.js"></script>
  <script type="text/javascript" src="js/jPlayer/jquery.jplayer.min.js"></script>
  <script type="text/javascript" src="js/jPlayer/add-on/jplayer.playlist.min.js"></script>
  <script type="text/javascript" src="js/jPlayer/demo.js"></script>
  <script type="text/javascript" src="js/update_profile.js"></script>

</body>
</html>