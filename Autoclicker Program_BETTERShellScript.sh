#!/bin/bash

#Get Txt File Contents
txtFolder="C:/Users/Public/"
txtFile="Windhunter's Programs - Main Folder Location.txt"
programDirectory=$(cat $txtFolder"$txtFile")

#Load the Program
directoryA="$programDirectory/Settings/JavaFX_SDK_16/lib"
directoryB="$programDirectory/Autoclicker-Program/Autoclicker Program.jar"
java --module-path "$directoryA" --add-modules javafx.controls,javafx.fxml -jar "$directoryB"
