import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Signup from './pages/Signup';
import Home from './pages/Home';
import Login from './pages/Login';
import About from './pages/About';
import Services from './pages/Services';

function App() {
  return (
   <BrowserRouter>
   <Routes>
    <Route  path='/' element={<Home/>}></Route>
    <Route  path='/about' element={<About/>}></Route>
    <Route  path='/login' element={<Login/>}></Route>
    <Route  path='/signup' element={<Signup/>}></Route>
    <Route path='/services' element={<Services/>}></Route>

   </Routes>
   </BrowserRouter>
  );
}

export default App;
