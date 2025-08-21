import { LibraryBig } from "lucide-react";

const Hero = () => {
  return (
    <section
      id="hero"
      className="p-24 px-48 grid grid-cols-2 max-lg:grid-cols-1 items-center gap-20"
    >
      <div className="flex justify-center">
        <LibraryBig className="h-full max-h-70 min-w-full" />
      </div>

      <div className="flex flex-col gap-10 flex-wrap text-center">
        <h1 className="text-foreground font-bold text-8xl max-lg:text-6xl">
          Read Better.
        </h1>
        <p className="text-muted text-2xl">
          Connect with other book lovers without the dated feel!
        </p>
      </div>
    </section>
  );
};

export default Hero;
