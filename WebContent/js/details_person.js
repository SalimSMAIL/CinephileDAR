$(document).ready(function(){
	
	PersonDetails.ReadPersonDetails();
	SeeLists(); 
		
});
/**
 * object account for actions in account page
 */
var PersonDetails = {
		id_person: '',
		name_person : '', 
		ReadPersonDetails: function(){
			this.id_person = GetURLParameter('id_person');
			this.name_person = GetURLParameter('name_person'); 
			if(this.id_person != undefined && this.name_person != undefined)
			{
				GetPersonDetails(this.id_person, this.name_person);
			}
			else
			{
				alert("La star n'est pas reconnue");
			}

		}
}



/** adding comment about a tvshow */
function addComment() 
{
	var content = $('textarea#content').val(); 
	var id_person = GetURLParameter('id_person'); 
	SaveComment(content, id_person); 
	
}

function encode_utf8(s) { 

	 return unescape(encodeURIComponent(s)); 

	} 

/** Saving a person : calling the servlet **/ 
function SaveComment(content, id_person)
{
	$.ajax({
		type: "POST",
		url : "AddComment",
		data :{
			"content": content,
			"id_person": id_person
		},
		dataType : "json",
		success : function(data) { 
			
			var total_comments = $('#comments_total').text(); 
			var number_comments = total_comments.split(' C');
			fillElement("#comments_total", parseInt(number_comments[0])+1 + " Commentaires : ")
			DisplayPersonComments(data);
			$('textarea#content').val("");
			
		},
		error : function(XHR, testStatus, errorThrown) 
		{
			console.log("status: " + XHR.status + ", erreur: " + XHR.responseText);
		}
	});
}



function GetPersonDetails(id_person, name_person){
	
	$.ajax({
		type: "GET",
		url : "DisplayPersonDetails",
		dataType : 'json',
		data : {
			"id_person" : id_person,
			"name_person" : name_person
			},
		success : function(data) {

			var resultat = data;
			if (resultat.response==200)
			{
				ShowPersonDetails(resultat);
				for(i=0; i<resultat.knownfor[0].known_for.length; i++)
				{
					DisplayPersonMoviesKnownFor(resultat.knownfor[0].known_for[i]); 
					if (i==8) break; 
				}
				
				GetPersonComments(id_person); 
								
			}
		},
		error : function(XHR, testStatus, errorThrown) 
		{
			console.log("status: " + XHR.status + ", erreur: " + XHR.responseText);
		}
	});
}


/** Getting comments about a tvshow : calling the servlet **/
function GetPersonComments(id_person){
	
	$.ajax({
		type: "GET",
		url : "DisplayElementComments",
		dataType : 'json',
		data : {
			"id_person" : id_person
			},
		success : function(data) {

			var resultat = data;
			if (resultat.response==200)
			{
				fillElement("#comments_total", resultat.comments.length + " Commentaires : ")
				for(i=0; i<resultat.comments.length; i++)
				{
					DisplayPersonComments(resultat.comments[i]); 
				}				
				
			}
		},
		error : function(XHR, testStatus, errorThrown) 
		{
			console.log("status: " + XHR.status + ", erreur: " + XHR.responseText);
		}
	});
}


/** Display comments of a person **/ 
function DisplayPersonComments(comment)
{
	var DivPersonComments = $("#person_comments"); 
	var articleComment = '<article id="comment-id-1" class="comment-item"><a class="pull-left thumb-sm">'+
		'<img src="images/a0.png" class="img-circle"></a><section class="comment-body m-b"><header>'+
		'<strong>'+comment.cinephile+'</strong><span class="text-muted text-xs block m-t-xs">'+
		comment.date+ '</span></header><div class="m-t-sm">'+comment.content+'</div></section></article>'; 
	DivPersonComments.append(articleComment); 
}




