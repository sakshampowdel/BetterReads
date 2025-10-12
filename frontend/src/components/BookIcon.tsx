import { useState } from "react";
import type { Book } from "../types/Book";
import { Link } from "react-router-dom";

const BookIcon = ({ book }: { book: Book }) => {
  const [imgError, setImgError] = useState(false);
  const imgLink = `https://covers.openlibrary.org/w/olid/${book.openLibraryId}-M.jpg?default=false`;

  return (
    <div className="aspect-[2/3] w-full overflow-hidden rounded-md border border-gray-100/20 shadow-sm hover:shadow-lg hover:scale-[1.02] transition-all duration-300">
      <Link to={`/book/${book.id}`}>
        {!imgError ? (
          <img
            src={imgLink}
            alt={book.title}
            loading="lazy"
            onError={() => setImgError(true)}
            className="h-full w-full object-cover transition-transform duration-300 hover:scale-105"
          />
        ) : (
          <div className="h-full w-full bg-gradient-to-br from-accent/30 via-accent/10 to-gray-800 flex items-end p-3 text-foreground">
            <span className="text-sm font-medium truncate">{book.title}</span>
          </div>
        )}
      </Link>
    </div>
  );
};

export default BookIcon;
