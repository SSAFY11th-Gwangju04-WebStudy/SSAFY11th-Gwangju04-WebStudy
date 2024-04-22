import React from 'react'
import { useNavigate } from "react-router-dom"

const ProductCard = ({item}) => {
  const navigate = useNavigate();
  const showDetail=()=>{
    navigate(`product/${item.id}`);
  }

  return (
    <div className="product-card" onClick={showDetail}>
        <img src={item?.img} alt="" />
        <div className="conscious-choice">{item?.choice? "Conscious choice":""}</div>
        <div>{item?.title}</div>
        <div>{item?.price}</div>
        <div>{item?.new ? "New":""}</div>
    </div>
  )
}

export default ProductCard