import pandas as pd

from sklearn.ensemble import RandomForestClassifier

import joblib

data = pd.read_csv("dataset.csv")

X = data.drop("failure", axis=1)

y = data["failure"]

model = RandomForestClassifier(
    n_estimators=100,
    random_state=42
)

model.fit(X, y)

joblib.dump(model, "model.pkl")

print("Model trained successfully.")