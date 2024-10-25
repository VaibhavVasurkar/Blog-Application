import logo from "./logo.svg";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Signup from "./pages/Signup";
import Home from "./pages/Home";
import Login from "./pages/Login";
import About from "./pages/About";
import Services from "./pages/Services";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import Userdashboard from "./pages/user-routes/Userdashboard";
import Privateroute from "./components/Privateroute";
import ProfileInfo from "./pages/user-routes/ProfileInfo";

function App() {
  return (
    <BrowserRouter>
      <ToastContainer />
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route path="/about" element={<About />}></Route>
        <Route path="/login" element={<Login />}></Route>
        <Route path="/signup" element={<Signup />}></Route>
        <Route path="/services" element={<Services />}></Route>

        <Route path="/user" element={<Privateroute />}>
          <Route path="dashboard" element={<Userdashboard />} />
          <Route path="profile-info" element={<ProfileInfo />} />

        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
