import React from "react";
import { useNavigate } from "react-router-dom";

function Home() {
  const navigate = useNavigate();
  return (
    <div>
      <h1>어린이/장애인 안심귀가 모니터링 및 SOS 서비스</h1>
      <h3>HOME</h3>
      <button onClick={() => navigate("/map")}>GO MAP</button>
    </div>
  );
}

export default Home;
