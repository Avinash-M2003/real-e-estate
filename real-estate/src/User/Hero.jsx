import React from "react";
import Image from "../assets/Image.jpg";
import "./Hero.css";
import mic from "../assets/mic.png";
import nearMeV2 from "../assets/nearMeV2.png";
function Hero() {
  return (
    <>
      <div>
        <div className="sector">
          <img className="image" src={Image} alt="Image" />
        </div>

        <div className="Main">
          <div className="Headings">
            <div id="list">Buy</div>
            <div id="list">Rent</div>
            <div id="list">New Lunch</div>
            <div id="list">PG/Co-Living</div>
            <div id="list">Commercial</div>
            <div id="list">Plots/Lands</div>
            <div id="list">Projects</div>
          </div>

          <div className="container">
            <div className="Residentials">
              <label className="All">All Residentials</label>
            </div>

            <input className="sb" type="text" placeholder="" />

            <div className="lo">
              <div className="location">
                <img src={nearMeV2} alt="" style={{ height: 40, width: 40 }} />
              </div>

              <div className="mic">
                <img src={mic} alt="" style={{ height: 40, width: 40 }} />
              </div>

              <div className="search">
                <button className=" btn">Search</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default Hero;
