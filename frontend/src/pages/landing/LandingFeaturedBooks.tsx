import { useEffect, useState } from "react";
import BookCard from "../../components/books/BookCard";
import type { Book } from "../../types/Book";
import type { Paginated } from "../../types/Paginated";

function FeaturedBooks() {
  const [books, setBooks] = useState<Paginated<Book> | null>(null);

  useEffect(() => {
    fetch("api/books?page=0&size=10")
      .then((res) => res.json())
      .then((data) => {
        setBooks(data);
      })
      .catch((err) => {
        console.error("Failed to fetch books:", err);
      });
  }, []);

  return (
    <div className="md:flex-column bg-white py-10 px-8 max-md:text-center space-y-8 max-md:max-w-105 max-md:mx-auto">
      <h1 className="font-semibold text-3xl ">Featured Books</h1>
      <div className="flex gap-8 max-md:overflow-x-scroll md:flex-wrap">
        {!books || books.data.length === 0 ? (
          <p>Loading...</p>
        ) : (
          <div className="flex gap-8 max-md:overflow-x-scroll md:flex-wrap">
            {books.data.map((book) => {
              console.log("Book:", book);
              return <BookCard key={book.id} book={book} />;
            })}
          </div>
        )}
      </div>
    </div>
  );
}

export default FeaturedBooks;
