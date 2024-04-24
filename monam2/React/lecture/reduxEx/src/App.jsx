import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { useRef } from "react";

const App = () => {
  const count = useSelector((state) => state.count);
  const id = useSelector((state) => state.id);
  const dispatch = useDispatch();
  const inputId = useRef();

  const increase = (num) => {
    dispatch({
      type: "INCREASE",
      payload: { num: num },
    });
  };

  const changeId = () => {
    dispatch({
      type: "GETID",
      payload: { newId: inputId.current.value },
    });
  };

  return (
    <div>
      <h1>{count}</h1>
      <button onClick={() => increase(1)}>증가</button>
      <button onClick={() => increase(5)}>+5</button>
      <button onClick={() => increase(-5)}>-5</button>
      <h2>id : {id}</h2>
      <button>id 받아오기</button>
      <input type="text" ref={inputId} />
      <button onClick={changeId}>id 변경하기</button>
    </div>
  );
};

export default App;
