import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Container, Col, Row } from "react-bootstrap";
import Dropdown from "react-bootstrap/Dropdown";
import Button from "react-bootstrap/Button";

const ProductDetail = () => {
  const [product, setProduct] = useState(null);

  let { id } = useParams();
  const getProductDetail = async () => {
    let url = `http://localhost:3001/products/${id}`;
    await fetch(url)
      .then((res) => res.json())
      .then((data) => setProduct(data));
  };

  useEffect(() => {
    getProductDetail();
  }, []);
  return (
    <div>
      <Container>
        <Row>
          <Col>
            <img src={product?.img} alt="" />
          </Col>
          <Col>
            <div>{product?.title}</div>
            <div>{product?.price}</div>
            <div>{product?.choice}</div>
            <Dropdown>
              <Dropdown.Toggle variant="light" id="dropdown-basic">
                사이즈 선택
              </Dropdown.Toggle>

              <Dropdown.Menu>
                {product.size.map(size=><Dropdown.Item href="#/action-1">{size}</Dropdown.Item>)}
              </Dropdown.Menu>
            </Dropdown>
            <div className="d-grid gap-2">
              <Button variant="dark" size="lg">
                추가
              </Button>
            </div>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default ProductDetail;
