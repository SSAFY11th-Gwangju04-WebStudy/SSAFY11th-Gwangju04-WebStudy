import { useState } from "react";
import "./App.css";
import Box from "./component/Box";

const choice = {
  rock: {
    name: "Rock",
    img: "https://cdn-icons-png.flaticon.com/512/1527/1527445.png",
  },
  scissors: {
    name: "Scissors",
    img: "https://cdn-icons-png.flaticon.com/512/4973/4973989.png",
  },
  paper: {
    name: "Paper",
    img: "https://cdn-icons-png.flaticon.com/512/2541/2541988.png",
  },
};

function App() {
  const [userSelect, setUserSelect] = useState(null);
  const [computerSelect, setComputerSelect] = useState(null);
  const [result, setResult] = useState("");



  const play = (userChoice) => {
    setUserSelect(choice[userChoice]);
    let computerChoice = randomChoice();
    setComputerSelect(choice[computerChoice]);
    setResult(judgement(choice[userChoice], choice[computerChoice]));
  };

  const judgement=(u,c)=>{
    if (u.name === c.name) return "tie";
    if (u.name === "Rock") return c.name === "Scissors" ? "Win" : "Lose";
    if (u.name === "Scissors") return c.name === "Paper" ? "Win" : "Lose";
    if (u.name === "Paper") return c.name === "Rock" ? "Win" : "Lose";
  }

  const randomChoice =()=>{
    let itemArray = Object.keys(choice);
    let randomItem = ~~(Math.random() * itemArray.length);
    return itemArray[randomItem];
  }

  return (
    <div className="main">
      <div className="main-box">
        <Box title="you" item={userSelect} result={result}/>
        <Box title="computer" item={computerSelect} result={result}/>
      </div>
      <div className="btns">
        <button
          onClick={() => {
            play("rock");
          }}
        >
          Rock
        </button>
        <button
          onClick={() => {
            play("scissors");
          }}
        >
          Scissors
        </button>
        <button
          onClick={() => {
            play("paper");
          }}
        >
          Paper
        </button>
      </div>
    </div>
  );
}

export default App;
