const Footer = () => {
  return (
    <footer className="mt-16  ">
      <div className="flex min-h-1.5 bg-accent"></div>
      <div className="text-center py-6 text-sm text-muted border-t border-tertiary bg-secondary">
        <p className="text-foreground">
          © {new Date().getFullYear()} BetterReads. Built by{" "}
          <a
            href="https://sakshampowdel.com"
            className="text-accent hover:underline"
          >
            Saksham Powdel
          </a>
          .
        </p>
        <p className="mt-1">
          Book data provided by{" "}
          <a
            href="https://openlibrary.org/developers/api"
            target="_blank"
            rel="noopener noreferrer"
            className="text-accent hover:underline"
          >
            Open Library API
          </a>
          .
        </p>
        <p className="mt-1">
          BetterReads is an independent project and is not affiliated with
          Goodreads or Amazon.
        </p>
      </div>
    </footer>
  );
};

export default Footer;
