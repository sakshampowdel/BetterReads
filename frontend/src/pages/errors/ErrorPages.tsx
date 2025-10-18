import { AlertTriangle } from "lucide-react";
import { Link } from "react-router-dom";

const ErrorPage = ({ message }: { message: string }) => (
  <main className="min-h-[calc(100vh-7rem)] flex flex-col items-center justify-center text-center px-6">
    <AlertTriangle className="w-20 h-20 text-accent mb-6" />
    <h1 className="text-4xl font-bold mb-2 text-foreground">
      Something went wrong
    </h1>
    <p className="text-muted text-lg mb-6">{message}</p>
    <Link
      to="/"
      className="bg-accent text-accent-foreground px-5 py-2 rounded-lg hover:opacity-90 transition"
    >
      Back to Home
    </Link>
  </main>
);

export default ErrorPage;
