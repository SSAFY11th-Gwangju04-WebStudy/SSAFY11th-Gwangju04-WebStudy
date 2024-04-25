import React, { useState } from "react";
import { Container, Form, Button } from "react-bootstrap";
import { useDispatch } from "react-redux";

const ContactForm = () => {
  const [name, setName] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const dispatch = useDispatch();

  const onSubmitHandler = (event) => {
    event.preventDefault();
    dispatch({
      type: "ADD_CONTACT",
      payload: {
        name,
        phoneNumber,
      },
    });
  };

  return (
    <Container>
      <Form onSubmit={onSubmitHandler}>
        <Form.Group className="mb-3" controlId="formBasicName">
          <Form.Label>Name</Form.Label>
          <Form.Control
            type="text"
            onChange={(event) => {
              setName(event.target.value);
            }}
            placeholder="Enter Name"
          />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Label>phone number</Form.Label>
          <Form.Control
            type="number"
            onChange={(event) => {
              setPhoneNumber(event.target.value);
            }}
            placeholder="phone number"
          />
        </Form.Group>
        <Button variant="primary" type="submit">
          추가
        </Button>
      </Form>
    </Container>
  );
};

export default ContactForm;
