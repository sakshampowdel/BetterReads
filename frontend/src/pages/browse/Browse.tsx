import { useEffect, useState } from "react";
import type { Book } from "../../types/Book";
import type { Paginated } from "../../types/Paginated";

async function fetchBooks(
  page: number,
  size: number
): Promise<Paginated<Book>> {
  const res = await fetch(`/api/books?page=${page}&size=${size}`);
  if (!res.ok) throw new Error("Failed to fetch books");
  return res.json();
}

const Browse = () => {
  const [books, setBooks] = useState<Book[]>([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);

  useEffect(() => {
    fetchBooks(page, 5).then((data) => {
      setBooks(data.data);
      setTotalPages(data.totalPages);
    });
  }, [page]);

  return (
    <div>
      <h2>Books</h2>
      <ul>
        {books.map((book) => (
          <li key={book.id}>
            <strong>{book.title}</strong> —{" "}
            {book.authors.map((a) => a.name).join(", ")}
            <p>{book.description}</p>
          </li>
        ))}
      </ul>

      <button disabled={page === 0} onClick={() => setPage((p) => p - 1)}>
        Prev
      </button>
      <span>
        {page + 1} / {totalPages}
      </span>
      <button
        disabled={page + 1 >= totalPages}
        onClick={() => setPage((p) => p + 1)}
      >
        Next
      </button>
    </div>
  );
};

export default Browse;
