import React from "react";
import { Row, Col } from "react-bootstrap";

const ContactItem = ({contact}) => {
  return (
    <div>
      <Row>
        <Col>
          <img
            style={{ width: 80 }}
            src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQRKjZLaWpaICB5bb2iBDQadL7-Q-bip8wb83uR3ffD3g&s"
            alt=""
          />
        </Col>
        <Col>
          <p>{contact.name}</p>
          <p>{contact.phoneNumber}</p>
        </Col>
      </Row>
    </div>
  );
};

export default ContactItem;
