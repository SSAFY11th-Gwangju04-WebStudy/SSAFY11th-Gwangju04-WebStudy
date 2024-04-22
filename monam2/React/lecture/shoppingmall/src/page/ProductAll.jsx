import React, { useEffect, useState } from "react";
import ProductCard from "../component/ProductCard";
import { Col, Container, Row } from "react-bootstrap";

const ProductAll = () => {
  const [productList, setProductList] = useState([]);

  const getProducts = async () => {
    let url = "http://localhost:3001/products";
    await fetch(url)
      .then((res) => res.json())
      .then((data) => setProductList(data))
  };
  useEffect(() => {
    getProducts();
  }, []);
  return (
    <div>
      <Container>
        <Row>
          {productList.map((item) => (
            <Col lg={3}>
              <ProductCard item={item}/>
            </Col>
          ))}
        </Row>
      </Container>
    </div>
  );
};

export default ProductAll;
