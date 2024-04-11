import { useEffect, useState } from "react";
import "./App.css";
import Box from "./component/Box";
import Btn from "./component/Btn";

function App() {
  let num = 0;
  const [num2, setNum2] = useState(0);

  let clickBtn = ()=>{
    num += 1;
    setNum2(num2+1);
    console.log("num: ", num, "num2: ", num2);
  }

  useEffect(()=>{
    console.log("useEffect 실행됨")
  },[])

  return (
    <div>
      <div>{num}</div>
      <div>{num2}</div>
      <button onClick={clickBtn}>클릭</button>
    </div>
  );
}

export default App;
