import React, { useState } from 'react'
import "./Home.css" 
const HomePage = () => {
  const [currentIndex, setCurrentIndex] = useState(0);
  const [data,setData]=useState([
    {
      "id": 1,
      "title": "Luxury Apartment in Downtown",
      "location": "Mumbai, Maharashtra",
      "price": 12000000,
      "area_sq_ft": 1500,
      "bedrooms": 3,
      "bathrooms": 2,
      "amenities": ["Gym", "Swimming Pool", "Parking", "24/7 Security"],
      "image_url": "https://images.pexels.com/photos/10827224/pexels-photo-10827224.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
    },
    {
      "id": 2,
      "title": "Cozy Cottage by the Beach",
      "location": "Goa, India",
      "price": 8500000,
      "area_sq_ft": 1200,
      "bedrooms": 2,
      "bathrooms": 1,
      "amenities": ["Beach Access", "Garden", "Parking"],
      "image_url": "https://images.pexels.com/photos/28272345/pexels-photo-28272345/free-photo-of-a-home-with-brick-walkway-and-red-brick-driveway.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load"
    },
    {
      "id": 3,
      "title": "Spacious Villa with Private Pool",
      "location": "Bangalore, Karnataka",
      "price": 25000000,
      "area_sq_ft": 3000,
      "bedrooms": 4,
      "bathrooms": 3,
      "amenities": ["Private Pool", "Garden", "Garage", "24/7 Security"],
      "image_url": "https://images.pexels.com/photos/6422928/pexels-photo-6422928.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load"
    },
    {
      "id": 4,
      "title": "Modern Studio Apartment",
      "location": "Delhi, India",
      "price": 5500000,
      "area_sq_ft": 600,
      "bedrooms": 1,
      "bathrooms": 1,
      "amenities": ["Gym", "Parking", "24/7 Security"],
      "image_url": "https://images.pexels.com/photos/20507713/pexels-photo-20507713/free-photo-of-house-with-garage.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load"
    },
    {
      "id": 5,
      "title": "Charming House in Suburbs",
      "location": "Chennai, Tamil Nadu",
      "price": 10000000,
      "area_sq_ft": 1800,
      "bedrooms": 3,
      "bathrooms": 2,
      "amenities": ["Garden", "Parking", "24/7 Security"],
      "image_url": "https://images.pexels.com/photos/17731034/pexels-photo-17731034/free-photo-of-a-gray-house-with-white-trim-and-gray-siding.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load"
    },
    {
      "id": 1,
      "title": "Luxury Apartment in Downtown",
      "location": "Mumbai, Maharashtra",
      "price": 12000000,
      "area_sq_ft": 1500,
      "bedrooms": 3,
      "bathrooms": 2,
      "amenities": ["Gym", "Swimming Pool", "Parking", "24/7 Security"],
      "image_url": "https://images.pexels.com/photos/10827224/pexels-photo-10827224.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
    },
    {
      "id": 2,
      "title": "Cozy Cottage by the Beach",
      "location": "Goa, India",
      "price": 8500000,
      "area_sq_ft": 1200,
      "bedrooms": 2,
      "bathrooms": 1,
      "amenities": ["Beach Access", "Garden", "Parking"],
      "image_url": "https://images.pexels.com/photos/28272345/pexels-photo-28272345/free-photo-of-a-home-with-brick-walkway-and-red-brick-driveway.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load"
    },
    {
      "id": 3,
      "title": "Spacious Villa with Private Pool",
      "location": "Bangalore, Karnataka",
      "price": 25000000,
      "area_sq_ft": 3000,
      "bedrooms": 4,
      "bathrooms": 3,
      "amenities": ["Private Pool", "Garden", "Garage", "24/7 Security"],
      "image_url": "https://images.pexels.com/photos/6422928/pexels-photo-6422928.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load"
    },
    {
      "id": 4,
      "title": "Modern Studio Apartment",
      "location": "Delhi, India",
      "price": 5500000,
      "area_sq_ft": 600,
      "bedrooms": 1,
      "bathrooms": 1,
      "amenities": ["Gym", "Parking", "24/7 Security"],
      "image_url": "https://images.pexels.com/photos/20507713/pexels-photo-20507713/free-photo-of-house-with-garage.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load"
    },
    {
      "id": 5,
      "title": "Charming House in Suburbs",
      "location": "Chennai, Tamil Nadu",
      "price": 10000000,
      "area_sq_ft": 1800,
      "bedrooms": 3,
      "bathrooms": 2,
      "amenities": ["Garden", "Parking", "24/7 Security"],
      "image_url": "https://images.pexels.com/photos/17731034/pexels-photo-17731034/free-photo-of-a-gray-house-with-white-trim-and-gray-siding.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load"
    }
    
  ]
  );

  const handleLeftClick = () => {
    if (currentIndex > 0) {
        setCurrentIndex(currentIndex - 1);
    }
};

const handleRightClick = () => {
    if (currentIndex < data.length - 1) {
        setCurrentIndex(currentIndex + 1);
    }
};

return (
  <div className="carousel-main">
      <h3 className="lap-text">The latest Let them unwrap Airpods</h3>
      <h4>DEMO</h4>
      <div className="carousel-container">
          <div className="nav-button" id="left-button" onClick={handleLeftClick}>
              <span className="arrow">&lt;</span>
          </div>
          <div className="carousel">
              {data.map((element, index) => (
                  <div
                      id="child"
                      key={index}
                      className={index === currentIndex ? "active" : ""}
                      style={{
                          transform: `translateX(-${currentIndex * 100}%)`,
                          width: '300px', 
                          height: '90%',
                      }}
                  >
                      <div
                          className="image"
                          style={{
                              backgroundImage: `url(${element.image_url})`,
                              backgroundSize: 'cover',  // This will ensure the image covers the container
                              backgroundPosition: 'center',
                              backgroundRepeat: 'no-repeat',
                              height: '90%',  // Adjust as needed
                              backgroundColor: 'white',
                              margin: '10px',
                          }}
                      ></div>
                      <div className="details" style={{ padding: '10px' }}>
                          <h3 style={{ margin: '5px 0', color: 'black', fontFamily: 'Helvetica, Helvetica, Arial, sans-serif' }}>
                              {element.title}
                          </h3>
                          <p style={{ margin: '5px 0', fontSize: '16px', color: 'black' }}>Price: ₹{element.price}</p>
                          <p style={{ margin: '5px 0', fontSize: '14px', color: 'black' }}>Location: {element.location}</p>
                          <p style={{ margin: '5px 0', fontSize: '14px', color: 'black' }}>Area: {element.area_sq_ft} sq ft</p>
                          <p style={{ margin: '5px 0', fontSize: '14px', color: 'black' }}>Bedrooms: {element.bedrooms}</p>
                          <p style={{ margin: '5px 0', fontSize: '14px', color: 'black' }}>Bathrooms: {element.bathrooms}</p>
                          {/* <p style={{ margin: '5px 0', fontSize: '14px', color: 'black' }}>Amenities: {element.amenities.join(', ')}</p> */}
                      </div>
                  </div>
              ))}
          </div>
          <div className="nav-button" id="right-button" onClick={handleRightClick}>
              <span className="arrow">&gt;</span>
          </div>
      </div>
  </div>
);

}

export default HomePage