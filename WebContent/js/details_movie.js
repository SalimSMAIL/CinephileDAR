$(document).ready(function(){
	
	MovieDetails.ReadMovieDetails();
	SeeLists();
		
});

var MovieDetails = {
		id_movie: '',

		ReadMovieDetails: function(){
			
			this.id_movie = GetURLParameter('id_movie');
			if(this.id_movie != undefined)
			{
				GetMovieDetails(this.id_movie);
			}
			else
			{
				alert("Le film n'est pas reconnu");
			}

		}
}

/** adding comment about a movie */
function addComment() 
{
	var content = $('textarea#content').val(); 
	var id_movie = GetURLParameter('id_movie'); 
	SaveComment(content, id_movie); 
	
}

/** Saving movie : calling the servlet **/ 
function SaveComment(content, id_movie)
{
	console.log("send to AddCommentServlet");
	$.ajax({
		type: "POST",
		url : "AddComment",
		data :{
			"content": content,
			"id_movie": id_movie
		},
		dataType : "json",
		success : function(data) { 
			
			var total_comments = $('#comments_total').text(); 
			var number_comments = total_comments.split(' C');
			fillElement("#comments_total", parseInt(number_comments[0])+1 + " Commentaires : ")
			DisplayMovieComments(data);
			$('textarea#content').val("");
			
		},
		error : function(XHR, testStatus, errorThrown) 
		{
			console.log("status: " + XHR.status + ", erreur: " + XHR.responseText);
		}
	});
}


/** Getting all details about a movie : callin servlet **/ 
function GetMovieDetails(id_movie){
	
	$.ajax({
		type: "GET",
		url : "DisplayMovieDetails",
		dataType : 'json',
		data : {
			"id_movie" : id_movie
			},
		success : function(data) {

			var resultat = data;
			if (resultat.response==200)
			{
				 
				ShowMovieDetails(resultat); 
				DisplayRating(resultat.vote_average); 
				if (resultat.trailers[0]!=null) DisplayMovieTrailer(resultat.trailers[0].key);
				for(i=0; i<resultat.genres.length; i++)
				{
					DisplayMovieGenres(resultat.genres[i]); 
				}
				
				for(i=0; i<8; i++)
				{
					if (i<resultat.credits.cast.length)
					{
						if (resultat.credits.cast[i].profile_path!=null )
							DisplayCastMovie(resultat.credits.cast[i]); 
					}
				}	
				
				for(i=0; i<5; i++)
				{
					DisplayRecommendedMovie(resultat.recommendations[i]); 
				}	
				
				GetMovieComments(id_movie); 				
			}
		},
		error : function(XHR, testStatus, errorThrown) 
		{
			console.log("status: " + XHR.status + ", erreur: " + XHR.responseText);
		}
	});
}

/** Getting comments about a movie : calling the servlet **/
function GetMovieComments(id_movie){
	
	$.ajax({
		type: "GET",
		url : "DisplayElementComments",
		dataType : 'json',
		data : {
			"id_movie" : id_movie
			},
		success : function(data) {

			var resultat = data;
			if (resultat.response==200)
			{
				fillElement("#comments_total", resultat.comments.length + " Commentaires : ")
				for(i=0; i<resultat.comments.length; i++)
				{
					DisplayMovieComments(resultat.comments[i]); 
				}				
				
			}
		},
		error : function(XHR, testStatus, errorThrown) 
		{
			console.log("status: " + XHR.status + ", erreur: " + XHR.responseText);
		}
	});
}

/** Display Genres of a movie **/ 
function DisplayMovieGenres(movie)
{
	var DivGenres = $("#genres"); 
	var DivGenre = '<h2 style="margin-left:5px; font-size:15px!important; background-color:#336e7b!important" class="label">'+movie.name+'</h2>'; 
	DivGenres.append(DivGenre);
}

/** Display cast of a movie **/ 
function DisplayCastMovie(actor)
{
	var DivCastMovie = $("#cast"); 
	var DivActor =  '<div class="col-xs-6 col-sm-3"><div class="item cast" style="cursor:pointer" onClick="SeeDetailsActor('+actor.id+',\''+actor.name+'\')" ><div class="pos-rlt">'+
		'<div class="item-overlay opacity r r-2x bg-black"><div class="center text-center m-t-n"></div></div>'+
        '<img src="https://image.tmdb.org/t/p/w185'+actor.profile_path+'"alt="" height="241px" width="160px" class="r r-2x img-full"></div>'+
        '<div class="padder-v"><a class="text-ellipsis">'+actor.name+'</a><small class="text-muted clear text-ellipsis">'+actor.character+'</small></div></div></div>'; 
	DivCastMovie.append(DivActor); 
}

