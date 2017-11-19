 $(document).ready(function(){
	 Usernamedefinition();
	 GetMostPopularMovies(); 
	 GetMostPopularTvShows();
	 GetTopRatedMovies(); 
	 SeeLists(); 
});

 function Usernamedefinition()
 {
 	$.ajax({
 		type: "GET",
 		url : "seeProfile",
 		dataType : 'json',
 		success : function(data) {
 			var DivContainer = $("#displayUser");
 			var DivContUser = $("#theusername");
 			if (data.response==200){
 				console.log("entrer par la");
 				if (data.gender=="femme"){
 					var DivList = '<img src="images/female.png" alt="...">';
 				}
 				if(data.gender=="homme"){
 					var DivList = '<img src="images/male.png" alt="...">';
 				}
 				DivContainer.append(DivList); 
 				DivContUser.append(data.username);
 			}
 			
 		},
 		error : function(XHR, testStatus, errorThrown) 
 		{
 			console.log("status: " + XHR.status + ", erreur: " + XHR.responseText);
 		}
 	});
 }

function GetMostPopularMovies()
{
	$.ajax({
		type: "GET",
		url : "SeeMostPopularMovies",
		dataType : 'json',
		success : function(data) {
			if (data.message==1)
			{
				var most_popular_movies = data.popular_movies;
				
				if(most_popular_movies.length == 0)
				{
					console.log("la liste contient 0 items"); 
				} 
				else 
				{
					for(i=0; i<18; i++)
						{
								 DisplayMovie(most_popular_movies[i]); 
						}	
				}
			}
			
		},
		error : function(XHR, testStatus, errorThrown) 
		{
			console.log("status: " + XHR.status + ", erreur: " + XHR.responseText);
		}
	});
}

function GetTopRatedMovies()
{
	$.ajax({
		type: "GET",
		url : "SeeTopRatedMovies",
		dataType : 'json',
		success : function(data) {
			if (data.response==200)
			{
				var top_rated_movies = data.top_movies;
				
				if(top_rated_movies.length == 0)
				{
					console.log("la liste contient 0 items"); 
				} 
				else 
				{
					for(i=0; i<10; i++)
						{
							if (top_rated_movies[i].original_language=="en") DisplayTopMovie(top_rated_movies[i]); 
						}	
				}
			}
			
		},
		error : function(XHR, testStatus, errorThrown) 
		{
			console.log("status: " + XHR.status + ", erreur: " + XHR.responseText);
		}
	});
}




function GetMostPopularTvShows()
{
	$.ajax({
		type: "GET",
		url : "SeeMostPopularTvShows",
		dataType : 'json',
		success : function(data) {
			if (data.response==200)
			{
				var most_popular_tvshows = data.popular_tvshows;
				
				if(most_popular_tvshows.length == 0)
				{
					console.log("la liste contient 0 items"); 
				} 
				else 
				{
					for(i=0; i<most_popular_tvshows.length; i++)
						{
						 first_air_date = most_popular_tvshows[i].first_air_date.split("-",1); 
						 if (parseInt(first_air_date)>=2007)
						 {
							 DisplayTvShow(most_popular_tvshows[i]);
						 }
						}	
				}
			}
			
		},
		error : function(XHR, testStatus, errorThrown) 
		{
			console.log("status: " + XHR.status + ", erreur: " + XHR.responseText);
		}
	});
}



function DisplayMovie(Movie)
{
	var DivMostPopularMovies = $("#MostPopularMovies"); 
	var DivMovie = '<div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">'+ '<div class="item movie" style="cursor:pointer" onClick="DetailsMovie('+Movie.id+')">'+'<div class="pos-rlt">'+
	'<div class="item-overlay opacity r r-2x bg-black">'+'<div class="center text-center m-t-n"  style="cursor:pointer">'+'<a>'
	+'<i class="icon-control-play i-2x"></i></a></div></div>'+
	'<div class="top"><span class="pull-right m-t-sm m-r-sm badge bg-white">'+Movie.vote_average+'/10</span> <span class="pull-right m-t-n-xs m-r-sm text-white">'+ 
	'<i class="fa fa-bookmark i-lg"></i></span></div>' 
	+'<a href="#"><img src="https://image.tmdb.org/t/p/w500'+ Movie.poster_path+'"  alt="" height="257px" width="173px" class="r r-2x img-full"></a>'+
	'</div><div class="padder-v"><a class="text-ellipsis" style="fontèsize:14px">'+Movie.original_title+'</a></div></div></div>';
	DivMostPopularMovies.append(DivMovie); 	
}

