import BookIcon from "../../components/BookIcon";
import type { Book } from "../../types/Book";

const testBook: Book = {
  id: 1,
  title: "Harry Potter and the Philosopher's Stone",
  authors: [
    {
      id: 1,
      name: "J. K. Rowling",
      openlibraryid: "OL23919A",
      bio: "British author, best known for the Harry Potter series.",
    },
  ],
  openlibraryid: "OL7353617M",
  description:
    "The first book in the Harry Potter series, following Harry's journey at Hogwarts.",
};

const TrendingBooks = () => {
  return (
    <section id="trending" className="p-24 px-48 max-md:p-2">
      <h1 className="text-4xl font-semibold max-md:text-2xl">Trending Books</h1>
      <div className="py-8 grid grid-cols-2 sm:grid-cols-4  lg:grid-cols-10 gap-2">
        <div>
          <BookIcon book={testBook} />
        </div>
        <div>
          <BookIcon book={testBook} />
        </div>
        <div>
          <BookIcon book={testBook} />
        </div>
        <div>
          <BookIcon book={testBook} />
        </div>
        <div>
          <BookIcon book={testBook} />
        </div>
        <div>
          <BookIcon book={testBook} />
        </div>
        <div>
          <BookIcon book={testBook} />
        </div>
        <div>
          <BookIcon book={testBook} />
        </div>
        <div className="block md:hidden lg:block">
          <BookIcon book={testBook} />
        </div>
        <div className="block md:hidden lg:block">
          <BookIcon book={testBook} />
        </div>
        <div className="block md:hidden lg:hidden">
          <BookIcon book={testBook} />
        </div>
        <div className="block md:hidden lg:hidden">
          <BookIcon book={testBook} />
        </div>
      </div>
    </section>
  );
};

export default TrendingBooks;
