import { useState } from "react";
import BrowseBooks from "./BrowseBooks";
import { Search } from "lucide-react";

const Browse = () => {
  const [titleQuery, setTitleQuery] = useState("");

  return (
    <main className="min-h-[calc(100vh-7rem)] bg-background text-foreground pt-16 px-6 md:px-16 lg:px-32 space-y-8">
      {/* Header */}
      <div className="flex flex-col items-center text-center space-y-4">
        <h1 className="text-5xl font-bold tracking-tight">Browse Books</h1>
        <p className="text-muted text-lg">
          Search our catalog powered by the Open Library API.
        </p>
      </div>

      {/* Search bar */}
      <div className="relative max-w-3xl mx-auto">
        <Search className="absolute left-3 top-1/2 -translate-y-1/2 text-muted h-5 w-5" />
        <input
          type="search"
          id="browse-books"
          placeholder="Search by title..."
          value={titleQuery}
          onChange={(e) => setTitleQuery(e.target.value)}
          className="w-full pl-10 pr-4 py-3 rounded-xl border border-tertiary bg-secondary/50 focus:outline-none focus:ring-2 focus:ring-accent text-lg transition-all"
        />
      </div>

      {/* Books */}
      <div className="max-w-7xl mx-auto">
        <BrowseBooks title={titleQuery} />
      </div>
    </main>
  );
};

export default Browse;
