$(document).ready(function () {
    /* Set position of table with content on center of page */
    setPageContentPositionCenter($("#outer-table"));
    /* The same but for dynamic screen size change */
    window.addEventListener("resize", function() {
        setPageContentPositionCenter($("#outer-table"));
    }, false);
    
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
    
    $("#logout-button").click(function(event) {
        window.location.href = "/forTRLogic/logout";
    });
    
    /* Business content on left side */
    var $accordion = $("#accordion").accordion();
});
