import logo from "./logo.svg";
import "./App.css";
import { useParams } from "react-router-dom";

function Map() {
  let { data } = useParams();
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>webview-client</p>
        <p>어린이/장애인 안심귀가 모니터링 및 SOS 서비스</p>
        <p>{data ? data : "data undefined"}</p>
      </header>
    </div>
  );
}

export default Map;
