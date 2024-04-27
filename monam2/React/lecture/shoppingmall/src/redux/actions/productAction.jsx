import { productActions } from "../reducers/productReducer";

const getProducts = (searchQuery) => {
  return async (dispatch, getState) => {
    let url = `http://localhost:3001/products?q=${searchQuery}`;
    await fetch(url)
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        // dispatch({
        //     type: "GET_PRODUCT_SUCCESS",
        //     payload : {data}
        // })
        dispatch(productActions.getAllProduct({data}))
      });
  };
};

export const productAction = { getProducts };
