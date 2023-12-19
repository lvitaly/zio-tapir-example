let
  pkgs = import <nixpkgs> {};

  jdk = pkgs.graalvm-ce;

  sbt = pkgs.sbt.override {
    jre = jdk;
  };

in pkgs.mkShell rec {
  name = "zio-tapir-example-shell";

  buildInputs = [
    jdk
    sbt
  ];
}
