# ExcelFileUploadWithPoiAndKafka
in this repository we determine how to use apache poi for read excel file 

* in this repository we have some modual for main projects classes , kafka services , poi services and finally save in the MongoDb
  * excel-file-upload
    * define controllers
    * determine project confing
  * general-classes
    * define general classed that use whole of the project like model and dtos classes
  * kafka-service
    * define Producers and Consumers confing classes
    * define listener for getting KafkaListeners 
  * poi-service
    * read excel file items
  * mongodb-service
    * save excel file and excle file rows in MongoDb
    * make relation with mongoDb by repository
  
