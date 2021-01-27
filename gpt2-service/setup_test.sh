# for tests
wget https://github.com/NewsPipe/gpt2-tfx-pipeline/releases/download/0.0.1/gpt355_dummy.zip
unzip gpt355_dummy

mkdir ./test/models/trained_models

mv gpt355_dummy tfmodel
cp -r tfmodel ./test/models/trained_models
