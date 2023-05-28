import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Map from "./Map";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/map" element={<Map />} />
      </Routes>
    </Router>
  );
}

export default App;
