import React from 'react'
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { useNavigate } from "react-router-dom";

const Login = ({setAuthenticate}) => {
  const navigate = useNavigate();
  const loginUser=(event)=>{
    event.preventDefault();
    setAuthenticate(true);
    navigate("/");
  }
  return (
    <container className="login-form">
      <Form onSubmit={(event)=>loginUser(event)}>
      <Form.Group className="mb-3" controlId="formBasicEmail">
        <Form.Label>Email address</Form.Label>
        <Form.Control type="email" placeholder="Enter email" />
        <Form.Text className="text-muted">
          We'll never share your email with anyone else.
        </Form.Text>
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicPassword">
        <Form.Label>Password</Form.Label>
        <Form.Control type="password" placeholder="Password" />
      </Form.Group>
      <Form.Group className="mb-3" controlId="formBasicCheckbox">
        <Form.Check type="checkbox" label="Check me out" />
      </Form.Group>
      <Button variant="danger" type="submit">
        로그인
      </Button>
    </Form>
    </container>
  )
}

export default Login