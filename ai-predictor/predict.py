import joblib

model = joblib.load("model.pkl")

sample = [[

    72,

    82,

    70,

    75,

    4,

    78

]]

prediction = model.predict(sample)

print(prediction)