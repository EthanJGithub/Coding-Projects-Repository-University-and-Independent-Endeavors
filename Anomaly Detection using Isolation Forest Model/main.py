import os
import pandas as pd
import matplotlib
matplotlib.use("Agg")  # Use the Agg backend for non-GUI environments
import matplotlib.pyplot as plt
from sklearn.ensemble import IsolationForest

# Load the dataset
data_path = "data/NAB-master/NAB-master/data/realTraffic/occupancy_6005.csv"
if not os.path.exists(data_path):
    print(f"Error: File not found at {data_path}")
    exit()

df = pd.read_csv(data_path)

# Convert 'timestamp' to datetime
df['timestamp'] = pd.to_datetime(df['timestamp'])

# Set 'timestamp' as the index
df.set_index('timestamp', inplace=True)

# Normalize the 'value' column (optional, for models sensitive to scale)
df['value'] = (df['value'] - df['value'].mean()) / df['value'].std()

# Save preprocessed data (optional)
processed_data_path = "data/NAB-master/NAB-master/data/realTraffic/processed_occupancy_6005.csv"
df.to_csv(processed_data_path)
print(f"Preprocessed data saved to {processed_data_path}")

print("\n### Preprocessed Data Head ###")
print(df.head())

# Display the first few rows of the dataset
print("Dataset Head:")
print(df.head())

# Inspect dataset information
print("\n### Dataset Info ###")
print(df.info())

# Summary statistics
print("\n### Summary Statistics ###")
print(df.describe())

# Train the Isolation Forest model
contamination = 0.02  # Adjust contamination as needed
print(f"Using contamination level: {contamination}")
model = IsolationForest(contamination=contamination)
df['anomaly'] = model.fit_predict(df[['value']])

# Save anomalies to a file
anomalies = df[df['anomaly'] == -1]
anomalies_path = "data/NAB-master/NAB-master/data/realTraffic/anomalies_occupancy_6005.csv"
anomalies.to_csv(anomalies_path)
print(f"Anomalies saved as {anomalies_path}")

# Visualize anomalies
plt.figure(figsize=(10, 5))
plt.plot(df.index, df['value'], label='Value')
plt.scatter(anomalies.index, anomalies['value'], color='red', label='Anomalies')
plt.title("Detected Anomalies")
plt.xlabel("Timestamp")
plt.ylabel("Value")
plt.legend()
plot_path = "anomalies_plot.png"
plt.savefig(plot_path)  # Save the plot as an image
print(f"Plot saved as {plot_path}")

print("\nProcess completed successfully! Anomalies have been detected and saved.")
