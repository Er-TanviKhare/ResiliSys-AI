import { useEffect, useState } from "react";
import api from "../services/api";

function RecoveryTable() {

    const [logs, setLogs] = useState([]);

    useEffect(() => {

        loadHistory();

        const timer = setInterval(loadHistory, 3000);

        return () => clearInterval(timer);

    }, []);

    async function loadHistory() {

        try {

            const response =
                await api.get("/metrics/history");

            setLogs(response.data);

        }

        catch (error) {

            console.log(error);

        }

    }

    return (

        <div className="table">

            <h2>Recovery History</h2>

            <table>

                <thead>

                    <tr>

                        <th>Time</th>
                        <th>Failure</th>
                        <th>Action</th>
                        <th>Status</th>

                    </tr>

                </thead>

                <tbody>

                    {

                        logs.slice(0,5).map((log,index)=>(

                            <tr key={index}>

                                <td>

                                    {

                                        new Date(log.timestamp)
                                            .toLocaleTimeString()

                                    }

                                </td>

                                <td>{log.failureType}</td>

                                <td>{log.recoveryAction}</td>

                                <td>{log.status}</td>

                            </tr>

                        ))

                    }

                </tbody>

            </table>

        </div>

    );

}

export default RecoveryTable;