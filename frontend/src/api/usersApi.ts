import type { JwtResponse } from "../types/Auth";
import type { LoginUser, UserRequest } from "../types/User";

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

export async function registerUser(user: UserRequest): Promise<JwtResponse> {
  const res = await fetch(`${API_BASE_URL}/api/users/register`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(user),
  });

  if (res.status === 409) throw new Error("Email already exists!");
  if (!res.ok) throw new Error("Failed to register user");

  const data = await res.json();
  return {
    token: data.token,
    user: data.userResponseDto,
  };
}

export async function loginUser(user: LoginUser): Promise<JwtResponse> {
  const res = await fetch(`${API_BASE_URL}/api/users/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(user),
  });

  if (res.status === 404) throw new Error("Email not found!");
  if (res.status === 401) throw new Error("Invalid credentials!");
  if (!res.ok) throw new Error("Failed to login user");

  const data = await res.json();
  return {
    token: data.token,
    user: data.userResponseDto,
  };
}
