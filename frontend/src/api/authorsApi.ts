import type { Author } from "../types/Author";
import type { Paginated } from "../types/Paginated";

export async function fetchAuthors(
  page: number,
  size: number
): Promise<Paginated<Author>> {
  const res = await fetch(`/api/authors?page=${page}&size=${size}`);
  if (!res.ok) throw new Error("Failed to fetch authors");
  const data = await res.json();
  return data;
}
