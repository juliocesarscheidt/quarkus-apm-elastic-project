# API with Quarkus

```bash
# quarkus CLI (windows with chocolatey)
choco install quarkus


# quarkus CLI (linux with SDKMAN)
curl -s "https://get.sdkman.io" | bash

chmod +x ~/.sdkman/bin/sdkman-init.sh
echo 'source ~/.sdkman/bin/sdkman-init.sh'

cat ~/.bashrc
export SDKMAN_DIR="$HOME/.sdkman"
[[ -s "$HOME/.sdkman/bin/sdkman-init.sh" ]] && source "$HOME/.sdkman/bin/sdkman-init.sh"

sdk install quarkus



# run in development
quarkus dev

# or with maven
mvn compile quarkus:dev

# package and run in production
mvn clean package

java -jar target/quarkus-app/quarkus-run.jar
```
