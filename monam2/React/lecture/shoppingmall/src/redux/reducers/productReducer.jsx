import { createSlice } from "@reduxjs/toolkit";

let initialState = {
  productList: [],
  selectedItem: null,
};

// const productReducer = (state = initialState, action) => {
//   const { type, payload } = action;

//   switch (type) {
//     case "GET_PRODUCT_SUCCESS":
//       return { ...state, productList: payload.data };
//     default:
//       return { ...state };
//   }
// };

// export default productReducer;


const productSlice = createSlice({
  name: "product",
  initialState,
  reducers : {
    getAllProduct(state, action) {
      state.productList = action.payload.data
    },
    getSingleProduct(state, action) {
      state.selectedItem = action.payload.data;
    }
  }
})

export const productActions = productSlice.actions
export default productSlice.reducer