import { Link } from "react-router-dom";

function Navbar(){

    return(

        <div className="navbar">

            <h2>ResiliSys AI</h2>

            <div>

                <Link to="/">Dashboard</Link>

                {" | "}

                <Link to="/failure">Failure Simulator</Link>

                {" | "}

                <Link to="/logs">Recovery Logs</Link>

            </div>

        </div>

    );

}

export default Navbar;