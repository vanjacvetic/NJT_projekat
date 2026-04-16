import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import api from "../api";
import "./AuthPage.css";

function LoginPage() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    email: "",
    lozinka: "",
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
      const response = await api.post("/api/auth/login", formData);

      const data = response.data;

      localStorage.setItem("token", data.token);
      localStorage.setItem("userId", data.id);
      localStorage.setItem("ime", data.ime);
      localStorage.setItem("prezime", data.prezime);
      localStorage.setItem("email", data.email);
      localStorage.setItem("rola", data.rola);

      setSuccess("Uspešna prijava.");

      if (data.rola === "ADMIN") {
        navigate("/admin");
      } else {
        navigate("/jelovnik");
      }
    } catch (err) {
      if (err.response?.data?.message) {
        setError(err.response.data.message);
      } else if (err.response?.data) {
        setError(typeof err.response.data === "string" ? err.response.data : "Greška pri prijavi.");
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
          <span className="auth-badge">Dobrodošli nazad</span>
          <h1>Prijava korisnika</h1>
          <p>
            Prijavi se na svoj nalog i nastavi sa pregledom jelovnika i poručivanjem hrane.
          </p>
        </div>

        <div className="auth-right">
          <form className="auth-form" onSubmit={handleSubmit}>
            <h2>Login</h2>

            {error && <div className="auth-message error">{error}</div>}
            {success && <div className="auth-message success">{success}</div>}

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

            <button type="submit" className="auth-btn" disabled={loading}>
              {loading ? "Prijava u toku..." : "Prijavi se"}
            </button>

            <p className="auth-switch">
              Nemaš nalog? <Link to="/register">Registruj se</Link>
            </p>
          </form>
        </div>
      </div>
    </div>
  );
}

export default LoginPage;