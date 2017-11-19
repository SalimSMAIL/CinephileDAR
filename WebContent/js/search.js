$(document).ready(function(){
	
	Results.SearchResults();
	var query = GetURLParameter('query').split("%20");
	console.log(query[0]);
	var query_search = ""; 
	for(i=0; i<query.length; i++)
		{
			query_search+=" "+query[i]; 
		}
	fillElement("#search_text", "Résultats de la recherche pour : "+ query_search);
	SeeLists();
});

function fillElement(eltDomInput, text){
	var textElt = $(eltDomInput);
	textElt.html(text);
}

var Results = {
		query: '',

		SearchResults: function(){
		
			this.query = GetURLParameter('query');
			if(this.query != undefined)
			{
				FetchSearchResults(this.query);
			}
			else
			{
				alert("Aucun résulat");
			}

		}
}


function FetchSearchResults(query){
	
	$.ajax({
		type: "GET",
		url : "MultipleSearch",
		dataType : 'json',
		data : {
			"query" : query
			},
		success : function(data) {

			var resultat = data;
			if (resultat.response==200)
			{
				if (resultat.results.length==0)
					{
						
						$("#search_result").css('display','inline'); 
					}
				else for(i=0; i<resultat.results.length; i++)
				{
					DisplaySearchResults(resultat.results[i]); 
				}
				
			}
		},
		error : function(XHR, testStatus, errorThrown) 
		{
			console.log("status: " + XHR.status + ", erreur: " + XHR.responseText);
		}
	});
}

function DisplaySearchResults(result)
{
	var released_date = "";
		if(result.media_type=="tv") released_date = result.first_air_date;
		else if (result.media_type=="movie") released_date = result.release_date;
		else released_date="";
	var title=""	
		if(result.media_type=="tv") title = result.original_name; 
		else if (result.media_type=="movie") title = result.original_title; 
		else title = result.name; 
		
	var overview=""; 
	if (result.media_type=="tv" || result.media_type=="movie") overview = result.overview; 
		
	if (overview.length > 150 ) overview = overview.substring(0,150) + "...";
	
	var url_photo=""; 
	if (result.media_type=="tv" || result.media_type=="movie")
		{
		if (result.poster_path!=null) url_photo = "https://image.tmdb.org/t/p/w500" +result.poster_path; 
		else url_photo="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAAA1BMVEUAAACnej3aAAAASElEQVR4nO3BgQAAAADDoPlTX+AIVQEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADwDcaiAAFXD1ujAAAAAElFTkSuQmCC";
		}
	else url_photo="https://image.tmdb.org/t/p/w500" +result.profile_path;
	
	if (url_photo==null) url_photo="data:image/jpeg;base64,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";
	
	var genre = result.media_type; 
	if(result.media_type=="tv") genre="Série TV"; 
	else if (result.media_type=="movie") genre = "Film"; 
	else genre = "Personnalité"; 
	
	var DivSerachResults = $("#searchResults"); 
	var DivSearchCard = '<div class="example-2 card col-sm-3" style="cursor:pointer" onClick="SeeDetails('+result.id+',\''+result.media_type+'\''+',\''+title+'\');"><div class="wrapper" style=" background: url('+url_photo+') center/cover no-repeat; margin-bottom:20px!important"><div class="header"><div class="date">'+
	          '<span class="day">'+released_date+'</span></div>'+
	           '<ul class="menu-content"><li><a href="#" class="fa fa-heart-o"><span>'+result.vote_average+'/10</span></a></li></ul></div>'+
	           '<div class="data"><div class="content"><span class="author">'+genre+'</span><h1 class="title">'+
	           '<a>'+title+'</a></h1><p class="text">'+ overview +'</p>'+
	           '<a style="cursor:pointer" onClick="SeeDetails('+result.id+',\''+result.media_type+'\''+',\''+title+'\');" class="button">Lire la suite</a></div></div></div></div>';
	DivSerachResults.append(DivSearchCard);

}

function SeeDetails(id, genre,title)
{
	if(genre =="tv") window.location=('TvShowDetails.jsp?id_tvshow='+id)
	else if (genre =="movie") window.location=('MovieDetails.jsp?id_movie='+id);
	else if (genre == "person") window.location=('PersonDetails.jsp?id_person='+id+'&name_person='+title);
	
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