import { useEffect, useState } from "react";
import "./App.css";
import WeatherBox from "./component/WeatherBox";
import "bootstrap/dist/css/bootstrap.min.css";
import WeatherButton from "./component/WeatherButton";

function App() {
  const [weather, setWeather]=useState(null);
  const [city, setCity] = useState("");

  const cities = ['광주', 'paris', 'newYork', 'LA', 'tokyo'];

  const getCurrentLocation = () => {
    navigator.geolocation.getCurrentPosition((position) => {
      let lat = position.coords.latitude;
      let lon = position.coords.longitude;
      getWeatherByCurrentLocation(lat, lon);
    });
  };

  const getWeatherByCurrentLocation = async (lat, lon) => {
    const apikey = "0a68abff2211f8d90cfbfb4c9a4936eb";
    let url = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${apikey}&units=metric`;
    // let response = await fetch(url)
    // let data = await response.json();
    // console.log("Data : ", data);
    await fetch(url)
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        setWeather(data);
      });
  };

  const getWeatherByCity= async ()=>{
    const apikey = "0a68abff2211f8d90cfbfb4c9a4936eb";
    let url = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apikey}&units=metric`;
    await fetch(url)
    .then((res) => {
      return res.json();
    })
    .then((data) => {
      setWeather(data);
    });
  }

  useEffect(() => {
    city==="" ? getCurrentLocation() : getWeatherByCity();
  }, [city]);

  return (
    <>
      <div className="container">
        <WeatherBox weather={weather}/>
        <WeatherButton cities={cities} setCity={setCity}/>
      </div>
    </>
  );
}

export default App;

/*
1. 앱이 실행되자 마자 현재 위치 기반의 날씨가 보인다.
-> useEffect
2. 날씨 정보에는 도시, 섭씨, 화씨의 날씨 상태가 보인다.
3. 5개의 버튼이 있다. (1개는 현재 위치, 4개는 다른 도시)
4. 각 도시 버튼을 클릭할 때마다 도시의 날씨가 나온다.
5. 현재 위치 버튼을 누르면 다시 현재위치 기반의 날씨가 나온다.
6. 데이터를 들고오는 동안 로딩 스피너가 돈다.
*/
