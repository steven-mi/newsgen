FROM mozilla/sbt

RUN apt-get update \
    && apt-get install -y \
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
RUN sh setup.sh

ENTRYPOINT ./gpt2service-1.0/bin/gpt2service
