mkdir -p ./gpt2service-1.0/conf/trained_models

wget https://github.com/NewsPipe/gpt2-tfx-pipeline/releases/download/1.1/gpt355_german-news.zip
unzip gpt355_german-news
mv ./gpt355_german-news ./gpt2service-1.0/conf/trained_models/
