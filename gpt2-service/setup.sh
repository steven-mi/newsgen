mkdir -p ./gpt2service-1.0/conf/trained_models

# download dummy model
wget https://github.com/NewsPipe/gpt2-tfx-pipeline/releases/download/1.0/gpt355_german-wiki.zip
unzip gpt355_german-wiki
mv ./gpt355_german-wiki ./gpt2service-1.0/conf/trained_models/
