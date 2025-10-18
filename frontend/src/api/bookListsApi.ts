export async function addBookToListById(
  bookListId: number,
  bookId: number,
  token: string
): Promise<void> {
  const res = await fetch(`/api/booklists/${bookListId}/books/${bookId}`, {
    method: "POST",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  if (!res.ok) {
    throw new Error(`Failed to add book to list (status ${res.status})`);
  }
}

export async function removeBookFromListById(
  bookListId: number,
  bookId: number,
  token: string
): Promise<void> {
  const res = await fetch(`/api/booklists/${bookListId}/books/${bookId}`, {
    method: "DELETE",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  if (!res.ok) {
    throw new Error(`Failed to remove book from list (status ${res.status})`);
  }
}
