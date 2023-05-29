import { useEffect, useRef } from "react";
import { useParams } from "react-router-dom";

function Map() {
  const { latitude, longitude } = useParams();
  const mapElement = useRef(null);

  useEffect(() => {
    const { naver } = window;
    if (!mapElement.current || !naver) return;
    console.log(latitude)
    console.log(longitude)

    const location = new naver.maps.LatLng(latitude, longitude);
    const mapOptions: naver.maps.MapOptions = {
      center: location,
      zoom: 18,
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
  }, [latitude, longitude]);

  return (
    <div>
      <p>어린이/장애인 안심귀가 모니터링 및 SOS 서비스</p>
      <div ref={mapElement} style={{ minHeight: "500px" }} />
    </div>
  );
}

export default Map;
