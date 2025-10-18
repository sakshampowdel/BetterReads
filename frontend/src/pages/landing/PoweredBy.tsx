import { BookOpen } from "lucide-react";

const PoweredBy = () => {
  return (
    <section
      id="powered-by"
      className="py-24 px-6 md:px-16 lg:px-32 text-center "
    >
      {/* Icon */}
      <div className="flex justify-center mb-8">
        <div className="bg-accent/10 p-6 rounded-full">
          <BookOpen className="w-16 h-16 text-accent" />
        </div>
      </div>

      {/* Title */}
      <h2 className="text-4xl md:text-5xl font-bold text-foreground mb-6">
        Powered by Open Library
      </h2>

      {/* Subtitle */}
      <p className="text-muted text-lg md:text-xl max-w-2xl mx-auto mb-8">
        Every book you see here is made possible thanks to the{" "}
        <a
          href="https://openlibrary.org/developers/api"
          target="_blank"
          rel="noopener noreferrer"
          className="text-accent hover:underline font-medium"
        >
          Open Library API
        </a>
        , a community-driven effort to catalog every book ever published.
      </p>

      {/* Gratitude / Callout */}
      <div className="max-w-xl mx-auto bg-accent text-accent-foreground py-4 px-6 rounded-2xl shadow-md">
        <p className="text-lg font-medium">
          Huge thanks to the{" "}
          <a
            href="https://openlibrary.org"
            target="_blank"
            rel="noopener noreferrer"
            className="underline hover:opacity-90"
          >
            Open Library
          </a>{" "}
          and the Internet Archive team for keeping the world’s books open to
          everyone.
        </p>
      </div>
    </section>
  );
};

export default PoweredBy;
