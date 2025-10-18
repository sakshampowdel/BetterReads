export type Review = {
  id: number;
  profileDisplayName: string;
  rating: number;
  comment: string;
};

export type ReviewRequest = {
  bookId: number;
  rating: number;
  comment: string;
};
