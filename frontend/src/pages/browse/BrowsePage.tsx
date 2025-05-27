import { useState } from "react";
import SearchBar from "./BrowseSearchBar";
import BookLibrary from "./BrowseBookLibrary";

function BrowsePage() {
  const [query, setQuery] = useState("");

  return (
    <div>
      <SearchBar query={query} setQuery={setQuery}></SearchBar>
      <BookLibrary query={query}></BookLibrary>
    </div>
  );
}

export default BrowsePage;
