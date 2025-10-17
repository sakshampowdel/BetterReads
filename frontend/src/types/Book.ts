import type { Author } from "./Author";

export type Book = {
  id: number;
  title: string;
  authors: Author[];
  openLibraryId: string;
  description: string;
};

export type BookPreview = {
  id: number;
  openLibraryId: string;
  title: string;
};

export type BookListPreview = {
  id: number;
  name: string;
  books: BookPreview[];
};
