import type { Book } from "../types/Book";
import type { Paginated } from "../types/Paginated";

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

export async function fetchBooks(
  page: number,
  size: number,
  title: string
): Promise<Paginated<Book>> {
  const res = await fetch(
    `${API_BASE_URL}/api/books?page=${page}&size=${size}&title=${encodeURIComponent(
      title
    )}`
  );
  if (!res.ok) throw new Error("Failed to fetch books");
  const data = await res.json();
  return data;
}

export async function fetchBookById(id: number): Promise<Book> {
  const res = await fetch(`${API_BASE_URL}/api/books/${id}`);
  if (res.status === 404) throw new Error("Book not found");
  if (!res.ok) throw new Error("Failed to fetch book");
  const data = await res.json();
  return data;
}
