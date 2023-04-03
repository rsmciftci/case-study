# Peoplist Case Study
#### Please open the terminal and run all the commands below respectively


> cd ~/Desktop
####
> git clone git@github.com:rsmciftci/peoplist-frontend.git
####
>  git clone git@github.com:rsmciftci/case-study.git
####
> cd peoplist-frontend
####
> docker build -f Dockerfile.dev -t peoplist-frontend:1.0.0 .
####
> cd ../case-study
####
> docker-compose -f docker-compose.yml up -d
####
> docker build -t case-study:1.0.0 .
####
##### Click the link [http://localhost:5431](http://localhost:5431) 
* Choose **PostgreSQL** as System.
* Username = **postgres**
* Password = **12345**
* Login and create a database named **peoplist**
####
> sudo docker run --net=host -p 8080:8080 case-study:1.0.0
####

### *Now, [frontend](http://localhost:3000) should be reachable. :)*

#### [Github Frontend Repo](https://github.com/rsmciftci/peoplist-frontend)

### Thank you very much

