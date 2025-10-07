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
    fetchBooks(PAGE_NUMBER, PAGE_SIZE)
      .then((data) => {
        setBooks(data.data);
        console.log("Books resposne:", data.data);
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
          <div className="py-8 grid grid-cols-2 sm:grid-cols-4 lg:grid-cols-10 gap-2 overflow-hidden">
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
