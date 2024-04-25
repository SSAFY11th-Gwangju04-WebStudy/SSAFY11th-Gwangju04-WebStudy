import React from "react";
import { Row, Col } from "react-bootstrap";

const ContactItem = ({ contact }) => {
  return (
    <Row>
      <Col lg={2}>
        <img
          width={60}
          src="https://www.alleganyco.gov/wp-content/uploads/unknown-person-icon-Image-from.png"
          alt=""
        />
      </Col>
      <Col lg={10}>
        <div>{contact.name}</div>
        <div>{contact.phoneNumber}</div>
      </Col>
    </Row>
  );
};

export default ContactItem;
