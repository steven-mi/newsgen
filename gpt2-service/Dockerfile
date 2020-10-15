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
RUN sbt dist
RUN unzip ./target/universal/gpt2service-1.0.zip
RUN chmod u+x ./gpt2service-1.0/bin/gpt2service
RUN sh setup_prod.sh

ENTRYPOINT ./gpt2service-1.0/bin/gpt2service
