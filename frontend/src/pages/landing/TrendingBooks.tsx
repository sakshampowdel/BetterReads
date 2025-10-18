import { useEffect, useState } from "react";
import BookIcon from "../../components/BookIcon";
import type { Book } from "../../types/Book";
import { fetchBooks } from "../../api";

const TrendingBooks = () => {
  const [books, setBooks] = useState<Book[]>([]);
  const [loading, setLoading] = useState(true);
  const PAGE_NUMBER = 0;
  const PAGE_SIZE = 10;

  useEffect(() => {
    fetchBooks(PAGE_NUMBER, PAGE_SIZE, "")
      .then((data) => setBooks(data.data))
      .catch((err) => console.error(err))
      .finally(() => setLoading(false));
  }, []);

  return (
    <section id="trending" className="px-6 md:px-16 lg:px-32">
      <h2 className="text-4xl md:text-5xl font-semibold text-foreground mb-10 text-center md:text-left">
        Trending Books
      </h2>

      {loading ? (
        <p className="text-muted text-center text-lg">Loading...</p>
      ) : (
        <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-6 md:gap-8">
          {books.map((book) => (
            <BookIcon key={book.id} book={book} />
          ))}
        </div>
      )}
    </section>
  );
};

export default TrendingBooks;
