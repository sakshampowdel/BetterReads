import { useState } from "react";
import BrowseBooks from "./BrowseBooks";

const Browse = () => {
  const [titleQuery, setTitleQuery] = useState("");

  return (
    <main className="min-h-[calc(100vh-7rem)] pt-12 px-48 max-md:p-2 space-y-4">
      <input
        className="text-2xl py-1 px-2 min-w-full border-2 border-muted rounded-sm bg-background focus:outline-none focus:border-accent transition-colors"
        type="search"
        id="browse-books"
        placeholder="Search..."
        value={titleQuery}
        onChange={(e) => setTitleQuery(e.target.value)}
      ></input>
      <BrowseBooks title={titleQuery} />
    </main>
  );
};

export default Browse;
