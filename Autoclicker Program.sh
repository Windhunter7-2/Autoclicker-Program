#!/bin/bash

#Put Your Installation Directory for Windhunter's Programs HERE:
programDirectory="C:/Windhunter's Programs"

#Below Is the Code to Load the Program; You Shouldn't Need to Worry About It...
directoryA="$programDirectory/Settings/JavaFX_SDK_16/lib"
directoryB="$programDirectory/Autoclicker-Program/Autoclicker Program.jar"
java --module-path "$directoryA" --add-modules javafx.controls,javafx.fxml -jar "$directoryB"
