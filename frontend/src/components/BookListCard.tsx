import type { BookListPreview } from "../types/Book";

type BookListCardProps = {
  bookList: BookListPreview;
  onDelete?: () => void;
};

const BookListCard = ({ bookList, onDelete }: BookListCardProps) => {
  return (
    <div key={bookList.id} className="bg-secondary rounded-xl p-5 shadow-sm">
      <div className="flex justify-between text-2xl font-semibold mb-3 text-accent">
        <h2>{bookList.name}</h2>
        {onDelete && (
          <button
            onClick={onDelete}
            className="text-muted hover:text-highlight transition"
            aria-label="Delete book list"
          >
            ✕
          </button>
        )}
      </div>

      {bookList.books.length === 0 ? (
        <p className="text-muted italic">No books in this list yet.</p>
      ) : (
        <ul className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-2">
          {bookList.books.map((book) => (
            <li
              key={book.id}
              className="bg-background border border-tertiary rounded-lg p-3 hover:shadow transition"
            >
              <p className="font-medium text-foreground">{book.title}</p>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default BookListCard;
