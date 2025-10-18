import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import type { Profile } from "../../types/Profile";
import { fetchProfileById } from "../../api";
import CreateBookList from "./CreateBookList";

function checkId(id: string | undefined): number {
  if (!id) return -1;
  const num = Number(id);
  return Number.isInteger(num) ? num : -1;
}

const ProfilePage = () => {
  const [profile, setProfile] = useState<Profile | null>(null);
  const [error, setError] = useState<string | null>(null);
  const { id } = useParams();

  useEffect(() => {
    const newId = checkId(id);
    if (newId === -1) return;

    fetchProfileById(newId)
      .then((data) => setProfile(data))
      .catch((err) => {
        console.error(err);
        setError("Failed to load profile.");
      });
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
                This user hasn’t created any book lists yet.
              </p>
            </div>
          ) : (
            profile.bookLists.map((bookList) => (
              <div
                key={bookList.id}
                className="bg-secondary rounded-xl p-5 shadow-sm"
              >
                <h2 className="text-2xl font-semibold mb-3 text-accent">
                  {bookList.name}
                </h2>
                {bookList.books.length === 0 ? (
                  <p className="text-muted italic">
                    No books in this list yet.
                  </p>
                ) : (
                  <ul className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-2">
                    {bookList.books.map((book) => (
                      <li
                        key={book.id}
                        className="bg-background border border-tertiary rounded-lg p-3 hover:shadow transition"
                      >
                        <p className="font-medium text-foreground">
                          {book.title}
                        </p>
                      </li>
                    ))}
                  </ul>
                )}
              </div>
            ))
          )}
        </div>

        {/* Create New List (only for current user’s profile) */}
        <div className="mt-10">
          <CreateBookList />
        </div>
      </section>
    </main>
  );
};

export default ProfilePage;
