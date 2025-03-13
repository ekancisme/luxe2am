<%@page import="user.UserDTO"%>
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
                <% if(loginUser == null) { %>
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
                <% } %>
                  
                
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
                        <li><a href="SearchCategoriesController?category=nam" class="d-flex">Adidas</a></li>
                        <li><a href="SearchCategoriesController?category=nu" class="d-flex">Nike </a></li>
                        <li><a href="SearchCategoriesController?category=ram" class="d-flex">MLB</a></li>
                    </form>
                </ul>
            </li>
            <li><a href="about.jsp">About</a></li>
            <% if(loginUser != null) { %>
            <li><a href="update.jsp">Account</a></li>
            <%} %>
          </ul>
        </div>
      </nav>    
    </header>

    <div class="site-blocks-cover" style="background-image: url(images/home.jpg); width:100%" data-aos="fade">
      <div class="container">
        <div class="row align-items-start align-items-md-center justify-content-end">
          <div class="col-md-5 text-center text-md-left pt-5 pt-md-0">
            <h1 class="mb-2">Finding Your Perfect Sneaker</h1>
            <div class="intro-text text-center text-md-left">
              <p class="mb-4">
                    Welcome to HienSneaker! We believe that the right pair of sneakers not only enhances your fashion sense, but also shows off your unique personality and style. 
                     Whether you're looking for a professional sneaker for school,
                     the weekend, or a sturdy shoe for the outdoors, we've got you covered.</p>
              <p>
                <a href="shop.jsp" class="btn btn-sm btn-dark">Shop Now</a>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="site-blocks-cover" style="background-image: url(images/trending.png); width:100%" data-aos="fade">
      <div class="container">
        <div class="pt-5">
          <div class="col-md-5 text-center text-md-left pt-5 pt-md-0">
            <h1 class="mb-2">HOT!!! Best Seller</h1>
            <div class="intro-text text-center text-md-left">
              <p class="mb-4">
                    With a chunky design that is both classic and youthful, the Chunky Liner Mid Denim sneaker is the perfect companion to show off your dynamic style. 
                    Featured with monogram motifs combined with the exclusive logo of the MLB brand, 
                    it not only creates a timeless look but also brings the elegance and personality that MLB teams are always looking for.</p>
              <p>
                <a href="shop.jsp" class="btn btn-sm btn-dark">GO TO ORDER NOW</a>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="site-section site-blocks-2">
      <div class="container">
        <div class="row">
          <div class="col-sm-6 col-md-6 col-lg-4 mb-4 mb-lg-0" data-aos="fade" data-aos-delay="">
            <a class="block-2-item" href="SearchCategoriesController?category=nu">
              <figure class="image">
                <img src="images/adidas_1.jpg" alt="" class="img-fluid">
              </figure>
              <div class="text">
                <span class="text-uppercase">Collections</span>
                <h3>Adidas Sneakers</h3>
              </div>
            </a>
          </div>
          <div class="col-sm-6 col-md-6 col-lg-4 mb-5 mb-lg-0" data-aos="fade" data-aos-delay="100">
            <a class="block-2-item" href="SearchCategoriesController?category=ram">
              <figure class="image">
                <img src="images/MLB Bigball Chunky Varsity.jpg" alt="" class="img-fluid">
              </figure>
              <div class="text">
                <span class="text-uppercase">Collections</span>
                <h3>MLB Sneakers</h3>
              </div>
            </a>
          </div>
          <div class="col-sm-6 col-md-6 col-lg-4 mb-5 mb-lg-0" data-aos="fade" data-aos-delay="200">
            <a class="block-2-item" href="SearchCategoriesController?category=nam">
              <figure class="image">
                <img src="images/Nike Nam Downshifter 11.jpg" alt="" class="img-fluid">
              </figure>
              <div class="text">
                <span class="text-uppercase">Collections</span>
                <h3>Nike Sneakers</h3>
              </div>
            </a>
          </div>
        </div>
      </div>
    </div>

    

    

    <footer class="site-footer bg-dark bg-dark border-top text-primary-emphasis">
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

  <script src="js/main.js"></script>
    
  </body>
</html>