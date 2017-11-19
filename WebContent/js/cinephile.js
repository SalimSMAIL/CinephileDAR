 $(document).ready(function(){

		$("#click_profile").click(routeur.profile);
	});
  
function CreateCinephileAccount(){
	
	var username = $('#username').val(); 
	var email = $('#email').val();
	var password = $('#password').val();
	var confirmed_password = $('#confirmed_password').val();
	var firstname = $('#firstname').val();
	var lastname = $('#lastname').val();
	var gender = $('#gender').val();
	var description = $('#description').val();
	var address = $('#address').val();
	
	var ok = VerifCinephile(username, email, password, confirmed_password, firstname, lastname,
			gender, description, address);
	if (ok) {
		SaveCinephile(username, email, password, firstname, lastname,
				gender, description, address);
	}else{
		
		console.log("method verif retourne faux, champs non ou " +
		"mal remplis");
		//alert("problème !! ")
	}

}	
  
  
	function VerifCinephile(username, email, password, confirmed_password, firstname, lastname,
			gender, description, address) 
	{
		var Okay = true; 

		if(firstname.length==0)
		{
			VerifInput('#firstname', '#firstname_error', "Prénom manquant");
			Okay = false; 
		}
		else InputOkay('#firstname', '#firstname_error')
		if(lastname.length==0)
		{ 
			VerifInput('#lastname', '#lastname_error', "Nom manquant");
			Okay = false; 
		}
		else InputOkay('#lastname', '#lastname_error')
		if(username.length==0)
		{
				VerifInput('#username', '#username_error', "Nom d'utilisateur manquant");
				Okay = false; 
		}
		else InputOkay('#username', '#username_error')
		if(email.length==0)
		{
			VerifInput('#email', '#email_error', "Email manquant"); 
			Okay = false; 
		} else InputOkay('#email', '#email_error')
		if (email.length!=0 && !isEmail(email))
		{
			VerifInput('#email', '#email_error', "Adresse mail invalide");
			Okay = false; 
		} 
		else if (isEmail(email)) InputOkay('#email', '#email_error')
		if(password.length==0)
		{
			VerifInput('#password', '#password_error', "Mot de passe manquant"); 
			Okay = false; 
		} else InputOkay('#password', '#password_error')
		if(confirmed_password.length==0)
		{
			VerifInput('#confirmed_password', '#confirmed_password_error', "Mot de passe de confirmation manquant"); 
			Okay = false; 
		} else InputOkay('#confirmed_password', '#confirmed_password_error')
		
		if(password.length>0 && (password.length<10  || scorePassword(password)<70))
		{
			VerifInput('#password', '#password_error', "Le mot de passe doit " +
					"contenir au moins 10 caractères dont des majuscules, " +
					"des minuscules, des chiffres et des symboles"); 
			Okay = false; 
		}
		
		if(password != confirmed_password && confirmed_password.length!=0 && password.length!=0)
			{
			VerifInput('#confirmed_password', '#confirmed_password_error', "Les mots de passe doivent être identiques"); 
			Okay = false; 
			}
		else if (confirmed_password.length!=0 && password.length!=0) InputOkay('#confirmed_password', '#confirmed_password_error')
		if(description.length==0)
		{
			VerifInput('#description', '#description_error', "Description manquante"); 
			Okay = false; 
		} else InputOkay('#description', '#description_error')		
		if(address.length==0)
		{
			VerifInput('#address', '#address_error', "Adresse manquante"); 
			Okay = false; 
		} else InputOkay('#address', '#address_error')
		if (Okay) 
		{
			
			return true;
		}
		else return false; 
		
	}

	function isEmail(email){

		var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		return re.test(email);

	}
	
	function VerifInput(element, element_error, message )
	{
		$(element).removeClass("form-control rounded input-lg text-center no-border"); 
		$(element).addClass("form-control rounded input-lg text-center parsley-validated parsley-error");
		$(element_error).css('display','inline');
		$(element_error).text(message);
	}
	
	function InputOkay(element, element_error)
	{
		$(element).attr('class', 'form-control rounded input-lg text-center no-border');
		$(element_error).css('display','none');
	}
	
	function scorePassword(pass) {
	    var score = 0;
	    if (!pass)
	        return score;

	    // award every unique letter until 5 repetitions
	    var letters = new Object();
	    for (var i=0; i<pass.length; i++) {
	        letters[pass[i]] = (letters[pass[i]] || 0) + 1;
	        score += 5.0 / letters[pass[i]];
	    }

	    // bonus points for mixing it up
	    var variations = {
	        digits: /\d/.test(pass),
	        lower: /[a-z]/.test(pass),
	        upper: /[A-Z]/.test(pass),
	        nonWords: /\W/.test(pass),
	    }

	    variationCount = 0;
	    for (var check in variations) {
	        variationCount += (variations[check] == true) ? 1 : 0;
	    }
	    score += (variationCount - 1) * 10;

	    return parseInt(score);
	}
	
	function SaveCinephile(username, email, password, firstname, lastname,
			gender, description, address) 
	{
		$.ajax({
			type: "GET",
			url : "signup",
			data :{
				"username": username,
				"email": email,
				"password": password,
				"firstname":firstname,
				"lastname":lastname,
				"gender": gender,
				"description": description,
				"address" : address
			},
			dataType : "json",
			success : function(data) { 
			    var resultat = data;
			    				
				if(resultat.message==200){ 
					routeur.home(resultat.id_cinephile);
				}
				
				if(resultat.message=="Username exists"){
					VerifInput('#username', '#username_error', "Nom d'utilisateur déjà pris");
				}
				if(resultat.message=="Email exists"){
					VerifInput('#email', '#email_error', "Email déjà existant");
				}
				
				
			},
			error : function(XHR, testStatus, errorThrown) 
			{
				console.log("status: " + XHR.status + ", erreur: " + XHR.responseText);
			}
		});
	}
	
	var routeur = {
			username_cinephile: "",

			index: function(){
				window.location=('Signup.html');
			},

			init: function(){
				this.username_cinephile = GetURLParameter('username');
			},

			home: function(id_cinephile){
				this.id_cinephile = id_cinephile;
				if(this.id_cinephile=="" || this.id_cinephile==undefined){
					this.index();
					
				}else{
					window.location=('Login.html');
				}
			}, 
			
			profile: function(){
				window.location=('CinephileProfile.jsp'); 
			}			
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
	

	
  