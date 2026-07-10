function PlatformStatus({status}){

    const services=[

        {name:"API Gateway",healthy:true},

        {name:"Service Monitor",healthy:true},

        {name:"Recovery Engine",healthy:true},

        {name:"AI Predictor",healthy:status==="UP"}

    ];

    return(

        <div className="platform-status">

            <h3>Platform Status</h3>

            {

                services.map(service=>(

                    <p key={service.name}>

                        {service.healthy?"🟢":"🔴"}

                        {" "}

                        {service.name}

                    </p>

                ))

            }

        </div>

    );

}

export default PlatformStatus;