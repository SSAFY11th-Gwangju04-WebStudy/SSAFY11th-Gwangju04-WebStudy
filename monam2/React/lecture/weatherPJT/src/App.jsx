import { useEffect } from "react"
import './App.css'

function App() {

  const getCurrentLocation=()=>{
    navigator.geolocation.getCurrentPosition((position)=>{
      let lat = position.coords.latitude;
      let lon = position.coords.longitude;
      console.log(lat, lon);
    });
  }

  useEffect(()=>{
    getCurrentLocation();
  },[]);

  return (
    <>
      
    </>
  )
}

export default App


/*
1. 앱이 실행되자 마자 현재 위치 기반의 날씨가 보인다.
-> useEffect
2. 날씨 정보에는 도시, 섭씨, 화씨의 날씨 상태가 보인다.
3. 5개의 버튼이 있다. (1개는 현재 위치, 4개는 다른 도시)
4. 각 도시 버튼을 클릭할 때마다 도시의 날씨가 나온다.
5. 현재 위치 버튼을 누르면 다시 현재위치 기반의 날씨가 나온다.
6. 데이터를 들고오는 동안 로딩 스피너가 돈다.
*/