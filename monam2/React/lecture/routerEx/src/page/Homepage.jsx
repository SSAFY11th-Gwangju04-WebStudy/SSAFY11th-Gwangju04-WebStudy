import React from "react";// eslint-disable-line no-unused-vars
import { Link, useNavigate } from "react-router-dom";

const Homepage = () => {
    const navigate = useNavigate();
    const goProductPage=()=>{
        navigate('/products?q=pants')
    }

  return (
    <div>
      <h1>홈페이지</h1>
      <Link to={"about"}>어바웃 페이지로 이동</Link>
      <button onClick={goProductPage}>상품 페이지로 이동</button>
    </div>
  );
};

export default Homepage;
