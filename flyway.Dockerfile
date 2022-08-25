#FROM centos:centos7
#
## Get flyway
#RUN ["curl", "-O", "http://repo1.maven.org/maven2/org/flywaydb/flyway-commandline/3.0/flyway-commandline-3.0.tar.gz"]
#RUN ["yum", "install", "-y", "tar"]
#RUN ["tar", "-xzf", "flyway-commandline-3.0.tar.gz"]
#
## Install jdbc postgres driver
#RUN ["yum", "install", "-y", "postgresql-jdbc"]
#RUN ["yum", "install", "-y", "nc"]
#RUN ["yum", "install", "-y", "postgresql"]
#
#WORKDIR flyway-3.0
#
## Copy the postgres driver to its required location
#RUN ["cp", "/usr/share/java/postgresql-jdbc.jar", "jars/"]
#
#CMD ["./flyway"]