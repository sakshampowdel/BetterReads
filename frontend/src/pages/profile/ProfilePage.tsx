import { useEffect, useState } from "react";
import type { Profile } from "../../types/Profile";
import { useParams } from "react-router-dom";
import { fetchProfileById } from "../../api";

function checkId({ id }: { id: string | undefined }) {
  if (id === undefined) return -1;

  const num = Number(id);
  return Number.isInteger(num) ? num : -1;
}

const ProfilePage = () => {
  const [profile, setProfile] = useState<Profile | null>(null);
  const { id } = useParams();

  useEffect(() => {
    const newId = checkId({ id });
    if (newId === -1) {
      return;
    }

    fetchProfileById(newId)
      .then((data) => setProfile(data))
      .catch((err) => console.log(err));
  }, [id]);

  if (!profile) {
    return (
      <main className="min-h-screen flex items-center justify-center">
        <h1 className="text-4xl font-bold">Profile Not Found!</h1>
      </main>
    );
  }

  return (
    <main>
      <div>
        {profile.bookLists.length === 0 ? (
          <p className="text-gray-500 italic">
            This user has no book lists yet.
          </p>
        ) : (
          profile.bookLists.map((bookList) => (
            <div key={bookList.id}>
              <h2 className="text-2xl font-light">{bookList.name}</h2>
              <div>
                {bookList.books.map((book) => (
                  <h3 key={book.id}>{book.title}</h3>
                ))}
              </div>
            </div>
          ))
        )}
      </div>
    </main>
  );
};

export default ProfilePage;
