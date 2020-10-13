mkdir ./conf/trained_models

# download dummy model
wget https://github.com/NewsPipe/gpt2-tfx-pipeline/releases/download/0.0.1/gpt355_dummy.zip
unzip gpt355_dummy
mkdir ./conf/trained_models
mv ./gpt355_dummy ./conf/trained_models/
rm gpt355_dummy.zip
