<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <link rel="icon" href=<c:url value="/images/favicon.ico?v=2"/> />
    <link href=<c:url value="/css/jquery-ui.min.css"/>  rel="stylesheet" media="screen">
    <link href=<c:url value="/css/profile.css"/>  rel="stylesheet" media="screen">
    <link href=<c:url value="/css/general.css"/>  rel="stylesheet" media="screen">
    <script src=<c:url value="/jQuery/jquery.min.js"/> ></script>
    <script src=<c:url value="/jQuery/jquery-ui.min.js"/> ></script>
    <script src=<c:url value="/js/profile.js"/> ></script>
    <script src=<c:url value="/jQuery/jquery.mask.min.js"/> ></script>
    <title>Home</title>
  </head>
  <body>
    <table id="outer-table" cellspacing="10" cellpadding="5" >
      <tr>
        <td class="first_col">
          <div id="photo">
            <img alt="photo" src=<c:url value="/images/photo.png"/>>
          </div>
        </td>
        <td class="second_col">
          <div id="contacts">
            <table id="contact_table">
              <tr>
                <td colspan="2" align="center">Contacts</td>
              </tr>
              <tr>
                <td>Name:</td><td id="name">${userInfo.name} ${userInfo.lastName} </td>
              </tr>
              <tr>
                <td>Email:</td><td id="email">${userInfo.email}</td>
              </tr>
              <tr>
                <td>Phone:</td><td id="phone">${userInfo.phone}</td>
              </tr>
              <tr>
                <td>
                  <button id="edit-button" class="ui-button">
                    Edit
                  </button>
                </td>
              </tr>
            </table>
          </div>
        </td>
        <td class="third_col" rowspan="2" valign="top">
          <div id="ads">Here can be your advertising</div>
        </td>
      </tr>
      <tr>
        <td class="first_col" valign="top">
          <div id="accordion">
            <h3><a href="#">Business</a></h3>
            <div>There are will be links on business info</div>
            <h3><a href="#">Partners</a></h3>
            <div>There are will be links on partners info</div>
            <h3><a href="#">News</a></h3>
            <div>There are will be last news</div>
            <h3><a href="#">Travels</a></h3>
            <div>There are will be links on travel companies offers</div>
          </div>
        </td>
        <td class="second_col" valign="top">
          <div id="news">News will be here</div>
        </td>
      </tr>
    </table>
    
    <div id="error_dialog" hidden="hidden">
    </div>
  </body>
</html>
