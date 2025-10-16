import { createContext } from "react";
import type { AuthState } from "../types/Auth";
import type { JwtResponse } from "../types/Auth";

export type AuthContextType = {
  authState: AuthState;
  login: (data: JwtResponse) => void;
  logout: () => void;
};

export const AuthContext = createContext<AuthContextType | undefined>(
  undefined
);
