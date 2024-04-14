import React from "react";
import { Button } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

const WeatherButton = ({cities, selectedCity, handleCityChange}) => {

  return (
    <div>
      <Button variant={`${selectedCity=="" ? "outline-warning" : "warning"}`} onClick={()=>handleCityChange("current")}>현재위치</Button>
      {cities.map(city=>{
        return <Button key={city} onClick={()=>handleCityChange(city)} variant={`${selectedCity==city ? "outline-warning" : "warning"}`}>{city}</Button>
      })}
    </div>
  );
};

export default WeatherButton;