function DisplayTopMovie(Movie)
{
	var DivTopRatedMovies = $("#TopRatedMovies"); 
	var DivMovie = '<a style="cursor:pointer" onClick="DetailsMovie('+Movie.id+')" class="list-group-item clearfix"><span class="pull-right h2 text-muted m-l"></span>'+
        '<span class="pull-left thumb-sm m-r" style="width:80px"><img src="https://image.tmdb.org/t/p/w500'+Movie.poster_path+'" alt="..."></span><span class="clear">'+
        '<span style="font-weight:bold">'+Movie.original_title+'</span><small class="text-muted" style="color:#428bca!important">    ('+Movie.vote_average+'/10)</small>'+
        '<small class="text-muted clear text-ellipsis">'+Movie.release_date+'</small>'+
        '<small class="text-muted">'+Movie.overview.substring(0,150)+'...</small></span></a>';
	DivTopRatedMovies.append(DivMovie); 	
}


function DisplayTvShow(Tvshow)
{
	var DivMostPopularTvShows = $("#MostPopularTvShows"); 
	var DivTvshow = '<div class="col-xs-6 col-sm-3"><div class="item tvshow" style="cursor:pointer" onClick="DetailsTvshow('+Tvshow.id+')" ><div class="pos-rlt">'+
	'<div class="item-overlay opacity r r-2x bg-black"><div class="center text-center m-t-n"><a  style="cursor:pointer">'
	+'<i class="icon-control-play i-2x"></i></a></div></div>'+
	'<div class="top"><span class="pull-right m-t-sm m-r-sm badge bg-white">'+Tvshow.vote_average+'/10</span> <span class="pull-right m-t-n-xs m-r-sm text-white">'+ 
	'<i class="fa fa-bookmark i-lg"></i></span></div>'+
    '<img src="https://image.tmdb.org/t/p/w185'+Tvshow.poster_path+'"alt="" height="241px" width="160px" class="r r-2x img-full"></div>'+
    '<div class="padder-v"><a class="text-ellipsis">'+Tvshow.original_name+'</a></div></div></div>';
	DivMostPopularTvShows.append(DivTvshow); 	
}


function DetailsMovie(id_movie)
{
	window.location=('MovieDetails.jsp?id_movie='+id_movie);
}

function DetailsTvshow(id_tvshow)
{
	window.location=('TvShowDetails.jsp?id_tvshow='+id_tvshow);
}



function SeeLists(){

	$.ajax({
		type: "GET",
		url : "SeeCinephileLists",
		dataType : 'json',
		success : function(data) {

			var resultat = data;
			
			if (resultat.response==200)
			{ 
				for (i=0; i<resultat.lists_cinephile.length; i++)
					displayList(resultat.lists_cinephile[i]); 
			}
		},
		error : function(XHR, testStatus, errorThrown) 
		{
			console.log("status: " + XHR.status + ", erreur: " + XHR.responseText);
		}
	});
}

function displayList(list)
{
	
	var DivContainer = $("#lists");
	var DivList = '<li><a onClick="seeListElements('+list.id+')" style="cursor:pointer"><i class="icon-book-open icon"></i>'+
    '<span>'+list.name+'</span></a></li>'; 	
    DivContainer.append(DivList); 
}

function seeListElements(id_list)
{
	window.location = ("ListElements.jsp?id_list="+id_list);
}


function SearchEvent()
{
	var query = $('#search_keywords').val(); 
	window.location=('SearchResults.jsp?query='+query); 
}

function SearchCinephileEvent()
{
	var query = $('#search_cinephile').val(); 
	window.location=('SearchCinephileResults.jsp?query='+query); 
}

function Logout()
{
	$.ajax({
		type: "POST",
		url : "logout",
		success : function() {
			window.location=('Login.html'); 
		},
		error : function(XHR, testStatus, errorThrown) 
		{
			console.log("status: " + XHR.status + ", erreur: " + XHR.responseText);
		}
	});
}

