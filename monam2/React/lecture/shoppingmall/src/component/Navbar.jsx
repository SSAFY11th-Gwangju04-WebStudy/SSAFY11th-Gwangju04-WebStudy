import React from "react";
import ReactDOM from "react-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSearch, faUser } from "@fortawesome/free-solid-svg-icons";
const Navbar = () => {
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
  return (
    <div>
      <div>
        <div className="login-button">
          <FontAwesomeIcon icon={faUser} />
          <div>로그인</div>
        </div>
      </div>
      <div className="nav-section">
        <div className="logo-img"></div>
      </div>
      <div className="menu-container">
        <ul className="menu-list">
          {menulist.map((menu) => (
            <li>{menu}</li>
          ))}
        </ul>
        <div>
            <FontAwesomeIcon icon={faSearch}></FontAwesomeIcon>
            <input type="text" />
        </div>
      </div>
    </div>
  );
};

export default Navbar;
