$(document).ready(function(){
	
	TvShowDetails.ReadTvShowDetails();
	SeeLists();
});

var TvShowDetails = {
		id_tvshow: '',

		ReadTvShowDetails: function(){
			console.log("TvShowDetails.ReadTvShowDetails");
			
			this.id_tvshow = GetURLParameter('id_tvshow');
			console.log('id_tvshow:'+this.id_tvshow)
			if(this.id_tvshow != undefined)
			{
				GetTvShowDetails(this.id_tvshow);
			}
			else
			{
				alert("La série n'est pas reconnue");
			}

		}
}


/** adding comment about a tvshow */
function addComment() 
{
	var content = $('textarea#content').val(); 
	var id_tvshow = GetURLParameter('id_tvshow'); 
	SaveComment(content, id_tvshow); 
	
}

/** Saving a tvshow : calling the servlet **/ 
function SaveComment(content, id_tvshow)
{
	$.ajax({
		type: "POST",
		url : "AddComment",
		data :{
			"content": content,
			"id_tvshow": id_tvshow
		},
		dataType : "json",
		success : function(data) { 
			
			var total_comments = $('#comments_total').text(); 
			var number_comments = total_comments.split(' C');
			fillElement("#comments_total", parseInt(number_comments[0])+1 + " Commentaires : ")
			DisplayTvshowComments(data);
			$('textarea#content').val("");
			
		},
		error : function(XHR, testStatus, errorThrown) 
		{
			console.log("status: " + XHR.status + ", erreur: " + XHR.responseText);
		}
	});
}



function GetTvShowDetails(id_tvshow){
	
	$.ajax({
		type: "GET",
		url : "DisplayTvShowDetails",
		dataType : 'json',
		data : {
			"id_tvshow" : id_tvshow
			},
		success : function(data) {

			var resultat = data;
			if (resultat.response==200)
			{
				 
				ShowTvShowDetails(resultat); 
				DisplayRating(resultat.vote_average); 
				if (resultat.trailers[0]!=null) DisplayTvShowTrailer(resultat.trailers[0].key);
				for(i=0; i<resultat.genres.length; i++)
				{
					DisplayTvShowGenres(resultat.genres[i]); 
				}
				
				for(i=0; i<8; i++)
				{
					if (i<resultat.credits.cast.length)
					{
						if (resultat.credits.cast[i].profile_path!=null )
							DisplayCastTvShow(resultat.credits.cast[i]); 
					}
					
				}	
				
				for(i=0; i<resultat.seasons.length; i++)
				{
					DisplaySeasonsTvShow(resultat.seasons[i]); 
					
				}	
				
				for(i=0; i<5; i++)
				{
					DisplayRecommendedTvShow(resultat.recommendations[i]); 
				}	
				
				GetTvshowComments(id_tvshow); 
				
			}
		},
		error : function(XHR, testStatus, errorThrown) 
		{
			console.log("status: " + XHR.status + ", erreur: " + XHR.responseText);
		}
	});
}



/** Getting comments about a tvshow : calling the servlet **/
function GetTvshowComments(id_tvshow){
	
	$.ajax({
		type: "GET",
		url : "DisplayElementComments",
		dataType : 'json',
		data : {
			"id_tvshow" : id_tvshow
			},
		success : function(data) {

			var resultat = data;
			if (resultat.response==200)
			{
				fillElement("#comments_total", resultat.comments.length + " Commentaires : ")
				for(i=0; i<resultat.comments.length; i++)
				{
					DisplayTvshowComments(resultat.comments[i]); 
				}				
				
			}
		},
		error : function(XHR, testStatus, errorThrown) 
		{
			console.log("status: " + XHR.status + ", erreur: " + XHR.responseText);
		}
	});
}


/** Display comments of a tvshow **/ 
function DisplayTvshowComments(comment)
{
	var DivTvshowComments = $("#tvshow_comments"); 
	var articleComment = '<article id="comment-id-1" class="comment-item"><a class="pull-left thumb-sm">'+
		'<img src="images/a0.png" class="img-circle"></a><section class="comment-body m-b"><header>'+
		'<strong>'+comment.cinephile+'</strong><span class="text-muted text-xs block m-t-xs">'+
		comment.date+ '</span></header><div class="m-t-sm">'+comment.content+'</div></section></article>'; 
	DivTvshowComments.append(articleComment); 
}



function DisplayTvShowGenres(tvshow)
{
	var DivGenres = $("#genres"); 
	var DivGenre = '<h2 style="margin-left:5px; font-size:15px!important; background-color:#336e7b!important" class="label">'+tvshow.name+'</h2>'; 
	DivGenres.append(DivGenre);
}