/** Display comments of a movie **/ 
function DisplayMovieComments(comment)
{
	var DivMovieComments = $("#movie_comments"); 
	var articleComment = '<article id="comment-id-1" class="comment-item"><a class="pull-left thumb-sm">'+
		'<img src="images/a0.png" class="img-circle"></a><section class="comment-body m-b"><header>'+
		'<strong>'+comment.cinephile+'</strong><span class="text-muted text-xs block m-t-xs">'+
		comment.date+ '</span></header><div class="m-t-sm">'+comment.content+'</div></section></article>'; 
	DivMovieComments.append(articleComment); 
}

/** See details of an actor action **/ 
function SeeDetailsActor(id, name)
{
	window.location=('PersonDetails.jsp?id_person='+id+'&name_person='+name);
}

/** Display recommendend films **/ 
function DisplayRecommendedMovie(movie)
{
	
	var DivRecommandedMovies = $("#suggestions_movie"); 
	var DivMovieSuggested =  '<article class="media"><a class="pull-left thumb thumb-wrapper l-t-xs"  style="cursor:pointer" onClick="SeeDetailsMovieSuggested('+movie.id+')">'+
		'<img src="https://image.tmdb.org/t/p/w185'+movie.poster_path+'"></a><div class="media-body">'+                        
		'<a class="font-semibold" style="cursor:pointer" onClick="SeeDetailsMovieSuggested('+movie.id+')">'+movie.original_title+'</a></div></article>'; 
	DivRecommandedMovies.append(DivMovieSuggested); 
}

/** See details of a suggested movie action **/ 
function SeeDetailsMovieSuggested(id_movie)
{
	window.location=('MovieDetails.jsp?id_movie='+id_movie);
}

/** Display movie's teaser **/ 
function DisplayMovieTrailer(trailer)
{
	var DivMovieTrailer= $("#movieTrailer"); 
	var codeYoutube = jQuery('<iframe src="https://www.youtube.com/embed/'+trailer+'"></iframe>'); 
	DivMovieTrailer.append(codeYoutube); 
}

/** Show some movie details **/ 
function ShowMovieDetails (resultat)
{
	
	fillElement("#movieTitle",resultat.original_title);
	fillElement("#movieSynopsis",resultat.overview);
	fillElement("#movieReleased",resultat.release_date);
	fillElement("#movieRate",resultat.vote_average + "/10");
	displayImage("#moviePoster", resultat.backdrop_path);
	
}

function fillElement(eltDomInput, text){
	var textElt = $(eltDomInput);
	textElt.html(text);
}

/** Displaying movie rate **/ 
function DisplayRating(movieRate)
{
	var DivMovieRating = $("#movieRateStars"); 
	var codeFullStar = '<i class="fa fa-star"></i>'; 
	var codeHalfStar = '<i class="fa fa-star-half-o text-muted"></i>'; 
	var codeMutedStar = '<i class="fa fa-star-o text-muted"></i>'; 
	var intRate = parseInt(movieRate); 
	var differenceRate = parseFloat(movieRate)-intRate;
	var diffTen = 10 - parseFloat(movieRate) - 1; 
	for(i=0; i<intRate; i++)
		{
		DivMovieRating.append(codeFullStar); 
		}
	if (differenceRate>0) DivMovieRating.append(codeHalfStar); 
	for(i=0; i<diffTen; i++)
	{
		DivMovieRating.append(codeMutedStar); 
	}
}

function displayImage(eltDomInput, urlImage)
{
	var ImageElt = $(eltDomInput);
	var src = "https://image.tmdb.org/t/p/w780"+urlImage; 
	ImageElt.attr("src", src);
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
				if (resultat.lists_cinephile.length==0)
					{
						$("#addListBtn").prop("disabled",true);
					}
				for (i=0; i<resultat.lists_cinephile.length; i++)
					{
						addOptionList(resultat.lists_cinephile[i]); 
						displayList(resultat.lists_cinephile[i]); 
					}
			}
		},
		error : function(XHR, testStatus, errorThrown) 
		{
			console.log("status: " + XHR.status + ", erreur: " + XHR.responseText);
		}
	});
}

function addOptionList(list)
{
	var select = $("#lists"); 
	var option = '<option name="'+list.id+'" value="'+list.id+'">'+list.name+'</option>';
	select.append(option); 
}


function addToList()
{
	
	$.ajax({
		type : "POST",
		url : "AddElementToList",
		data : {
			"id_list" : $("#lists").val(),
			"id_movie" : GetURLParameter("id_movie")
		},
		dataType : "json",
		success : function(data) {
			console.log(data.response);
			var resultat=data;
			if (resultat.response==200)
			{			
				$("#added").css("display", "inline"); 
			}
			else if (resultat.response=="element existe deja")
			{
				alert("Ce film existe déjà dans cette liste !");
			}
			
		},
		error : function(XHR, testStatus, errorThrown) {
			console.log("status: " + XHR.status + ", erreur: "
					+ XHR.responseText);
		}
	});
	

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



function displayList(list)
{
	
	var DivContainer = $("#lists_menu");
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