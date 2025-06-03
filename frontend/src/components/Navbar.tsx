import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Navbar() {
  const [isOpen, setIsOpen] = useState(false);
  const navigate = useNavigate();

  const handleBrowse = () => {
    navigate("/browse");
  };

  const handleHome = () => {
    navigate("/");
  };

  return (
    <nav className="flex max-md:flex-col bg-white justify-between py-10 px-8 max-md:text-center">
      <button className="p-3 font-bold" onClick={handleHome}>
        BetterReads
      </button>
      <button className="md:hidden p-3" onClick={() => setIsOpen(!isOpen)}>
        Menu
      </button>
      {isOpen && (
        <div className="md:hidden flex flex-col items-center gap-4">
          <div className="p-3">Trending</div>
          <button className="p-3" onClick={handleBrowse}>
            Browse
          </button>
          <button className="bg-black text-white py-3 px-5 rounded-md">
            Sign Up
          </button>
        </div>
      )}
      <div className="max-md:hidden flex justify-between gap-4">
        <div className="p-3">Trending</div>
        <button className="p-3" onClick={handleBrowse}>
          Browse
        </button>
        <button className="bg-black text-white py-3 px-5 rounded-md">
          Sign Up
        </button>
      </div>
    </nav>
  );
}

export default Navbar;
