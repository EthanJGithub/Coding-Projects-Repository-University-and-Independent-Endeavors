import React from 'react';
import { Link } from 'react-router-dom';

const SkillsPage = () => {
  return (
    <main>
      <header>
        <h1>My Technical Skills</h1>
     
      </header>

      <div className="center-content">
        {/* Link back to home page */}
        <p><Link to="/" style={{ color: 'blue', textDecoration: 'underline' }}>Collapse</Link></p>
        
        <h2>A passion for creating software</h2>
        <p>
          My extensive experience at Georgia Southern has honed my skills, enabling effective problem-solving through the application of mathematical principles...
        </p>
        
        {/* List your skills and descriptions */}
        <h3>Java Development</h3>
        <p>Ethan is crafting dynamic and robust applications with precision using the power of Java.</p>
        <h3>SQL Sorcerer</h3>
        <p>Mastering the art of database manipulation to ensure seamless and efficient data management.</p>
        <h3>HTML & Web Development Skills</h3>
        <p>I am bringing web visions to life through a fusion of creative HTML design and expert web development software.</p>
        <h3>JavaScript Jedi</h3>
        <p>Transforming static web pages into interactive and dynamic experiences with the magic of JavaScript</p>
        <h3>Experienced Software Engineer</h3>
        <p>Architecting cutting-edge software solutions with a focus on efficiency, scalability, and innovation</p>
        <h3>Development Assistance</h3>
        <p>Guiding fellow developers through the intricacies of coding, transforming challenges into triumphs</p>
        <h3>An Array of opportunities</h3>
        <p>Unleashing the potential of arrays in programming and beyond. From Java arrays to SQL databases, HTML structures, and dynamic JavaScript arrays, I navigate through opportunities with precision and versatility, turning code into endless possibilities.</p>
        <h3>Mobile App Creation</h3>
        <p>Ethan is Designing mobile apps that captivate and engage users, focusing on intuitive interfaces and optimal user experiences.</p>
        <p>Innovation in Every Swipe: Leveraging cutting-edge technologies and creative solutions to develop mobile applications that stand out in the digital landscape.</p>
        <p>Android Studio Expertise: Harnessing the power of Android Studio, I bring your ideas to life on the Android platform, ensuring robust, efficient, and user-friendly mobile applications.</p>
        
        {/* Link to GitHub repository */}
        <p>Check out my <a href="https://github.com/EthanJGithub/Coding-Projects-Repository-University-and-Independent-Endeavors" style={{ color: 'blue', textDecoration: 'underline' }} target="_blank" rel="noopener noreferrer">GitHub Repository</a>!</p>
        
        {/* A list of projects or skills */}
        <ul>
          <li>Contains Java Projects!</li>
          <li>Will soon have Mobile Apps!</li>
          <li>Contains code to this website!</li>
        </ul>

        {/* A blockquote for a quote you find inspiring */}
        <blockquote>
          <p>“Imagination is the Discovering Faculty, pre-eminently. It is that which penetrates into the unseen worlds around us, the worlds of Science...” - Ada Lovelace</p>
        </blockquote>
      </div>
      {/* Footer component should be rendered in the parent App component, not here */}
      <footer>
        &copy; 2024 Ethan Jones | Contact: ej06939@georgiasouthern.edu | Phone: (404) 751-8752 | LinkedIn: <a href="https://www.linkedin.com/in/ethan-jones-8290262b2/" style={{ color: 'red', textDecoration: 'underline' }} target="_blank">Ethan Jones</a>
      </footer>
    </main>
  );
};

export default SkillsPage;
