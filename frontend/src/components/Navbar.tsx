import { useEffect, useState } from "react";
import ThemeToggle from "./ThemeToggle";
import { Menu, X } from "lucide-react";
import { Link, NavLink } from "react-router-dom";
import { useAuth } from "../context/useAuth";

const Navbar = () => {
  const [theme, setTheme] = useState<"light" | "dark">(
    () => (localStorage.getItem("theme") as "light" | "dark") || "light"
  );
  const [menu, setMenu] = useState(false);

  const { authState, logout } = useAuth();

  useEffect(() => {
    document.documentElement.setAttribute("data-theme", theme);
    localStorage.setItem("theme", theme);
  }, [theme]);

  useEffect(() => {
    document.body.style.overflow = menu ? "hidden" : "auto";
  }, [menu]);

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
              to="/profile"
              className={({ isActive }) =>
                `hover:cursor-pointer ${
                  isActive ? "text-accent underline" : ""
                }`
              }
            >
              My Books
            </NavLink>
          </li>
          {authState.user ? (
            <li className="flex items-center space-x-8">
              <span>
                Welcome,{" "}
                <span className="font-semibold text-accent">
                  {authState.user.displayName}
                </span>
              </span>
              <button
                className="bg-accent text-accent-foreground font-semibold px-4 py-2 rounded-md hover:opacity-90 transition-opacity"
                onClick={logout}
              >
                Logout
              </button>
            </li>
          ) : (
            <li>
              <Link
                to="/login"
                className="bg-accent text-accent-foreground font-semibold px-4 py-2 rounded-md hover:opacity-90 transition-opacity"
              >
                Login
              </Link>
            </li>
          )}
          <li>
            <ThemeToggle theme={theme} setTheme={setTheme}></ThemeToggle>
          </li>
        </ul>
      </div>

      {/* Mobile */}
      <div className="md:hidden relative z-50">
        {/* Top bar */}
        <div className="flex justify-between px-4 py-5 items-center relative z-50">
          <h1 className="text-foreground text-2xl font-bold">
            <Link to="/" onClick={() => setMenu(false)}>
              BetterReads
            </Link>
          </h1>

          {menu ? (
            <X
              className="hover:cursor-pointer transition-transform duration-200 active:scale-90"
              onClick={() => setMenu(false)}
            />
          ) : (
            <Menu
              className="hover:cursor-pointer transition-transform duration-200 active:scale-90"
              onClick={() => setMenu(true)}
            />
          )}
        </div>

        {/* Mobile Menu Overlay */}
        <div
          className={`fixed inset-0 bg-background/95 backdrop-blur-sm flex flex-col items-center justify-center gap-8 text-xl transition-all duration-300 ease-in-out ${
            menu ? "opacity-100 visible" : "opacity-0 invisible"
          }`}
          style={{ zIndex: 40 }} // keeps it below the top bar but above the page
        >
          <ul className="flex flex-col items-center gap-6">
            <li>
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

            <li>
              <NavLink
                to="/profile"
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

            {authState.user ? (
              <li>
                <button
                  className="bg-accent text-accent-foreground font-semibold px-4 py-2 rounded-md hover:opacity-90 transition-opacity"
                  onClick={() => {
                    logout();
                    setMenu(false);
                  }}
                >
                  Logout
                </button>
              </li>
            ) : (
              <li>
                <Link
                  to="/login"
                  className="bg-accent text-accent-foreground font-semibold px-4 py-2 rounded-md hover:opacity-90 transition-opacity"
                  onClick={() => setMenu(false)}
                >
                  Login
                </Link>
              </li>
            )}

            <li>
              <ThemeToggle theme={theme} setTheme={setTheme} />
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
