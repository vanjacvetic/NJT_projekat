import React from "react";
import "./HomePage.css";
import {
  FaUtensils,
  FaMotorcycle,
  FaLeaf,
  FaShoppingCart,
} from "react-icons/fa";
import { useNavigate } from "react-router-dom";

export default function HomePage() {
  const navigate = useNavigate();

  function handleGoToMenu() {
    navigate("/jelovnik");
  }

  function handleHowItWorks() {
    const section = document.getElementById("how-it-works");
    if (section) {
      section.scrollIntoView({ behavior: "smooth" });
    }
  }

  function handleOrderNow() {
    const token = localStorage.getItem("token");

    if (token) {
      navigate("/jelovnik");
    } else {
      navigate("/login");
    }
  }

  return (
    <div className="home-page">
      <section className="hero-section">
        <div className="hero-overlay" />
        <div className="hero-content">
          <div className="hero-text">
            <span className="hero-badge">Online poručivanje hrane</span>
            <h1>
              Ukusna hrana,
              <br />
              brza dostava,
              <br />
              jednostavna porudžbina
            </h1>
            <p>
              Pregledaj omiljena jela, poruči u nekoliko klikova i uživaj u
              obroku bez čekanja. Moderna platforma za brzo i jednostavno
              naručivanje hrane.
            </p>

            <div className="hero-actions">
              <button className="primary-btn" onClick={handleGoToMenu}>
                Pogledaj jelovnik
              </button>
              <button className="secondary-btn" onClick={handleHowItWorks}>
                Kako funkcioniše
              </button>
            </div>
          </div>

          <div className="hero-card">
            <div className="hero-card-top">
              <span className="status-dot" />
              Dostava dostupna
            </div>

            <h3>Najpopularnije danas</h3>

            <div className="mini-menu">
              <div className="mini-menu-item">
                <span>Capricciosa</span>
                <strong>950 RSD</strong>
              </div>
              <div className="mini-menu-item">
                <span>Carbonara</span>
                <strong>780 RSD</strong>
              </div>
              <div className="mini-menu-item">
                <span>Cheesecake</span>
                <strong>430 RSD</strong>
              </div>
            </div>

            <button className="card-btn" onClick={handleOrderNow}>
              Poruči odmah
            </button>
          </div>
        </div>
      </section>

      <section className="features-section" id="how-it-works">
        <div className="section-heading">
          <span>Zašto baš naša platforma</span>
          <h2>Jednostavno iskustvo od izbora do dostave</h2>
        </div>

        <div className="features-grid">
          <div className="feature-card">
            <div className="feature-icon">
              <FaUtensils />
            </div>
            <h3>Veliki izbor jela</h3>
            <p>
              Pice, paste, salate, pića i dezerti na jednom mestu, pregledno i
              lako dostupno.
            </p>
          </div>

          <div className="feature-card">
            <div className="feature-icon">
              <FaShoppingCart />
            </div>
            <h3>Brza porudžbina</h3>
            <p>
              Dodavanje u korpu i naručivanje su jednostavni, bez komplikovanih
              koraka.
            </p>
          </div>

          <div className="feature-card">
            <div className="feature-icon">
              <FaMotorcycle />
            </div>
            <h3>Dostava na adresu</h3>
            <p>
              Unesi adresu, kontakt i napomenu, a porudžbina stiže brzo i
              organizovano.
            </p>
          </div>

          <div className="feature-card">
            <div className="feature-icon">
              <FaLeaf />
            </div>
            <h3>Sveže i kvalitetno</h3>
            <p>
              Fokus je na kvalitetnim sastojcima, jasnim informacijama i boljem
              korisničkom iskustvu.
            </p>
          </div>
        </div>
      </section>

      <section className="categories-section">
        <div className="section-heading center">
          <span>Kategorije</span>
          <h2>Pronađi ono što ti se jede</h2>
        </div>

        <div className="categories-grid">
          <div className="category-card category-pizza">
            <h3>Pice</h3>
            <p>Od klasičnih do specijalnih kombinacija ukusa.</p>
          </div>

          <div className="category-card category-pasta">
            <h3>Paste</h3>
            <p>Kremaste, mesne i italijanske paste za svaki ukus.</p>
          </div>

          <div className="category-card category-salad">
            <h3>Salate</h3>
            <p>Lakši i sveži obroci za balansiran izbor.</p>
          </div>

          <div className="category-card category-dessert">
            <h3>Dezerti</h3>
            <p>Slatki završetak svakog obroka.</p>
          </div>
        </div>
      </section>

      <section className="cta-section">
        <div className="cta-box">
          <div>
            <span className="cta-label">Spreman za poručivanje?</span>
            <h2>Izaberi omiljeno jelo i poruči već danas</h2>
            <p>
              U par koraka možeš pregledati ponudu, dodati proizvode u korpu i
              završiti porudžbinu.
            </p>
          </div>
          <button className="primary-btn" onClick={handleGoToMenu}>
            Idi na jelovnik
          </button>
        </div>
      </section>
    </div>
  );
}