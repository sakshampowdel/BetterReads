function HeroSection() {
  return (
    <div className="md:flex-column bg-white py-10 px-8 max-md:text-center space-y-8">
      <h1 className="font-bold text-5xl">Welcome to BetterReads</h1>
      <p className="text-xl">Keep track of books you read with a modern feel</p>
      <button className="bg-black text-white py-3 px-5 rounded-md">
        Get Started
      </button>
    </div>
  );
}

export default HeroSection;
