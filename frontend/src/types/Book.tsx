import type { Author } from "./Author";

export type Book = {
  id: number;
  title: string;
  authors: Author[];
  openLibraryId: string;
  description: string;
};
