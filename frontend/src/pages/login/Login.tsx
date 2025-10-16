import { useEffect, useState } from "react";
import { loginUser } from "../../api";
import type { LoginUser } from "../../types/User";
import { useAuth } from "../../context/useAuth";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const { authState, login } = useAuth();
  const navigate = useNavigate();

  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  const isEmailValid = emailRegex.test(email);
  const isPasswordValid = password.trim().length > 0;
  const isFormValid = isEmailValid && isPasswordValid;

  useEffect(() => {
    if (authState.user) {
      navigate("/mybooks");
    }
  }, [authState.user, navigate]);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!isFormValid) return;

    const user: LoginUser = { email, password };

    try {
      const data = await loginUser(user);
      login(data);
      navigate("/mybooks", { replace: true });
    } catch (err) {
      console.error("Failed to login: " + err);
    }
  };

  return (
    <main className="flex justify-center items-center min-h-[calc(100vh-7rem)] bg-background text-foreground">
      <div className="p-20 bg-secondary border-2 border-accent rounded-xl space-y-6 shadow-md shadow-accent/20">
        <h1 className="text-2xl font-bold text-foreground">Login</h1>

        <form className="flex flex-col space-y-4" onSubmit={handleSubmit}>
          <div className="flex flex-col">
            <label className="font-medium">Email:</label>
            <input
              className={`text-m py-1 px-2 border-2 rounded-sm bg-background focus:outline-none transition-colors
                ${
                  email === ""
                    ? "border-muted"
                    : isEmailValid
                    ? "border-green-500"
                    : "border-red-500"
                }`}
              type="email"
              placeholder="example@email.com"
              required
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>

          <div className="flex flex-col">
            <label className="font-medium">Password:</label>
            <input
              className={`text-m py-1 px-2 border-2 rounded-sm bg-background focus:outline-none transition-colors
                ${
                  password === ""
                    ? "border-muted"
                    : isPasswordValid
                    ? "border-green-500"
                    : "border-red-500"
                }`}
              type="password"
              placeholder="SuperStr0ngP@22word!"
              required
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>

          <input
            type="submit"
            value="Sign In"
            disabled={!isFormValid}
            className={`mt-6 py-2 rounded-md font-semibold transition-opacity
              ${
                isFormValid
                  ? "bg-accent text-accent-foreground cursor-pointer hover:opacity-90"
                  : "bg-muted text-foreground cursor-not-allowed opacity-60"
              }`}
          />
        </form>
      </div>
    </main>
  );
};

export default Login;
