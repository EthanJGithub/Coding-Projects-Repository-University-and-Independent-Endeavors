import React from 'react';

const ContactForm = () => {
  return (
    <div>
      <h2>Contact Ethan</h2>
      <form action="https://formspree.io/f/xzbnveln" method="POST">
        <label htmlFor="name">Name:</label>
        <input type="text" id="name" name="name" required />
        
        <label htmlFor="email">Email:</label>
        <input type="email" id="email" name="email" required />
        
        <label htmlFor="message">Message:</label>
        <textarea id="message" name="message" required></textarea>
        
        <button type="submit">Submit</button>
      </form>
    </div>
  );
};

export default ContactForm;
