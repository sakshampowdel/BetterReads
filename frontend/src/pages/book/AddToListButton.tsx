import { useState, useEffect } from "react";
import { addBookToListById } from "../../api";
import { useAuth } from "../../context/useAuth";
import type { BookListPreview } from "../../types/Book";

function AddToListButton({ bookId }: { bookId: number }) {
  const { authState } = useAuth();
  const token = authState.token;
  const [bookLists, setBookLists] = useState<BookListPreview[]>([]);
  const [loading, setLoading] = useState(true);
  const [message, setMessage] = useState<string | null>(null);

  useEffect(() => {
    if (!token) return;
    fetch("/api/profiles/me", {
      headers: { Authorization: `Bearer ${token}` },
    })
      .then((res) => res.json())
      .then((data) => {
        setBookLists(data.bookLists || []);
        setLoading(false);
      })
      .catch(() => setLoading(false));
  }, [token]);

  const handleAdd = async (listId: number) => {
    if (!token) return alert("Please log in first");
    try {
      await addBookToListById(listId, bookId, token);
      setMessage("Book added successfully!");
    } catch {
      setMessage("Failed to add book.");
    }
  };

  if (!token)
    return (
      <p className="text-muted italic">Login to add this book to your lists.</p>
    );

  if (loading)
    return <p className="text-muted italic">Loading your book lists...</p>;

  return (
    <div className="mt-6 bg-secondary rounded-xl p-4 shadow-sm">
      <label className="block font-medium text-foreground mb-2">
        Add to one of your lists
      </label>

      {bookLists.length === 0 ? (
        <p className="text-muted italic">You don't have any lists yet.</p>
      ) : (
        <select
          onChange={(e) => handleAdd(Number(e.target.value))}
          defaultValue=""
          className="w-full bg-background text-foreground border border-tertiary rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-highlight transition"
        >
          <option value="" disabled>
            Select a list
          </option>
          {bookLists.map((list) => (
            <option key={list.id} value={list.id}>
              {list.name}
            </option>
          ))}
        </select>
      )}

      {message && (
        <p className="mt-2 text-sm text-highlight font-medium">{message}</p>
      )}
    </div>
  );
}

export default AddToListButton;
