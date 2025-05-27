import BookCard from "../../components/books/BookCard";

function FeaturedBooks() {
  const dummyBook = {
    id: 1,
    title: "Clean Code",
    isbn: "978-0132350884",
    authors: [{ id: 1, name: "Robert C. Martin" }],
  };

  return (
    <div className="md:flex-column bg-white py-10 px-8 max-md:text-center space-y-8 max-md:max-w-105 max-md:mx-auto">
      <h1 className="font-semibold text-3xl ">Featured Books</h1>
      <div className="flex gap-8 max-md:overflow-x-scroll md:flex-wrap">
        <BookCard book={dummyBook} />
        <BookCard book={dummyBook} />
        <BookCard book={dummyBook} />
        <BookCard book={dummyBook} />
        <BookCard book={dummyBook} />
        <BookCard book={dummyBook} />
        <BookCard book={dummyBook} />
        <BookCard book={dummyBook} />
        <BookCard book={dummyBook} />
        <BookCard book={dummyBook} />
      </div>
    </div>
  );
}

export default FeaturedBooks;