function DisplayPersonMoviesKnownFor(result)
{
		var released_date = "";
			if(result.media_type=="tv") released_date = result.first_air_date;
			else if (result.media_type=="movie") released_date = result.release_date;
			
		var title=""	
			if(result.media_type=="tv") title = result.original_name; 
			else if (result.media_type=="movie") title = result.original_title; 
			else title = result.name; 
		
		var url_photo=""; 
		if (result.media_type=="tv" || result.media_type=="movie")
			{
			if (result.poster_path!=null) url_photo = "https://image.tmdb.org/t/p/w500" +result.poster_path; 
			else url_photo="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAAA1BMVEUAAACnej3aAAAASElEQVR4nO3BgQAAAADDoPlTX+AIVQEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADwDcaiAAFXD1ujAAAAAElFTkSuQmCC";
			}
		
		var genre = result.media_type; 
		if(result.media_type=="tv") genre="Série TV"; 
		else if (result.media_type=="movie") genre = "Film"; 
		
			
	var DivKnownFor = $("#knownfor"); 
	var DivElement =  '<div class="col-xs-6 col-sm-3"><div class="item cast" style="cursor:pointer" onClick="SeeDetails('+result.id+',\''+result.media_type+'\')" ><div class="pos-rlt">'+
		'<div class="item-overlay opacity r r-2x bg-black"><div class="center text-center m-t-n"></div></div>'+
        '<img src="'+url_photo+'"alt="" height="241px" width="160px" class="r r-2x img-full"></div>'+
        '<div class="padder-v"><a class="text-ellipsis">'+title+'</a><small class="text-muted clear text-ellipsis">'+genre+' - '+ released_date+'</small></div></div></div>'; 
	DivKnownFor.append(DivElement); 
}

function SeeDetails(id, genre)
{

	if(genre =="tv") window.location=('TvShowDetails.jsp?id_tvshow='+id)
	else if (genre =="movie") window.location=('MovieDetails.jsp?id_movie='+id);
	else if (genre == "person") alert("yes");
	
}



function DisplayPersonTvShowsKnownFor(element)
{
	var urlPhto=""; 
	
	if (element.poster_path!=null) urlPhoto= "https://image.tmdb.org/t/p/w500"+element.poster_path; 
	else urlPhoto = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAAA1BMVEUAAACnej3aAAAASElEQVR4nO3BgQAAAADDoPlTX+AIVQEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADwDcaiAAFXD1ujAAAAAElFTkSuQmCC";
	
	
	var DivCastTvShow = $("#knownfor"); 
	var DivShow =  '<div class="col-xs-6 col-sm-3"><div class="item cast" ><div class="pos-rlt">'+
		'<div class="item-overlay opacity r r-2x bg-black"><div class="center text-center m-t-n"></div></div>'+
        '<img src="'+urlPhoto+'"alt="" height="241px" width="160px" class="r r-2x img-full"></div>'+
        '<div class="padder-v"><a class="text-ellipsis">'+element.originale_name+'</a><small class="text-muted clear text-ellipsis">'+element.first_air_date+'</small></div></div></div>'; 
	DivCastTvShow.append(DivShow); 
}

function ShowPersonDetails (resultat)
{
	var gender =""; 
	if (resultat.gender=="1")  gender="Femme";
	else gender="Homme";
	
	var site = ""; 
	if (resultat.homepage==null) site= "-"; 
	else site= resultat.homepage; 
	
	fillElement("#personName",resultat.name);
	fillElement("#personBiography",resultat.biography);
	fillElement("#personGender",gender);
	fillElement("#personBirthdayDate",resultat.birthday);
	fillElement("#personBirthdayPlace",resultat.place_of_birth);
	fillElement("#personHomepage",site);
	displayImage("#personProfile", resultat.profile_path);
}

function fillElement(eltDomInput, text){
	var textElt = $(eltDomInput);
	textElt.html(text);
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
			"id_person" : GetURLParameter("id_person")
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