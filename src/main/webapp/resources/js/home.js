$(document).ready(function () {
    /* Do "sign in" and "sign up" tabs */
    var $tabs = $('#sign_board').tabs();
    
    /* Set position of tabs panel on center of page */
    setPageContentPositionCenter($('#sign_board'));
    /* Reset position of tabs panel when screen size changed (mobile devices) */
    window.addEventListener("resize", function() {
        setPageContentPositionCenter($('#sign_board'));
    }, false);
    
    $("#sign_up_btn").click(function(event) {
        signUp();
    });
    $("#phone").mask('+00(000)000-00-00');
    /* Allow jQuery tooltip */
    $( document ).tooltip();
});

/* 
 * Make dialog with message and OK button. 
 * elementForFocus - the element with problem for which the dialog was called
 * Focus will return to this problem element. 
 * */
function errorDialog(title_, message, elementForFocus) {
    $("#error_dialog").dialog({
        resizable:false,
        title:title_,
        modal:true,
        buttons:{
          "Ok": function(){
            $(this).dialog( "close" );
            if (elementForFocus != null) {
                /* Return focus to the problem element */
                elementForFocus.focus();
            }
          }
        }
      }).html(message);
}

/* Make dialog with message and OK button for normal situations */
function messageDialog(title_, message) {
    $("#message_dialog").dialog({
        resizable:false,
        title:title_,
        modal:true,
        buttons:{
          "Ok": function(){
            $(this).dialog( "close" );
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

function isPassValid(pass) {
    if (pass.match(/[a-z]+/) == null || 
            pass.match(/[A-Z]+/) == null ||
            pass.match(/[0-9]+/) == null) {
        errorDialog("Password", 
                "Please, use lowercase, uppercase letters " +
                "and numbers for your password.",
                $('#pass'));
        return false;
    }
    return true;
}

function isNameValid(fieldName, text, forFocus) {
    var err;
    var title = fieldName + " format";
    if (text.length > 40) {
        errorDialog(title, 
                "Invalid " + fieldName + " format.\n" +
                "The name can be up to 40 characters.",
                forFocus);
        return false;
    }
    if (text.match(/^[a-zA-Z\-]+$/) == null) {
        errorDialog(title, 
                "Invalid " + fieldName + " format.\n" +
                "Please use only latin characters and '-'.",
                forFocus);
        return false;
    }
    return true;
}

/* After successful registration all fields must be cleared. */
function cleanSignUpForm() {
    $("#user_name").val("");
    $("#last_name").val("");
    $("#email").val("");
    $("#phone").val("");
    $("#pass").val("");
    $("#pass_2").val("");
}

function serverProblemError() {
    errorDialog("Error", 
            "We apologize, there are problems on the server, " +
            "please try again later.", 
            null);
}

function signUp() {
    var nameVar = $("#user_name").val();
    var lastName = $("#last_name").val();
    var emailVar = $("#email").val();
    var phoneVar = $("#phone").val();
    var passVar = $("#pass").val();
    var pass2Var = $("#pass_2").val();
    
    /* ===== Validation part ===== */
    if (nameVar == null || nameVar.trim() == "") {
        errorDialog("Name", "Please, enter your name.", $("#user_name"));
        return;
    }
    if (!isNameValid("Name", nameVar, $("#user_name"))) {
        return;
    }
    if (lastName == null || lastName.trim() == "") {
        errorDialog("Last name", "Please, enter your last name.", $("#last_name"))
        return;
    }
    if (!isNameValid("Last name", lastName, $("#last_name"))) {
        return;
    }
    if (!isEmailValid(emailVar)) {
        return;
    }
    if (!isPhoneValid(phoneVar)) {
        return;
    }
    if (!isPassValid(passVar)) {
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
    
    /* ===== Validation part finish ===== */
    /* Sending user data on server */
    $.get("/signup", {
        name: nameVar,
        last_name: lastName,
        phone: phoneVar,
        email: emailVar,
        pass: passVar,
        pass_confirm: pass2Var
    }).done(function(response, status, xhr) {
        /* 
         * If server return some technical page with stack trace to the 
         * client side 
         * */
        var n = response.search(/<html>/i);
        if (n != -1) {
            serverProblemError();
            return;
        }
        
        var jsonObj = JSON.parse(response);

        if (jsonObj.status.success) {
            messageDialog("Welcome", jsonObj.status.message, null);
            cleanSignUpForm();
        } else {
            errorDialog("Warning", jsonObj.status.message, null);
        }
        
    }).fail(function(xhr, status, error) {
        serverProblemError();
    });
    
}