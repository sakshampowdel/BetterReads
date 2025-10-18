import { fetchBookById } from "../../api";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import type { Book } from "../../types/Book";
import BookPicture from "../../components/BookPicture";
import ReactMarkdown from "react-markdown";
import AddToListButton from "./AddToListButton";
import BookReviews from "./BookReviews";

function checkId({ id }: { id: string | undefined }) {
  if (id === undefined) return -1;
  const num = Number(id);
  return Number.isInteger(num) ? num : -1;
}

const BookDetails = () => {
  const [book, setBook] = useState<Book | null>(null);
  const { id } = useParams();

  useEffect(() => {
    const newId = checkId({ id });
    if (newId === -1) return;

    fetchBookById(newId)
      .then((data) => setBook(data))
      .catch((err) => console.error(err));
  }, [id]);

  if (!book) {
    return (
      <main className="min-h-screen flex items-center justify-center">
        <h1 className="text-4xl font-bold text-muted">Book Not Found!</h1>
      </main>
    );
  }

  return (
    <main className="min-h-screen bg-background text-foreground py-10 px-6 flex flex-col items-center">
      <div className="max-w-3xl w-full space-y-8 text-center">
        {/* --- Book Cover --- */}
        <div className="flex justify-center">
          <BookPicture book={book} />
        </div>

        {/* --- Title & Author --- */}
        <div>
          <h1 className="text-4xl font-bold leading-tight">{book.title}</h1>
          <div className="mt-2 space-y-1">
            {book.authors.map((author) => (
              <h2
                key={author.name}
                className="text-xl font-light text-muted tracking-wide"
              >
                {author.name}
              </h2>
            ))}
          </div>
        </div>

        {/* --- Add to List --- */}
        <div className="flex justify-center">
          <AddToListButton bookId={book.id} />
        </div>

        {/* --- Description --- */}
        <div className="text-left bg-secondary/40 border border-tertiary rounded-xl p-6 shadow-sm leading-relaxed">
          <ReactMarkdown>{book.description}</ReactMarkdown>
        </div>

        {/* --- Reviews --- */}
        <div className="border-t border-tertiary pt-8">
          <BookReviews bookId={book.id} />
        </div>
      </div>
    </main>
  );
};

export default BookDetails;
