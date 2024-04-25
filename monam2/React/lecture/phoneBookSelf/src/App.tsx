import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import ContactForm from "./component/ContactForm";
import { Row, Col } from "react-bootstrap";
import ContactList from "./component/ContactList";

function App() {
  return (
    <>
      <h1>PhoneBook</h1>
      <Row>
        <Col sm={6}><ContactForm /></Col>
        <Col sm={6}><ContactList /></Col>
      </Row>
    </>
  );
}

export default App;
