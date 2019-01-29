<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <link rel="icon" href=<c:url value="/images/favicon.ico?v=2"/> />
    <link href=<c:url value="/css/jquery-ui.min.css"/>  rel="stylesheet" media="screen">
    <link href=<c:url value="/css/home.css"/>  rel="stylesheet" media="screen">
    <script src=<c:url value="/jQuery/jquery.min.js"/> ></script>
    <script src=<c:url value="/jQuery/jquery-ui.min.js"/> ></script>
    <script src=<c:url value="/js/home.js"/> ></script>
    <script src=<c:url value="/jQuery/jquery.mask.min.js"/> ></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
  </head>
  <body>
    <div id="sign_board" align="center">
      <ul>
        <li><a href="#sign_in">Sign in</a></li>
        <li><a href="#sign_up">Sign up</a></li>
      </ul>
        <div id="sign_in">
          <form name='login_form' action="<c:url value="/signin" />" method="POST">
            <table>
              <tr align="center">
                <td><input type="text" name="email" placeholder="E-mail"/></td>
              </tr>
              <tr align="center">
                <td><input type="password" name="pass" placeholder="Password"/></td>
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
                <td><input id = "user_name"  type="text" name="username" placeholder="Name"/></td>
              </tr>
              <tr align="center">
                <td><input id = "last_name"  type="text" name="lastname" placeholder="Last name"/></td>
              </tr>
              <tr align="center">
                <td><input id = "phone" type="text" name="phone" placeholder="Phone"/></td>
              </tr>
              <tr align="center">
                <td><input id = "email" type="text" name="email" placeholder="E-mail"/></td>
              </tr>
              <tr align="center">
                <td><input id = "pass" type="password" name="pass" placeholder="Password"/></td>
              </tr>
              <tr align="center">
                <td><input id = "pass_2" type="password" name="pass_2" placeholder="Confirm password"/></td>
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
      <div id="error_dialog" hidden="hidden">

      </div>
    </body>
</html>
