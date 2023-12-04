%%{init: {'theme': 'default'}}%%
flowchart TB
client -- HTTP --> nginx{nginx}
nginx -- HTTP --> service2
nginx -- HTTP --> service1

service2_1 --> mysql[(mysql)]
service2_2 --> mysql[(mysql)]
service2_3 --> mysql[(mysql)]

service2_1 --> zipkin
service2_2 --> zipkin
service2_3 --> zipkin

service2_1 ---> eureka
service2_2 ---> eureka
service2_3 ---> eureka

service2_1 --> Kafka
service2_2 --> Kafka
service2_3 --> Kafka

service1_1 --> Kafka
service1_2 --> Kafka


filebeat{{filebeat}} --> service2_1
filebeat --> service2_2
filebeat --> service2_3

filebeat --> logstash{{logstash}}

Kafka --->Zookiper

grafana([grafana]) --> prometheus
kibana([kibana]) --> elasticsearch[(elasticsearch)]
logstash --> elasticsearch
prometheus --> eureka
prometheus --> service2_1
prometheus --> service2_2
prometheus --> service2_3

subgraph service2[service2 : N instances]
service2_1
service2_2
service2_3
end

subgraph service1[service1 : N instances]
service1_1
service1_2
end

service1 --> nginx
  
