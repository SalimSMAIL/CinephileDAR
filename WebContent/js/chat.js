 $(document).ready(function(){
	 	ConsultMessages(); 
		GetMessages(); 
		SeeLists(); 
		
	});
  
  function ConsultMessages()
  {
	  
  	$.ajax({
  		type: "GET",
  		url : "ConsultMessages",
  		dataType : 'json',
  		success : function(data) {
  			if (data.response==200)
  			{
  				
  				var messages = data.cinephiles;
  				if(messages.length == 0)
  				{
  					console.log("la liste contient 0 items"); 
  				} 
  				else 
  				{
  					for(i=0; i<messages.length; i++)
  						{
  							DisplayCinephile(messages[i]); 
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
  
  function DisplayCinephile(cinephile)
  {
	  var DivCinephile; 
	  if (GetURLParameter('id_other')==cinephile.id)
	  DivCinephile = '<a class="list-group-item" style="cursor:pointer; background-color:#f3f5f7" onClick="SeeMessages('+cinephile.id+')"><i class="fa fa-comment icon-muted" style="margin-right:10px"></i>'+cinephile.username+'</a>'; 
	  else DivCinephile = '<a class="list-group-item" style="cursor:pointer" onClick="SeeMessages('+cinephile.id+')"><i class="fa fa-comment icon-muted" style="margin-right:10px"></i>'+cinephile.username+'</a>'; 
	  var DivMessages = $("#cinephiles");
	  DivMessages.append(DivCinephile); 
  }
  
  function SeeMessages(id)
  {
	  window.location=('MessagesCinephile.jsp?id_other='+id);
  }
  

  function sendMessage() {
  	var content= $('#message').val();
  	//alert(content);
  	var id_receiver = GetURLParameter('id_other');
  	SaveMessage(id_receiver,content);

  }
  //penser à regler la jsp de l'affichage des msg apres envoie
  function SaveMessage(id_receiver,content) {
  	console.log("send to AddCommentServlet");
  	$.ajax({
  		type : "POST",
  		url : "AddMessage",
  		data : {
  			"id_other" : id_receiver,
  			"content" : content
  		},
  		dataType : "json",
  		success : function(data) {
  			var resultat=data;
  			if (resultat.response==200)
  			{			
  				DisplayNewMessage(data);	
  				$('#message').val("");
  			}
  		},
  		error : function(XHR, testStatus, errorThrown) {
  			console.log("status: " + XHR.status + ", erreur: "
  					+ XHR.responseText);
  		}
  	});
  }

  function DisplayMyMessage(message)
  {
  	var date = new Date(message.date);
  	var displayDate = formatAMPM(date);
  	
  	var picture = ""; 
  	var gender = message.cinephile; 
  	
  	if (gender=="homme") picture ="images/male.png"; 
  	else picture="images/female.png"; 
  	
  	var DivChat = $("#chat"); 
  	var DivMine = '<article id="chat-id-2" class="chat-item right" style="border-left-color: #36b0c8!important;"><a href="#" class="pull-right thumb-sm avatar">'+
  		'<img src='+picture+' class="img-circle" alt="..."></a><section class="chat-body"><div class="panel bg-light text-sm m-b-none" style="    background-color: #36b0c8; color: #fff;" >'+
  		'<div class="panel-body"><span class="arrow right" style="border-left-color: #36b0c8!important;"></span><p class="m-b-none">'+message.content+'</p></div></div>'+
          '<small class="text-muted">'+displayDate+'</small></section></article> '; 
  	DivChat.append(DivMine); 
  	
  }

  function DisplayNewMessage(message)
  {
  	var date = new Date();
  	var displayDate = formatAMPM(date);
  	var picture = ""; 
  	var gender = message.cinephile; 
  	if (gender=="homme") picture ="images/male.png"; 
  	else picture="images/female.png"; 
  	
  	var DivChat = $("#chat"); 
  	var DivMine = '<article id="chat-id-2" class="chat-item right" style="border-left-color: #36b0c8!important;"><a href="#" class="pull-right thumb-sm avatar">'+
  		'<img src='+picture+' class="img-circle" alt="..."></a><section class="chat-body"><div class="panel bg-light text-sm m-b-none" style="    background-color: #36b0c8; color: #fff;" >'+
  		'<div class="panel-body"><span class="arrow right" style="border-left-color: #36b0c8!important;"></span><p class="m-b-none">'+message.content+'</p></div></div>'+
          '<small class="text-muted">'+displayDate+'</small></section></article> '; 
  	DivChat.append(DivMine);  
  	
  }

  function DisplayMessageOther(message)
  {
  	var date = new Date(message.date);
  	var displayDate = formatAMPM(date);
  	
  	var picture = ""; 
  	var gender = message.cinephile; 
  	if (gender=="homme") picture ="images/male.png"; 
  	else picture="images/female.png"; 
  	var DivChat = $("#chat"); 
  	var DivOther = '<article id="chat-id-1" class="chat-item left"><a href="#" class="pull-left thumb-sm avatar">'
  		+'<img src='+picture+' alt="..."></a><section class="chat-body"><div class="panel b-light text-sm m-b-none">'+
            '<div class="panel-body"><span class="arrow left"></span><p class="m-b-none">'+message.content+'</p></div></div>'+
          '<small class="text-muted"><i class="fa fa-ok text-success"></i>'+displayDate+'</small></section></article>'; 
  	DivChat.append(DivOther); 
  	
  }

  function GetMessages()
  {
  	id_receiver = GetURLParameter('id_other'); 
  	$.ajax({
  		type: "GET",
  		url : "DisplayMessagesBetweenTwoCinephiles",
  		dataType : 'json',
  		data : {
  			"id_other" : id_receiver
  			},
  		success : function(data) {

  			var resultat = data;
  			if (resultat.response==200)
  			{
  				
  				var messages = resultat.messages.sort(function(a, b) {
  				    return new Date(a.date).getTime() - new Date(b.date).getTime();
  				});
  				for(i=0; i<messages.length; i++)
  				{
  					if (messages[i].status=="sent") DisplayMyMessage(messages[i]);
  					else DisplayMessageOther(messages[i]);
  				}	
  				
  				
  			}
  		},
  		error : function(XHR, testStatus, errorThrown) 
  		{
  			console.log("status: " + XHR.status + ", erreur: " + XHR.responseText);
  		}
  	});
  }

  function formatAMPM(date) { 
  	  var hours = date.getHours();
  	  var minutes = date.getMinutes();
  	  minutes = minutes < 10 ? '0'+minutes : minutes;
  	  hours = hours == 0 ? '0'+hours : hours; 
  	  var strTime = hours + ':' + minutes;
  	  return displayDate = date.getDate()+ '-' + (date.getMonth()+1)+'-' +date.getFullYear()+ ' ' +strTime;;
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