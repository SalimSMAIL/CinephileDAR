$(document).ready(function(){
	Usernamedefinition();
	GetCinemas(); 
	SeeLists();
});
 
function sendEvent() {
	var title= $('#title').val();
	var description = $('textarea#description').val();
	var place = $('#place').val();
	var date = $('#date').val();
	var limit = $('#limit').val();
	SaveEvent(title, description, place, date, limit);

}


function SaveEvent(title, description, place, date, limit) {
	console.log("send to AddEventServlet");
	$.ajax({
		type : "POST",
		url : "AddEvent",
		data : {
			"title" : title, 
			"description" : description, 
			"place" : place,
			"date" : date, 
			"limit" : limit
		},
		dataType : "json",
		success : function(data) {
			var resultat=data;
			if (resultat.response==200)
			{			
				window.location=('Events.html');
			}
		},
		error : function(XHR, testStatus, errorThrown) {
			console.log("status: " + XHR.status + ", erreur: "
					+ XHR.responseText);
		}
	});
}

function seeCinemas()
{
	window.location=('MapCinemasIleDeFrance.html');
}



function GetCinemas()
{
	$.ajax({
		type: "GET",
		url : "GetIleDeFranceCinemas",
		dataType : 'json',
		success : function(data) {
			if (data.response==200)
			{
				
				var seances = data.seances; 
				
					for(i=0; i<seances.length; i++)
					{
						var seance = seances[i].id + " / " +seances[i].movie + " / " + seances[i].date;  
						addOptionSeance(seance);
						//console.log(seances[i]); 
					}	
					
					$.getJSON("js/cinemas_idf.json", function(cin) {
					    
					    var array = new Array();
						for(i=0; i<cin.length; i++)
							{
							console.log(i);  
									 array.push(cin[i].fields.enseigne); 
							}	
						var options = {
								data : array,
								list: {
									maxNumberOfElements: 309,
									match: {
										enabled: true
									}
								}
							};
						$("#place").easyAutocomplete(options);
					})
					
					
				
			}
			
		},
		error : function(XHR, testStatus, errorThrown) 
		{
			console.log("status: " + XHR.status + ", erreur: " + XHR.responseText);
		}
	});
}

function addOptionSeance(seance)
{
	var select = $("#date"); 
	var option = '<option name="'+seance+'">'+seance+'</option>';
	select.append(option); 
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


function cin()
{
	$.getJSON("js/cinemas_idf.json", function(json) {
	    console.log(json); // this will show the info it in firebug console
	});
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
				console.log(data.gender);
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
