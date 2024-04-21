
import './App.css'
import { Route, Routes } from 'react-router-dom';
import ProductAll from "./page/ProductAll";
import Login from "./page/Login";
import ProductDetail from "./page/ProductDetail";
import ErrorPage from "./page/ErrorPage";
import Navbar from './component/Navbar';

function App() {

  return (
    <>
      <Navbar/>
      <Routes>
        <Route path="/" element={<ProductAll/>}/>
        <Route path="/login" element={<Login/>}/>
        <Route path="/product/:id" element={<ProductDetail/>}/>
        <Route path="/*" element={<ErrorPage/>}/>

      </Routes>
    </>
  )
}

export default App

/*
1. 전체 상품 페이지, 로그인, 상품 상세 페이지
- 네이게이션 바 만들기
2. 전체 상품페이지
- 전체 상품을 볼 수 있다.
3. 로그인 버튼을 누르면 로그인 페이지가 나온다
- 상품 디테일 페이지를 눌렀으나, 로그인이 안되어있을 경우에는
  로그인 페이지가 먼저 나온다.
4. 로그인 되어있을 경우에는 상품 디테일 페이지를 볼 수 있다.
5. 로그아웃 버튼을 클릭하면 로그아웃이 된다.
- 로그아웃이 되면 상품 디테일 페이지를 볼 수 없다. 다시 로그인 페이지가 보인다.
6. 로그인하면 로그아웃이 보이고, 로그아웃을 하면 로그인이 보인다.
7. 상품을 검색할 수 있다.
*/