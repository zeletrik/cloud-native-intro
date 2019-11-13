from __future__ import print_function
import boto3
import json

dynamodb = boto3.resource('dynamodb',
                          aws_access_key_id="",
                          aws_secret_access_key="",
                          region_name="local",
                          endpoint_url="http://localhost:8000")

with open('scripts/init/tables.json', 'r') as f:
    tables_dict = json.load(f)

for tableDef in tables_dict:
    table = dynamodb.create_table(
         TableName= tableDef['TableName'],
         KeySchema= tableDef['KeySchema'],
         AttributeDefinitions= tableDef['AttributeDefinitions'],
         ProvisionedThroughput= {
              'ReadCapacityUnits': 1,
              'WriteCapacityUnits': 1
    })
    
    print("Table status:", table.table_status)


with open('scripts/init/items.json', 'r') as f:
    items_dict = json.load(f)

for itemDef in items_dict:
    table = dynamodb.Table(itemDef['TableName'])
    for ins in itemDef['Items']:
        table.put_item(
           Item=ins
        )
