import { BrowserRouter, Routes, Route } from "react-router-dom";

import Navbar from "./components/Navbar";

import Dashboard from "./pages/Dashboard";
import FailureSimulator from "./pages/FailureSimulator";
import RecoveryLogs from "./pages/RecoveryLogs";

function App() {

    return (

        <BrowserRouter>

            <Navbar />

            <Routes>

                <Route
                    path="/"
                    element={<Dashboard />}
                />

                <Route
                    path="/failure"
                    element={<FailureSimulator />}
                />

                <Route
                    path="/logs"
                    element={<RecoveryLogs />}
                />

            </Routes>

        </BrowserRouter>

    );

}

export default App;