{ pkgs ? import <nixpkgs> {} }:

pkgs.mkShell {
  buildInputs = with pkgs; [
    openjdk21
    maven
  
  ];

  shellHook = ''
        export JAVA_HOME=${pkgs.openjdk21}

    '';
}