function DisplayCastTvShow(actor)
{
	var DivCastTvShow = $("#cast"); 
	var DivActor =  '<div class="col-xs-6 col-sm-3"><div class="item cast" style="cursor:pointer" onClick="SeeDetailsActor('+actor.id+',\''+actor.name+'\')" ><div class="pos-rlt">'+
		'<div class="item-overlay opacity r r-2x bg-black"><div class="center text-center m-t-n"></div></div>'+
        '<img src="https://image.tmdb.org/t/p/w185'+actor.profile_path+'"alt="" height="241px" width="160px" class="r r-2x img-full"></div>'+
        '<div class="padder-v"><a class="text-ellipsis">'+actor.name+'</a><small class="text-muted clear text-ellipsis">'+actor.character+'</small></div></div></div>'; 
	DivCastTvShow.append(DivActor); 
}

function SeeDetailsActor(id, name)
{
	window.location=('PersonDetails.jsp?id_person='+id+'&name_person='+name);
}

function DisplaySeasonsTvShow(season)
{
	var DivSeasonsTvShow = $("#seasons"); 
	var DivSeason =  '<div class="col-xs-6 col-sm-3"><div class="item cast" ><div class="pos-rlt">'+
		'<div class="item-overlay opacity r r-2x bg-black"><div class="center text-center m-t-n"></div></div>'+
        '<img src="https://image.tmdb.org/t/p/w185'+season.poster_path+'"alt="" height="241px" width="160px" class="r r-2x img-full"></div>'+
        '<div class="padder-v"><a class="text-ellipsis"> Saison '+season.season_number+'  -  '+season.episode_count + ' épisodes</a><small class="text-muted clear text-ellipsis">'+season.air_date+'</small></div></div></div>'; 
	DivSeasonsTvShow.append(DivSeason); 
}

function DisplayRecommendedTvShow(tvshow)
{
	
	var DivRecommandedTvshows = $("#suggestions_tvshow"); 
	var DivTvShowSuggested =  '<article class="media"><a class="pull-left thumb thumb-wrapper l-t-xs" style="cursor:pointer" onClick="SeeDetailsShowSuggested('+tvshow.id+')">'+
		'<img src="https://image.tmdb.org/t/p/w185'+tvshow.poster_path+'"></a><div class="media-body">'+                        
		'<a class="font-semibold" style="cursor:pointer" onClick="SeeDetailsShowSuggested('+tvshow.id+')">'+tvshow.original_name+'</a></div></article>'; 
	DivRecommandedTvshows.append(DivTvShowSuggested); 
}

function SeeDetailsShowSuggested(id_show)
{
	window.location=('TvShowDetails.jsp?id_tvshow='+id_show);
}


function DisplayTvShowTrailer(trailer)
{
	var DivMovieTrailer= $("#tvshowTrailer"); 
	var codeYoutube = jQuery('<iframe src="https://www.youtube.com/embed/'+trailer+'"></iframe>'); 
	DivMovieTrailer.append(codeYoutube); 
}

function ShowTvShowDetails (resultat)
{
	
	fillElement("#tvshowTitle",resultat.original_name);
	fillElement("#tvshowSynopsis",resultat.overview);
	fillElement("#tvshowReleased",resultat.first_air_date);
	fillElement("#tvshowRate",resultat.vote_average + "/10");
	displayImage("#tvshowPoster", resultat.backdrop_path);
	
}

function fillElement(eltDomInput, text){
	var textElt = $(eltDomInput);
	textElt.html(text);
}

function DisplayRating(tvshowRate)
{
	var DivTvShowRating = $("#tvshowRateStars"); 
	var codeFullStar = '<i class="fa fa-star"></i>'; 
	var codeHalfStar = '<i class="fa fa-star-half-o text-muted"></i>'; 
	var codeMutedStar = '<i class="fa fa-star-o text-muted"></i>'; 
	var intRate = parseInt(tvshowRate); 
	var differenceRate = parseFloat(tvshowRate)-intRate;
	var diffTen = 10 - parseFloat(tvshowRate) - 1; 
	for(i=0; i<intRate; i++)
		{
		DivTvShowRating.append(codeFullStar); 
		}
	if (differenceRate>0) DivTvShowRating.append(codeHalfStar); 
	for(i=0; i<diffTen; i++)
	{
		DivTvShowRating.append(codeMutedStar); 
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
			"id_tvshow" : GetURLParameter("id_tvshow")
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
				alert("Cette série existe déjà dans cette liste !");
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