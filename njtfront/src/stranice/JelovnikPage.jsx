import React, { useEffect, useMemo, useState } from "react";
import api from "../api";
import { addToCart, getCart } from "../utils/cart";
import "./JelovnikPage.css";

function JelovnikPage() {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  const [searchTerm, setSearchTerm] = useState("");
  const [onlyAvailable, setOnlyAvailable] = useState(false);
  const [sortBy, setSortBy] = useState("naziv-asc");
  const [currentPage, setCurrentPage] = useState(1);

  const [cartMap, setCartMap] = useState({});

  const itemsPerPage = 6;

  useEffect(() => {
    fetchProducts();
    refreshCartState();
  }, []);

  function refreshCartState() {
    const cart = getCart();
    const map = {};

    cart.forEach((item) => {
      map[item.id] = item.kolicina;
    });

    setCartMap(map);
  }

  async function fetchProducts() {
    try {
      const response = await api.get("/api/jela");
      setProducts(response.data);
    } catch (err) {
      setError("Greška pri učitavanju jela.");
    } finally {
      setLoading(false);
    }
  }

  function handleAddToCart(product) {
    addToCart(product);
    refreshCartState();
  }

  const filteredAndSortedProducts = useMemo(() => {
    let result = [...products];

    if (searchTerm.trim()) {
      const lower = searchTerm.toLowerCase();
      result = result.filter(
        (item) =>
          item.naziv?.toLowerCase().includes(lower) ||
          item.opis?.toLowerCase().includes(lower)
      );
    }

    if (onlyAvailable) {
      result = result.filter((item) => item.dostupan === true);
    }

    result.sort((a, b) => {
      if (sortBy === "naziv-asc") {
        return a.naziv.localeCompare(b.naziv);
      }
      if (sortBy === "naziv-desc") {
        return b.naziv.localeCompare(a.naziv);
      }
      if (sortBy === "cena-asc") {
        return Number(a.cena) - Number(b.cena);
      }
      if (sortBy === "cena-desc") {
        return Number(b.cena) - Number(a.cena);
      }
      return 0;
    });

    return result;
  }, [products, searchTerm, onlyAvailable, sortBy]);

  const totalPages = Math.ceil(filteredAndSortedProducts.length / itemsPerPage);

  const paginatedProducts = useMemo(() => {
    const start = (currentPage - 1) * itemsPerPage;
    return filteredAndSortedProducts.slice(start, start + itemsPerPage);
  }, [filteredAndSortedProducts, currentPage]);

  useEffect(() => {
    setCurrentPage(1);
  }, [searchTerm, onlyAvailable, sortBy]);

  if (loading) return <h2 className="center">Učitavanje...</h2>;
  if (error) return <h2 className="center">{error}</h2>;

  return (
    <div className="menu-page">
      <div className="menu-header">
        <div>
          <span className="menu-badge">Jelovnik</span>
          <h1 className="menu-title">Pregled svih jela</h1>
        </div>
      </div>

      <div className="menu-toolbar">
        <input
          type="text"
          placeholder="Pretraži jela..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="menu-search"
        />

        <select
          value={sortBy}
          onChange={(e) => setSortBy(e.target.value)}
          className="menu-select"
        >
          <option value="naziv-asc">Naziv A-Z</option>
          <option value="naziv-desc">Naziv Z-A</option>
          <option value="cena-asc">Cena rastuće</option>
          <option value="cena-desc">Cena opadajuće</option>
        </select>

        <label className="menu-checkbox">
          <input
            type="checkbox"
            checked={onlyAvailable}
            onChange={(e) => setOnlyAvailable(e.target.checked)}
          />
          Samo dostupna jela
        </label>
      </div>

      <div className="menu-grid">
        {paginatedProducts.map((p) => {
          const quantityInCart = cartMap[p.id] || 0;

          return (
            <div key={p.id} className="menu-card">
              <img
                src={
                  p.slika ||
                  "https://via.placeholder.com/300x200?text=No+image"
                }
                alt={p.naziv}
              />

              <div className="menu-card-body">
                <h3>{p.naziv}</h3>
                <p>{p.opis}</p>

                <div className="menu-meta">
                  <span>{p.jedinicaMere}</span>
                  <span className={p.dostupan ? "available" : "unavailable"}>
                    {p.dostupan ? "Dostupno" : "Nedostupno"}
                  </span>
                </div>

                <div className="menu-footer">
                  <span>{p.cena} RSD</span>
                  <button
                    onClick={() => handleAddToCart(p)}
                    disabled={!p.dostupan}
                    className={quantityInCart > 0 ? "added-to-cart-btn" : ""}
                  >
                    {quantityInCart > 0
                      ? `U korpi: ${quantityInCart}`
                      : "Dodaj u korpu"}
                  </button>
                </div>
              </div>
            </div>
          );
        })}
      </div>

      {paginatedProducts.length === 0 && (
        <p className="center">Nema rezultata za prikazane kriterijume.</p>
      )}

      {totalPages > 1 && (
        <div className="pagination">
          <button
            onClick={() => setCurrentPage((prev) => prev - 1)}
            disabled={currentPage === 1}
          >
            Prethodna
          </button>

          <span>
            Strana {currentPage} / {totalPages}
          </span>

          <button
            onClick={() => setCurrentPage((prev) => prev + 1)}
            disabled={currentPage === totalPages}
          >
            Sledeća
          </button>
        </div>
      )}
    </div>
  );
}

export default JelovnikPage;