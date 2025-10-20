import type { Profile } from "../types/Profile";

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

export async function fetchProfileById(
  id: number,
  token?: string
): Promise<Profile> {
  const res = await fetch(`${API_BASE_URL}/api/profiles/${id}`, {
    headers: token ? { Authorization: `Bearer ${token}` } : {},
  });

  if (res.status === 404) throw new Error("Profile not found");
  if (!res.ok) throw new Error("Failed to fetch profile");

  return res.json();
}
