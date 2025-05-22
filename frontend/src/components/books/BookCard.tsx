import type { Book } from "../../types/Book";
function BookCard({ book }: { book: Book }) {
  const tailwindColors = [
    "bg-red-500",
    "bg-blue-500",
    "bg-green-500",
    "bg-yellow-500",
    "bg-purple-500",
    "bg-pink-500",
    "bg-indigo-500",
  ];

  const randomColor =
    tailwindColors[Math.floor(Math.random() * tailwindColors.length)];

  return (
    <div
      className={`${randomColor} flex-col max-w-85 min-w-85 min-h-120 p-4 rounded-2xl snap-x snap-mandatory scroll-smooth`}
    >
      <h1 className="font-bold truncate text-2xl">{book.title}</h1>
      <p className="font-semibold truncate">{book.authors[0].name}</p>
    </div>
  );
}

export default BookCard;
