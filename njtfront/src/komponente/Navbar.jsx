import React, { useEffect, useState } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { FaUtensils, FaShoppingCart, FaUserShield, FaSignOutAlt } from "react-icons/fa";
import "./Navbar.css";
import { getCart } from "../utils/cart";

function Navbar() {
  const navigate = useNavigate();
  const location = useLocation();

  const [user, setUser] = useState({
    token: null,
    ime: "",
    rola: "",
  });

  const [cartCount, setCartCount] = useState(0);

  useEffect(() => {
    refreshUser();
    refreshCartCount();
  }, [location]);

  function refreshUser() {
    setUser({
      token: localStorage.getItem("token"),
      ime: localStorage.getItem("ime") || "",
      rola: localStorage.getItem("rola") || "",
    });
  }

  function refreshCartCount() {
    const cart = getCart();
    const totalItems = cart.reduce((sum, item) => sum + Number(item.kolicina), 0);
    setCartCount(totalItems);
  }

  function handleLogout() {
    localStorage.removeItem("token");
    localStorage.removeItem("userId");
    localStorage.removeItem("ime");
    localStorage.removeItem("prezime");
    localStorage.removeItem("email");
    localStorage.removeItem("rola");

    setUser({
      token: null,
      ime: "",
      rola: "",
    });

    navigate("/");
  }

  const isLoggedIn = !!user.token;
  const isAdmin = user.rola === "ADMIN";
  const isUser = user.rola === "KORISNIK";

  return (
    <nav className="navbar">
      <div className="navbar-left">
        <Link to="/" className="navbar-logo">
          FoodApp
        </Link>
      </div>

      <div className="navbar-center">
        <Link to="/" className="navbar-link">
          Početna
        </Link>

        <Link to="/jelovnik" className="navbar-link">
          Jelovnik
        </Link>

        {!isLoggedIn && (
          <>
            <Link to="/login" className="navbar-link">
              Login
            </Link>
            <Link to="/register" className="navbar-link">
              Registracija
            </Link>
          </>
        )}

        {isUser && (
          <>
            <Link to="/korpa" className="navbar-link navbar-cart-link">
              <FaShoppingCart />
              <span>Korpa</span>
              {cartCount > 0 && <span className="cart-badge">{cartCount}</span>}
            </Link>
          </>
        )}

        {isAdmin && (
          <Link to="/admin" className="navbar-link navbar-admin-link">
            <FaUserShield />
            <span>Admin panel</span>
          </Link>
        )}
      </div>

      <div className="navbar-right">
        {isLoggedIn ? (
          <div className="navbar-user-box">
            <span className="navbar-user-name">
              {user.ime} ({user.rola})
            </span>
            <button className="navbar-logout-btn" onClick={handleLogout}>
              <FaSignOutAlt />
              <span>Logout</span>
            </button>
          </div>
        ) : (
          <div className="navbar-guest">
            <FaUtensils />
            <span>Dobrodošli</span>
          </div>
        )}
      </div>
    </nav>
  );
}

export default Navbar;