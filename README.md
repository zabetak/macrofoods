# macrofoods
This repository holds the implementation for a multilingual web application managing recipes with detailed macro and micro nutrient information.   

The easiest way to test and run the entire application is by using
[docker](https://docs.docker.com/engine/install/) and
[docker-compose](https://docs.docker.com/compose/install/). Then you can
simply launch the following commands to start-up everything and build the
projects.

1. Build the docker images: `docker-compose build`
2. Start the services:`docker-compose up -d`
3. (Optional) Stop the services and destroy volumes:`docker-compose down -v`

The compilation of the backend usually takes most of the time so you can
check the logs to if everything is ready using the following command.
```
docker logs -f mf_build_backend
```
To check that everything is ready you can enter the following urls
on your browser.
```
http://localhost:4200/recipes/top
http://localhost:4200/recipes/new
http://localhost:4200/login
```
The first one should bring back a list of recipes. The content is mostly
placeholders for testing purposes and do not reflect the real content
of the site. Note that some images, and text, have been copied directly
from [argiro.gr](https://www.argiro.gr/), and [fitmencook](https://fitmencook.com/)
and it is subject of copyright as per each site.
