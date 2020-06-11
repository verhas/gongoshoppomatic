# GonGo Shoppomatic

This is a simple PoC application to the EPAM ASMT process for Advanced Engineering
Assessment. Peter Verhas 2020-06-11
 
The structure of the repository:

- DOCUMENTATION contains documentation files
    - [Architecture document with diagrams](DOCUMENTATION/ARCHITECTURE.adoc)
    - [Requirements](DOCUMENTATION/REQUIREMENTS.md) (unedited, original)
    - [Requirement Analysis](DOCUMENTATION/REQUIREMENT_ANALYSIS.md) (rudimentary)
- root directory contains the projects for the proof of concept application
    - `gongo-api` core classes, data models
    - `gongo-persistence` simple persistence interface (only save and load entities)
    - `gongo-persistence-memory` in-memory implementation of the persistence interface, early
      development, not used in the project
    - `gongo-persistence-file` plain file based implementation of the persistence interface
    - `gondo-server-rest` is the module containing the code for the presentation server and
      the code of some business models. This version does not separate these into segregated
      services.
- REFERENCE_DATA constant reference data

