$(document).ready(function(){
	Usernamedefinition();
	GetSeances(); 
	SeeLists();
	
});

function GetSeances()
{
	$.ajax({
		type: "GET",
		url : "SeeSeancesMovies",
		dataType : 'json',
		success : function(data) {
			if (data.response==200)
			{
				var seances = data.seances;
				
				if(seances.length == 0)
				{
					console.log("la liste contient 0 items"); 
				} 
				else 
				{
					for(i=0; i<seances.length; i++)
						{
							displaySeance(seances[i]); 
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

function displaySeance(seance)
{

	
	var date =new Date(seance.date);  //converts the string into date object
	  var day =date.getDate(); //get the value of month
	  var monthNames = ["Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
		  "Juillet", "Août", "septembre", "Octobre", "Novembre", "Decembre"
		];
	  var month = monthNames[date.getMonth()]; 
	  var year = date.getFullYear(); 
	  var hour = date.getHours() + ':'+date.getMinutes();
	
	var DivContainer = $("#seances"); 
	var DivSeance = '<a class="list-group-item clearfix"><span class="pull-right h2 text-muted m-l"></span>'+
    '<span class="pull-left thumb-sm m-r" style="width:120px"><img src="'+seance.affiche+'" alt="..."></span><span class="clear">'+
    '<span style="font-weight:bold; font-size:30px">'+seance.movie+'</span><small class="text-muted" style="color:#428bca!important"></small>'+
    '<small class="text-muted clear text-ellipsis" style="font-size:20px"><i class="icon-calendar icon text-success" style="margin-right:20px"></i>'+day+ ' '+month+' '+year+' </small>'+
    '<small class="text-muted clear text-ellipsis" style="font-size:20px"><i class="icon-clock icon text-info" style="margin-right:20px"></i>'+hour+' </small>'+
    '<small class="text-muted"style="font-size:20px"><i class="fa fa-map-marker icon text-primary" style="margin-right:20px"></i>'+seance.place+'</small></span></a>';
		
	DivContainer.append(DivSeance);

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