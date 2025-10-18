import { BookX } from "lucide-react";
import { Link } from "react-router-dom";

const NotFoundPage = ({
  message = "Page not found.",
}: {
  message?: string;
}) => (
  <main className="min-h-[calc(100vh-7rem)] flex flex-col items-center justify-center text-center px-6">
    <BookX className="w-24 h-24 text-accent mb-6" />
    <h1 className="text-5xl font-bold text-foreground mb-3">404</h1>
    <p className="text-muted text-lg mb-8">{message}</p>
    <Link
      to="/"
      className="bg-accent text-accent-foreground font-medium px-6 py-3 rounded-lg hover:opacity-90 transition"
    >
      Return Home
    </Link>
  </main>
);

export default NotFoundPage;
