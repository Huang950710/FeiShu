# FeiShu

- 功能说明：

         -此 Jar 包主要功能就是将 Grafana 与 kafka Eagle 的告警对接到飞书机器人

- 使用说明：

         - java -jar Feishu-1.0-SNAPSHOT.jar + webhook地址 + moudle (Grafana / KafkaEagle) + 端口号

- 举例：

         - 将 Grafana 的告警对接飞书机器人 
         - java -jar Feishu-1.0-SNAPSHOT.jar https://open.feishu.cn/open-apis/bot/v2/hook/xxxxxxxxxxxxxx Grafana 654
         
         - 将 KafkaEagle 的告警对接飞书机器人
         - java -jar Feishu-1.0-SNAPSHOT.jar https://open.feishu.cn/open-apis/bot/v2/hook/xxxxxxxxxxxxxx KafkaEagle 654

![image](https://user-images.githubusercontent.com/100354510/185335386-dbd60916-6593-498b-8160-4a848b0789df.png)
