import React from "react";

const Button = ({click}) => {
  return (
    <div>
      <input onClick={click} type="button" value="클릭" />
    </div>
  );
};

export default Button;