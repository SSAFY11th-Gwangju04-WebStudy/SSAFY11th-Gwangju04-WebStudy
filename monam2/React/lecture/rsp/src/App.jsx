import { useState } from "react";
import "./App.css";
import Box from "./component/Box";

const choice = {
  rock: {
    name: "Rock",
    img: "../assets/rock.png",
  },
  scissors: {
    name: "Scissors",
    img: "../assets/scissors.png",
  },
  paper: {
    name: "paper",
    img: "../assets/paper.png",
  },
};

function App() {
  const [userSelect, setUserSelect] = useState(null);

  const play = (userChoice) => {
    setUserSelect(choice[userChoice]);
  };

  return (
    <div className="main">
      <div className="main-box">
        <Box title="you" item={userSelect} />
        <Box title="computer" />
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
