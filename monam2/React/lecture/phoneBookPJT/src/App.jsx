import React from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { Container, Row, Col } from "react-bootstrap";
import ContactForm from "./component/ContactForm";
import ContactList from "./component/ContactList";

const App = () => {
  return (
    <div className="App">
      <h1 className="title">Contatct</h1>
      <Container>
        <Row>
          <Col>
            <ContactForm />
          </Col>
          <Col>
            <ContactList />
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default App;

/*
redux 활용

1. 왼쪽에는 연락처 등록하는 폼,
  오른쪽에는 연락처 리스트와 search 창이 있다.
2. 리스트에 유저 이름과 전화번호를 추가할 수 있다.
3. 리스트에 아이템이 몇개있는지 보인다.
4. 사용자가 유저를 이름 검색으로 찾을 수 있다.
*/
