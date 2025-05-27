type SearchBarProps = {
  query: string;
  setQuery: (value: string) => void;
};

function SearchBar({ query, setQuery }: SearchBarProps) {
  return (
    <div className="md:flex bg-white py-10 px-8 max-md:text-center gap-4 space-y-4 min-h-40 justify-center">
      <input
        type="text"
        placeholder="Search..."
        value={query}
        onChange={(event) => setQuery(event.target.value)}
        className="bg-gray-200 outline-2 max-h-10 px-1"
      ></input>
    </div>
  );
}

export default SearchBar;
