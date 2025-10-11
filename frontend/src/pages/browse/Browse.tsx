import { useState } from "react";
import BrowseBooks from "./BrowseBooks";

const Browse = () => {
  const [titleQuery, setTitleQuery] = useState("");

  return (
    <main className="min-h-screen pt-12 px-48 max-md:p-2 space-y-4">
      <input
        className="text-2xl py-1 min-w-full border-2 border-muted rounded-sm"
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
