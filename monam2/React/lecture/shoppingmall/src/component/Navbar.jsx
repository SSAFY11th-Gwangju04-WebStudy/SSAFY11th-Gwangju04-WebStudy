import React, { useRef } from "react";
import ReactDOM from "react-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSearch, faUser } from "@fortawesome/free-solid-svg-icons";
import { Link, useNavigate } from "react-router-dom";
const Navbar = ({authenticate, setAuthenticate}) => {
  const removeInputValue = useRef();
  const menulist = [
    "여성",
    "Divided",
    "남성",
    "아동",
    "신생아/유아",
    "H&M Home",
    "sale",
    "지속가능성",
  ];
  const navigate = useNavigate();
  const goToLogin = () => {
    navigate("/login");
  };
  const goToLogout =()=> {
    setAuthenticate(false);
    navigate("/");
  }
  const search = (event) => {
    if (event.key === "Enter") {
      //입력한 검색어를 읽어와서 url을 변경하기
      let keyword = event.target.value;
      navigate(`/?q=${keyword}`);
    }
  };
  const goToProductAll=()=>{
    removeInputValue.current.value = '';
    navigate("/");
  }

  return (
    <div>
      <div>
        <div className="login-button">
          <FontAwesomeIcon icon={faUser} />
          {authenticate?<div onClick={goToLogout}>로그아웃</div>:<div onClick={goToLogin}>로그인</div>}
        </div>
      </div>
      <div className="nav-section">
        <div className="logo-img" onClick={goToProductAll}></div>
      </div>
      <div className="menu-container">
        <ul className="menu-list">
          {menulist.map((menu,idx) => (
            <li key={idx}>{menu}</li>
          ))}
        </ul>
        <div className="navbar-serach-container">
          <FontAwesomeIcon icon={faSearch}></FontAwesomeIcon>
          <input type="text" ref={removeInputValue} onKeyDown={(event) => search(event)} />
        </div>
      </div>
    </div>
  );
};

export default Navbar;
