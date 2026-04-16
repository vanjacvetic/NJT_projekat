import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import api from "../api";
import "./AuthPage.css";

function RegisterPage() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    ime: "",
    prezime: "",
    email: "",
    lozinka: "",
    brojTelefona: "",
    rola: "KORISNIK",
  });

  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const [loading, setLoading] = useState(false);

  function handleChange(e) {
    const { name, value } = e.target;

    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  }

  async function handleSubmit(e) {
    e.preventDefault();
    setError("");
    setSuccess("");
    setLoading(true);

    try {
      const response = await api.post("/api/auth/register", formData);

      const data = response.data;

      localStorage.setItem("token", data.token);
      localStorage.setItem("userId", data.id);
      localStorage.setItem("ime", data.ime);
      localStorage.setItem("prezime", data.prezime);
      localStorage.setItem("email", data.email);
      localStorage.setItem("rola", data.rola);

      setSuccess("Uspešna registracija.");
      navigate("/");
    } catch (err) {
      if (err.response?.data?.message) {
        setError(err.response.data.message);
      } else if (err.response?.data) {
        setError(typeof err.response.data === "string" ? err.response.data : "Greška pri registraciji.");
      } else {
        setError("Server nije dostupan.");
      }
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="auth-page">
      <div className="auth-card">
        <div className="auth-left">
          <span className="auth-badge">Napravi nalog</span>
          <h1>Registracija korisnika</h1>
          <p>
            Kreiraj svoj nalog, pregledaj ponudu jela i poruči brzo i jednostavno.
          </p>
        </div>

        <div className="auth-right">
          <form className="auth-form" onSubmit={handleSubmit}>
            <h2>Registracija</h2>

            {error && <div className="auth-message error">{error}</div>}
            {success && <div className="auth-message success">{success}</div>}

            <div className="form-row">
              <div className="form-group">
                <label>Ime</label>
                <input
                  type="text"
                  name="ime"
                  placeholder="Unesite ime"
                  value={formData.ime}
                  onChange={handleChange}
                />
              </div>

              <div className="form-group">
                <label>Prezime</label>
                <input
                  type="text"
                  name="prezime"
                  placeholder="Unesite prezime"
                  value={formData.prezime}
                  onChange={handleChange}
                />
              </div>
            </div>

            <div className="form-group">
              <label>Email</label>
              <input
                type="email"
                name="email"
                placeholder="Unesite email"
                value={formData.email}
                onChange={handleChange}
              />
            </div>

            <div className="form-group">
              <label>Lozinka</label>
              <input
                type="password"
                name="lozinka"
                placeholder="Unesite lozinku"
                value={formData.lozinka}
                onChange={handleChange}
              />
            </div>

            <div className="form-group">
              <label>Broj telefona</label>
              <input
                type="text"
                name="brojTelefona"
                placeholder="Unesite broj telefona"
                value={formData.brojTelefona}
                onChange={handleChange}
              />
            </div>

            <div className="form-group">
              <label>Rola</label>
              <select name="rola" value={formData.rola} onChange={handleChange}>
                <option value="KORISNIK">KORISNIK</option>
                <option value="ADMIN">ADMIN</option>
              </select>
            </div>

            <button type="submit" className="auth-btn" disabled={loading}>
              {loading ? "Registracija u toku..." : "Registruj se"}
            </button>

            <p className="auth-switch">
              Već imaš nalog? <Link to="/login">Prijavi se</Link>
            </p>
          </form>
        </div>
      </div>
    </div>
  );
}

export default RegisterPage;