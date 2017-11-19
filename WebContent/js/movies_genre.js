$(document).ready(function(){
	
	MoviesGenre.ReadMovies();
	SelectItem();	
	SeeLists();
		
});

var MoviesGenre = {
		id_genre: '',

		ReadMovies: function(){
			console.log("MoviesGenre.ReadMovies");
			
			this.id_genre = GetURLParameter('id_genre');
			console.log('id_genre:'+this.id_genre)
			if(this.id_genre != undefined)
			{
				GetMovieGenres(this.id_genre);
			}
			else
			{
				alert("Genre non reconnu");
			}

		}
}

function GetMovieGenres(id_genre){
	
	$.ajax({
		type: "GET",
		url : "SeeMoviesByGenre",
		dataType : 'json',
		data : {
			"id_genre" : id_genre
			},
		success : function(data) {

			var resultat = data;
			if (resultat.response==200)
			{
				for(i=0; i<resultat.movies_genre.length; i++)
				{
					if (resultat.movies_genre[i].original_language=="en") DisplayMovie(resultat.movies_genre[i]);
				}
				
			}
		},
		error : function(XHR, testStatus, errorThrown) 
		{
			console.log("status: " + XHR.status + ", erreur: " + XHR.responseText);
		}
	});
}

function DisplayMoviesGenre(id_genre)
{
	window.location=('MoviesByGenre.jsp?id_genre='+id_genre);	
}

function DisplayMovie(Movie)
{
	var DivMovies = $("#MoviesGenre"); 
	var DivMovie = '<div class="col-xs-6 col-sm-4 col-md-3 col-lg-2"><div class="item"><div class="pos-rlt">'+
      	'<div class="item-overlay opacity r r-2x bg-black"><div class="center text-center m-t-n">'+
        '<a style="cursor:pointer" onClick="DetailsMovie('+Movie.id+')"><i class="fa fa-play-circle i-2x"></i></a></div></div>'+
        '<a onClick="DetailsMovie('+Movie.id+')"><img src="https://image.tmdb.org/t/p/w500'+Movie.poster_path+'"alt="" height="208px" width="141px" class="r r-2x img-full"></a></div>'+
        '<div class="padder-v">'+
        '<a data-bjax data-target="#bjax-target" data-el="#bjax-el" data-replace="true" class="text-ellipsis">'+Movie.original_title+'</a>'+
        '<a data-bjax data-target="#bjax-target" data-el="#bjax-el" data-replace="true" class="text-ellipsis text-xs text-muted">'+Movie.release_date+'</a>'+
        '</div></div></div>';
	DivMovies.append(DivMovie); 
}

function DetailsMovie(id)
{
	window.location=('MovieDetails.jsp?id_movie='+id);
}

function SelectItem()
{
	var id_genre = GetURLParameter('id_genre');
	if (id_genre==28)
	{
		fillElement("#Genre", "Films d'action"); 
		$("#action").attr('class', 'list-group-item active');
	}
	else if (id_genre==12)
	{
		fillElement("#Genre", "Films d'aventure"); 
		$("#aventure").attr('class', 'list-group-item active');
	}
	else if (id_genre==16)
	{
		fillElement("#Genre", "Films d'animation"); 
		$("#animation").attr('class', 'list-group-item active');
	}
	else if (id_genre==35)
	{
		fillElement("#Genre", "Films de comedie"); 
		$("#comedie").attr('class', 'list-group-item active');
	}
	else if (id_genre==80)
	{
		fillElement("#Genre", "Films de crime"); 
		$("#crime").attr('class', 'list-group-item active');
	}
	else if (id_genre==99)
	{
		fillElement("#Genre", "Documentaires"); 
		$("#documentaire").attr('class', 'list-group-item active');
	}
	else if (id_genre==18)
	{
		fillElement("#Genre", "Drama"); 
		$("#drama").attr('class', 'list-group-item active');
	}
	else if (id_genre==10751)
	{
		fillElement("#Genre", "Films de famille"); 
		$("#famille").attr('class', 'list-group-item active');
	}
	else if (id_genre==14)
	{
		fillElement("#Genre", "Films fantastiques"); 
		$("#fantastic").attr('class', 'list-group-item active');
	}
	else if (id_genre==36)
	{
		fillElement("#Genre", "Films d'histoire"); 
		$("#histoire").attr('class', 'list-group-item active');
	}
	else if (id_genre==27)
	{
		fillElement("#Genre", "Films d'horreur"); 
		$("#horreur").attr('class', 'list-group-item active');
	}
	else if (id_genre==10402)
	{
		fillElement("#Genre", "Films de musique"); 
		$("#musique").attr('class', 'list-group-item active');
	}
	else if (id_genre==10749)
	{
		fillElement("#Genre", "Films romantiques"); 
		$("#romance").attr('class', 'list-group-item active');
	}
	else if (id_genre==9648)
	{
		fillElement("#Genre", "Films mystérieux"); 
		$("#mystere").attr('class', 'list-group-item active');
	}
	else if (id_genre==878)
	{
		fillElement("#Genre", "Films de Science Fiction"); 
		$("#fiction").attr('class', 'list-group-item active');
	}
	else if (id_genre==53)
	{
		fillElement("#Genre", "Thrillers"); 
		$("#thriller").attr('class', 'list-group-item active');
	}
	else if (id_genre==10752)
	{
		fillElement("#Genre", "Films de guerre"); 
		$("#guerre").attr('class', 'list-group-item active');
	}
	else if (id_genre==37)
	{
		fillElement("#Genre", "Films Western"); 
		$("#western").attr('class', 'list-group-item active');
	}
	
	
}

function fillElement(eltDomInput, text){
	var textElt = $(eltDomInput);
	textElt.html(text);
}

function GetURLParameter(sParam)
{
	var sPageURL = window.location.search.substring(1);
	var sURLVariables = sPageURL.split('&');
	for (var i = 0; i < sURLVariables.length; i++)
	{
		var sParameterName = sURLVariables[i].split('=');
		if (sParameterName[0] == sParam)
		{
			return sParameterName[1];
		}
	}
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

