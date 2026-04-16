import React, { useEffect, useState } from "react";
import api from "../api";
import "./AdminDashboardPage.css";

function AdminDashboardPage() {
  const [activeTab, setActiveTab] = useState("kategorije");

  const [kategorije, setKategorije] = useState([]);
  const [jela, setJela] = useState([]);
  const [porudzbine, setPorudzbine] = useState([]);

  const [loadingKategorije, setLoadingKategorije] = useState(false);
  const [loadingJela, setLoadingJela] = useState(false);
  const [loadingPorudzbine, setLoadingPorudzbine] = useState(false);

  const [message, setMessage] = useState("");
  const [error, setError] = useState("");

  const [kategorijaForm, setKategorijaForm] = useState({
    naziv: "",
    opis: "",
  });

  const [jeloForm, setJeloForm] = useState({
    naziv: "",
    opis: "",
    cena: "",
    jedinicaMere: "",
    slika: "",
    dostupan: true,
    kategorijaJelaId: "",
  });

  useEffect(() => {
    fetchKategorije();
    fetchJela();
    fetchPorudzbine();
  }, []);

  async function fetchKategorije() {
    try {
      setLoadingKategorije(true);
      const response = await api.get("/api/kategorije-jela");
      setKategorije(response.data);
    } catch (err) {
      setError("Greška pri učitavanju kategorija.");
    } finally {
      setLoadingKategorije(false);
    }
  }

  async function fetchJela() {
    try {
      setLoadingJela(true);
      const response = await api.get("/api/jela");
      setJela(response.data);
    } catch (err) {
      setError("Greška pri učitavanju jela.");
    } finally {
      setLoadingJela(false);
    }
  }

  async function fetchPorudzbine() {
    try {
      setLoadingPorudzbine(true);
      const response = await api.get("/api/porudzbine");
      setPorudzbine(response.data);
    } catch (err) {
      setError("Greška pri učitavanju porudžbina.");
    } finally {
      setLoadingPorudzbine(false);
    }
  }

  function handleKategorijaChange(e) {
    const { name, value } = e.target;
    setKategorijaForm((prev) => ({
      ...prev,
      [name]: value,
    }));
  }

  function handleJeloChange(e) {
    const { name, value, type, checked } = e.target;
    setJeloForm((prev) => ({
      ...prev,
      [name]: type === "checkbox" ? checked : value,
    }));
  }

  async function handleAddKategorija(e) {
    e.preventDefault();
    setMessage("");
    setError("");

    try {
      await api.post("/api/kategorije-jela", kategorijaForm);
      setMessage("Kategorija je uspešno dodata.");
      setKategorijaForm({
        naziv: "",
        opis: "",
      });
      fetchKategorije();
    } catch (err) {
      setError("Greška pri dodavanju kategorije.");
    }
  }

  async function handleDeleteKategorija(id) {
    setMessage("");
    setError("");

    try {
      await api.delete(`/api/kategorije-jela/${id}`);
      setMessage("Kategorija je uspešno obrisana.");
      fetchKategorije();
    } catch (err) {
      setError("Greška pri brisanju kategorije.");
    }
  }

  async function handleAddJelo(e) {
    e.preventDefault();
    setMessage("");
    setError("");

    try {
      const payload = {
        ...jeloForm,
        cena: Number(jeloForm.cena),
        kategorijaJelaId: Number(jeloForm.kategorijaJelaId),
      };

      await api.post("/api/jela", payload);

      setMessage("Jelo je uspešno dodato.");
      setJeloForm({
        naziv: "",
        opis: "",
        cena: "",
        jedinicaMere: "",
        slika: "",
        dostupan: true,
        kategorijaJelaId: "",
      });
      fetchJela();
    } catch (err) {
      setError("Greška pri dodavanju jela.");
    }
  }

  async function handleDeleteJelo(id) {
    setMessage("");
    setError("");

    try {
      await api.delete(`/api/jela/${id}`);
      setMessage("Jelo je uspešno obrisano.");
      fetchJela();
    } catch (err) {
      setError("Greška pri brisanju jela.");
    }
  }

  return (
    <div className="admin-page">
      <div className="admin-header">
        <div>
          <span className="admin-badge">Admin panel</span>
          <h1>Upravljanje sadržajem</h1>
          <p>Na jednom mestu upravljaj kategorijama, jelima i pregledom porudžbina.</p>
        </div>
      </div>

      <div className="admin-tabs">
        <button
          className={activeTab === "kategorije" ? "tab-btn active" : "tab-btn"}
          onClick={() => setActiveTab("kategorije")}
        >
          Kategorije
        </button>
        <button
          className={activeTab === "jela" ? "tab-btn active" : "tab-btn"}
          onClick={() => setActiveTab("jela")}
        >
          Jela
        </button>
        <button
          className={activeTab === "porudzbine" ? "tab-btn active" : "tab-btn"}
          onClick={() => setActiveTab("porudzbine")}
        >
          Porudžbine
        </button>
      </div>

      {message && <div className="admin-alert success">{message}</div>}
      {error && <div className="admin-alert error">{error}</div>}

      {activeTab === "kategorije" && (
        <div className="admin-section">
          <div className="admin-form-card">
            <h2>Dodaj kategoriju</h2>
            <form onSubmit={handleAddKategorija} className="admin-form">
              <div className="admin-form-group">
                <label>Naziv</label>
                <input
                  type="text"
                  name="naziv"
                  value={kategorijaForm.naziv}
                  onChange={handleKategorijaChange}
                  placeholder="Unesite naziv kategorije"
                />
              </div>

              <div className="admin-form-group">
                <label>Opis</label>
                <textarea
                  name="opis"
                  value={kategorijaForm.opis}
                  onChange={handleKategorijaChange}
                  placeholder="Unesite opis"
                />
              </div>

              <button type="submit" className="admin-primary-btn">
                Dodaj kategoriju
              </button>
            </form>
          </div>

          <div className="admin-list-card">
            <h2>Lista kategorija</h2>

            {loadingKategorije ? (
              <p>Učitavanje...</p>
            ) : (
              <div className="admin-table-wrapper">
                <table className="admin-table">
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>Naziv</th>
                      <th>Opis</th>
                      <th>Akcije</th>
                    </tr>
                  </thead>
                  <tbody>
                    {kategorije.map((kategorija) => (
                      <tr key={kategorija.id}>
                        <td>{kategorija.id}</td>
                        <td>{kategorija.naziv}</td>
                        <td>{kategorija.opis}</td>
                        <td>
                          <button
                            className="admin-danger-btn"
                            onClick={() => handleDeleteKategorija(kategorija.id)}
                          >
                            Obriši
                          </button>
                        </td>
                      </tr>
                    ))}
                    {kategorije.length === 0 && (
                      <tr>
                        <td colSpan="4">Nema kategorija.</td>
                      </tr>
                    )}
                  </tbody>
                </table>
              </div>
            )}
          </div>
        </div>
      )}

      {activeTab === "jela" && (
        <div className="admin-section">
          <div className="admin-form-card">
            <h2>Dodaj jelo</h2>
            <form onSubmit={handleAddJelo} className="admin-form">
              <div className="admin-form-group">
                <label>Naziv</label>
                <input
                  type="text"
                  name="naziv"
                  value={jeloForm.naziv}
                  onChange={handleJeloChange}
                  placeholder="Unesite naziv jela"
                />
              </div>

              <div className="admin-form-group">
                <label>Opis</label>
                <textarea
                  name="opis"
                  value={jeloForm.opis}
                  onChange={handleJeloChange}
                  placeholder="Unesite opis jela"
                />
              </div>

              <div className="admin-two-cols">
                <div className="admin-form-group">
                  <label>Cena</label>
                  <input
                    type="number"
                    step="0.01"
                    name="cena"
                    value={jeloForm.cena}
                    onChange={handleJeloChange}
                    placeholder="Unesite cenu"
                  />
                </div>

                <div className="admin-form-group">
                  <label>Jedinica mere</label>
                  <input
                    type="text"
                    name="jedinicaMere"
                    value={jeloForm.jedinicaMere}
                    onChange={handleJeloChange}
                    placeholder="kom, porcija..."
                  />
                </div>
              </div>

              <div className="admin-form-group">
                <label>Slika</label>
                <input
                  type="text"
                  name="slika"
                  value={jeloForm.slika}
                  onChange={handleJeloChange}
                  placeholder="URL ili naziv slike"
                />
              </div>

              <div className="admin-form-group">
                <label>Kategorija</label>
                <select
                  name="kategorijaJelaId"
                  value={jeloForm.kategorijaJelaId}
                  onChange={handleJeloChange}
                >
                  <option value="">Izaberi kategoriju</option>
                  {kategorije.map((kategorija) => (
                    <option key={kategorija.id} value={kategorija.id}>
                      {kategorija.naziv}
                    </option>
                  ))}
                </select>
              </div>

              <div className="admin-checkbox-row">
                <input
                  type="checkbox"
                  name="dostupan"
                  checked={jeloForm.dostupan}
                  onChange={handleJeloChange}
                />
                <label>Jelo je dostupno</label>
              </div>

              <button type="submit" className="admin-primary-btn">
                Dodaj jelo
              </button>
            </form>
          </div>

          <div className="admin-list-card">
            <h2>Lista jela</h2>

            {loadingJela ? (
              <p>Učitavanje...</p>
            ) : (
              <div className="admin-table-wrapper">
                <table className="admin-table">
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>Naziv</th>
                      <th>Cena</th>
                      <th>Jedinica</th>
                      <th>Dostupan</th>
                      <th>Akcije</th>
                    </tr>
                  </thead>
                  <tbody>
                    {jela.map((jelo) => (
                      <tr key={jelo.id}>
                        <td>{jelo.id}</td>
                        <td>{jelo.naziv}</td>
                        <td>{jelo.cena}</td>
                        <td>{jelo.jedinicaMere}</td>
                        <td>{jelo.dostupan ? "Da" : "Ne"}</td>
                        <td>
                          <button
                            className="admin-danger-btn"
                            onClick={() => handleDeleteJelo(jelo.id)}
                          >
                            Obriši
                          </button>
                        </td>
                      </tr>
                    ))}
                    {jela.length === 0 && (
                      <tr>
                        <td colSpan="6">Nema jela.</td>
                      </tr>
                    )}
                  </tbody>
                </table>
              </div>
            )}
          </div>
        </div>
      )}

      {activeTab === "porudzbine" && (
        <div className="admin-section single-column">
          <div className="admin-list-card">
            <h2>Pregled porudžbina</h2>

            {loadingPorudzbine ? (
              <p>Učitavanje...</p>
            ) : (
              <div className="admin-table-wrapper">
                <table className="admin-table">
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>Adresa</th>
                      <th>Kontakt</th>
                      <th>Napomena</th>
                      <th>Ukupan iznos</th>
                      <th>Status</th>
                      <th>Korisnik ID</th>
                    </tr>
                  </thead>
                  <tbody>
                    {porudzbine.map((porudzbina) => (
                      <tr key={porudzbina.id}>
                        <td>{porudzbina.id}</td>
                        <td>{porudzbina.adresa}</td>
                        <td>{porudzbina.kontakt}</td>
                        <td>{porudzbina.napomena}</td>
                        <td>{porudzbina.ukupanIznos}</td>
                        <td>{porudzbina.status}</td>
                        <td>{porudzbina.korisnikId}</td>
                      </tr>
                    ))}
                    {porudzbine.length === 0 && (
                      <tr>
                        <td colSpan="7">Nema porudžbina.</td>
                      </tr>
                    )}
                  </tbody>
                </table>
              </div>
            )}
          </div>
        </div>
      )}
    </div>
  );
}

export default AdminDashboardPage;