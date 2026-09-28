{
  description = "A very basic flake";

  inputs = {
    nixpkgs.url     = "github:NixOS/nixpkgs/d41521c807dccb71b1b866ac3b7df5a59e726d9d"; ## graalvm v25.0.1
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs = { self, nixpkgs, flake-utils }:
    flake-utils.lib.eachDefaultSystem (system: 
      let
        pkgs = import nixpkgs { inherit system; };

        jdk = pkgs.graalvmPackages.graalvm-ce;
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
