# Clarification Questions and Answers

questions and answers during assessment preparation 2020-06-10

1) "What does it mean that a registered user has a verified badge?" - "verified badge" is a kind of mark that facebook
or twitter have. the badge shows other users that your account is verified by a service provider, and it guarantees that
you are the real person. see https://help.twitter.com/en/managing-your-account/about-twitter-verified-accounts

2) "Is there a list of types, or we just go with some predefined sample type and design the system so that it can later
incorporate other types?" - we don't have an exact list of options right now, and we don't have to implement all of them
during prototyping. we just need a prototype that can show that we can build the system with specified requirements.

3) "Date time: assumed 1sec precision should be enough." - we don't know, and it seems the customer don't know too

4) "Do we have predefined set of categories available?" - not yet. our research shows that the customer has pretty
complex taxonomy on his existed website, more complex then hierarchical tree

5) "Can you provide accepted response times and statistical measures for the response time variance? Like 90% should be
accepted in less than 2sec and no big should be processed for more than 30sec." - don't think we can have such
requirements right now. The requirements are no so strict as for HFT systems, but the customer want to get as much as
possible from what he can have

6) "Currencies do not have fixed exchange rate. The rate changes by time. This inheretnly creates a financial risk
related to the transactions. Is there a strategy who will bear this risk?" - good catch! could you, please, mention that
during our talk with the customer? also, we need to propose different options to let the customer choose the best option
for him

7) "What kind of interface does business analytics have? Are they doing the calculation on-line or in a daily batch
using file exchange?" - we need to discuss these options with the customer. I think they will use a schedule for running
batch calculations, but they definitely need an option to re-calculate the index for past periods.

8) "There are many other possibilities. How is a merged auction site cooperation envisioned?" - let's consider
different options. it is possible to create an API for auction portal that mergers can use? also, we should think about
securing this API

9) "I assume that the preferred way is not to duplicate all the installation on a different instance and run the
application in multiple instances." - sounds good, but we will have to explain this to the customer. also, don't forget
about potential legislation issues (ex. personal data storage restrictions).

10) "What functional requirement consequences are there of this past event? What was the kind of the alleged fraud?" -
it seems that the company management is worrying regarding potential fraud issues, and there definitely will be
questions regarding system security and preventing frauds.

11) "Assumption is that certified auditable transaction history is a feature that the company would appreciate." -
having secured and reliable transation log is must have feature, that's for sure

12) "Presales team: Red Flag! Customer architect is mixing the operational and the development cloud functionality.
Smells." - shame on him! but we know he works in the customer company for a long time and the management trusts him.
that means we will have a long discussion, and we should be prepared for hard technical questions



