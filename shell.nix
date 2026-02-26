{ pkgs ? import <nixpkgs> {} }:

pkgs.mkShell {
  buildInputs = with pkgs; [
    openjdk21
    maven
    gradle
  ];

  shellHook = ''
        export JAVA_HOME=${pkgs.openjdk21}

    '';
}