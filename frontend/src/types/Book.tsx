import type { Author } from "./Author";

export type Book = {
  id: number;
  title: string;
  authors: Author[];
  openlibraryid: string;
  description: string;
};
