import React, { useEffect, useState } from "react";
import api from "../api";
import {
  clearCart,
  getCart,
  getCartTotal,
  removeFromCart,
  updateCartQuantity,
} from "../utils/cart";
import "./KorpaPage.css";

function KorpaPage() {
  const [cartItems, setCartItems] = useState([]);
  const [ukupanIznos, setUkupanIznos] = useState(0);

  const [formData, setFormData] = useState({
    adresa: "",
    kontakt: "",
    napomena: "",
  });

  const [message, setMessage] = useState("");
  const [error, setError] = useState("");
  const [loadingOrder, setLoadingOrder] = useState(false);
  const [racun, setRacun] = useState(null);

  useEffect(() => {
    refreshCart();
  }, []);

  function refreshCart() {
    setCartItems(getCart());
    setUkupanIznos(getCartTotal());
  }

  function handleQuantityChange(id, value) {
    updateCartQuantity(id, Number(value));
    refreshCart();
  }

  function handleRemove(id) {
    removeFromCart(id);
    refreshCart();
  }

  function handleChange(e) {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  }

  async function handleOrder() {
    setMessage("");
    setError("");
    setRacun(null);

    if (cartItems.length === 0) {
      setError("Korpa je prazna.");
      return;
    }

    const userId = localStorage.getItem("userId");

    if (!userId) {
      setError("Morate biti ulogovani da biste poručili.");
      return;
    }

    if (!formData.adresa || !formData.kontakt) {
      setError("Adresa i kontakt su obavezni.");
      return;
    }

    try {
      setLoadingOrder(true);

      const porudzbinaPayload = {
        adresa: formData.adresa,
        kontakt: formData.kontakt,
        napomena: formData.napomena,
        ukupanIznos: ukupanIznos,
        korisnikId: Number(userId),
        status: "OBRADA",
      };

      const porudzbinaResponse = await api.post("/api/porudzbine", porudzbinaPayload);
      const porudzbina = porudzbinaResponse.data;

      for (const item of cartItems) {
        const stavkaPayload = {
          kolicina: item.kolicina,
          jedinicnaCena: item.cena,
          iznosStavke: Number(item.cena) * Number(item.kolicina),
          porudzbinaId: porudzbina.id,
          jeloId: item.id,
        };

        await api.post("/api/stavke-porudzbine", stavkaPayload);
      }

      setRacun({
        porudzbinaId: porudzbina.id,
        datum: new Date().toLocaleString("sr-RS"),
        stavke: cartItems,
        ukupno: ukupanIznos,
        adresa: formData.adresa,
        kontakt: formData.kontakt,
        napomena: formData.napomena,
      });

      clearCart();
      refreshCart();

      setFormData({
        adresa: "",
        kontakt: "",
        napomena: "",
      });

      setMessage("Porudžbina je uspešno kreirana.");
    } catch (err) {
      setError("Greška pri kreiranju porudžbine.");
    } finally {
      setLoadingOrder(false);
    }
  }

  return (
    <div className="cart-page">
      <div className="cart-header">
        <span className="cart-badge">Korpa</span>
        <h1>Pregled korpe i poručivanje</h1>
      </div>

      {message && <div className="cart-alert success">{message}</div>}
      {error && <div className="cart-alert error">{error}</div>}

      <div className="cart-layout">
        <div className="cart-items-card">
          <h2>Stavke u korpi</h2>

          {cartItems.length === 0 ? (
            <p>Korpa je trenutno prazna.</p>
          ) : (
            <div className="cart-items-list">
              {cartItems.map((item) => (
                <div key={item.id} className="cart-item">
                  <img
                    src={
                      item.slika ||
                      "https://via.placeholder.com/120x90?text=No+image"
                    }
                    alt={item.naziv}
                  />

                  <div className="cart-item-info">
                    <h3>{item.naziv}</h3>
                    <p>{item.opis}</p>
                    <span>{item.cena} RSD</span>
                  </div>

                  <div className="cart-item-actions">
                    <input
                      type="number"
                      min="1"
                      value={item.kolicina}
                      onChange={(e) =>
                        handleQuantityChange(item.id, e.target.value)
                      }
                    />
                    <button onClick={() => handleRemove(item.id)}>Obriši</button>
                  </div>
                </div>
              ))}
            </div>
          )}
        </div>

        <div className="cart-summary-card">
          <h2>Podaci za porudžbinu</h2>

          <div className="cart-form-group">
            <label>Adresa</label>
            <input
              type="text"
              name="adresa"
              value={formData.adresa}
              onChange={handleChange}
              placeholder="Unesite adresu"
            />
          </div>

          <div className="cart-form-group">
            <label>Kontakt</label>
            <input
              type="text"
              name="kontakt"
              value={formData.kontakt}
              onChange={handleChange}
              placeholder="Unesite kontakt telefon"
            />
          </div>

          <div className="cart-form-group">
            <label>Napomena</label>
            <textarea
              name="napomena"
              value={formData.napomena}
              onChange={handleChange}
              placeholder="Dodatna napomena"
            />
          </div>

          <div className="cart-total-box">
            <span>Ukupan iznos</span>
            <strong>{ukupanIznos.toFixed(2)} RSD</strong>
          </div>

          <button
            className="cart-order-btn"
            onClick={handleOrder}
            disabled={loadingOrder || cartItems.length === 0}
          >
            {loadingOrder ? "Poručivanje..." : "Potvrdi porudžbinu"}
          </button>
        </div>
      </div>

      {racun && (
        <div className="invoice-card">
          <h2>Račun</h2>
          <p><strong>Broj porudžbine:</strong> {racun.porudzbinaId}</p>
          <p><strong>Datum:</strong> {racun.datum}</p>
          <p><strong>Adresa:</strong> {racun.adresa}</p>
          <p><strong>Kontakt:</strong> {racun.kontakt}</p>
          <p><strong>Napomena:</strong> {racun.napomena || "-"}</p>

          <table className="invoice-table">
            <thead>
              <tr>
                <th>Jelo</th>
                <th>Količina</th>
                <th>Cena</th>
                <th>Iznos</th>
              </tr>
            </thead>
            <tbody>
              {racun.stavke.map((stavka) => (
                <tr key={stavka.id}>
                  <td>{stavka.naziv}</td>
                  <td>{stavka.kolicina}</td>
                  <td>{stavka.cena}</td>
                  <td>{(Number(stavka.cena) * Number(stavka.kolicina)).toFixed(2)}</td>
                </tr>
              ))}
            </tbody>
          </table>

          <div className="invoice-total">
            Ukupno: {racun.ukupno.toFixed(2)} RSD
          </div>
        </div>
      )}
    </div>
  );
}

export default KorpaPage;