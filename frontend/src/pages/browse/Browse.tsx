import { useState } from "react";
import BrowseBooks from "./BrowseBooks";

const Browse = () => {
  const [titleQuery, setTitleQuery] = useState("");

  return (
    <section
      id="browse"
      className="min-h-screen pt-24 px-48 max-md:p-2 space-y-4"
    >
      <input
        className="text-2xl py-1 min-w-full border-2 border-muted rounded-sm"
        type="search"
        id="browse-books"
        placeholder="Search..."
        value={titleQuery}
        onChange={(e) => setTitleQuery(e.target.value)}
      ></input>
      <BrowseBooks title={titleQuery} />
    </section>
  );
};

export default Browse;
