import { useState } from "react";
import type { Book } from "../types/Book";

const BookPicture = ({ book }: { book: Book }) => {
  const [imgError, setImgError] = useState(false);
  return (
    <div>
      {!imgError ? (
        <img
          className=""
          src={`https://covers.openlibrary.org/w/olid/${book.openLibraryId}-M.jpg?default=false`}
          onError={() => setImgError(true)}
        />
      ) : (
        <div className="h-75 w-50 bg-gradient-to-br from-accent/30 via-accent/10 to-gray-800 flex items-end p-3 text-foreground">
          <span className="text-sm font-medium truncate">{book.title}</span>
        </div>
      )}
    </div>
  );
};

export default BookPicture;
