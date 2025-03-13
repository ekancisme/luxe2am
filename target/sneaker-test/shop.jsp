<%@page import="phuKien.phuKienDTO"%>
<%@page import="phuKien.phuKienDAO"%>
<%@page import="user.UserDTO"%>
<%@page import="Sneakers.SneakersDTO"%>
<%@page import="java.util.List"%>
<%@page import="Sneakers.SneakersDAO"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>HienSneakers</title>
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
                                        <li><a href="SearchCategoriesController?category=Adidas" class="d-flex">Adidas</a></li>
                                        <li><a href="SearchCategoriesController?category=Nike" class="d-flex">Nike</a></li>
                                        <li><a href="SearchCategoriesController?category=MLB" class="d-flex">MLB</a></li>
                                    </form>
                                </ul>
                            </li>
                            <li><a href="about.jsp">About</a></li>
                                <% if (loginUser != null) { %>
                            <li><a href="update.jsp">Account</a></li>
                                <%} %>
                        </ul>
                    </div>
                </nav>
            </header>

            <div class="bg-light py-3">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12 mb-0"><a href="home.jsp">Home</a> <span class="mx-2 mb-0">/</span> <strong class="text-black">Shop</strong></div>
                    </div>
                </div>
            </div>

            <div class="site-section">
                <div class="container">

                    <div class="row mb-5">  
                        <%--show case--%>    
                        <%
                            phuKienDAO dao = new phuKienDAO();
                            List<phuKienDTO> phuKienList = dao.getAllPK();
                            if (request.getAttribute("Glasses_List") != null) {
                                phuKienList = (List<phuKienDTO>) request.getAttribute("Glasses_List");
                            } else {
                                phuKienList = dao.getAllPK();
                            }
                        %>        
                        <div class="col-md-9 order-2">
                            <div class="row">    
                                <div class="col-md-12">
                                    <div class="float-md-left mb-4"><h2 class="text-black h5">Shop All</h2></div>
                                    <%
                                        String message = (String) request.getAttribute("message");
                                        if (message == null) {
                                            message = "";
                                        }
                                    %>
                                    <div class="d-flex justify-content-center">
                                        <h3 style="color: red;"><%= message%></h3>
                                    </div> 
                                </div>            


                            </div>
                            <div class="row mb-5">
                                <% for (phuKienDTO phuDTO : phuKienList) {%>
                                <div class="col-sm-6 col-lg-4 mb-4" data-aos="fade-up">
                                    <div class="block-4 text-center border">
                                        <figure class="block-4-image">
                                            <img src="images/<%= phuDTO.getImage()%>" alt="Image placeholder" class="img-fluid">
                                        </figure>
                                        <div class="block-4-text p-4">
                                            <h3><a href="#" class="text-black"><%= phuDTO.getName()%></a></h3>
                                            <p class="mb-0"><%= phuDTO.getDescription()%></p>
                                            <p class="text-danger font-weight-bold">$<%= phuDTO.getPrice()%></p>
                                            <form action="AddToCartController" method="POST">
                                                <input type="hidden" name="SneakerID" value="<%= phuDTO.getPkID()%>">
                                                <input type="hidden" name="price" value="<%= phuDTO.getPrice()%>">
                                                <input type="hidden" name="image" value="<%= phuDTO.getImage()%>">
                                                <% if (loginUser == null) { %>
                                                <input type="hidden" name="userID" value="">
                                                <% } else {%>
                                                <input type="hidden" name="userID" value="<%= loginUser.getUserID()%>">
                                                <% } %>
                                                <input type="hidden" name="invId" value="null">
                                                <input type="hidden" name="quantity" value="1">
                                                <button type="submit" name="action" value="Add" class="btn btn-dark">Add to Cart</button>                          
                                            </form>                              
                                        </div>
                                    </div>
                                </div>
                                <% }%>
                            </div>

                        </div>

                        <%--Searching bar--%>

                        <div class="col-md-3 order-1 mb-5 mb-md-0">
                            <div class="border p-4 rounded mb-4">
                                <h3 class="mb-3 h6 text-uppercase text-black d-block">Brand</h3>
                                <ul class="list-unstyled mb-0">
                                    <li class="mb-1"><a href="SearchCategoriesController?brand=LV" class="d-flex"><span>LV</span></a></li>
                                    <li class="mb-1"><a href="SearchCategoriesController?brand=Prada" class="d-flex"><span>Prada</span> </a></li>
                                    <li class="mb-1"><a href="SearchCategoriesController?brand=Dior" class="d-flex"><span>Dior</span></a></li>
                                    
                                </ul>
                            </div>
                            <div class="border p-4 rounded mb-4">
                                <h3 class="mb-3 h6 text-uppercase text-black d-block">Categories</h3>
                                <ul class="list-unstyled mb-0">
                                    <li class="mb-1 dropdown">
                                        <a href="SearchCategoriesController?type=glasses" class="d-flex category-toggle"><span>Kinh</span></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="SearchCategoriesController?type=glasses&brand=LV">LV</a></li>
                                            <li><a href="SearchCategoriesController?type=glasses&brand=Prada">Prada</a></li>
                                            <li><a href="SearchCategoriesController?type=glasses&brand=Dior">Dior</a></li>
                                        </ul>
                                    </li>
                                    <li class="mb-1 dropdown">
                                        <a href="SearchCategoriesController?type=scarf" class="d-flex category-toggle"><span>Vong Tay</span></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="SearchCategoriesController?type=scarf&brand=LV">LV</a></li>
                                            <li><a href="SearchCategoriesController?type=scarf&brand=Prada">Prada</a></li>
                                            <li><a href="SearchCategoriesController?type=scarf&brand=Dior">Dior</a></li>
                                        </ul>
                                    </li>
                                    <li class="mb-1 dropdown">
                                        <a href="SearchCategoriesController?type=necklace" class="d-flex category-toggle"><span>Vong Co</span></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="SearchCategoriesController?type=necklace&brand=LV">LV</a></li>
                                            <li><a href="SearchCategoriesController?type=necklace&brand=Prada">Prada</a></li>
                                            <li><a href="SearchCategoriesController?type=necklace&brand=Dior">Dior</a></li>
                                        </ul>
                                    </li>
                                    <li class="mb-1 dropdown">
                                        <a href="SearchCategoriesController?type=all" class="d-flex category-toggle"><span>All</span></a>
                                    </li>
                                </ul>
                            </div>

                            <style>
                                /* ??nh d?ng dropdown */
                                .dropdown {
                                    position: relative;
                                }
                                .dropdown-menu {
                                    display: none;
                                    position: absolute;
                                    left: 0;
                                    top: 100%;
                                    background: white;
                                    border: 1px solid #ccc;
                                    padding: 10px;
                                    min-width: 150px;
                                    list-style: none;
                                    box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
                                    z-index: 100;
                                }
                                .dropdown-menu li {
                                    padding: 5px 0;
                                }
                                .dropdown-menu li a {
                                    text-decoration: none;
                                    color: black;
                                }
                                .dropdown-menu li a:hover {
                                    color: #ff6600;
                                }
                                .dropdown:hover .dropdown-menu {
                                    display: block;
                                }
                            </style>

                            <div class="border p-4 rounded mb-4">
                                <div class="mb-4">
                                    <form action="MainController" method="POST" id="priceForm">
                                        <h3 class="mb-3 h6 text-uppercase text-black d-block">Search by Price</h3>
                                        <label for="minPrice">Min Price:</label>
                                        <input type="number" id="minPrice" name="minPrice" style="border-radius: 5px" min="0" step="1">
                                        <label for="maxPrice">Max Price:</label>
                                        <input type="number" id="maxPrice" name="maxPrice" style="border-radius: 5px" min="0" step="1">
                                        <input type="hidden" name="action" value="SearchPrice">
                                        <button type="submit" value="Filter" class="btn btn-sm btn-secondary mb-1 mt-3">Search</button>
                                    </form>
                                </div>
                            </div>
                            <div>
                                <img style="width: 255px; height: 900px" src="images/banner.jpg" alt="alt">
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

        <script src="js/main.js"></script>

    </body>
</html>