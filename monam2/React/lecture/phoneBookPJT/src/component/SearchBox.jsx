import React from "react";
import { Form, Row, Col } from "react-bootstrap";

const SearchBox = () => {
  return (
    <Row>
      <Col lg={10}>
        <Form.Control type="text" placeholder="Enter name" />
      </Col>
      <Col lg={2}>
        <button>Search</button>
      </Col>
    </Row>
  );
};

export default SearchBox;
