import { BrowserRouter, Route, Routes } from "react-router-dom";
import Footer from "./components/Footer.tsx";
import Navbar from "./components/Navbar.tsx";
import LandingPage from "./pages/landing/LandingPage.tsx";
import BrowsePage from "./pages/browse/BrowsePage.tsx";

function App() {
  return (
    <div className="scroll-smooth">
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path="/" element={<LandingPage />} />
          <Route path="/browse" element={<BrowsePage />} />
        </Routes>
        <Footer />
      </BrowserRouter>
    </div>
  );
}

export default App;
