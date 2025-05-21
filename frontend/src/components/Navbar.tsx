import { useState } from "react";

function Navbar() {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <nav className="md:flex bg-white justify-between py-10 px-8 max-md:text-center">
      <div className="p-3 font-bold">BetterReads</div>
      <button className="md:hidden p-3" onClick={() => setIsOpen(!isOpen)}>
        Menu
      </button>
      {isOpen && (
        <div className="md:hidden flex-col justify-between gap-4">
          <div className="p-3">Trending</div>
          <div className="p-3">Browse</div>
          <button className="bg-black text-white py-3 px-5 rounded-md">
            Sign Up
          </button>
        </div>
      )}
      <div className="max-md:hidden flex justify-between gap-4">
        <div className="p-3">Trending</div>
        <div className="p-3">Browse</div>
        <button className="bg-black text-white py-3 px-5 rounded-md">
          Sign Up
        </button>
      </div>
    </nav>
  );
}

export default Navbar;
