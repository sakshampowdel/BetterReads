import { BrowserRouter, Route, Routes } from "react-router-dom";
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";

function App() {
  return (
    <div className="bg-background text-foreground">
      <BrowserRouter>
        <Navbar />
        {/* <Routes>
          <Route path="/" element={1} />
          <Route path="/browse" element={1} />
        </Routes> */}
        <Footer />
      </BrowserRouter>
    </div>
  );
}

export default App;
