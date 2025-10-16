import type { User } from "./User";

export type JwtResponse = {
  token: string;
  user: User;
};

export type AuthState = {
  token: string | null;
  user: User | null;
  error?: string | null;
};
