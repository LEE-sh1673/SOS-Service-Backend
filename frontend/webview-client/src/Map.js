import { useEffect, useRef, useState } from "react";

function Map() {
  const [data, setData] = useState([]);
  useEffect(() => {
    window.addEventListener("message", (e) => setData(JSON.parse(e.data)));
  }, []);

  const mapElement = useRef(null);

  useEffect(() => {
    const { naver } = window;
    if (!mapElement.current || !naver) return;

    const location = new naver.maps.LatLng(data.latitude, data.longitude);
    const mapOptions: naver.maps.MapOptions = {
      center: location,
      zoom: 15,
      zoomControl: true,
      zoomControlOptions: {
        position: naver.maps.Position.TOP_RIGHT,
      },
    };
    const map = new naver.maps.Map(mapElement.current, mapOptions);
    new naver.maps.Marker({
      position: location,
      map,
    });
  }, [data]);

  return (
    <div>
      <p>어린이/장애인 안심귀가 모니터링 및 SOS 서비스</p>
      {data ? <p>{data.latitude}</p> : <p>latitude undefined</p>}
      {data ? <p>{data.longitude}</p> : <p>longitude undefined</p>}
      <div ref={mapElement} style={{ minHeight: "500px" }} />
    </div>
  );
}

export default Map;
