

# 팀 리더 1
curl --request POST -sL \
     --url 'localhost:8080/join'\
     --header 'Content-Type: application/json' \
      --data '{
                  "name": "김팀장",
                  "employeeId": "1111001",
                  "email": "bush@hyundai-autoever.com",
                  "role": "TEAM_LEADER",
                  "password": "1234",
                  "teamId": 1
              }' \
     --output './output'

cat ./output | sed G

# 파트 리더 1
curl --request POST -sL \
     --url 'localhost:8080/join'\
     --header 'Content-Type: application/json' \
      --data '{
                  "name": "이파트",
                  "employeeId": "1111002",
                  "email": "prolee@hae.com",
                  "role": "PART_LEADER",
                  "password": "1234",
                  "teamId": 1
              }' \
     --output './output'
cat ./output | sed G

# 파트 리더 2
curl --request POST -sL \
     --url 'localhost:8080/join'\
     --header 'Content-Type: application/json' \
      --data '{
                  "name": "최파트",
                  "employeeId": "1111003",
                  "email": "prochoi@hae.com",
                  "role": "PART_LEADER",
                  "password": "1234",
                  "teamId": 1
              }' \
     --output './output'
cat ./output | sed G

# 팀 멤버 1
curl --request POST -sL \
     --url 'localhost:8080/join'\
     --header 'Content-Type: application/json' \
      --data '{
                  "name": "박멤버",
                  "employeeId": "1111004",
                  "email": "jihyun@hyundai-autoever.com",
                  "role": "TEAM_MEMBER",
                  "password": "1234",
                  "teamId": 1
              }' \
     --output './output'
cat ./output | sed G

# 팀 멤버 2
curl --request POST -sL \
     --url 'localhost:8080/join'\
     --header 'Content-Type: application/json' \
      --data '{
                  "name": "김멤버",
                  "employeeId": "1111005",
                  "email": "mj@hyundai-autoever.com",
                  "role": "TEAM_MEMBER",
                  "password": "1234",
                  "teamId": 1
              }' \
     --output './output'
cat ./output | sed G

# 팀 멤버 3
curl --request POST -sL \
     --url 'localhost:8080/join'\
     --header 'Content-Type: application/json' \
      --data '{
                  "name": "김한나",
                  "employeeId": "1111006",
                  "email": "hannkim@hyundai-autoever.com",
                  "role": "TEAM_MEMBER",
                  "password": "1234",
                  "teamId": 1
              }' \
     --output './output'
cat ./output | sed G

# 팀 멤버 4
curl --request POST -sL \
     --url 'localhost:8080/join'\
     --header 'Content-Type: application/json' \
      --data '{
                  "name": "김지현",
                  "employeeId": "1111007",
                  "email": "jihyukim@hyundai-autoever.com",
                  "role": "TEAM_MEMBER",
                  "password": "1234",
                  "teamId": 1
              }' \
     --output './output'
cat ./output | sed G

# 팀 멤버 5
curl --request POST -sL \
     --url 'localhost:8080/join'\
     --header 'Content-Type: application/json' \
      --data '{
                  "name": "김성민",
                  "employeeId": "1111008",
                  "email": "seongmik@hyundai-autoever.com",
                  "role": "TEAM_MEMBER",
                  "password": "1234",
                  "teamId": 1
              }' \
     --output './output'
cat ./output | sed G

# 팀 멤버 6
curl --request POST -sL \
     --url 'localhost:8080/join'\
     --header 'Content-Type: application/json' \
      --data '{
                  "name": "송승훈",
                  "employeeId": "1111009",
                  "email": "seunghso@hyundai-autoever.com",
                  "role": "TEAM_MEMBER",
                  "password": "1234",
                  "teamId": 1
              }' \
     --output './output'
cat ./output | sed G

rm ./output

