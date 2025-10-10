import { useEffect, useState } from "react";
import type { Book } from "../../types/Book";
import { fetchBooks } from "../../api";
import BookIcon from "../../components/BookIcon";

const BrowseBooks = ({ title: title }: { title: string }) => {
  const [books, setBooks] = useState<Book[]>([]);
  const [loading, setLoading] = useState(true);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const PAGE_SIZE = 30;

  useEffect(() => {
    setLoading(true);
    fetchBooks(page, PAGE_SIZE, title)
      .then((data) => {
        setBooks(data.data);
        setTotalPages(data.totalPages);
      })
      .catch((err) => console.error(err))
      .finally(() => setLoading(false));
  }, [page, title]);

  useEffect(() => {
    setPage(0);
  }, [title]);

  return (
    <div>
      <div>
        {loading ? (
          <div>Loading...</div>
        ) : (
          <div className="grid grid-cols-2 sm:grid-cols-4 md:grid-cols-6 lg:grid-cols-8 xl:grid-cols-10 gap-2">
            {books.map((book) => (
              <BookIcon key={book.id} book={book} />
            ))}
          </div>
        )}
      </div>
      <div className="flex justify-center space-x-4 py-4">
        <button
          className="hover:cursor-pointer bg-accent text-xl text-accent-foreground p-1 px-4 w-20 rounded-xl"
          onClick={() => setPage(page - 1)}
          disabled={page === 0}
        >
          Prev
        </button>
        <h1 className="text-4xl">{page + 1}</h1>
        <button
          className="hover:cursor-pointer bg-accent text-xl text-accent-foreground p-1 px-4 w-20 rounded-xl"
          onClick={() => setPage(page + 1)}
          disabled={page === totalPages - 1}
        >
          Next
        </button>
      </div>
    </div>
  );
};

export default BrowseBooks;
