import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import type { Profile } from "../../types/Profile";
import { fetchProfileById } from "../../api";
import CreateBookList from "./CreateBookList";
import BookListCard from "../../components/BookListCard";
import { useAuth } from "../../context/useAuth";

function checkId(id: string | undefined): number {
  if (!id) return -1;
  const num = Number(id);
  return Number.isInteger(num) ? num : -1;
}

const ProfilePage = () => {
  const [profile, setProfile] = useState<Profile | null>(null);
  const [error, setError] = useState<string | null>(null);
  const { id } = useParams();
  const { authState } = useAuth();

  const handleDeleteBookList = async (listId: number) => {
    if (!window.confirm("Are you sure you want to delete this list?")) return;

    try {
      const res = await fetch(`/api/booklists/${listId}`, {
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${authState.token}`,
        },
      });

      if (!res.ok) throw new Error("Failed to delete list");

      setProfile((prev) =>
        prev
          ? {
              ...prev,
              bookLists: prev.bookLists.filter((l) => l.id !== listId),
            }
          : prev
      );
    } catch (err) {
      console.error(err);
      alert("Error deleting list");
    }
  };

  const loadProfile = async (profileId: number) => {
    try {
      const data = await fetchProfileById(profileId);
      setProfile(data);
    } catch (err) {
      console.error(err);
      setError("Failed to load profile.");
    }
  };

  useEffect(() => {
    const newId = checkId(id);
    if (newId !== -1) loadProfile(newId);
  }, [id]);

  if (error) {
    return (
      <main className="min-h-screen flex items-center justify-center">
        <h1 className="text-2xl text-highlight font-medium">{error}</h1>
      </main>
    );
  }

  if (!profile) {
    return (
      <main className="min-h-screen flex items-center justify-center">
        <h1 className="text-4xl font-semibold text-muted">
          Profile Not Found!
        </h1>
      </main>
    );
  }

  return (
    <main className="min-h-screen bg-background text-foreground p-6 md:p-10">
      <section className="max-w-4xl mx-auto">
        {/* Header */}
        <div className="mb-8 border-b border-tertiary pb-4">
          <h1 className="text-4xl font-bold text-foreground">
            {profile.displayName}
          </h1>
          {profile.bio && (
            <p className="text-muted italic mt-2">{profile.bio}</p>
          )}
        </div>

        {/* Book Lists */}
        <div className="space-y-8">
          {profile.bookLists.length === 0 ? (
            <div className="bg-secondary p-6 rounded-xl shadow-sm text-center">
              <p className="text-muted italic">
                This user hasn't created any book lists yet.
              </p>
            </div>
          ) : (
            profile.bookLists.map((bookList) => (
              <BookListCard
                key={bookList.id}
                bookList={bookList}
                onDelete={() => handleDeleteBookList(bookList.id)}
              />
            ))
          )}
        </div>

        {/* Create New List (only for current user’s profile) */}
        <div className="mt-10">
          {profile && (
            <CreateBookList onListCreated={() => loadProfile(profile.id)} />
          )}
        </div>
      </section>
    </main>
  );
};

export default ProfilePage;
