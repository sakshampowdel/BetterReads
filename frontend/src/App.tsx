import { BrowserRouter, Route, Routes } from "react-router-dom";
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import Landing from "./pages/landing/Landing";

function App() {
  return (
    <div className="bg-background text-foreground">
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path="/" element={<Landing />} />
        </Routes>
        <Footer />
      </BrowserRouter>
    </div>
  );
}

export default App;
