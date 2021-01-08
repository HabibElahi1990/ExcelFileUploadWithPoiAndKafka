# ExcelFileUploadWithPoiAndKafka
In this repository we have an example and determine how to use apache poi for read excel file and save all items in mongoDB

* In this repository we have some module for main projects classes , kafka services , poi services and finally save in the MongoDb
  * excel-file-upload
    * running and testing project
    * define controllers
    * determine project config
  * general-classes
    * define general classed that use whole of the project like model and dto's classes
  * kafka-service
    * define Producers and Consumers config classes
    * define listener for getting KafkaListeners 
  * poi-service
    * read excel file items
  * mongodb-service
    * save excel file and excel file rows in MongoDb
    * make relation with mongoDb by repository
  
  
 * By ExcelFileUploadApplicationTest class you can test this repository but you need local mongoDb and run zookeeper and kafka server
 . also you can select and change my excel file in this test
