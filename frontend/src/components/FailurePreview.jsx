const preview = {

    cpu:{

        description:"Simulates high CPU utilization.",

        severity:"🔴 HIGH",

        target:"Failure Injector",

        recovery:"Restart Service"

    },

    latency:{

        description:"Introduces slow service response.",

        severity:"🟠 MEDIUM",

        target:"Failure Injector",

        recovery:"Circuit Breaker"

    }

};

function FailurePreview({type}){

    const data = preview[type];

    return(

        <div className="preview-panel">

            <h3>Live Preview</h3>

            <p><b>Description :</b> {data.description}</p>

            <p><b>Severity :</b> {data.severity}</p>

            <p><b>Target :</b> {data.target}</p>

            <p><b>Recovery :</b> {data.recovery}</p>

        </div>

    );

}

export default FailurePreview;