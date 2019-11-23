#!/usr/bin/env bash

echo "Create a UserProfile Aggregate which also create a MyRecipe Aggregate"
echo '{
	"userId": "9535152e-e5cc-40c6-a680-f7b01da97653",
	"firstname": "Michael",
	"lastname": "Meierhoff",
	"nickname": "cookingmime",
	"birthday": "1969-07-18",
	"email": "michael.meierhoff@gmail.com"
}' |  \
  http POST http://localhost:8080/userprofiles \
  Accept:'*/*' \
  Accept-Encoding:'gzip, deflate' \
  Cache-Control:no-cache \
  Connection:keep-alive \
  Content-Length:200 \
  Content-Type:application/json \
  Host:localhost:8080 \
  Postman-Token:'bae862ed-d910-42d3-b0d7-b9ecebb83765,66822054-b048-4792-ba77-54f5f93fd02d' \
  User-Agent:PostmanRuntime/7.19.0 \
  cache-control:no-cache

echo "Create a Recipe Aggregate whcih also create a TimelineRecipe Aggregate and create a MyRecipe.Recipe Elmente in MyRecipe Aggregate."
echo '{
    "name": "Spaghetti",
    "description": "Beeschreibung",
    "images": [
        {
            "position": 1,
            "imageId": "statics/images/food-1932466_640.jpg"
        }
    ],
    "expense": "MEDIUM",
    "meal": [
    	"MAIN"
    ],
    "times": {
        "preparation": 20,
        "cooking": 0,
        "rest": 0
    },
    "ingredients": [
        {
            "position": 1,
            "count": 500,
            "unit": "g",
            "name": "Pasta"
        },
        {
            "position": 2,
            "count": 1,
            "unit": "kg",
            "name": "Tomaten"
        }
    ],
    "preparations": [
        {
            "step": 1,
            "stepDescription": "Erster Schritt..."
        },
        {
            "step": 2,
            "stepDescription": "Zweiter Schritt..."
        }
    ],
    "userId": "9535152e-e5cc-40c6-a680-f7b01da97653"
}' |  \
  http POST http://localhost:8080/recipes \
  Accept:'*/*' \
  Accept-Encoding:'gzip, deflate' \
  Cache-Control:no-cache \
  Connection:keep-alive \
  Content-Length:896 \
  Content-Type:application/json \
  Host:localhost:8080 \
  Postman-Token:'0817700d-9c16-4db6-97f9-ccd636882851,14797275-2096-43ed-bb00-447d6df08776' \
  User-Agent:PostmanRuntime/7.19.0 \
  cache-control:no-cache
