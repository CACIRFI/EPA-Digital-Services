# EPA-Digital-Services
CACI Response to EPA Digital Service RFI
##Background

To address the requirements of the EPA Digital Service RFI and show a process of code re-use, CACI started with a previously developed mobile application constructed for the Department of Justice.  The baseline app can be located here: https://play.google.com/store/apps/details?id=gov.usdoj.lawjobs.  
##Use Case
Knowing that enforcement and investigation of a potential or active environmental accident is a portion of the EPA activities, CACI has developed an application to track and show the details of pipeline leakage incidents on a mobile platform.  This would allow the EPA personnel to access details while in route or on site during the investigation.  Further enhancements that could be created for an application of this nature would be to add capabilities from the field to report on containment, actions and findings.
##Approach
Our approach to programming has been appraised at CMMI-DEV Maturity Level 3, and is fully tailorable to any organization and follows industry best practices.  Our approach operates as an Agile or Waterfall lifecycle to provide the right control and efficiency needed dependent on the requirements of the application owners. Our agile approach enables faster time to market, improved quality of service, and increased team productivity through streamlined and standardized processes to reduce cost of service.   Our programming approach covers all phases of the application development lifecycle from initiation through release management and includes knowledge and change management activities to provide our clients with full lifecycle support of programming activities.
For this project, we assigned a Project Manager who also acted as a Business Analyst and a Sr. Developer.
###Initiation/Planning/Discovery
The first step in our process was to identify the initial high-level requirements for the system based on the data set we chose.  During this phase, we determined the end state environment we were developing to, Android, the method of development, Agile and set the timeline for completion. 
###Requirements Definition
Our analyst and developer worked together to determine which functionality we intended to capture in the final deployment.  The initial development would result in a map based, mobile application that utilized environmental data from pipeline leakage datasets.  The requirements for the project where to geo-code the location data, plot the results on a map and provide additional details upon drilling into the application.
In full project efforts, we utilize System or Service Requirements Specification (SRS) as the baseline requirements for application enhancements, modernization, or maintenance and provide the necessary CMMI Level 3 documentation.  We ensure we have defined user needs clearly and accurately before proceeding with design and development activities. The deliverables for this phase depends on the development methodology selected and may include, but is not limited to, Quality of Service Specification, List of Service Candidate Descriptions, Composite Application Development Plan, Iterative Development Schedule, Composite Application Baseline for Each Release, Status and Change Reports, Concept of Operations, Feasibility Studies, and Alternatives Analysis and Agile artifacts like an initial product backlog and user stories.
###Design
Our developer utilizing the baseline from our DOJ application worked on prototyping the new application to include user interface designs and interactions. As this was a short development cycle and the assignment was to create a basic demo, some decisions were made in design to trade off in favor of speed over robustness:
 
-	Rely on the built-in Android OS navigation, such as the Back button
-	Pare down the wide dataset to a smaller number of records and fields and convert from an excel spreadsheet to a csv to reduce space
-	Keep the layouts and data formatting clean and simple, as such the raw source data is largely presented unchanged
-	Discard all data that was not complete or could not be geocoded 

Traditionally our design phase process begins when project sponsors and key stakeholders have approved the Functional Requirements Specifications (FRS) and SRS documents. We prepare the design specification documentation that includes a data model and application architecture and we will prepare individual design packages for design and development activities. The objectives are to ensure that functional and technical requirements for the software product are addressed in one or more design specifications, reusable components or services are explicitly considered, and designs are inspected to determine that requirements have been adequately addressed.
###Development and Unit Testing
During the development and unit testing phase, our developer performed the actual coding and unit testing per our design discussions. Each module or service is tested to ensure that it works on its own and fulfills the purpose as designed before it is integrated into the application build. 
The objectives of the process are to ensure the design is adequately implemented in the code; validate appropriate program logic, communications, and functionality; test code, workflows, and interfaces are programmed to the design specification; and check against the requirements traceability documentation to ensure released software meets all requirements. 
###Integration and Systems Testing Acceptance
Traditionally CACI works with our clients to establish a Quality Assurance (QA) team, either contract or government personnel, responsible for testing or verifying planned system releases. We perform system integration testing in established integrated testing labs to validate and ensure each system or component scheduled to be released meets the operational and performance requirements as defined, which includes assessing and evaluating system functionality based on the requirements, and verifying operational and deployment readiness. We use a testing matrix to verify appropriate functionality based on user test cases and the concept of operations (CONOPS). During this phase, we support IV&V activities and all required documentation and intellectual property is reviewed and finalized for delivery to the CM team. The final step is project sponsors, and key stakeholders accept and consent to the system readiness for use in the production environment. 
##Deployment of Mobile Application for Review
For this RFI response our deployment process was to establish an APK file that has been loaded into the GITHUB repository.  Normal process would utilize deployment into the Google Play Store are through a dedicated location for user to access and install the file.  The app is targeted to phones running Anroid version 5.0 or greater.  It has been tested on Samsung Galaxy S5 and Nexus 5.  It has not been tested on tablets. To install the file on a tested device follow the below steps:
-	Get the latest APK from GitHub: <link the the apk folder in GitHub>
-	Email the APK file to an account that can be accessed from the target device. 
-	Open email with the APK attachment.
-	Download the APK attachment to the device and open it.
-	Android should open the APK file with the Package Installer, or give you the open of doing so.
-	You will see a screen listing the permissions required by the app, select INSTALL.
-	Once the installation has completed, select OPEN.

##Application user instructions
Once the application has been installed users are able to access the available data through simple user driven filters.  
Select the State and Cause of the incidents that you wish to see mapped on the screen.
Once the data has been mapped you able to access the details of the incident by selecting one of the location pins from the map.
The detail page will provide specific details of the incident.


