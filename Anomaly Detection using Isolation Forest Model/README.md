# Anomaly Detection Using Isolation Forest

This project leverages the Isolation Forest algorithm to detect anomalies in time-series data. It preprocesses the data, identifies anomalies, visualizes the results, and generates reports.

## Features
- Preprocessing of time-series data
- Anomaly detection using the Isolation Forest algorithm
- Visualization of anomalies
- Generation of processed data and anomaly reports

Key Parameters
Contamination Level: Adjusted via the contamination parameter in the code (default is 0.02).

---

## Prerequisites
Before running the project, ensure you have the following installed:
- Python 3.8 or later
- pip package manager
- Download NAB https://github.com/numenta/NAB (could use your own data)
- Assign the location of the data you would like to perform machine learning analysis on to data_path

### Required Python Libraries:
Install the necessary Python libraries using:
```bash
pip install pandas, matplotlib, scikit-learn, streamlit


### Use Cases
- This program can be applied in scenarios such as:

  - Detecting anomalies in network traffic
  - Monitoring occupancy or energy usage patterns
  - Identifying unusual trends in time-series data
