import React, { Suspense, lazy } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

const Home = lazy(() => import("./Home.js"));
// const About = lazy(() => import("./routes/About"));

const App = () => (
  <Router>
    {/* suspense fallback is a place to put a loading message */}
    <Suspense fallback={<div></div>}>
      <Routes>
        <Route path="/" element={<Home />} />
        {/* <Route path="/users" element={<Users />} /> */}
      </Routes>
    </Suspense>
  </Router>
);
export default App;
