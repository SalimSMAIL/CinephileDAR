$(document).ready(
		function() {});

function LoginCinephile(){
	
	var email = $('#email').val();
	var password = $('#password').val();
		
	var Ok = VerifCinephile(email, password);
	if (Ok) {
		SigninCinephile(email, password);
	}else{
		
		console.log("method verif retourne faux, champs non ou " +
		"mal remplis");
		//alert("problème !! ")
	}

}	

	function VerifCinephile(email, password) 
	{
		var Okay = true; 

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
		
	function SigninCinephile(email, password) 
	{
		$.ajax({
			type: "GET",
			url : "Login",
			data :{
				"email": email,
				"password": password
			},
			dataType : "json",
			success : function(data){
			    var resultat = data;
				if(resultat.message==200){ 
					window.location = ('Home.html');
				}
				else if (resultat.message=="Invalid email")
				{
				VerifInput('#email', '#email_error', "Email invalide");
				}
				
				else if (resultat.message=="Invalid password")
					{
					VerifInput('#password', '#password_error', "Mot de passe invalide ");
					}
				
			},
			error : function(XHR, testStatus, errorThrown) 
			{
				console.log("status: " + XHR.status + ", erreur: " + XHR.responseText);
			}
		});
	}		
		
		
/*
			$("#userlogin").on(
					"click",
					function(event) {
						event.preventDefault();
						var e = window.event || e;
						var email = $('#email').val();
						var password = $('#password').val();
						$
								.ajax({
									type : "GET",
									url : encodeURI("Login"),
									data : {
										"email" : email,
										"password" : password,
									},
									dataType : "json",
									success : function(result) {
										var resultat = result;
										if (resultat.message == "1") {
											window.location = ('Home.jsp');
											e.returnValue = false;
											return false;
										}
										else{
											console.log("here");
											window.location = ('Login.jsp');
											e.returnValue = false;
											return false;
										}
										},
									error : function(xhr, err) {
										alert('Ajax readyState: '
												+ xhr.readyState + '\nstatus: '
												+ xhr.status + ' ' + err);
									}
								});
					});

		});
*/
