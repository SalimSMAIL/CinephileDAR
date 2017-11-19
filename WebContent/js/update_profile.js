$(document).ready(function() {
	SeeLists();
});

function updateUser() {
	var username = $('#usernameup').val(); 
	var email = $('#emailup').val();
	var password = $('#passwordup').val();
	var confirmation= $('#confirmationup').val();
	var firstname = $('#firstnameup').val();
	var lastname = $('#lastnameup').val();
	var gender= $('input[name=optionsRadios]:checked').val();
	var description = $('textarea#descriptionup').val();
	var address = $('#addressup').val();
	if(password == confirmation){
		updateprofile(username,email,password,firstname,lastname,description,address,gender);
	}

}



function VerifInput(element, element_error, message )
{
	$(element).removeClass("form-control"); 
	$(element).addClass("form-control rounded input-lg text-center parsley-validated parsley-error");
	$(element_error).css('display','inline');
	$(element_error).text(message);
}


function updateprofile(username,email,password,firstname,lastname,description,address,gender){
	$.ajax({
		type : "GET",
		url : "UpdateProfile",
		
		data : { 
				"username" : username,
				"email" : email,
				"firstname" : firstname,
				"lastname" : lastname,
				"description" : description,
				"address" : address,
				"gender" : gender,
				"password" : password

		},
		dataType : 'json',
		success : function(data) {
			var resultat = data;
			console.log(resultat.message)
			if(resultat.message==200){ 
			window.location = ('CinephileProfile.jsp');
			
			}
			if(resultat.message=="Username exists"){
				VerifInput('#username', '#username_errorup', "Nom d'utilisateur déjà pris");
			}
			if(resultat.message=="Email exists"){
				VerifInput('#email', '#email_errorup', "Email déjà existant");
			}
			
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