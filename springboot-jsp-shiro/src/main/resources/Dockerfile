FROM centos
MAINTAINER fadedos<fadedos@126.com>
#把宿主机当前上下文的c.txt拷贝到容器/usr/local/路径下
COPY c.txt /usr/local/cincontainer.txt
#把java与tomcat添加到容器中
ADD jdk-8u211-linux-x64.tar.gz /usr/local/
ADD apache-tomcat-9.0.41.tar.gz /usr/local/
#安装vim编辑器
RUN yum -y install vim
#设置工作访问时候的WORKDIR路径，登录落脚点
ENV MYPATH /usr/local
WORKDIR $MYPATH
#配置java与tomcat环境变量
ENV JAVA_HOME /usr/local/jdk1.8.0_211
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV CATALINA_HOME /usr/local/apache-tomcat-9.0.41
ENV CATALINA_BASE /usr/local/apache-tomcat-9.0.41
ENV PATH $PATH:$JAVA_HOME/bin:$CATALINA_HOME/lib:$CATALINA_HOME/bin
#容器运行时监听的端口
EXPOSE 8080
#启动时运行tomcat
# ENTRYPOINT ["/usr/local/apache-tomcat-9.0.8/bin/startup.sh" ]
# CMD ["/usr/local/apache-tomcat-9.0.8/bin/catalina.sh","run"]
CMD /usr/local/apache-tomcat-9.0.41/bin/startup.sh && tail -F /usr/local/apache-tomcat-9.0.41/bin/logs/catalina.out

apache-tomcat-9.0.41
apache-tomcat-9.0.41.tar.gz

jdk-8u211-linux-x64.tar.gz
jdk1.8.0_211

docker run -p 6380:6379 --name myredis -v /fadedos/myredis/data:/data -v /fadedos/myredis/conf/redis.conf:/usr/local/etc/redis/redis.conf -d redis:3.2 redis-server /usr/local/etc/redis/redis.conf --appendonly yes

docker run -p 6379:6379 --name myredis -v /usr/local/docker/redis/redis.conf:/etc/redis/redis.conf -v /usr/local/docker/redis/data:/data -d redis redis-server /etc/redis/redis.conf --appendonly yes