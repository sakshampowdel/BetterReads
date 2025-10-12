import { fetchBookById } from "../../api";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import type { Book } from "../../types/Book";
import BookPicture from "../../components/BookPicture";
import ReactMarkdown from "react-markdown";

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
    if (newId === -1) {
      return;
    }

    fetchBookById(newId)
      .then((data) => setBook(data))
      .catch((err) => console.log(err));
  }, [id]);

  if (!book) {
    return (
      <main className="min-h-screen flex items-center justify-center">
        <h1 className="text-4xl font-bold">Book Not Found!</h1>
      </main>
    );
  }

  return (
    <main className="min-h-screen p-6 md:px-24 lg:px-48 space-y-8">
      <div className="max-w-6xl mx-auto flex flex-col md:flex-row gap-8 items-center md:items-start">
        <BookPicture book={book} />
        <div className="flex-1 space-y-4">
          <h1 className="text-5xl font-bold underline break-words text-center md:text-left">
            {book.title}
          </h1>
          <div>
            {book.authors.map((author) => (
              <h2 key={author.name} className="text-2xl font-light">
                {author.name}
              </h2>
            ))}
          </div>
          <ReactMarkdown>{book.description}</ReactMarkdown>
        </div>
      </div>
    </main>
  );
};

export default BookDetails;
