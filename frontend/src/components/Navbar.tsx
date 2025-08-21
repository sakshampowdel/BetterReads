import { useEffect, useState } from "react";
import ThemeToggle from "./ThemeToggle";
import { Menu, X } from "lucide-react";
import { Link, NavLink } from "react-router-dom";

const Navbar = () => {
  const [theme, setTheme] = useState<"light" | "dark">(
    () => (localStorage.getItem("theme") as "light" | "dark") || "light"
  );
  const [menu, setMenu] = useState(false);

  useEffect(() => {
    document.documentElement.setAttribute("data-theme", theme);
    localStorage.setItem("theme", theme);
  }, [theme]);

  return (
    <nav className="bg-background sticky top-0 z-50 text-xl font-semibold">
      <div className="flex min-h-1.5 bg-accent"></div>

      {/* Desktop */}
      <div className="max-md:hidden flex justify-between px-6 py-6 items-center">
        <h1 className="hover:cursor-pointer text-foreground">
          <Link to="/">BetterReads</Link>
        </h1>
        <ul className="flex space-x-8 items-center">
          <li className="hover:cursor-pointer">
            <NavLink
              to="/browse"
              className={({ isActive }) =>
                `hover:cursor-pointer ${
                  isActive ? "text-accent underline" : ""
                }`
              }
            >
              Browse
            </NavLink>
          </li>
          <li className="hover:cursor-pointer">
            <NavLink
              to="/mybooks"
              className={({ isActive }) =>
                `hover:cursor-pointer ${
                  isActive ? "text-accent underline" : ""
                }`
              }
            >
              My Books
            </NavLink>
          </li>
          <li className="hover:cursor-pointer bg-accent text-accent-foreground p-2 px-4 w-fit rounded-xl">
            <Link to="/signup">Sign Up</Link>
          </li>
          <li>
            <ThemeToggle theme={theme} setTheme={setTheme}></ThemeToggle>
          </li>
        </ul>
      </div>

      {/* Mobile */}
      <div className="md:hidden flex flex-col px-4">
        <div className="flex justify-between px-6 py-6 items-center">
          <h1 className="hover:cursor-pointer text-foreground">
            <Link to="/" onClick={() => setMenu(false)}>
              BetterReads
            </Link>
          </h1>
          {menu ? (
            <X
              className="hover:cursor-pointer"
              onClick={() => setMenu(false)}
            />
          ) : (
            <Menu
              className="hover:cursor-pointer"
              onClick={() => setMenu(true)}
            />
          )}
        </div>
        <div className="overflow-hidden">
          {menu && (
            <ul className="flex flex-col justify-evenly gap-6 items-center min-h-screen text-2xl">
              <li className="hover:cursor-pointer">
                <NavLink
                  to="/browse"
                  onClick={() => setMenu(false)}
                  className={({ isActive }) =>
                    `hover:cursor-pointer ${
                      isActive ? "text-accent underline" : ""
                    }`
                  }
                >
                  Browse
                </NavLink>
              </li>
              <li className="hover:cursor-pointer">
                <NavLink
                  to="/mybooks"
                  onClick={() => setMenu(false)}
                  className={({ isActive }) =>
                    `hover:cursor-pointer ${
                      isActive ? "text-accent underline" : ""
                    }`
                  }
                >
                  My Books
                </NavLink>
              </li>
              <li className="hover:cursor-pointer bg-accent text-accent-foreground p-2 px-4 w-fit rounded-xl">
                <Link to="/signup" onClick={() => setMenu(false)}>
                  Sign Up
                </Link>
              </li>
              <li>
                <ThemeToggle theme={theme} setTheme={setTheme}></ThemeToggle>
              </li>
            </ul>
          )}
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
