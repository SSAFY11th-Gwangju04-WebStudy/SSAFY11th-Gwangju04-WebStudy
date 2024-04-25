import React from "react";
import SearchBox from "./SearchBox";
import { Container, Col, Row } from "react-bootstrap";
import ContactItem from "./ContactItem";
import { useSelector } from "react-redux";

const ContactList = () => {
  const contactList = useSelector((state) => state.contactList);
  console.log(contactList);
  return (
    <Container>
      <Row>
        <SearchBox />
        {contactList.map((contact) => (
          <ContactItem contact={contact} />
        ))}
      </Row>
    </Container>
  );
};

export default ContactList;
