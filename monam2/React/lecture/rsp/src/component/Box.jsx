const Box = ({title, item, result}) => {
  let comRes;
  if (title === "computer" && result == "Win") {
    comRes = "Lose";
  } else if (title === "computer" && result == "Lose") {
    comRes = "Win";
  } else {
    comRes = result;
  }

  return (
    <div className={`box ${comRes}`}>
      <h1>{title}</h1>
      <img className="item-img" src={item && item.img} alt="" />
      <h2>{comRes}</h2>
    </div>
  );
};

export default Box;
