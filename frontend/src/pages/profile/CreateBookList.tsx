import { useAuth } from "../../context/useAuth";
import { useState } from "react";

type Props = {
  onListCreated?: () => void;
};

function CreateBookList({ onListCreated }: Props) {
  const { authState } = useAuth();
  const [name, setName] = useState("");
  const [message, setMessage] = useState("");
  const token = authState.token;

  const handleCreate = async () => {
    if (!token || !name.trim()) return;

    try {
      const res = await fetch("/api/booklists", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({ name }),
      });

      if (!res.ok) throw new Error("Failed to create list");

      setMessage("List created successfully!");
      setName("");
      onListCreated?.();
    } catch {
      setMessage("Error creating list");
    }
  };

  return (
    <div className="mt-8 bg-secondary p-5 rounded-xl shadow-sm">
      <h3 className="text-lg font-semibold text-foreground mb-3">
        Create a new book list
      </h3>

      <div className="flex flex-col sm:flex-row gap-3">
        <input
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
          placeholder="List name..."
          className="flex-1 bg-background text-foreground border border-tertiary rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-highlight transition"
        />

        <button
          onClick={handleCreate}
          className="bg-accent text-accent-foreground font-medium px-4 py-2 rounded-lg hover:opacity-90 transition"
        >
          Create
        </button>
      </div>

      {message && (
        <p className="mt-3 text-sm text-highlight font-medium">{message}</p>
      )}
    </div>
  );
}

export default CreateBookList;
