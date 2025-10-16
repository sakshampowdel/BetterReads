import { useState } from "react";
import { registerUser } from "../../api";
import type { UserRequest } from "../../types/User";
import { useAuth } from "../../context/useAuth";
import { useNavigate } from "react-router-dom";

const Register = () => {
  const [displayName, setDisplayName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const { login } = useAuth();
  const navigate = useNavigate();

  const isDisplayNameValid =
    displayName.length >= 3 && displayName.length <= 15;
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  const isEmailValid = emailRegex.test(email);
  const isPasswordValid = password.length >= 8;
  const isFormValid = isDisplayNameValid && isEmailValid && isPasswordValid;

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!isFormValid) {
      return;
    }

    const user: UserRequest = {
      displayName: displayName,
      email: email,
      password: password,
    };
    try {
      const data = await registerUser(user);
      login(data);
      navigate("/mybooks", { replace: true });
    } catch (err) {
      console.error("Failed to register user" + err);
    }
  };

  return (
    <main className="flex justify-center items-center min-h-[calc(100vh-7rem)] bg-background text-foreground">
      <div className="p-20 bg-secondary border-2 border-accent rounded-xl space-y-6 shadow-md shadow-accent/20">
        <h1 className="text-2xl font-bold text-foreground">Register</h1>

        <form className="flex flex-col space-y-4" onSubmit={handleSubmit}>
          <div className="flex flex-col">
            <label className="font-medium">Display Name:</label>
            <input
              className={`text-m py-1 px-2 border-2 rounded-sm bg-background focus:outline-none transition-colors
                ${
                  !displayName
                    ? "border-muted"
                    : isDisplayNameValid
                    ? "border-green-500"
                    : "border-red-500"
                }`}
              type="text"
              placeholder="example"
              required
              value={displayName}
              onChange={(e) => setDisplayName(e.target.value)}
            />
          </div>

          <div className="flex flex-col">
            <label className="font-medium">Email:</label>
            <input
              className={`text-m py-1 px-2 border-2 rounded-sm bg-background focus:outline-none transition-colors
                ${
                  !email
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
                  !password
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
            value="Create Account"
            disabled={!isFormValid}
            className={`mt-6 py-2 rounded-md font-semibold cursor-pointer transition-opacity
              ${
                isFormValid
                  ? "bg-accent text-accent-foreground hover:opacity-90"
                  : "bg-muted text-foreground cursor-not-allowed opacity-60"
              }`}
          />
        </form>
      </div>
    </main>
  );
};

export default Register;
