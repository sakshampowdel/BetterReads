import type { Book } from "../types/Book";
import type { Paginated } from "../types/Paginated";

export async function fetchBooks(
  page: number,
  size: number,
  title: string
): Promise<Paginated<Book>> {
  const res = await fetch(
    `/api/books?page=${page}&size=${size}&title=${encodeURIComponent(title)}`
  );
  if (!res.ok) throw new Error("Failed to fetch books");
  return res.json();
}
