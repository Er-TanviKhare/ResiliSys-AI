import { useEffect, useState } from "react";
import api from "../services/api";

function RecoveryLogs() {

    const [logs, setLogs] = useState([]);

    useEffect(() => {

        loadLogs();

    }, []);

    async function loadLogs() {

        try {

            const response = await api.get("/metrics/history");

            console.log("Response:", response);

            console.log("Data:", response.data);

            setLogs(response.data);

        }

        catch (error) {

            console.log("ERROR:", error);

        }

    }

    return (

        <div className="container">

            <h2>Recovery Logs</h2>

            <table>

                <thead>

                    <tr>

                        <th>Timestamp</th>

                        <th>Failure</th>

                        <th>Action</th>

                        <th>Service</th>

                        <th>Status</th>

                    </tr>

                </thead>

                <tbody>

                    {

                        logs.map((log, index) => (

                            <tr key={index}>

                                <td>{log.timestamp}</td>

                                <td>{log.failureType}</td>

                                <td>{log.recoveryAction}</td>

                                <td>{log.serviceName}</td>

                                <td>{log.status}</td>

                            </tr>

                        ))

                    }

                </tbody>

            </table>

        </div>

    );

}

export default RecoveryLogs;