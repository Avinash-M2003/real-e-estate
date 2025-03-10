import React, { useState } from 'react'
import "./Service.css"
import image from "../assets/Images/image.png";
import image2 from "../assets/Images/image2.webp"
import image3 from "../assets/Images/image3.webp"
import image4 from "../assets/Images/image4.webp"
import image5 from "../assets/Images/image5.webp"
import image6 from "../assets/Images/image6.webp"
const Services = () => {

    const [data,setData]=useState( [
          {
            "image":`${image2}`,
            "category": "Buying a home",
            "types": [
              "Apartments",
              "Land",
              "Builder floors",
              "Villas and more"
            ]
          },
          {
            "image":`${image3}`,
            "category": "Buying a commercial property",
            "types": [
              "Shops",
              "Offices",
              "Land",
              "Factories",
              "Warehouses and more"
            ]
          },
          {
            "image":`${image4}`,
            "category": "Renting a home",
            "types": [
              "Apartments",
              "Builder floors",
              "Villas and more"
            ]
          },
          {
            "image":`${image5}`,
            "category": "Leasing a commercial property",
            "types": [
              "Shops",
              "Offices",
              "Land",
              "Factories",
              "Warehouses and more"
            ]
          },
          {
            "image":`${image6}`,
            "category": "Buy Plots/Land",
            "types": [
              "Residential Plots",
              "Agricultural Farm lands",
              "Institutional Lands and more"
            ]
          }
        ]
      
      )

    const background = {
        backgroundImage: `url(${image})`,
        backgroundSize: 'cover',
        // backgroundPosition: 'center',
        height: '300px', 
        width: '1260px', 
      };
  return (
    <div className="serv">
        <div className="serv1" style={background}>
            <div className="serv1-div1">99acres</div>
            <div className="serv1-div2">Explore our services</div>
        </div>
        <div className="serv2">
            {
                data.map((element,index)=>{
                    return(
                        <div className='serv2-1' key={index}>
                            <div className='serv2-1-img'><img src={element.image} alt="" className='serv2-1-img'/></div>
                            <div className='serv2-1-div'>
                                <div className='serv2-1-div1'>{element.category}</div>
                                <div className='serv2-1-div2'>{element.types.join(', ')}</div>
                            </div>
                        </div>
                    )
                })
            }
        </div>
    </div>
  )
}

export default Services