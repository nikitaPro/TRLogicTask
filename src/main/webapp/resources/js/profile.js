$(document).ready(function () {
    $("#outer-table").position({
        my: "center",  // position on the positioned element
        at: "center",  // place on the element relative to which will be positioning
        of: "body"        // element relative to which will be positioning
    });
    $("#edit-button").click(function(event) {
        $("#error_dialog").dialog({
            resizable:false,
            title:"Not implemented",
            modal:true,
            buttons:{
              "Ok": function(){
                $(this).dialog( "close" );
              }
            }
        }).html("This function will be implemented soon.");
    });
    var $accordion = $("#accordion").accordion();
});