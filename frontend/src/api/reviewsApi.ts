import type { Paginated } from "../types/Paginated";
import type { Review, ReviewRequest } from "../types/Review";

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

export async function fetchReviewsByBookId(
  bookId: number,
  page = 0,
  size = 10
): Promise<Paginated<Review>> {
  const res = await fetch(
    `${API_BASE_URL}/api/reviews/book/${bookId}?page=${page}&size=${size}`
  );
  if (!res.ok) throw new Error("Failed to fetch reviews");
  return res.json();
}

export async function postReview(
  review: ReviewRequest,
  token: string
): Promise<Review> {
  const res = await fetch(`${API_BASE_URL}/api/reviews`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify(review),
  });

  if (!res.ok) throw new Error("Failed to post review");
  return res.json();
}
