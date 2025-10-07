import type { Book } from "../types/Book";

const BookIcon = ({ book }: { book: Book }) => {
  const imgLink = `https://covers.openlibrary.org/w/olid/${book.openLibraryId}-M.jpg?default=false`;

  return (
    <div className="w-full aspect-[2/3] overflow-hidden rounded-xl bg-neutral-100 flex items-center justify-center text-accent-foreground text-center">
      <img
        src={imgLink}
        alt={book.title}
        onError={(e) => (e.currentTarget.src = "")}
        className="w-full h-full object-cover"
      />
    </div>
  );
};

export default BookIcon;
