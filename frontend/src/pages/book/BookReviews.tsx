import { useEffect, useState } from "react";
import { fetchReviewsByBookId, postReview } from "../../api";
import { useAuth } from "../../context/useAuth";
import type { Review } from "../../types/Review";
import type { Paginated } from "../../types/Paginated";

const BookReviews = ({ bookId }: { bookId: number }) => {
  const { authState } = useAuth();
  const [reviews, setReviews] = useState<Review[]>([]);
  const [pagination, setPagination] = useState({
    page: 0,
    totalPages: 1,
  });
  const [rating, setRating] = useState(5);
  const [comment, setComment] = useState("");
  const [message, setMessage] = useState("");

  useEffect(() => {
    fetchReviewsByBookId(bookId)
      .then((data: Paginated<Review>) => {
        setReviews(data.data ?? data.data ?? []);
        setPagination({ page: data.page, totalPages: data.totalPages });
      })
      .catch((err) => console.error("Failed to load reviews", err));
  }, [bookId]);

  async function handleLoadMore() {
    if (pagination.page + 1 >= pagination.totalPages) return;

    const nextPage = pagination.page + 1;
    const data = await fetchReviewsByBookId(bookId, nextPage);
    setReviews((prev) => [...prev, ...(data.data ?? data.data)]);
    setPagination({ page: data.page, totalPages: data.totalPages });
  }

  async function handleSubmit() {
    if (!authState.token) {
      setMessage("You must be logged in to leave a review.");
      return;
    }

    try {
      const newReview: Review = await postReview(
        { bookId, rating, comment },
        authState.token
      );

      setReviews((prev) => [newReview, ...prev]);
      setComment("");
      setRating(5);
      setMessage("Review added successfully!");
    } catch {
      setMessage("Error adding review.");
    }
  }

  return (
    <section className="max-w-3xl mx-auto space-y-6 mt-12">
      <h2 className="text-3xl font-semibold text-accent">Reviews</h2>

      {/* --- List of reviews --- */}
      {reviews.length === 0 ? (
        <p className="text-muted italic">No reviews yet.</p>
      ) : (
        <div className="space-y-4">
          {reviews.map((r) => (
            <div key={r.id} className="bg-secondary p-4 rounded-xl shadow-sm">
              <p className="font-medium text-foreground">
                {r.profileDisplayName} — ⭐ {r.rating}/5
              </p>
              <p className="text-muted">{r.comment}</p>
            </div>
          ))}
        </div>
      )}

      {/* --- Load More Button --- */}
      {pagination.page + 1 < pagination.totalPages && (
        <button
          onClick={handleLoadMore}
          className="mt-4 bg-accent text-accent-foreground px-4 py-2 rounded-lg hover:opacity-90 transition"
        >
          Load More
        </button>
      )}

      {/* --- Create New Review Form --- */}
      <div className="bg-tertiary p-6 rounded-xl mt-8 space-y-3">
        <h3 className="text-2xl font-semibold text-foreground">
          Write a Review
        </h3>

        <div className="flex items-center gap-3">
          <label>Rating:</label>
          <select
            value={rating}
            onChange={(e) => setRating(Number(e.target.value))}
            className="rounded-lg bg-background border border-tertiary p-2"
          >
            {[1, 2, 3, 4, 5].map((r) => (
              <option key={r} value={r}>
                {r}
              </option>
            ))}
          </select>
        </div>

        <textarea
          value={comment}
          onChange={(e) => setComment(e.target.value)}
          placeholder="Write your thoughts..."
          className="w-full bg-background border border-tertiary rounded-lg p-3 h-24"
        />

        <button
          onClick={handleSubmit}
          className="bg-accent text-accent-foreground font-medium px-4 py-2 rounded-lg hover:opacity-90 transition"
        >
          Submit
        </button>

        {message && (
          <p className="text-highlight font-medium mt-2">{message}</p>
        )}
      </div>
    </section>
  );
};

export default BookReviews;
