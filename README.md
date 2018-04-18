# WSO2 EI Samples

## What does it contain ?

### esb-config

The project which contains the ESB configuration elements such as APIs and sequences.

### distribution

The project which packages the ESB configurations in to a CAR file.

### excel-message-builder

A custom message build which converts the incoming MS Excel binary to an XML payload.

### client

[Postman](https://www.getpostman.com/) scripts along with sample payloads to test the samples.

### resources

Resources (e.g. input files) which are used for data processing etc ..

## How should I use these samples ?

1. Import the project

      Import the project to WSO2 Developer Studio as an existing WSO2 project.

2. Build the project

      mvn clean install

3. Deploy the artifacts

   1. Deploy the CAR file

         Right click on the *distibution* project in the Developer Studio and select Export Composite Application Project and locate the WSO2 EI home

   2. Deploy the MS Excel message builder

         1. Copy PROJECT_HOME/excel-message-builder/target/excel-message-builder-1.0.0.jar to EI_HOME/lib
         2. Copy PROJECT_HOME/lib/commons-collections4-4.1.jar to EI_HOME/lib

4. Import the Postman script file to Postman client to call the sample endpoints.

## Folder structure for VFS sample.

```
├── csv
│   ├── failure
│   ├── in
│   │   └── payments.csv
│   ├── out
│   └── processed
├── excel
│   ├── failure
│   ├── in
│   │   └── payments.xlsx
│   ├── out
│   └── processed
```

