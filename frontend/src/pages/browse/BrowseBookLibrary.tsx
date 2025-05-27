function BookLibrary({ query }: { query: string }) {
  return (
    <div className="md:flex-column bg-white py-10 px-8 max-md:text-center space-y-8 max-md:max-w-105 max-md:mx-auto">
      <div className="text-center">Filtered books for: {query}</div>
      <div className="flex gap-8 max-md:overflow-x-scroll md:flex-wrap"></div>
    </div>
  );
}

export default BookLibrary;
