<%@page import="user.UserDTO"%>

<%
    String savedUser = "";
    String savedPass = "";
    String rememberMeChecked = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie c : cookies) {
            if (c.getName().equals("username")) {
                savedUser = c.getValue();
                rememberMeChecked = "checked"; // N?u c� username th� m?c ??nh b?t Remember Me
            }
            if (c.getName().equals("password")) {
                savedPass = c.getValue();
            }
        }
    }
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>HienSneaker</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Mukta:300,400,700"> 
        <link rel="stylesheet" href="fonts/icomoon/style.css">

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/magnific-popup.css">
        <link rel="stylesheet" href="css/jquery-ui.css">
        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">


        <link rel="stylesheet" href="css/aos.css">

        <link rel="stylesheet" href="css/style.css">

    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
        %>    
        <%-- searh bar --%>
        <div class="site-wrap">
            <header class="site-navbar" role="banner">
                <div class="site-navbar-top">
                    <div class="container">
                        <div class="row align-items-center">

                            <div class="col-6 col-md-4 order-2 order-md-1 site-search-icon text-left">
                                <form action="MainController" method="POST" class="site-block-top-search">
                                    <span class="icon icon-search2"></span>
                                    <input type="text" name="search" class="form-control border-0" placeholder="Search">
                                    <input type="hidden" name="action" value="Search">
                                </form>
                            </div>

                            <div class="col-12 mb-3 mb-md-0 col-md-4 order-1 order-md-2 text-center">
                                <div>
                                    <a href="home.jsp">
                                        <img style="width: 50%; padding: -100px" src="images/logo2.jpg">
                                    </a>
                                </div>
                            </div>
                            <%--login-cart-update account information -logout--%>
                            <div class="col-6 col-md-4 order-3 order-md-3 text-right">
                                <form action="MainController" method="POST">
                                    <div class="site-top-icons">
                                        <ul>
                                            <% if (loginUser == null) { %>
                                            <li><a href="login.jsp"><span class="icon icon-person"></span> Login</a></li>
                                            <li><a href="register.jsp"><span class="icon icon-person"></span> Register</a></li>
                                                <% } else { %>
                                            <li><a href="LogoutController"></span>LOG OUT</a></li>
                                            <li><a href="update.jsp"><span class="icon icon-person"></span></a></li>
                                            <li>
                                                <a href="cart.jsp" class="site-cart">
                                                    <span class="icon icon-shopping_cart"></span>

                                                </a>
                                            </li> 
                                            <% }%>


                                            <li class="d-inline-block d-md-none ml-md-0"><a href="#" class="site-menu-toggle js-menu-toggle"><span class="icon-menu"></span></a></li>
                                        </ul>
                                    </div> 
                                </form>    
                            </div>

                        </div>
                    </div>
                </div> 
                <nav class="site-navigation text-right text-md-center" role="navigation">
                    <div class="container">
                        <ul class="site-menu js-clone-nav d-none d-md-block">
                            <li><a href="home.jsp">Home</a></li>
                            <li class="has-children">
                                <a href="shop.jsp">Shop</a>

                                <ul class="dropdown" style="list-style-type: none;">
                                    <form action="MainController" method="POST">
                                        <li><a href="SearchCategoriesController?category=Adidas" class="d-flex">Adidas</a></li>
                                        <li><a href="SearchCategoriesController?category=Nike" class="d-flex">Nike</a></li>
                                        <li><a href="SearchCategoriesController?category=MLB" class="d-flex">MLB</a></li>
                                    </form>
                                </ul>
                            </li>
                            <li><a href="about.jsp">About</a></li>
                            <li><a href="update.jsp">Account</a></li>

                        </ul>
                    </div>
                </nav>
            </header>
            <div class="bg-light py-3">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12 mb-0"><a href="index.html">Home</a> <span class="mx-2 mb-0">/</span> <strong class="text-black">Contact</strong></div>
                    </div>
                </div>
            </div>  




            <div class="site-section">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-md-12 text-center">
                            <h2 class="h3 mb-3 text-black">Login</h2>
                        </div>
                        <div class="col-md-7 mx-auto">

                            <form action="MainController" method="POST">

                                <div class="p-3 p-lg-5 border">
                                    <div class="form-group row">
                                        <div class="col-md-12">
                                            <label for="c_username" class="text-black">UserID or Email <span class="text-danger">*</span></label>
                                            <input type="text" placeholder="UserID/Email" class="form-control" id="c_username" name="userIDorEmail" value="<%= savedUser%>">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-md-12">
                                            <label for="c_password" class="text-black">Password <span class="text-danger">*</span></label>
                                            <input type="password" placeholder="Password" class="form-control" id="c_password" name="password" value="<%= savedPass%>">
                                        </div>
                                    </div>
                                    <%
                                        String error = (String) request.getAttribute("message");
                                        if (error == null)
                                            error = "";
                                    %>
                                    <h1 class="text-danger"><%= error%></h1>
                                    <input type="checkbox" name="rememberMe" <%= rememberMeChecked%> /> Remember Me
                                    <div class="form-group row">
                                        <div class="col-lg-12">
                                            <input type="submit" name="action"class="btn btn-info btn-lg btn-block" value="Login">
                                        </div>
                                    </div>

                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>


            <footer class="site-footer bg-dark border-top">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-6 mb-5 mb-lg-0">
                            <div class="row">
                                <div class="col-md-12">
                                    <h3 class="footer-heading mb-4">Navigations</h3>
                                </div>
                                <div class="col-md-6 col-lg-4">
                                    <ul class="list-unstyled">
                                        <li><a href="register.jsp">Create new account</a></li>
                                        <li><a href="login.jsp">Login</a></li>
                                        <li><a href="home.jsp">Menu</a></li>

                                    </ul>
                                </div>
                                <div class="col-md-6 col-lg-4">
                                    <ul class="list-unstyled">
                                        <li><a href="shop.jsp">Shop</a></li>

                                        <li><a href="about.jsp">About</a></li>

                                    </ul>
                                </div>

                            </div>
                        </div>
                        <div class="col-md-6 col-lg-3 mb-4 mb-lg-0">

                        </div>
                        <div class="col-md-6 col-lg-3">
                            <div class="block-5 mb-5">
                                <h3 class="footer-heading mb-4">Contact Info</h3>
                                <ul class="list-unstyled">
                                    <li class="address">FPT University</li>
                                    <li class="phone"><a href="tel://1234567">+12 345 6789</a></li>
                                    <li class="email">hiennhse180274@fpt.edu.vn</li>
                                </ul>
                            </div>


                        </div>
                    </div>

                </div>
            </footer>
        </div>

        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/jquery-ui.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/aos.js"></script>
        <script>
            window.onload = function () {
                var usernameField = document.getElementById("c_username");
                var passwordField = document.getElementById("c_password");

                if (usernameField.value !== "") {
                    passwordField.value = "<%= savedPass%>";
                }
            };

        </script>
        <script src="js/main.js"></script>

    </body>
</html>