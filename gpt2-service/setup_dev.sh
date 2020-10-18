# for tests
wget https://github.com/NewsPipe/gpt2-tfx-pipeline/releases/download/0.0.1/gpt355_dummy.zip
unzip gpt355_dummy
cp -r ./gpt355_dummy ./test/models/

# for dev
mkdir -p ./conf/trained_models
mv ./gpt355_dummy ./conf/trained_models/
rm gpt355_dummy.zip
