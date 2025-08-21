import { Moon, Sun } from "lucide-react";

type ThemeToggleProps = {
  theme: "light" | "dark";
  setTheme: (theme: "light" | "dark") => void;
};

const ThemeToggle = ({ theme, setTheme }: ThemeToggleProps) => {
  return (
    <div>
      <button
        className="hover:cursor-pointer min-h-7"
        onClick={() => setTheme(theme === "light" ? "dark" : "light")}
      >
        {theme === "light" ? (
          <Sun className="w-5 h-5" />
        ) : (
          <Moon className="w-5 h-5" />
        )}
      </button>
    </div>
  );
};

export default ThemeToggle;
