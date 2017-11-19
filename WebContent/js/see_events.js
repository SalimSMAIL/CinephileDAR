$(document).ready(function(){
	Usernamedefinition();
		GetEvents(); 	
		SeeLists(); 
	});

	function GetEvents()
	{
		$.ajax({
			type: "GET",
			url : "SeeEvents",
			dataType : 'json',
			success : function(data) {
				if (data.response==200)
				{
					var events = data.events;
					
					if(events.length == 0)
					{
						console.log("la liste contient 0 items"); 
					} 
					else 
					{
						for(i=0; i<events.length; i++)
							{
								displayEvent(events[i]); 
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

	function displayEvent(event)
	{
		
		
		  var date =new Date(event.date);  //converts the string into date object
		  var day =date.getDate(); //get the value of month
		  var monthNames = ["JAN", "FEV", "MARS", "AVR", "MAI", "JUIN",
			  "JUIL", "AOUT", "SEP", "OCT", "NOV", "DEC"
			];
		  var month = monthNames[date.getMonth()]; 
		  var year = date.getFullYear(); 
		  var hour = date.getHours() + ':'+date.getMinutes();
		 
		
		
		var DivContainer = $("#events_cards"); 
		var DivEvent = '<div class="example-1 card col-sm-4" style="margin-bottom:15px"><div class="wrapper" style="padding:0px!important; background: url('+event.affiche+') center/cover no-repeat;"><div class="date"><span class="day">'+day+'</span><span class="month">'+month+'</span><span class="year">'+year+'</span></span><span class="year" style="font-weight:bold">'+hour+'</span>'+
	      '</div><div class="data"><div class="content"><span class="author">Organisé par : <a href="#" style="color:red">'+event.cinephile+'</a></span><h1 class="title"><a style="font-size:24px">'+event.title+'</a></h1>'+
	          '<p class="text">'+event.description+'</p>'+
	          '<label for="show-menu'+event.id_event+'" class="menu-button"><span></span></label></div><input type="checkbox" id="show-menu'+event.id_event+'" />'+
	           '<ul class="menu-content" style="cursor:pointer" onClick="participateEvent('+event.id_event+')" ><li><a id="icon'+event.id_event+'" class="icon-like" style="margin-top:10px"><span style="width:350px; top: -15px;">Participer</span></a></li><li><a class="fa fa-map-marker" style="margin-top:10px"><span style="width:350px; top: -15px;">'+event.place+'</span></a></li>'+
	          '<li><a class="icon-users"  style="margin-top:10px"><span style=" width:100px; top: -15px;">MAX = '+event.limit+'</span></a></li></ul></div></div></div>'
		DivContainer.append(DivEvent);

	}
	
function participateEvent(event_id)
{
	$.ajax({
		type : "POST",
		url : "ParticipateEvent",
		data : {
			"id_event" : event_id
		},
		dataType : "json",
		success : function(data) {
			console.log(data.response);
			var resultat=data;
			if (resultat.response==200)
			{			
				var element = "#icon"+event_id; 
				$(element).removeClass("icon-like"); 
				$(element).addClass("fa fa-thumbs-up");
			}
			else if (resultat.response=="deja inscrit a cet event")
			{
				alert("Vous vous êtes déjà inscrit à cet événement !");
			}
			else if (resultat.response=="plus de places") 
			alert("Malheureusement, il n'y a plus de places disponibles pour participer à cet événement !");
		},
		error : function(XHR, testStatus, errorThrown) {
			console.log("status: " + XHR.status + ", erreur: "
					+ XHR.responseText);
		}
	});
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
	