import React from 'react'

const ProductCard = ({product}) => {
    console.log(product)
  return (
    <div>
        <img src={`${product[0].img}`} alt="" />
        <div>Conscious choice</div>
        <div>상품명</div>
        <div>가격</div>
        <div>신제품</div>

    </div>
  )
}

export default ProductCard