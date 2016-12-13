# Data-Processer-Demo
     此Demo提供了模拟数据生成，模拟数据发送的功能。
     你只需在dictionaries目录下创建词典文件，在templates目录下创建模版文件，然后，启动应用，即可看到效果。
     项目中已提供了demo模版文件和词典文件，只需执行com.cloudwise.toushibao.sds.bootstap.SdsBootstrap即可生成数据，并发送到http://localhost:5678/dataCenter/collect接口。 
     目前只提供了http post方式，将数据放到body体中发送。
