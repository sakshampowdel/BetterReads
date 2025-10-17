import type { Profile } from "../types/Profile";

export async function fetchProfileById(
  id: number,
  token?: string
): Promise<Profile> {
  const res = await fetch(`/api/profiles/${id}`, {
    headers: token ? { Authorization: `Bearer ${token}` } : {},
  });

  if (res.status === 404) throw new Error("Profile not found");
  if (!res.ok) throw new Error("Failed to fetch profile");

  return res.json();
}
