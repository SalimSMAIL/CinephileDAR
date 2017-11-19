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
      <div class="navbar-header aside bg-info nav-xs">
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
        <aside class="bg-black dk nav-xs aside hidden-print" id="nav">          
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
            <section class="w-f-md" id="bjax-target" style="bottom:10px">
              <section class="hbox stretch">
                <!-- side content -->
                <aside class="aside bg-light dk" id="sidebar">
                  <section class="vbox animated fadeInUp">
                    <section class="scrollable hover">
                      <div class="list-group no-radius no-border no-bg m-t-n-xxs m-b-none auto">
                        <a style="cursor:pointer" onClick="DisplayMoviesGenre(28)" id="action" class="list-group-item">
                          Action
                        </a>
                        <a  style="cursor:pointer" onClick="DisplayMoviesGenre(12)" id="aventure"class="list-group-item">
                          Aventure
                        </a>
                        <a style="cursor:pointer" onClick="DisplayMoviesGenre(16)" id="animation" class="list-group-item">
                          Animation
                        </a>
                        <a style="cursor:pointer" onClick="DisplayMoviesGenre(35)" id="comedie" class="list-group-item">
                          Comédie
                        </a>
                        <a style="cursor:pointer" onClick="DisplayMoviesGenre(878)" id="fiction" class="list-group-item">
                          Science Fiction
                        </a>
                        <a style="cursor:pointer" onClick="DisplayMoviesGenre(53)" id="thriller" class="list-group-item">
                          Thriller
                        </a>
                        <a style="cursor:pointer" onClick="DisplayMoviesGenre(10751)" id="famille" class="list-group-item">
                          Famille
                        </a>
                        <a style="cursor:pointer" onClick="DisplayMoviesGenre(14)" id="fantastic" class="list-group-item">
                          Fantastic
                        </a>
                        <a style="cursor:pointer" onClick="DisplayMoviesGenre(36)" id="histoire" class="list-group-item">
                          Histoire
                        </a>
                        <a style="cursor:pointer" onClick="DisplayMoviesGenre(27)" id="horreur" class="list-group-item">
                          Horreur
                        </a>
                        <a style="cursor:pointer" onClick="DisplayMoviesGenre(10402)" id="musique" class="list-group-item">
                          Musique
                        </a>
                        <a style="cursor:pointer" onClick="DisplayMoviesGenre(10749)" id="romance" class="list-group-item">
                          Romance
                        </a>
                        <a style="cursor:pointer" onClick="DisplayMoviesGenre(10752)" id="guerre" class="list-group-item">
                          Guerre
                        </a>
                        <a style="cursor:pointer" onClick="DisplayMoviesGenre(9648)" id="mystere" class="list-group-item">
                          Mystère
                        </a>
                        <a style="cursor:pointer" onClick="DisplayMoviesGenre(80)" id="crime" class="list-group-item">
                          Crime
                        </a>
                        <a style="cursor:pointer" onClick="DisplayMoviesGenre(99)" id="documentaire" class="list-group-item">
                          Documentaire
                        </a>
                        <a style="cursor:pointer" onClick="DisplayMoviesGenre(18)" id="drama" class="list-group-item">
                          Drama
                        </a>
                        <a style="cursor:pointer" onClick="DisplayMoviesGenre(37)" id="western" class="list-group-item">
                          Western
                        </a>
                        
                      </div>
                    </section>
                  </section>
                </aside>
                <!-- / side content -->
                <section>
                  <section class="vbox">
                    <section class="scrollable padder-lg">
                      <h2 class="font-thin m-b" id="Genre"></h2>
                      <div class="row row-sm" id="MoviesGenre"> </div>
                     <div class="row m-t-lg m-b-lg">
                 	 <div class="col-sm-6">
                      <div class="wrapper-md r" style="background-color:#0078bb; color:white!important">
                        <a href="https://drive.google.com/open?id=16dQgm-MM95vE9oLlpsQt5F8iWNZf7Ow5">
                          <span class="h4 m-b-xs block" style="color:white"><i class="icon-cloud-download i-lg">  </i> Rapport</span>
                          <span class="text-muted" style="color:#ddd" >Télécharger le rapport et le manuel d'utilisation</span>
                        </a>
                      </div>
                    </div>
                    <div class="col-sm-6">
                      <div class="wrapper-md r" style="background-color:#2b3137; color:white!important">
                        <a href="https://github.com/Sbnll/CinephileDAR">
                          <span class="h4 m-b-xs block" style="color:white"><i class="fa fa-github i-lg">  </i> Code source</span>
                          <span class="text-muted" style="color:#ddd" >Voir le code source sur Github.</span>
                        </a>
                      </div>
                    </div>
                  </div>
                <!-- footer -->
				  <footer id="footer">
				    <div class="text-center padder clearfix">
				      <p>
				        <small>DCinephila - Réseau social pour les cinéphiles<br>&copy; 2017</small>
				      </p>
				    </div>
				  </footer>
				  <!-- / footer -->
                  
                    </section>                    
                  </section>
                </section>
              </section>
           
             </section>
            </section>
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
  <script src="js/app.plugin.js"></script>
  <script type="text/javascript" src="js/movies_genre.js"></script>
</body>
</html>