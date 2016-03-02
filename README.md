# wordplay

### Configuration

1. Clone the repository
2. Update the inputData value in config.properties file to location of your data files.
3. Build and deploy the application on tomcat.
4. Service end point looks like "http://[HOST_NAME]:[PORT]/wordplay/search_word/[SEARCH_TERM]"

### How it works

1. Data will loaded at the time of server startup and will be persisted to Map.
2. On every request, the service will check the map for the number of occurences of the search term.
