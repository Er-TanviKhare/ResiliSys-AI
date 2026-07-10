import api from "../services/api";

function FailureSimulator() {

    async function injectCpu() {

        await api.post("/inject/cpu");

        alert("CPU Spike Injected");

    }

    async function injectLatency() {

        await api.post("/inject/latency");

        alert("Latency Injected");

    }

    return (

        <div className="container">

            <h1>Failure Simulation Laboratory</h1>

            <p>

                Inject failures to validate the AI-powered
                recovery capabilities of ResiliSys AI.

            </p>

            <div className="failure-grid">

                <div className="failure-card">

                    <h2>🔥 CPU Spike</h2>

                    <p>

                        Simulate high CPU utilization.

                    </p>

                    <span className="available">

                        Available

                    </span>

                    <button onClick={injectCpu}>

                        Inject

                    </button>

                </div>

                <div className="failure-card">

                    <h2>🐢 Latency</h2>

                    <p>

                        Simulate slow service response.

                    </p>

                    <span className="available">

                        Available

                    </span>

                    <button onClick={injectLatency}>

                        Inject

                    </button>

                </div>

                <div className="failure-card disabled">

                    <h2>💥 Crash Service</h2>

                    <p>

                        Planned for future release.

                    </p>

                    <span className="coming">

                        Coming Soon

                    </span>

                    <button disabled>

                        Unavailable

                    </button>

                </div>

                <div className="failure-card disabled">

                    <h2>💾 Memory Leak</h2>

                    <p>

                        Planned for future release.

                    </p>

                    <span className="coming">

                        Coming Soon

                    </span>

                    <button disabled>

                        Unavailable

                    </button>

                </div>

                <div className="failure-card disabled">

                    <h2>🌐 Network Failure</h2>

                    <p>

                        Planned for future release.

                    </p>

                    <span className="coming">

                        Coming Soon

                    </span>

                    <button disabled>

                        Unavailable

                    </button>

                </div>

                <div className="failure-card disabled">

                    <h2>📦 Disk Failure</h2>

                    <p>

                        Planned for future release.

                    </p>

                    <span className="coming">

                        Coming Soon

                    </span>

                    <button disabled>

                        Unavailable

                    </button>

                </div>

            </div>

        </div>

    );

}

export default FailureSimulator;