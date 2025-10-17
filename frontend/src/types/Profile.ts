import type { BookListPreview } from "./Book";

export type Profile = {
  id: number;
  displayName: string;
  bio: string;
  bookLists: BookListPreview[];
};

export type ProfileRequest = {
  displayName: string;
  bio: string;
};
