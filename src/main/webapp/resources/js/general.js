/* Parameter element must be jQuery object - $('selector') */
function setPageContentPositionCenter(element) {
    element.position({
        my: "center",  // position on the positioned element
        at: "center",  // place on the element relative to which will be positioning
        of: "body"     // element relative to which will be positioning
    });
}