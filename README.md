Test project "Taco" (Walls C. Spring in Action 6ed 2022)

docker run --name my-mongo -p 27017:27017 -d mongo:latest

docker exec -it my-mongo bash
mongosh

use tacoclouddb

db.ingredients.find()
db.tacoOrders.find()
db.users.find()
db.tacos.find()

