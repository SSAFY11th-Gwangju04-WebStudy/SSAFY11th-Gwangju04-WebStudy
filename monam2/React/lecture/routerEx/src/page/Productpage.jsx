import React from 'react'// eslint-disable-line no-unused-vars
import { useSearchParams } from "react-router-dom"

const Productpage = () => {
    let [query, setQuery] = useSearchParams();
    console.log("쿼리 : ", query.get("q"))
  return (
    <div>
        <h1>Show All Products</h1>
    </div>
  )
}

export default Productpage