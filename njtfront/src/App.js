import logo from './logo.svg';
import './App.css';
import HomePage from './stranice/HomePage';
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import LoginPage from './stranice/LoginPage';
import RegisterPage from './stranice/RegisterPage';
import JelovnikPage from './stranice/JelovnikPage';
import AdminDashboardPage from './stranice/AdminDashboardPage';
import KorpaPage from './stranice/KorpaPage';
import Navbar from './komponente/Navbar';
function App() {
  return (
    <BrowserRouter>
       
      <Navbar />
      <Routes>
        <Route path="/" element={<HomePage />} /> 
         <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/jelovnik" element={<JelovnikPage />} />
        <Route path="/admin" element={<AdminDashboardPage />} />
             <Route path="/korpa" element={<KorpaPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
