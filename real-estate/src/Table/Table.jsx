import React from "react";
import "./Table.css";

function Table() {
  const data = [
    { id:"KPHB",name:"9,300", age: 2, city:"87.9% YOY" },
    {  id:"Champapet",name: "5,900", age:"NA",city:"51.3% YOY" },
    { id:"Kothaguda",name:"10,000" , age: "NA", city: "44.4% YOY" },
    { id:"Hyder Nagar",name: "7,600", age:"NA", city: "42.1% YOY" },
    { id:"Habsiguda",name: "6,650", age: "NA", city: "33% YOY" },
  ];

  return (
    <div className="main-con">
    
    <table className="table" border={1} align="center" cellPadding={30} width={947}>
        <thead>
          <tr>
            <th>Locality </th>
            <th>Rate on 99acres</th>
            <th>Rental Yield</th>
            <th>Price Trends</th>
          </tr>
        </thead>
    


        <tbody>
          {data.map((item,index) => (
            <tr key={index} className="text-center">
              <td className="">{item.id}</td>
              <td className="">{item.name}</td>
              <td className="">{item.age}</td>
              <td className="">{item.city}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Table;
