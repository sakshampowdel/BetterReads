import type { Book } from "../types/Book";

const BookIcon = ({ book }: { book: Book }) => {
  const imgLink = `https://covers.openlibrary.org/b/OLID/${book.openlibraryid}-M.jpg`;

  return (
    <div className="w-full">
      <img
        src={imgLink}
        className="w-full h-auto outline-2 rounded-xl outline-accent-foreground"
      />
    </div>
  );
};

export default BookIcon;
