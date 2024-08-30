import React, { useEffect, useState, useRef } from 'react';
import './App.css';
import Chart from 'chart.js/auto';
import { BrowserRouter as Router, Link, Route, Routes } from 'react-router-dom';
import SkillsPage from './SkillsPage';
import ContactForm from './ContactForm';

function App() {
  const [theme, setTheme] = useState('royal-blue');
  const [themeChangeCount, setThemeChangeCount] = useState(0);
  const [likes, setLikes] = useState([0, 0, 0]);
  const [isGraphVisible, setIsGraphVisible] = useState(false);
  const [enlargedImages, setEnlargedImages] = useState([]);
  const [filter, setFilter] = useState('all');
  const [weather, setWeather] = useState(null); // Add state for weather
  const photosRef = useRef(null);

  useEffect(() => {
    generateGraph();
  }, [likes, themeChangeCount]);

  useEffect(() => {
    applyThemeStyles(theme);
  }, [theme]);

  useEffect(() => {
    // Fetch weather data when component mounts
    fetchWeather();
  }, []);

  const applyThemeStyles = (selectedTheme) => {
    document.body.classList.remove('royal-blue-theme', 'pikachu-theme', 'shadow-theme');
    document.body.classList.add(`${selectedTheme}-theme`);
  };

  const handleThemeChange = (newTheme) => {
    setTheme(newTheme);
    setThemeChangeCount((prevCount) => prevCount + 1);
  };

  const handleImageClick = (index) => {
    setLikes((prevLikes) => {
      const newLikes = [...prevLikes];
      newLikes[index]++;
      return newLikes;
    });
    enlargeImage(index);
    animateFlyingEmoji(index);
  };

  const enlargeImage = (index) => {
    setEnlargedImages((prevImages) => {
      if (prevImages.includes(index)) {
        return prevImages.filter((imageIndex) => imageIndex !== index);
      } else {
        return [...prevImages, index];
      }
    });
  };

  const animateFlyingEmoji = (index) => {
    const flyingEmoji = document.createElement('div');
    flyingEmoji.textContent = '💯';
    flyingEmoji.classList.add('flying-emoji');
    document.body.appendChild(flyingEmoji);

    const image = document.querySelectorAll('.image-grid img')[index];
    const rect = image.getBoundingClientRect();
    const imageTop = rect.top;
    const imageCenterX = rect.left + rect.width / 2;

    flyingEmoji.style.left = imageCenterX + 'px';
    flyingEmoji.style.top = imageTop + 'px';

    setTimeout(() => {
      flyingEmoji.style.animation = 'flyToTop 2s linear forwards';
      setTimeout(() => {
        flyingEmoji.remove();
      }, 2000);
    }, 500);
  };

  const generateGraph = () => {
    const ctx = document.getElementById('userChart');
    if (!ctx) return;

    if (window.userChart instanceof Chart) {
      window.userChart.destroy();
    }

    window.userChart = new Chart(ctx, {
      type: 'bar',
      data: {
        labels: ['Image 1', 'Image 2', 'Image 3', 'Theme Changes'],
        datasets: [
          {
            label: 'User interactions!',
            data: [...likes, themeChangeCount],
            backgroundColor: [
              'rgba(255, 99, 132, 0.2)',
              'rgba(54, 162, 235, 0.2)',
              'rgba(255, 206, 86, 0.2)',
              'rgba(75, 192, 192, 0.2)',
            ],
            borderColor: [
              'rgba(255,99,132,1)',
              'rgba(54, 162, 235, 1)',
              'rgba(255, 206, 86, 1)',
              'rgba(75, 192, 192, 1)',
            ],
            borderWidth: 1,
          },
        ],
      },
      options: {
        scales: {
          y: {
            beginAtZero: true,
            max: 20,
          },
        },
      },
    });
  };

  const toggleGraphVisibility = () => {
    setIsGraphVisible((prev) => !prev);
  };

  const handleFilterChange = (filter) => {
    setFilter(filter);
    switch (filter) {
      case 'all':
        setEnlargedImages([0, 1, 2]);
        break;
      case '2023':
        setEnlargedImages([0]);
        break;
      case '2024':
        setEnlargedImages([1, 2]);
        break;
      default:
        break;
    }
  };

  const scrollToPhotos = () => {
    if (photosRef.current) {
      photosRef.current.scrollIntoView({ behavior: 'smooth' });
    }
  };

  // Function to fetch weather data
  const fetchWeather = async () => {
    try {
      const response = await fetch('https://api.openweathermap.org/data/2.5/weather?q=Statesboro,US&appid=7e9eac7a1d654d79f9e0dd2e47bf241e&units=imperial');
      
      if (!response.ok) {
        throw new Error('Failed to fetch weather data');
      }
      
      const data = await response.json();
      console.log("Weather data:", data); // Add this line to log the weather data
      setWeather(data);
    } catch (error) {
      console.error('Error fetching weather data:', error);
    }
  };

  return (
    <Router>
      <div className={`App ${theme}`}>
        <header>
          <h1>Ethan Jones</h1>
          <div className="theme-buttons">
            <button onClick={() => handleThemeChange('royal-blue')}>Royal Blue</button>
            <button onClick={() => handleThemeChange('pikachu')}>Pikachu</button>
            <button onClick={() => handleThemeChange('shadow')}>Shadow</button>
          </div>
          <button className="reset-button" onClick={() => handleThemeChange('')}>Reset</button>
        </header>

        <nav id="scrollLinks">
          <Link to="#photos" onClick={scrollToPhotos}>Photos</Link>
          <Link to="#showUserInteractions" onClick={toggleGraphVisibility}>Graph</Link>
          <Link to="/skills">Skills</Link>
        </nav>

        <main>
          <div className="center-content">
            <h2>About Me</h2>
            <p>
              Salutations, I am a distinguished Senior Computer Science major, deeply committed to the realm of software engineering...
            </p>
            <p>
              I am studying Computer Science at none other than Georgia Southern University! Being in the Association of Computing Machinery has taught me so much! You can select the Royal Blue, Pikachu or Shadow themes by clicking their themes above. My personal favorite is the Shadow theme. I adore the amount of enjoyable learning I have in my free time. Follow my journey through code by connecting with me on <a href="https://www.linkedin.com/in/ethan-jones-8290262b2/" style={{ color: 'red', textDecoration: 'underline' }} target="_blank" rel="noopener noreferrer">LinkedIn</a>! Thank you very much for visiting my personal website.
            </p>
            <p>
              <Link to="/skills" style={{ color: 'blue', textDecoration: 'underline' }}>Click this link to learn more about my diverse skillset!</Link>
            </p>
          </div>

          <div className="image-grid" id="photos" ref={photosRef}>
            <div>
              <img src="https://i.imgur.com/QHuuBZ8.jpg" alt="Ethan image 1" onClick={() => handleImageClick(0)} className={enlargedImages.includes(0) ? 'enlarged' : ''} />
              <button className="likes-button">💯 {likes[0]}</button>
            </div>
            <div>
              <img src="https://i.imgur.com/vpY8PPr.jpg" alt="Ethan image 2" onClick={() => handleImageClick(1)} className={enlargedImages.includes(1) ? 'enlarged' : ''} />
              <button className="likes-button">💯 {likes[1]}</button>
            </div>
            <div>
              <img src="https://i.imgur.com/qZP3t2L.jpg" alt="Ethan image 3" onClick={() => handleImageClick(2)} className={enlargedImages.includes(2) ? 'enlarged' : ''} />
              <button className="likes-button">💯 {likes[2]}</button>
            </div>
          </div>

          <div id="imageFilters">
            <button className={`filter-button ${filter === 'all' ? 'active' : ''}`} onClick={() => handleFilterChange('all')}>All</button>
            <button className={`filter-button ${filter === '2023' ? 'active' : ''}`} onClick={() => handleFilterChange('2023')}>2023</button>
            <button className={`filter-button ${filter === '2024' ? 'active' : ''}`} onClick={() => handleFilterChange('2024')}>2024</button>
          </div>

          <button id="showUserInteractions" onClick={toggleGraphVisibility}>{isGraphVisible ? 'Hide' : 'Show'} User Interactions</button>
          <canvas id="userChart" style={{ display: isGraphVisible ? 'block' : 'none' }}></canvas>
        </main>

        {/* Conditional rendering for weather info */}
        <div className="weather-info">
          <p>Weather in Statesboro:</p>
          {weather && weather.weather && weather.main ? (
            <>
              <p>{weather.weather[0].description}</p>
              <p>Temperature: {weather.main.temp}°C</p>
              {/* Add more weather details as needed */}
            </>
          ) : (
            <p>Loading weather data...</p>
          )}
        </div>

        <Routes>
          <Route path="/skills" element={<SkillsPage />} />
        </Routes>
        <ContactForm />
        <footer>
          &copy; 2024 Ethan Jones | Contact: ej06939@georgiasouthern.edu | Phone: (404) 751-8752
        </footer>
      </div>
    </Router>
  );
}

export default App;
