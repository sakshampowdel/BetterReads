import { useEffect, useState } from "react";
import type { Book } from "../../types/Book";
import { fetchBooks } from "../../api";
import BookIcon from "../../components/BookIcon";

const Browse = () => {
  const [books, setBooks] = useState<Book[]>([]);
  const [loading, setLoading] = useState(true);
  const [page, setPage] = useState(0);
  const PAGE_SIZE = 10;

  useEffect(() => {
    fetchBooks(page, PAGE_SIZE)
      .then((data) => {
        setBooks(data.data);
        console.log("Books resposne:", data.data);
      })
      .catch((err) => console.error(err))
      .finally(() => setLoading(false));
  }, [page]);

  return (
    <section id="trending" className="p-24 px-48 max-md:p-2">
      <h1 className="text-4xl font-semibold max-md:text-2xl">Trending Books</h1>
      <div className="py-8 grid grid-cols-2 sm:grid-cols-4  lg:grid-cols-10 gap-2">
        {loading ? (
          <div>Loading...</div>
        ) : (
          <div>
            {books.map((book) => (
              <BookIcon key={book.id} book={book} />
            ))}
          </div>
        )}
      </div>
    </section>
  );
};

export default Browse;
