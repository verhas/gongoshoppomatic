# Presale Case: Auction
 
## The customer
 
Shoppomatic — a big e-commerce company that runs online c2c shopping platform (usually it's second-hand and used goods). They are planning to expand their business by creating new trading opportunities for the platform audience — online auction portal "GonGo".
 
## Users
 
Registered users with verified badge (an auction owner) should have the ability to schedule a new auction for an item they want to sell and set up auction parameters (auction type, date/time, initial minimum bid, and other options). All registered users may apply for a scheduled auction if they want to buy something. The auction flow may vary dramatically, and it depends on the auction type.
 
## Requirements
 
- Auctions must be categorized and "discoverable";
- Auctions must be as real-time as possible;
- The auction portal should be able to handle automatic money exchange to give the users an ability to pay in their local currencies;
- The auction portal should have a reputation functionality for all registered users (both for auction owners and auction participants):
 * Reputation index model will include number of deals; shipment delay; payment reliability; number of cancelled deals;
 * Reputation index calculation logic may be updated or replaced in the future. The customer expects that his business analytics will be able to support that feature;
- Auctions should have an option to be enabled or disables in certain regions and countries;
- Scalability: scale up to hundreds of participants (per auction), potentially up to thousands of participants, and as many simultaneous auctions as possible;
- Dashboard: the portal management expects to see some statistics on the dashboard (avg. bid price, avg. sell price, etc);
- Chatbot: the customer thinks that it would be a good idea to give registered users an ability to communicate with the portal using chatbot services not only for managing auctions and making bids, but also for receiving notifications about new auctions on a certain category.
 
## Additional context
 
- No budget limits — this is a strategic direction;
- The company is expanding aggressively by merging with smaller competitors — think about REST API for mergers;
- The company has plans to start in one country only, but if the auction portal will have a success, they are going to replicate the model overseas;
- The company just exited a lawsuit where they settled a suit alleging fraud.
 
## Technical considerations
 
- The customer has plans to create a cloud-native solution. A customer architect is not sure that cloud provider is best for him, but he expects to have all required environments, CI/CD pipelines, databases in the cloud of the one of top cloud vendors;
- Preferable platform is Java (Java language or any JVM-based language); JavaScript or TypeScript for front-end app;
- The customer expects our solution will have no 3rd-party components; anyway, we can propose such components to him, but this will require an additional discussion with the customer;
 
## Expectations
 
- You have to create a solution design with necessary diagrams and documentation. The solutions should satisfy the provided requirements;
- We have a scheduled meeting with the customer people — project managers, the architect and a technical lead;
 * They expect to see ready-to-use prototype that is deployed in the cloud with implemented key features;
 * They will definitely ask technical questions, and we expect a discussion regarding the solution details and technical implementation;
 * Be prepared for live-coding — they may ask you to implement a new feature on the fly;
- The customer's architect did its own analysis and designed a high-level solution; we know, that the architect and the technical lead proposed to use Kafka, Sprint Boot and an enterprise RDBMS (Oracle or MS SQL);
- You can use an open dataset for your prototype. Example — https://data.world/datafiniti/electronic-products-and-pricing-data;
- Our presale team will be ready to help you in case of any questions.