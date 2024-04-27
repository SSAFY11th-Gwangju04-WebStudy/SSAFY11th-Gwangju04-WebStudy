import { legacy_createStore, applyMiddleware } from "redux";
import { thunk } from "redux-thunk";
import rootReducer from "./reducers/index";
import { configureStore } from "@reduxjs/toolkit";
import authenticateReducer from './reducers/authenticateReducer';
import productReducer from "./reducers/productReducer";

// let store = configureStore(rootReducer, applyMiddleware(thunk));

// combineReducer
// thunk
// applyMiddleware
// composeWithDevTools
// configureStore에는 이 4개가 다 자동으로 설정되어 있음

const store = configureStore({
    reducer : {
        auth : authenticateReducer,
        product: productReducer
    }
})

export default store;
