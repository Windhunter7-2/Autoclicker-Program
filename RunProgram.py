import os
import sys
import subprocess

def main():
    #Get User's Directory
    platform = sys.platform
    directoryA = 'Not set yet...'
    directoryB = 'Not set yet...'
    if (platform == 'win32'):   #Windows
        print('Running program...')
        fName = "Windhunter\'s Programs - Main Folder Location.txt"
        fPath = ("C:\\Users\\Public\\" + fName)
        file = open(fPath, "r")
        directory = file.readline()
        directory = directory.replace("/", "\\")
        directoryA = (directory + '\Settings\JavaFX_SDK_16\lib')
        runFile = 'Autoclicker Program.jar'
        directoryB = (directory + '\Autoclicker-Program\\' + runFile)
    else:
        print('This operating system is not yet supported. '
            + 'Current versions only support Windows...')

    #Main Command, and Concatenation
    begin = 'java --module-path "'
    modules = '" --add-modules javafx.controls'
    end = ',javafx.fxml -jar "'
    final = (begin + directoryA + modules + end + directoryB + '"')

    #Run the Program
    subprocess.call(['powershell','-command', final])

if __name__ == "__main__":
    main()
