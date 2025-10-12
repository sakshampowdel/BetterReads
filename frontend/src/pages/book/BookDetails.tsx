import { fetchBookById } from "../../api";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import type { Book } from "../../types/Book";

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

  return (
    <main>
      {book === null ? (
        <div>
          <h1>Book Not Found!</h1>
        </div>
      ) : (
        <div>
          <h1>{book.title}</h1>
          <div>
            {book.authors.map((author) => (
              <h2>{author.name}</h2>
            ))}
          </div>
          <p>{book.description}</p>
        </div>
      )}
    </main>
  );
};

export default BookDetails;
