import "./App.css";
import Aboutpage from "./page/Aboutpage";
import Homepage from "./page/Homepage";
import { Routes, Route, Navigate } from "react-router-dom";
import Productpage from "./page/Productpage";
import ProductDetailpage from "./page/ProductDetailpage";
import LoginPage from "./page/LoginPage";
import { useState } from "react";
import UserPage from "./page/UserPage";

function App() {
  //false면 로그인 안한거, true면 로그인 한거
  const [authenticate, setAuthenticate] = useState(true);

  const PrivateRoute =()=>{
    return authenticate?<UserPage/>:<Navigate to ="/login"/>
  }
  return (
    <>
      <Routes>
        <Route path="/" element={<Homepage/>}/>
        <Route path="/about" element={<Aboutpage/>}/>
        <Route path="/products" element={<Productpage/>}/>
        <Route path="/detail/:id/:name/:gill" element={<ProductDetailpage/>}/>
        <Route path="/login" element={<LoginPage/>}/>
        <Route path="/user" element={<PrivateRoute/>}/>
      </Routes>
    </>
  );
}

export default App;
