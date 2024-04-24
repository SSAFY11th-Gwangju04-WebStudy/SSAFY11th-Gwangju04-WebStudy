import React from 'react'
import ProductDetail from "../page/ProductDetail"
import { Navigate } from "react-router-dom"

const PrivateRoute = ({authenticate}) => {
  return (
    <div>{authenticate?<ProductDetail/>:<Navigate to="/login"/>}</div>
  )
}

export default PrivateRoute