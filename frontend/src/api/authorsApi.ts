import type { Author } from "../types/Author";
import type { Paginated } from "../types/Paginated";

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

export async function fetchAuthors(
  page: number,
  size: number
): Promise<Paginated<Author>> {
  const res = await fetch(
    `${API_BASE_URL}/api/authors?page=${page}&size=${size}`
  );
  if (!res.ok) throw new Error("Failed to fetch authors");
  const data = await res.json();
  return data;
}
