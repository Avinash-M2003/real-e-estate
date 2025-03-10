import React from "react";
import "./Dash.css";
import Photo from "../assets/Photo.png";

function Dash() {
  return (
    <>
      <div id="big-one">
        <div className="sector-1">
          <div className="second-container">
            <div>
              <h3 id="level-1">SELL OR RENT YOUR PROPERTY</h3>
            </div>
            <div id="space"></div>
            <div className="level-2">
              Register to post your property for Free
            </div>
            <div className="level-3">
              Post your residential / commercial property
            </div>

            {/* boxes */}

            <div id="numbers">
              <div className="box">
                <div className="num">10L+</div>
                <div id="one">Property Listings</div>
              </div>

              <div className="box">
                <div className="num">45L+</div>
                <div id="one">Monthly Searches</div>
              </div>

              <div className="box">
                <div className="num">2L+</div>
                <div id="two">Owners advertise Monthly</div>
              </div>
            </div>
            <div>
              <button id="btn">
                <span id="sp">Post Your property for FREE</span>
              </button>
            </div>
            <div id="ft">
          <h5>or post via whatsapp, send a "hi" to +123456789</h5>
            </div>
          </div>

          {/* first-container */}
          <div className="first-container">
            <img className="photo" src={Photo} alt="photo" />
          </div>
        </div>
      </div>
    </>
  );
}

export default Dash;
