import React from "react"; // eslint-disable-line no-unused-vars
import { useParams } from "react-router-dom";

const ProductDetailpage = () => {
  let p = useParams();
  console.log(p);
  return (
    <div>
      <h1>ProductDetail</h1>
      <h2>{`${p.id}번째 상품`}</h2>
    </div>
  );
};

export default ProductDetailpage;
