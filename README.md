[![Made in Ukraine](https://img.shields.io/badge/made_in-Ukraine-ffd700.svg?labelColor=0057b7)](https://stand-with-ukraine.pp.ua)

zio-tapir-example
===

This is an example of how [ZIO ecosystem](https://zio.dev/ecosystem/) and [tapir](https://tapir.softwaremill.com/) library can be used to build a mature API server.

## Quick start

If you don't have [sbt](https://www.scala-sbt.org) installed already, you can use the provided wrapper script:

```shell
./sbtx -h # shows an usage of a wrapper script
./sbtx compile # build the project
./sbtx test # run the tests
./sbtx reStart # run the application (Main)
./sbtx reStop # stop the application (Main)
```

For more details check the [sbtx usage](https://github.com/dwijnand/sbt-extras#sbt--h) page.

Otherwise, if sbt is already installed, you can use the standard commands:

```shell
sbt compile # build the project
sbt test # run the tests
sbt reStart # run the application (Main)
sbt reStop # stop the application (Main)
```

Alternatively, if you are a nix user, you can use nix-shell with the following command:

```shell
nix-shell shell.nix
```

## Todo list

- [x] Create a basic API to query books
- [ ] Remove usage of package objects cause it's deprecated in the Scala 3 (see [here](https://docs.scala-lang.org/scala3/reference/dropped-features/package-objects.html))
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
