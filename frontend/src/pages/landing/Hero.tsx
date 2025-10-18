import { LibraryBig } from "lucide-react";
import { Link } from "react-router-dom";

const Hero = () => {
  return (
    <section
      id="hero"
      className="pt-24 pb-32 px-6 md:px-16 lg:px-32 grid grid-cols-1 md:grid-cols-2 items-center gap-16"
    >
      {/* Icon side */}
      <div className="flex justify-center md:justify-end">
        <LibraryBig className="w-2/3 max-w-sm h-auto text-accent" />
      </div>

      {/* Text side */}
      <div className="flex flex-col items-center md:items-start text-center md:text-left space-y-6">
        <h1 className="text-5xl md:text-7xl font-extrabold leading-tight text-foreground">
          Read Better.
        </h1>
        <p className="text-muted text-xl md:text-2xl max-w-lg">
          Connect with other book lovers — without the dated feel.
        </p>
        <Link
          to="/register"
          className="bg-accent text-accent-foreground text-lg font-semibold px-6 py-3 rounded-lg hover:opacity-90 transition"
        >
          Join Us
        </Link>
      </div>
    </section>
  );
};

export default Hero;
