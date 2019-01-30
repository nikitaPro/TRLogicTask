<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <link rel="icon" href=<c:url value="/images/favicon.ico?v=2"/> />
    <link href=<c:url value="/css/jquery-ui.min.css"/>  rel="stylesheet" media="screen">
    <link href=<c:url value="/css/home.css"/>  rel="stylesheet" media="screen">
    <link href=<c:url value="/css/general.css"/>  rel="stylesheet" media="screen">
    <script src=<c:url value="/jQuery/jquery.min.js"/> ></script>
    <script src=<c:url value="/jQuery/jquery-ui.min.js"/> ></script>
    <script src=<c:url value="/js/general.js"/> ></script>
    <script src=<c:url value="/js/home.js"/> ></script>
    <script src=<c:url value="/jQuery/jquery.mask.min.js"/> ></script>
    <title>Home</title>
  </head>
  <body>
    <div id="sign_board" align="center">
      <ul>
        <li><a href="#sign_in">Sign in</a></li>
        <li><a href="#sign_up">Sign up</a></li>
      </ul>
      <div id="sign_in">
        <c:if test="${not empty error}">
          <div class="errorText">${error}</div>
        </c:if>
        <c:if test="${not empty logout}">
          <div class="successText">${logout}</div>
        </c:if>
        <div>Enter your email and password to sign in.</div>
        <form name='login_form' action="<c:url value="/signin" />" method="POST">
          <table>
            <tr align="center">
              <td><input type="text" name="email" placeholder="Email" maxlength="80"/></td>
            </tr>
            <tr align="center">
              <td><input type="password" name="pass" placeholder="Password" maxlength="80"/></td>
            </tr>
            <tr align="center">
              <td>
                <button class="ui-button" name="submit" type="submit" value="Login">
                  Sign in
                </button>
              </td>
            </tr>
          </table>
        </form>
      </div>
      <div id="sign_up">
        <form action="/signup" method = "GET">
          <table>
            <tr align="center">
              <td><input id = "user_name"  type="text" name="username" 
                      placeholder="Name" maxlength="40" 
                      title="Enter your first name, e.g. John"/></td>
            </tr>
            <tr align="center">
              <td><input id = "last_name"  type="text" name="lastname" 
                      placeholder="Last name" maxlength="40" 
                      title="Enter your last name, e.g. Brown"/></td>
            </tr>
            <tr align="center">
              <td><input id = "phone" type="text" name="phone" 
                      placeholder="Phone" title="Enter your phone number in 
                      international format, e.g. +38(050)111-11-11"/></td>
            </tr>
            <tr align="center">
              <td><input id = "email" type="text" name="email" 
                      placeholder="E-mail" maxlength="80" 
                      title="Enter your email address, e.g. example@example.com"/></td>
            </tr>
            <tr align="center">
              <td><input id = "pass" type="password" name="pass" 
                      placeholder="Password, 8-20 characters" maxlength="80"
                      title="Enter password with a lenght 8-20 characters"/></td>
            </tr>
            <tr align="center">
              <td><input id = "pass_2" type="password" name="pass_2" 
                      placeholder="Confirm password" maxlength="80"
                      title="Enter your password again"/></td>
            </tr>
            <tr align="center">
              <td>
                <button id = "sign_up_btn" class="ui-button" 
                name="button" type="button" value="Register">
                  Sign up
                </button>
              </td>
            </tr>
          </table>
        </form>
      </div>
    </div>
    <div class="errorText" id="error_dialog" hidden="hidden">
    </div>
    <div class="messageText" id="message_dialog" hidden="hidden">
    </div>
  </body>
</html>
