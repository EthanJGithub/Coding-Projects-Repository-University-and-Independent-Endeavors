document.addEventListener('DOMContentLoaded', function () {
    const showGraphButton = document.getElementById('showGraph');
    const hideGraphButton = document.getElementById('hideGraph');
    const userChart = document.getElementById('userChart');
    const resetButton = document.getElementById('homeButton');
    const themeButtons = document.querySelectorAll('.theme-buttons button');

    let myChart;
    let themeChangeCount = 0;
    let images = document.querySelectorAll('.image-grid img');

    userChart.style.display = 'none'; // Hide the graph by default
    hideGraphButton.style.display = 'none'; // Hide the hide button by default

    function setTheme(theme) {
        document.body.className = theme + "-theme";
    }

    function resetTheme() {
        document.body.className = "";
    }

    function incrementThemeChangeCount() {
        themeChangeCount++;
    }

    function showGraph() {
        userChart.style.display = 'block';
        hideGraphButton.style.display = 'block';
        showGraphButton.style.display = 'none';
        hideGraphButton.textContent = 'Hide User Interactions'; // Change button text
        generateGraph();
        userChart.scrollIntoView({ behavior: 'smooth' });
    }

    function hideGraph() {
        userChart.style.display = 'none';
        hideGraphButton.style.display = 'none';
        showGraphButton.style.display = 'block';
        showGraphButton.textContent = 'Show User Interactions'; // Change button text
    }

    showGraphButton.addEventListener('click', showGraph);
    hideGraphButton.addEventListener('click', hideGraph);

    const graphSmoothScrollItem = document.querySelector('#scrollLinks a[href="#graph"]');
    graphSmoothScrollItem.addEventListener('click', function (event) {
        event.preventDefault();
        showGraph();
    });

    function updateChart() {
        if (myChart) {
            myChart.destroy();
        }
        generateGraph();
    }

    function generateGraph() {
        const ctx = userChart.getContext('2d');
        myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['Image 1', 'Image 2', 'Image 3', 'Theme Changes'], // Added label for theme changes
                datasets: [{
                    label: 'User interactions!',
                    data: [
                        parseInt(images[0].getAttribute('data-likes')),
                        parseInt(images[1].getAttribute('data-likes')),
                        parseInt(images[2].getAttribute('data-likes')),
                        themeChangeCount
                    ],
		
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255,99,132,1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true,
                        max: 20 // Change the maximum value to 20
                    }
                }
            }
        });
    }

    images.forEach((image, index) => {
        image.addEventListener('click', function () {
            if (image.classList.contains('enlarged')) {
                image.classList.remove('enlarged');
            } else {
                images.forEach(img => img.classList.remove('enlarged'));
                image.classList.add('enlarged');
            }
            handleLikes(image);
            image.style.animation = 'spin 0.5s linear forwards';
            setTimeout(() => {
                image.style.animation = '';
            }, 500);

            const flyingEmoji = document.createElement('div');
            flyingEmoji.textContent = '💯';
            flyingEmoji.classList.add('flying-emoji');
            document.body.appendChild(flyingEmoji);

            const rect = image.getBoundingClientRect();
            const imageTop = rect.top;
            const imageCenterX = rect.left + rect.width / 2;

            flyingEmoji.style.left = imageCenterX + 'px';
            flyingEmoji.style.top = imageTop + 'px';

            setTimeout(() => {
                flyingEmoji.style.animation = 'flyToTop 2s linear forwards'; // Adjust animation duration
                setTimeout(() => {
                    flyingEmoji.remove();
                }, 2000); // Adjust delay before removing the emoji
            }, 500);
        });
    });

    function handleLikes(image) {
        let likesCount = parseInt(image.getAttribute('data-likes')) || 0;
        likesCount++;
        image.setAttribute('data-likes', likesCount);
        const likesDisplay = image.nextElementSibling;
        likesDisplay.textContent = '💯 ' + likesCount;
        updateChart();
    }

    themeButtons.forEach(button => {
        button.addEventListener('click', function () {
            let theme = button.textContent.toLowerCase();
            if (theme === 'royal blue') {
                theme = 'royal-blue';
            }
            setTheme(theme);
            incrementThemeChangeCount();
            updateChart();
        });
    });

    resetButton.addEventListener('click', function () {
        resetTheme();
        images.forEach(image => {
            image.setAttribute('data-likes', 0);
            const likesDisplay = image.nextElementSibling;
            likesDisplay.textContent = '💯 0';
        });
        themeChangeCount = 0;
        updateChart();
    });

    const categoryAButton = document.getElementById('categoryA');
    const categoryBButton = document.getElementById('categoryB');
    const allButton = document.getElementById('allButton');

    categoryAButton.addEventListener('click', function () {
        images.forEach((image, index) => {
            if (index < 2) {
                image.classList.toggle('enlarged');
            }
        });
    });

    categoryBButton.addEventListener('click', function () {
        images[2].classList.toggle('enlarged');
    });

    allButton.addEventListener('click', function () {
        images.forEach(image => {
            image.classList.toggle('enlarged');
        });
    });
});
