{
  description = "A very basic flake";

  inputs = {
    nixpkgs.url     = "github:NixOS/nixpkgs/nixpkgs-unstable";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs = { self, nixpkgs, flake-utils }:
    flake-utils.lib.eachDefaultSystem (system: 
      let
        pkgs = import nixpkgs { inherit system; };

        jdk = pkgs.graalvm-ce;
        sbt = pkgs.sbt.override {
          jre = jdk;
        };
      in 
      {
        devShells.default = pkgs.mkShell {
          name = "zio-tapir-example-shell";

          packages = [
            jdk
            sbt
          ];
        };
      }
    );
}
