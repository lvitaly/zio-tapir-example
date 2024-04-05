[![Made in Ukraine](https://img.shields.io/badge/made_in-Ukraine-ffd700.svg?labelColor=0057b7)](https://stand-with-ukraine.pp.ua)

zio-tapir-example
===

This is an example of how [ZIO ecosystem](https://zio.dev/ecosystem/) and [tapir](https://tapir.softwaremill.com/) library can be used to build a mature API server. The project uses Scala 3, but almost the same code can be adopted to build a project with Scala 2.13 or even with Scala 2.12.

## Quick start

JDK and [sbt](https://www.scala-sbt.org) are required.

If you don't have [sbt](https://www.scala-sbt.org) installed and you are a Nix user, you can use Nix-shell with all required dependencies with the following command:

```shell
nix develop
```

Otherwise, you need to install [sbt](https://www.scala-sbt.org), and afterward, you can use the standard commands:

```shell
sbt compile # build the project
sbt test # run the tests
sbt reStart # run the application (Main)
sbt reStop # stop the application (Main)
```

## Features

After application started, you will see next message in the console:
```shell
rootProject 2024-04-05 23:16:38,120 | INFO | ZScheduler-Worker-6 | c.l.a.s.Server$package | Go to http://localhost:8000/docs for Swagger UI
```

You can reach Swagger UI by the http://localhost:8000/docs link and Prometheus metrics by the http://localhost:8000/metrics link.
To access authorized endpoints, use the `auth.token` from the `application.conf` file.
_Please note that this authorization technique is used only for example purposes._
_In real life, you should consider proper access management solutions._

## Todo list

- [x] Create a basic API to query books
- [x] Remove usage of package objects cause it's deprecated in the Scala 3 (see [here](https://docs.scala-lang.org/scala3/reference/dropped-features/package-objects.html))
- [ ] Add DB layer
- [ ] Add tests

## Links:

* [zio documentation](https://zio.dev/overview/getting-started)
* [zio github](https://github.com/zio)
* [tapir documentation](https://tapir.softwaremill.com/)
* [tapir github](https://github.com/softwaremill/tapir)
* [bootzooka: template microservice using tapir](https://softwaremill.github.io/bootzooka/)
* [sbtx wrapper](https://github.com/dwijnand/sbt-extras#installation)
* [nix documentation](https://nixos.org/manual/nix/stable/)
