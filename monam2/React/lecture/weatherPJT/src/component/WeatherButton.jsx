import React from "react";
import { Button } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

const WeatherButton = ({cities, setCity}) => {

  return (
    <div>
      
      {cities.map(city=>{
        return <Button key={city} onClick={()=>setCity(city)} variant="warning">{city}</Button>
      })}
    </div>
  );
};

export default WeatherButton;
