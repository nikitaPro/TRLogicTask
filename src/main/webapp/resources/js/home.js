$(document).ready(function () {
    var $tabs = $('#sign_board').tabs();
    $("#sign_board").position({
        my: "center",  // position on the positioned element
        at: "center",  // place on the element relative to which will be positioning
        of: "body"        // element relative to which will be positioning
    });
    $("#sign_up_btn").click(function(event) {
        signUp();
    });
    $("#phone").mask('+00(000)000-00-00');
});

function errorDialog(title_, message, elementForFocus) {
    $("#error_dialog").dialog({
        resizable:false,
        title:title_,
        modal:true,
        buttons:{
          "Ok": function(){
            $(this).dialog( "close" );
            if (elementForFocus != null) {
                elementForFocus.focus();
            }
          }
        }
      }).html(message);
}

function isEmailValid(email) {
    if (email.match(/^.+@.+\..+$/) == null) {
        errorDialog("E-mail format", 
                "Invalid email format. Please, check your email, " +
                "it must look like example@example.com",
                $('#email'));
        return false;
    }
    return true
}

function isPhoneValid(phone) {
    if (phone.match(/^\+\d\d\(\d{3}\)\d{3}\-\d\d\-\d\d$/) == null) {
        errorDialog("Phone format", 
                "Invalid phone number format. Please, check your phone number, " +
                "it must look like +00(000)000-00-00",
                $('#phone'));
        return false;
    }
    return true
}

function signUp() {
    var nameVar = $("#user_name").val();
    var lastName = $("#last_name").val()
    var emailVar = $("#email").val();
    var phoneVar = $("#phone").val();
    var passVar = $("#pass").val();
    var pass2Var = $("#pass_2").val();
    
    if (nameVar == null || nameVar.trim() == "") {
        errorDialog("Name", "Please, enter your name.", $("#user_name"));
        return;
    }
    if (lastName == null || lastName.trim() == "") {
        errorDialog("Last name", "Please, enter your last name.", $("#last_name"))
        return;
    }
    if (!isEmailValid(emailVar)) {
        return;
    }
    if (!isPhoneValid(phoneVar)) {
        return;
    }
    if (passVar.length < 8 || passVar.length > 20) {
        errorDialog("Password", 
                "Password is invalid! Please, choose password in 8-20 length range.",
                $("#pass"));
        return;
    }
    if (passVar != pass2Var){
        errorDialog("Password", "Passwords are not matching.", $("#pass_2"));
        return;
    }
    $.get("/forTRLogic/signup", {
        name: nameVar,
        last_name: lastName,
        phone: phoneVar,
        email: emailVar,
        pass: passVar,
        pass_confirm: pass2Var
    }).done(function(response, status, xhr) {
                
        var n = response.search(/<html>/i);
        if (n != -1) window.location.href = "/home";
        
        var jsonObj = JSON.parse(response);

        if (jsonObj.status.success) {
            errorDialog("Welcome", jsonObj.status.message, null);
        } else {
            errorDialog("Warning", jsonObj.status.message, null);
        }
        
    }).fail(function(xhr, status, error) {
        errorDialog("Error", 
                "We apologize, there are problems on the server, " +
                "please try again later.", 
                null);
    });
}