import { useEffect, useState } from "react";

import api from "../services/api";

import MetricCard from "../components/MetricCard";
import RecoveryTable from "../components/RecoveryTable";
import PlatformStatus from "../components/PlatformStatus";
import FailurePreview from "../components/FailurePreview";

function Dashboard() {

    const [metrics, setMetrics] = useState({});
    const [prediction, setPrediction] = useState({});
    const [features, setFeatures] = useState({});
    const [failureType, setFailureType] = useState("cpu");

    useEffect(() => {

        loadData();

        const timer = setInterval(loadData, 3000);

        return () => clearInterval(timer);

    }, []);

    async function loadData() {

        try {

            const monitor = await api.get("/monitor/metrics");
            setMetrics(monitor.data);

        } catch (e) {

            console.log(e);

        }

        try {

            const ai = await api.get("/metrics/prediction");
            setPrediction(ai.data);

        } catch (e) {

            console.log(e);

        }

        try {

            const feature = await api.get("/metrics/ai-features");
            setFeatures(feature.data);

        } catch (e) {

            console.log(e);

        }

    }

    return (

        <div className="container">

            {/* ===================== TOP METRICS ===================== */}

            <div className="metric-grid">

                <MetricCard
                    title="CPU Usage"
                    value={`${metrics.cpu ?? 0} %`}
                />

                <MetricCard
                    title="Memory Usage"
                    value={`${metrics.memory ?? 0} %`}
                />

                <MetricCard
                    title="Latency"
                    value={`${metrics.latency ?? 0} ms`}
                />

                <MetricCard
                    title="System Status"
                    value={metrics.status ?? "Loading..."}
                />

            </div>

            {/* ===================== MIDDLE ROW ===================== */}

            <div className="middle-grid">

                {/* ---------- AI Prediction ---------- */}

                <div className="prediction">

                    <div className="split-card">

                        <div>

                            <h2>AI Prediction</h2>

                            <h1>

                                {prediction.decision ?? "Loading..."}

                            </h1>

                            <p>

                                Confidence :

                                <b>

                                    {" "}

                                    {prediction.confidence ?? 0} %

                                </b>

                            </p>

                            <p>

                                Risk Score :

                                <b>

                                    {" "}

                                    {features.riskScore ?? 0}

                                </b>

                            </p>

                        </div>

                        <PlatformStatus
                            status={metrics.status}
                        />

                    </div>

                </div>

                {/* ---------- Failure Injection ---------- */}

                <div className="failure-console">

                    <div className="split-card">

                        <div>

                            <h2>Failure Injection</h2>

                            <label>

                                Failure Type

                            </label>

                            <select

                                value={failureType}

                                onChange={(e) =>
                                    setFailureType(e.target.value)
                                }

                            >

                                <option value="cpu">

                                    🔥 CPU Spike

                                </option>

                                <option value="latency">

                                    🐢 Latency

                                </option>

                                <option disabled>

                                    💥 Crash (Coming Soon)

                                </option>

                                <option disabled>

                                    💾 Memory Leak (Coming Soon)

                                </option>

                                <option disabled>

                                    🌐 Network Failure (Coming Soon)

                                </option>

                                <option disabled>

                                    📦 Disk Failure (Coming Soon)

                                </option>

                            </select>

                            <button
                                className="inject-btn"
                            >

                                Inject Failure

                            </button>

                        </div>

                        <FailurePreview
                            type={failureType}
                        />

                    </div>

                </div>

            </div>

            {/* ===================== RECOVERY TABLE ===================== */}

            <RecoveryTable />

        </div>

    );

}

export default Dashboard;