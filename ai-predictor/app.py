import os

os.environ["OPENBLAS_NUM_THREADS"] = "1"
os.environ["OMP_NUM_THREADS"] = "1"
os.environ["MKL_NUM_THREADS"] = "1"
from fastapi import FastAPI
from pydantic import BaseModel
import joblib

app = FastAPI()

model = joblib.load("model.pkl")


class FeatureVector(BaseModel):

    avgCpu: float
    maxCpu: float
    avgMemory: float
    avgLatency: float
    failureCount: int
    riskScore: int


@app.get("/")
def home():

    return {
        "service": "AI Predictor",
        "status": "UP"
    }


@app.post("/predict")
def predict(feature: FeatureVector):

    values = [[

        feature.avgCpu,

        feature.maxCpu,

        feature.avgMemory,

        feature.avgLatency,

        feature.failureCount,

        feature.riskScore

    ]]

    prediction = model.predict(values)[0]

    probability = model.predict_proba(values)[0][1]

    return {

        "prediction": int(prediction),

        "confidence": round(float(probability) * 100, 2),

        "decision":

            "RECOVERY_REQUIRED"

            if prediction == 1

            else

            "SYSTEM_HEALTHY"

    }