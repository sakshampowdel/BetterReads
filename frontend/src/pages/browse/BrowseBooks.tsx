import { useEffect, useState } from "react";
import type { Book } from "../../types/Book";
import { fetchBooks } from "../../api";
import BookIcon from "../../components/BookIcon";

const BrowseBooks = ({ title }: { title: string }) => {
  const [books, setBooks] = useState<Book[]>([]);
  const [loading, setLoading] = useState(true);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [debouncedTitle, setDebouncedTitle] = useState(title);
  const PAGE_SIZE = 30;

  // Debounce title query
  useEffect(() => {
    const handler = setTimeout(() => setDebouncedTitle(title), 400);
    return () => clearTimeout(handler);
  }, [title]);

  useEffect(() => {
    setLoading(true);
    fetchBooks(page, PAGE_SIZE, debouncedTitle)
      .then((data) => {
        setBooks(data.data);
        setTotalPages(data.totalPages);
      })
      .catch((err) => console.error(err))
      .finally(() => setLoading(false));
  }, [page, debouncedTitle]);

  useEffect(() => {
    setPage(0);
  }, [debouncedTitle]);

  return (
    <section className="space-y-8">
      {loading ? (
        <div className="flex justify-center py-12 text-muted text-lg animate-pulse">
          Loading books...
        </div>
      ) : books.length === 0 ? (
        <div className="flex justify-center py-12 text-muted text-lg italic">
          No books found for "{debouncedTitle}".
        </div>
      ) : (
        <div className="grid grid-cols-2 sm:grid-cols-4 md:grid-cols-6 lg:grid-cols-8 xl:grid-cols-10 gap-4 md:gap-6">
          {books.map((book) => (
            <BookIcon key={book.id} book={book} />
          ))}
        </div>
      )}
      <div className="flex justify-center items-center space-x-6 pb-8">
        <button
          onClick={() => setPage(page - 1)}
          disabled={page === 0}
          className="px-4 py-2 rounded-lg bg-accent text-accent-foreground font-medium disabled:opacity-50 disabled:cursor-not-allowed hover:opacity-90 transition"
        >
          Prev
        </button>
        <span className="text-xl font-semibold">
          Page {page + 1} / {totalPages || 1}
        </span>
        <button
          onClick={() => setPage(page + 1)}
          disabled={page >= totalPages - 1}
          className="px-4 py-2 rounded-lg bg-accent text-accent-foreground font-medium disabled:opacity-50 disabled:cursor-not-allowed hover:opacity-90 transition"
        >
          Next
        </button>
      </div>
    </section>
  );
};

export default BrowseBooks;
