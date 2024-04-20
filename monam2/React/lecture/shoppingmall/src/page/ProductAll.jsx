import React, { useEffect, useState } from 'react'
import ProductCard from "../component/ProductCard";

const ProductAll = () => {
    const [productList, setProductList] = useState([]);

    const getProducts=async()=>{
        let url = "http://localhost:3001/products";
        await fetch(url)
        .then(res=>res.json())
        .then(data=>setProductList([data]))
    }
    useEffect(()=>{
        getProducts();
    },[])
  return (
    <div>
        {productList.map(product=><ProductCard product={product}/>)}

        
    </div>
  )
}

export default ProductAll