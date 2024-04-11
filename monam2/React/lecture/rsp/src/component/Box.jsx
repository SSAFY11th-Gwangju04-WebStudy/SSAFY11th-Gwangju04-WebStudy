import React from "react";

const Box = (props) => {
  console.log(props.item);
  return (
    <div className="box">
      <h1>{props.title}</h1>
      <img className="item-img" src={require(props.item.img)} alt="" />
      <h2>Win</h2>
    </div>
  );
};

export default Box;
