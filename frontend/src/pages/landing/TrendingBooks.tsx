import { useEffect, useState } from "react";
import BookIcon from "../../components/BookIcon";
import type { Book } from "../../types/Book";
import { fetchBooks } from "../../api";

const TrendingBooks = () => {
  const [books, setBooks] = useState<Book[]>([]);
  const [loading, setLoading] = useState(true);
  const PAGE_NUMBER = 0;
  const PAGE_SIZE = 8;

  useEffect(() => {
    fetchBooks(PAGE_NUMBER, PAGE_SIZE, "")
      .then((data) => {
        setBooks(data.data);
      })
      .catch((err) => console.error(err))
      .finally(() => setLoading(false));
  }, []);

  return (
    <section id="trending" className="p-24 px-48 max-md:p-2">
      <h1 className="text-4xl font-semibold max-md:text-2xl">Trending Books</h1>
      <div className="">
        {loading ? (
          <div>Loading...</div>
        ) : (
          <div className="grid grid-cols-2 sm:grid-cols-4 md:grid-cols-6 lg:grid-cols-8 gap-4 md:gap-6 xl:gap-8 mt-6">
            {books.map((book) => (
              <BookIcon key={book.id} book={book} />
            ))}
          </div>
        )}
      </div>
    </section>
  );
};

export default TrendingBooks;
