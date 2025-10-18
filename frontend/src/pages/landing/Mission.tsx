import { Heart, Users, Sparkles } from "lucide-react";

const Mission = () => {
  return (
    <section className="px-6 md:px-16 lg:px-32 py-24 text-center bg-background text-foreground">
      <h2 className="text-4xl md:text-5xl font-bold mb-8">Our Mission</h2>
      <p className="text-lg md:text-xl text-muted max-w-3xl mx-auto mb-12">
        BetterReads was created for readers who crave a cleaner, faster, and
        more human way to share their love of books. No clutter — just stories,
        connections, and discovery.
      </p>

      <div className="grid grid-cols-1 md:grid-cols-3 gap-10 max-w-5xl mx-auto">
        <div className="flex flex-col items-center space-y-4">
          <Heart className="w-10 h-10 text-accent" />
          <h3 className="text-xl font-semibold">Built with Passion</h3>
          <p className="text-muted">
            Designed by readers, for readers — because we believe in better book
            communities.
          </p>
        </div>
        <div className="flex flex-col items-center space-y-4">
          <Users className="w-10 h-10 text-accent" />
          <h3 className="text-xl font-semibold">Community First</h3>
          <p className="text-muted">
            Your shelves, lists, and reviews — built around real people, not
            algorithms.
          </p>
        </div>
        <div className="flex flex-col items-center space-y-4">
          <Sparkles className="w-10 h-10 text-accent" />
          <h3 className="text-xl font-semibold">A Modern Touch</h3>
          <p className="text-muted">
            Clean, responsive, and easy on the eyes. Reading should feel
            inspiring, not overwhelming.
          </p>
        </div>
      </div>
    </section>
  );
};

export default Mission;
