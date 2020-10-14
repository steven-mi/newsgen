ARG OPENJDK_TAG=8u232
FROM openjdk:${OPENJDK_TAG}

ARG SBT_VERSION=1.3.13

# Install sbt
RUN \
  curl -L -o sbt-$SBT_VERSION.deb https://dl.bintray.com/sbt/debian/sbt-$SBT_VERSION.deb && \
  dpkg -i sbt-$SBT_VERSION.deb && \
  rm sbt-$SBT_VERSION.deb && \
  apt-get update && \
  apt-get install sbt && \
  sbt sbtVersion

RUN apt-get install -y \
            python${PYTHON_VERSION} \
            python3-pip \
    && apt-get autoremove -yqq --purge \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

RUN pip3 install --upgrade pip \
    && pip install --upgrade setuptools \
    && pip install regex

WORKDIR /
COPY . ./
RUN sh setup.sh
RUN sbt clean stage

ENTRYPOINT java -jar /target/scala-2.12/gpt2service_2.12-1.0-web-assets.jar
