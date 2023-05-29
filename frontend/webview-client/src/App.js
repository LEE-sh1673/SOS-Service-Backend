import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "./Home";
import Map from "./Map";

const App = () => {
  return (
    <Router basename={process.env.PUBLIC_URL}>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/map/:latitude/:longitude" element={<Map />} />
      </Routes>
    </Router>
  );
};

export default App;
